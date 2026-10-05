package co.edu.demoacademico.matriculas;

import co.edu.demoacademico.common.exception.BusinessException;
import co.edu.demoacademico.common.exception.NotFoundException;
import co.edu.demoacademico.estudiantes.EstudianteEntity;
import co.edu.demoacademico.estudiantes.EstudianteRepository;
import co.edu.demoacademico.grupos.GrupoEntity;
import co.edu.demoacademico.grupos.GrupoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MatriculaServiceImpl implements MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final GrupoRepository grupoRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository, EstudianteRepository estudianteRepository, GrupoRepository grupoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
        this.grupoRepository = grupoRepository;
    }

    @Override
    public MatriculaEntity create(Long idEstudiante, Long idGrupo) {
        EstudianteEntity existingEstudiante = estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con ID: " + idEstudiante + "."));

        GrupoEntity existingGrupo = grupoRepository.findById(idGrupo)
                .orElseThrow(() -> new NotFoundException("Grupo no encontrado con ID: " + idGrupo + "."));

        if (matriculaRepository.existsByEstudianteIdAndGrupoId(idEstudiante, idGrupo)) {
            throw new BusinessException("Ya existe una matrícula con el estudiante con el ID: " + idEstudiante
                    + ", y el grupo con el ID: " + idGrupo + ".");
        }

        long matriculados = matriculaRepository.countByGrupoId(idGrupo);
        if (matriculados >= existingGrupo.getMaximoCupos()) {
            throw new BusinessException("No hay más cupos para el grupo con el ID: " + idGrupo + ".");
        }

        MatriculaEntity matricula = new MatriculaEntity();

        matricula.setEstudiante(existingEstudiante);
        matricula.setGrupo(existingGrupo);

        return matriculaRepository.save(matricula);
    }
}
