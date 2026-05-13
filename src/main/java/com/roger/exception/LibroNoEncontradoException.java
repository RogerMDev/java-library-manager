package com.roger.exception;

public class LibroNoEncontradoException extends RuntimeException {

    public LibroNoEncontradoException(String isbn) {
        super("No existe ningún libro con ISBN: " + isbn);
    }
}
