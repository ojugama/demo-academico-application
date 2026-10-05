package co.edu.demoacademico.repository;

import co.edu.demoacademico.model.GrupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<GrupoEntity, Long> {
    boolean existsByCodigoAndAsignaturaId(String codigo, Long idAsignatura);
}
