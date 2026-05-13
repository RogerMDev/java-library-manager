package com.roger.exception;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException(int idUsuario) {
        super("No existe ningún usuario con ID: " + idUsuario);
    }
}