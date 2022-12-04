package com.classes.BO;

import com.classes.DAO.PerfilDAO;
import com.classes.DTO.CRLV;
import com.classes.DTO.Perfil;
import com.classes.DTO.Usuario;
import com.classes.conexao.ConectorMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PerfilBO {
    public boolean inserir(Perfil perfil) {
        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.inserir(perfil);
    }

    public boolean excluir(Perfil perfil) {
        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.excluir(perfil);
    }

    public Perfil procurarPorUsuarioID(Usuario usuario){
        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.procurarPorUsuarioID(usuario);
    }

    public Perfil procurarPorId(Perfil perfil) {
        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.procurarPorId(perfil);
    }

    public List<CRLV> procurarVeiculos(Perfil perfil){
        PerfilDAO perfilDAO = new PerfilDAO();
        return perfilDAO.procurarVeiculos(perfil);
    }
}
