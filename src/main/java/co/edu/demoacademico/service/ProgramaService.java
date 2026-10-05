package co.edu.demoacademico.service;

import co.edu.demoacademico.model.ProgramaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramaService {
    ProgramaEntity create(ProgramaEntity programa);

    ProgramaEntity findById(Long id);

    Page<ProgramaEntity> findAll(Pageable pageable);

    ProgramaEntity update(Long id, ProgramaEntity programa);

    void delete(Long id);
}
