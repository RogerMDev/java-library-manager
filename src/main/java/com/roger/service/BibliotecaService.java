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

    public void agregarLibro(Libro libro){
        libros.add(libro);
    }
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    public void mostrarLibros(){
        for (Libro libro : this.libros) {
            System.out.println(libro);
        }
    }

    public void mostrarUsuarios() {
        for (Usuario usuario : this.usuarios) {
            System.out.println(usuario);
        }
    }

    public Libro buscarLibroPorIsbn(String isbn){
        for (Libro libroEnLista : this.libros){
            if (libroEnLista.getIsbn().equalsIgnoreCase(isbn)){
                return libroEnLista;
            }

        }
        System.out.println("No se han encontrado libros con ese ISBN");
        return null;
    }

    public Usuario buscarUsuarioPorId(int id){
        for (Usuario usuarioEnLista : this.usuarios){
            if (id == usuarioEnLista.getId()){
                return usuarioEnLista;
            }
        }
        System.out.println("No se han encontrado usuarios con ese ID");
        return null;
    }
}


