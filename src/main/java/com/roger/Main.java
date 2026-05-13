package com.roger;

import com.roger.model.Libro;
import com.roger.model.Usuario;
import com.roger.service.BibliotecaService;
import com.roger.exception.LibroNoEncontradoException;
import com.roger.exception.UsuarioNoEncontradoException;
import com.roger.exception.LibroNoDisponibleException;
import com.roger.exception.PrestamoNoEncontradoException;

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

        bibliotecaService.prestarLibro("1",1);

        try {
            bibliotecaService.devolverLibro("2", 1);
        } catch (LibroNoEncontradoException e) {
            System.out.println("Error de libro: " + e.getMessage());
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("Error de usuario: " + e.getMessage());
        } catch (PrestamoNoEncontradoException e) {
            System.out.println("Error de préstamo: " + e.getMessage());
        }
    }
}