package co.edu.demoacademico.service;

import co.edu.demoacademico.exception.BusinessException;
import co.edu.demoacademico.exception.NotFoundException;
import co.edu.demoacademico.model.ProgramaEntity;
import co.edu.demoacademico.repository.ProgramaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProgramaServiceImpl implements ProgramaService {
    private final ProgramaRepository programaRepository;

    public ProgramaServiceImpl(ProgramaRepository programaRepository) {
        this.programaRepository = programaRepository;
    }

    @Override
    public ProgramaEntity create(ProgramaEntity programa) {
        if (programaRepository.existsByCodigo(programa.getCodigo())) {
            throw new BusinessException("Ya existe un programa con el código: " + programa.getCodigo() + ".");
        }

        return programaRepository.save(programa);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramaEntity findById(Long id) {
        return programaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Programa no encontrado con ID: " + id + "."));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProgramaEntity> findAll(Pageable pageable) {
        return programaRepository.findAll(pageable);
    }

    @Override
    public ProgramaEntity update(Long id, ProgramaEntity programa) {
        ProgramaEntity existingPrograma = findById(id);

        existingPrograma.setNombre(programa.getNombre());

        return programaRepository.save(existingPrograma);
    }

    @Override
    public void delete(Long id) {
        ProgramaEntity existingPrograma = findById(id);

        programaRepository.delete(existingPrograma);
    }
}
