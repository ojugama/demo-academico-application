package co.edu.demoacademico.service;

import co.edu.demoacademico.exception.BusinessException;
import co.edu.demoacademico.exception.NotFoundException;
import co.edu.demoacademico.model.AsignaturaEntity;
import co.edu.demoacademico.model.GrupoEntity;
import co.edu.demoacademico.repository.AsignaturaRepository;
import co.edu.demoacademico.repository.GrupoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GrupoServiceImpl implements GrupoService {
    private final GrupoRepository grupoRepository;
    private final AsignaturaRepository asignaturaRepository;

    public GrupoServiceImpl(GrupoRepository grupoRepository, AsignaturaRepository asignaturaRepository) {
        this.grupoRepository = grupoRepository;
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public GrupoEntity create(GrupoEntity grupo) {
        if (grupo.getAsignatura() == null || grupo.getAsignatura().getId() == null) {
            throw new BusinessException("El ID de la asignatura es requerido.");
        }

        AsignaturaEntity existingAsignatura = asignaturaRepository.findById(grupo.getAsignatura().getId())
                .orElseThrow(() -> new NotFoundException("Asignatura no encontrada con ID: "
                        + grupo.getAsignatura().getId() + "."));

        grupo.setAsignatura(existingAsignatura);

        if (grupoRepository.existsByCodigoAndAsignaturaId(grupo.getCodigo(), grupo.getAsignatura().getId())) {
            throw new BusinessException("Ya existe un grupo con el código: " + grupo.getCodigo()
                    + ", y la asignatura con el ID: " + grupo.getAsignatura().getId() + ".");
        }

        return grupoRepository.save(grupo);
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoEntity findById(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Grupo no encontrado con ID: " + id + "."));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GrupoEntity> findAll(Pageable pageable) {
        return grupoRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        GrupoEntity existingGrupo = findById(id);

        grupoRepository.delete(existingGrupo);
    }
}
