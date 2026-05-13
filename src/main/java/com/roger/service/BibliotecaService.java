package com.roger.service;
import com.roger.model.Libro;
import com.roger.model.Prestamo;
import com.roger.model.Usuario;
import com.sun.source.doctree.SystemPropertyTree;

import java.util.ArrayList;

public class BibliotecaService {

    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;

    public BibliotecaService() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        for (Libro libroAnadido : this.libros) {
            if (libroAnadido.getIsbn().equalsIgnoreCase(libro.getIsbn())) {
                System.out.println("El libro ya está añadido");
                return;
            }
        }
        this.libros.add(libro);
        System.out.println("Libro añadido correctamente");
    }

    public void agregarUsuario(Usuario usuario) {
        for (Usuario usuarioAnadido : this.usuarios) {
            if (usuarioAnadido.getId() == usuario.getId()) {
                System.out.println("El usuario ya está añadido");
                return;
            }
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
        System.out.println("No se han encontrado libros con ese ISBN");
        return null;
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuarioEnLista : this.usuarios) {
            if (id == usuarioEnLista.getId()) {
                return usuarioEnLista;
            }
        }
        System.out.println("No se han encontrado usuarios con ese ID");
        return null;
    }

    public void prestarLibro(String isbn, int idUsuario) {
        Libro libro = buscarLibroPorIsbn(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (libro == null) {
            System.out.println("El ISBN proporcionado no corresponde a ningún libro guardado");
            return;
        }

        if (usuario == null) {
            System.out.println("El ID de usuario proporcionado no corresponde a ningún usuario guardado");
            return;
        }

        if (!libro.isDisponible()) {
            System.out.println("El libro no está disponible");
            return;
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
            System.out.println("El ISBN proporcionado no corresponde a ningún libro guardado");
            return;
        }

        if (usuario == null) {
            System.out.println("El ID de usuario proporcionado no corresponde a ningún usuario guardado");
            return;
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

        System.out.println("No existe un préstamo activo para ese libro y usuario");
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

