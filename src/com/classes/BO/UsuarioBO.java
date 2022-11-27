package com.classes.BO;

import com.classes.DAO.UsuarioDAO;
import com.classes.DTO.Usuario;
import java.util.List;

public class UsuarioBO {
    public boolean inserir(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.inserir(usuario);
    }

    public boolean alterar(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.alterar(usuario);
    }

    public boolean excluir(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.excluir(usuario);
    }

    public Usuario procurarPorId(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.procurarPorId(usuario);
    }

    public boolean existe(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.existe(usuario);
    }

    public List<Usuario> pesquisarTodos() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.pesquisarTodos();
    }
}
