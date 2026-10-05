package co.edu.demoacademico.repository;

import co.edu.demoacademico.model.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Long> {
    boolean existsByCodigo(String codigo);
}
