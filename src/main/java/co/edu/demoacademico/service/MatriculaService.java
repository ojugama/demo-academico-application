package co.edu.demoacademico.service;

import co.edu.demoacademico.model.MatriculaEntity;

public interface MatriculaService {
    MatriculaEntity create(Long idEstudiante, Long idGrupo);
}
