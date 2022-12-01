package com.classes.main;

import com.classes.BO.UsuarioBO;
import com.classes.DTO.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TesteUsuario {
    public static void main(String[] args) {
        UsuarioBO usuarioBO = new UsuarioBO();
        List<Usuario> lista = usuarioBO.pesquisarTodos();
        for (Usuario usuario : lista) {
            System.out.println(usuario.toString());
        }
    }
}
