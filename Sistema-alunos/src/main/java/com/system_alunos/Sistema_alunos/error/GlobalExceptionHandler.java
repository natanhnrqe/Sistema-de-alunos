package com.system_alunos.Sistema_alunos.error;

import com.system_alunos.Sistema_alunos.exceptions.AlunoAlreadyExistException;
import com.system_alunos.Sistema_alunos.exceptions.AlunoNotFoundException;
import com.system_alunos.Sistema_alunos.exceptions.RecursoNaoEncontradoException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiValidationError> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                fieldErrors.putIfAbsent(err.getField(), err.getDefaultMessage())
        );

        ApiValidationError body = new ApiValidationError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Erro de validação",
                fieldErrors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrity(org.springframework.dao.DataIntegrityViolationException ex){
        String msg = "Violação de integridade no banco.";

        Throwable cause = ex.getMostSpecificCause();
        if (cause != null && cause.getMessage() != null && cause.getMessage().contains("chk_aluno_nota_range")) {
            msg = "Nota deve estar entre 0 e 10.";
        }
        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );

        return ResponseEntity.badRequest().body(error);
    }

    //404
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleNotFound(RecursoNaoEncontradoException ex){

        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //409
    @ExceptionHandler(AlunoAlreadyExistException.class)
    public ResponseEntity<ApiError> handleConflict(AlunoAlreadyExistException ex) {

        ApiError error = new ApiError(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    //500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {

        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Erro inesperado"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex){
        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
