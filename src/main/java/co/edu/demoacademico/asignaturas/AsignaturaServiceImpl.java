package co.edu.demoacademico.asignaturas;

import co.edu.demoacademico.common.exception.BusinessException;
import co.edu.demoacademico.common.exception.NotFoundException;
import co.edu.demoacademico.programas.ProgramaEntity;
import co.edu.demoacademico.programas.ProgramaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AsignaturaServiceImpl implements AsignaturaService {
    private final AsignaturaRepository asignaturaRepository;
    private final ProgramaRepository programaRepository;

    public AsignaturaServiceImpl(AsignaturaRepository asignaturaRepository, ProgramaRepository programaRepository) {
        this.asignaturaRepository = asignaturaRepository;
        this.programaRepository = programaRepository;
    }

    @Override
    public AsignaturaEntity create(AsignaturaEntity asignatura) {
        if (asignatura.getPrograma() == null || asignatura.getPrograma().getId() == null) {
            throw new BusinessException("El ID del programa es requerido.");
        }

        ProgramaEntity existingPrograma = programaRepository.findById(asignatura.getPrograma().getId())
                .orElseThrow(() -> new NotFoundException("Programa no encontrado con ID: " + asignatura.getPrograma().getId() + "."));

        if (asignaturaRepository.existsByCodigo(asignatura.getCodigo())) {
            throw new BusinessException("Ya existe una asignatura con el código: " + asignatura.getCodigo() + ".");
        }

        asignatura.setPrograma(existingPrograma);

        return asignaturaRepository.save(asignatura);
    }

    @Override
    @Transactional(readOnly = true)
    public AsignaturaEntity findById(Long id) {
        return asignaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Asignatura no encontrada con ID: " + id + "."));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AsignaturaEntity> findAll(Pageable pageable) {
        return asignaturaRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        AsignaturaEntity existingAsignatura = findById(id);

        asignaturaRepository.delete(existingAsignatura);
    }
}
