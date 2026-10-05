package co.edu.demoacademico.repository;

import co.edu.demoacademico.model.ProgramaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepository extends JpaRepository<ProgramaEntity, Long> {
    boolean existsByCodigo(String codigo);
}
