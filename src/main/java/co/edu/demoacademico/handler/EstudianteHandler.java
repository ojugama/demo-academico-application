package co.edu.demoacademico.handler;

import co.edu.demoacademico.dto.EstudianteCreateDTO;
import co.edu.demoacademico.dto.EstudianteDTO;
import co.edu.demoacademico.dto.EstudianteUpdateDTO;
import co.edu.demoacademico.model.EstudianteEntity;
import co.edu.demoacademico.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EstudianteHandler {
    @Autowired
    private EstudianteService estudianteService;

    private EstudianteDTO toDto(EstudianteEntity estudiante) {
        EstudianteDTO estudianteDTO = new EstudianteDTO();
        estudianteDTO.setId(estudiante.getId());
        estudianteDTO.setNombre(estudiante.getNombre());
        estudianteDTO.setEmail(estudiante.getEmail());
        return estudianteDTO;
    }

    public EstudianteDTO create(EstudianteCreateDTO in) {
        EstudianteEntity entity = new EstudianteEntity();
        entity.setNombre(in.getNombre());
        entity.setEmail(in.getEmail());

        EstudianteEntity savedEntity = estudianteService.create(entity);

        return toDto(savedEntity);
    }

    public EstudianteDTO findById(Long id) {
        return toDto(estudianteService.findById(id));
    }

    public Page<EstudianteDTO> findAll(Pageable pageable) {
        return estudianteService.findAll(pageable).map(this::toDto);
    }

    public EstudianteDTO update(Long id, EstudianteUpdateDTO in) {
        EstudianteEntity entity = new EstudianteEntity();
        entity.setNombre(in.getNombre());
        entity.setEmail(in.getEmail());

        EstudianteEntity updatedEntity = estudianteService.update(id, entity);

        return toDto(updatedEntity);
    }

    public void delete(Long id) {
        estudianteService.delete(id);
    }

    public EstudianteDTO findByEmail(String email) {
        return toDto(estudianteService.findByEmail(email));
    }
}
