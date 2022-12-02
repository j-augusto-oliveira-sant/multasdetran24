package com.classes.DAO;

import com.classes.DTO.Usuario;
import com.classes.conexao.ConectorMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    final String NOMEDATABELA = "usuario";

    public boolean inserir(Usuario usuario) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (nome,senha,admin) VALUES (?,?,?);";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2,usuario.getSenha());
            ps.setBoolean(3,usuario.isAdmin());
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

    public boolean alterar(Usuario usuario) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET nome = ? senha = ? admin = ? WHERE user_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2,usuario.getSenha());
            ps.setBoolean(3,usuario.isAdmin());
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(Usuario usuario) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE user_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario procurarPorId(int id) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE user_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setAdmin(rs.getBoolean(4));
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

    public Usuario procurarPorNome(String nome) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setAdmin(rs.getBoolean(4));
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

    public boolean existe(Usuario usuario) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
            //System.err.println("Erro: " + e.toString());
            //e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<Usuario> pesquisarTodos() {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            return montarLista(rs);
        } catch (Exception e) {
            //System.err.println("Erro: " + e.toString());
            //e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> montarLista(ResultSet rs) {
        List<Usuario> listObj = new ArrayList<Usuario>();
        try {
            while (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setSenha(rs.getString(3));
                obj.setAdmin(rs.getBoolean(4));
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
