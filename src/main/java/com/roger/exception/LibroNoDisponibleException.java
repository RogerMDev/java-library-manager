package com.roger.exception;

public class LibroNoDisponibleException extends RuntimeException {

    public LibroNoDisponibleException(String titulo) {
        super("El libro no está disponible:  " + titulo);
    }
}