package com.roger.exception;

public class PrestamoNoEncontradoException extends RuntimeException {

    public PrestamoNoEncontradoException(String isbn, int idUsuario) {
        super("No existe un préstamo activo para el libro con ISBN " + isbn +
                " y usuario con ID " + idUsuario);
    }
}