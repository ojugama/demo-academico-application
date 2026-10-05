package co.edu.demoacademico.controller;

import co.edu.demoacademico.api.ApiResponse;
import co.edu.demoacademico.api.ResponseBuilder;
import co.edu.demoacademico.dto.MatriculaCreateDTO;
import co.edu.demoacademico.dto.MatriculaDTO;
import co.edu.demoacademico.handler.MatriculaHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    private final MatriculaHandler matriculaHandler;

    public MatriculaController(MatriculaHandler matriculaHandler) {
        this.matriculaHandler = matriculaHandler;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MatriculaDTO>> create(@Valid @RequestBody MatriculaCreateDTO in) {
        return ResponseBuilder.created("Se ha creado correctamente la matrícula.", matriculaHandler.create(in));
    }
}
