package co.edu.demoacademico.handler;

import co.edu.demoacademico.dto.MatriculaCreateDTO;
import co.edu.demoacademico.dto.MatriculaDTO;
import co.edu.demoacademico.model.MatriculaEntity;
import co.edu.demoacademico.service.MatriculaService;
import org.springframework.stereotype.Component;

@Component
public class MatriculaHandler {
    private final MatriculaService matriculaService;

    public MatriculaHandler(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    private MatriculaDTO toDto(MatriculaEntity matriculaEntity) {
        MatriculaDTO matriculaDTO = new MatriculaDTO();

        matriculaDTO.setId(matriculaEntity.getId());
        matriculaDTO.setIdEstudiante(matriculaEntity.getEstudiante().getId());
        matriculaDTO.setNombreEstudiante(matriculaEntity.getEstudiante().getNombre());
        matriculaDTO.setIdGrupo(matriculaEntity.getGrupo().getId());
        matriculaDTO.setCodigoGrupo(matriculaEntity.getGrupo().getCodigo());
        matriculaDTO.setFechaRegistro(matriculaEntity.getFechaRegistro());

        return matriculaDTO;
    }

    public MatriculaDTO create(MatriculaCreateDTO in) {
        MatriculaEntity matriculaEntity = matriculaService.create(in.getIdEstudiante(), in.getIdGrupo());

        return toDto(matriculaEntity);
    }
}
