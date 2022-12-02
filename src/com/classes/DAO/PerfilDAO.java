package com.classes.DAO;

import com.classes.DTO.CRLV;
import com.classes.DTO.Perfil;
import com.classes.DTO.Usuario;
import com.classes.conexao.ConectorMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO {

    final String NOMEDATABELA = "perfil";

    public boolean inserir(Perfil perfil) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (user_id,cnh_id) VALUES (?,?);";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, perfil.getUser_id());
            ps.setInt(2, perfil.getCnh_id());
            //ID AUTO_INCREMENT
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(Perfil perfil) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE perfil_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, perfil.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Perfil procurarPorId(Perfil perfil) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE perfil_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, perfil.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Perfil obj = new Perfil();
                obj.setId(rs.getInt(1));
                obj.setUser_id(rs.getInt(2));
                obj.setCnh_id(rs.getInt(3));
                ps.close();
                rs.close();
                conn.close();
                return obj;
            } else {
                ps.close();
                rs.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CRLV> procurarVeiculos(Perfil perfil){
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM CRLV WHERE perfil_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, perfil.getId());
            ResultSet rs = ps.executeQuery();
            return montarLista(rs);
        } catch (Exception e) {
            //System.err.println("Erro: " + e.toString());
            //e.printStackTrace();
            return null;
        }
    }

    private List<CRLV> montarLista(ResultSet rs) {
        List<CRLV> listObj = new ArrayList<CRLV>();
        try {
            while (rs.next()) {
                CRLV obj = new CRLV();
                obj.setCrlv_id(rs.getInt(1));
                obj.setPerfil_id(rs.getInt(2));
                obj.setTipo(rs.getString(3));
                obj.setCor_predominante(rs.getString(4));
                obj.setCategoria(rs.getString(5));
                obj.setModelo(rs.getString(6));
                obj.setData_documento(new java.util.Date(rs.getDate(7).getTime()));
                obj.setAno_fabricacao(new java.util.Date(rs.getDate(8).getTime()));
                obj.setAno_modificacao(new java.util.Date(rs.getDate(9).getTime()));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            e.printStackTrace();
            return null;
        }
    }
}
