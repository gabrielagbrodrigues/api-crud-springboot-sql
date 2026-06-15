package com.gabi.crudusuarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> tratarUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    @ExceptionHandler(CpfJaCadastradoException.class)
    public ResponseEntity<String> tratarCpfJaCadastrado(CpfJaCadastradoException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> tratarErroDeValicao(
            MethodArgumentNotValidException ex){

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(erro ->
                        erros.put(
                                erro.getField(),
                                erro.getDefaultMessage()
                        )
                );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erros);

    }


}
