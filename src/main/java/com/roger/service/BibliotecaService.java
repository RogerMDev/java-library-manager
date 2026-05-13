package com.roger.service;
import com.roger.model.Libro;
import com.roger.model.Prestamo;
import com.roger.model.Usuario;
import com.roger.exception.LibroNoEncontradoException;
import com.roger.exception.UsuarioNoEncontradoException;
import com.roger.exception.LibroNoDisponibleException;
import com.roger.exception.PrestamoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    public BibliotecaService() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {

        if (this.libros.contains(libro)) {
                System.out.println("El libro ya está añadido");
                return;
        }

        this.libros.add(libro);
        System.out.println("Libro añadido correctamente");
    }

    public void agregarUsuario(Usuario usuario) {
        if (this.usuarios.contains(usuario)) {
            System.out.println("El usuario ya está añadido");
            return;
        }
        this.usuarios.add(usuario);
        System.out.println("Usuario añadido correctamente");
    }

    public void mostrarLibros() {
        for (Libro libro : this.libros) {
            System.out.println(libro);
        }
    }

    public void mostrarUsuarios() {
        for (Usuario usuario : this.usuarios) {
            System.out.println(usuario);
        }
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro libroEnLista : this.libros) {
            if (libroEnLista.getIsbn().equalsIgnoreCase(isbn)) {
                return libroEnLista;
            }

        }
        return null;
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuarioEnLista : this.usuarios) {
            if (id == usuarioEnLista.getId()) {
                return usuarioEnLista;
            }
        }
        return null;
    }

    public void prestarLibro(String isbn, int idUsuario) {
        Libro libro = buscarLibroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (libro == null) {
            throw new LibroNoEncontradoException(isbn);
        }

        if (usuario == null) {
            throw new UsuarioNoEncontradoException(idUsuario);
        }

        if (!libro.isDisponible()) {
            throw new LibroNoDisponibleException(libro.getTitulo());
        }

        libro.prestar();

        int idPrestamo = this.prestamos.size() + 1;
        Prestamo prestamo = new Prestamo(idPrestamo, libro, usuario);
        this.prestamos.add(prestamo);

        System.out.println("Libro prestado correctamente");
    }

    public void mostrarPrestamos() {
        for (Prestamo prestamoAnadido : this.prestamos) {
            System.out.println(prestamoAnadido);
        }
    }

    public void devolverLibro(String isbn, int idUsuario) {
        Libro libro = buscarLibroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (libro == null) {
            throw new LibroNoEncontradoException(isbn);
        }

        if (usuario == null) {
            throw new UsuarioNoEncontradoException(idUsuario);
        }

        for (Prestamo prestamo : this.prestamos) {
            boolean mismoLibro = prestamo.getLibro().getIsbn().equalsIgnoreCase(isbn);
            boolean mismoUsuario = prestamo.getUsuario().getId() == idUsuario;
            boolean prestamoActivo = !prestamo.isDevuelto();

            if (mismoLibro && mismoUsuario && prestamoActivo) {
                prestamo.marcarComoDevuelto();
                libro.devolver();

                System.out.println("Libro devuelto correctamente");
                return;
            }
        }
        throw new PrestamoNoEncontradoException(isbn,idUsuario);
    }

    public void mostrarPrestamosActivos() {
        for (Prestamo prestamo : this.prestamos) {
            if (!prestamo.isDevuelto()) {
                System.out.println(prestamo);
            }
        }
    }

    public void mostrarHistorialPrestamos() {
        for (Prestamo prestamo : this.prestamos) {
            if (prestamo.isDevuelto()) {
                System.out.println(prestamo);
            }
        }
    }
}

