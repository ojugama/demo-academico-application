package co.edu.demoacademico.repository;

import co.edu.demoacademico.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    boolean existsByEstudianteIdAndGrupoId(Long idEstudiante, Long idGrupo);

    long countByGrupoId(Long idGrupo);
}
