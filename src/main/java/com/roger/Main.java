package com.roger;

import com.roger.model.Libro;
import com.roger.model.Usuario;
import com.roger.service.BibliotecaService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BibliotecaService bibliotecaService = new BibliotecaService();
        Libro libro1 = new Libro("1", "Cien años de soledad", "Gabriel García Márquez", 1967);
        Libro libro2 = new Libro("2", "El alquimista", "Paulo Coelho", 1988);
        Usuario usuario1 = new Usuario(1,"user1","user1@gmail.com");
        Usuario usuario2 = new Usuario(2,"user2","user2@gmail.com");
        bibliotecaService.agregarLibro(libro1);
        bibliotecaService.agregarLibro(libro2);
        bibliotecaService.agregarUsuario(usuario1);
        bibliotecaService.agregarUsuario(usuario2);
        System.out.println("=== LIBROS ===");
        bibliotecaService.mostrarLibros();
        System.out.println("=== USUARIOS ===");
        bibliotecaService.mostrarUsuarios();
    }
}