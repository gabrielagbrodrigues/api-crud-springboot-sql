package com.gabi.crudusuarios.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
