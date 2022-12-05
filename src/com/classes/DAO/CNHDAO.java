package com.classes.DAO;

import com.classes.DTO.CNH;
import com.classes.DTO.CRLV;
import com.classes.DTO.Perfil;
import com.classes.conexao.ConectorMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CNHDAO {

    final String NOMEDATABELA = "CNH";

    public boolean inserir(CNH cnh) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA
                    + " (perfil_id,pontuacao,tipo_carteira,cpf," +
                    "data_1_habilitacao,emissao,validade,identidade,nacionalidade) VALUES (?,?,?,?,?,?,?,?,?);";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cnh.getPerfil_id());
            ps.setInt(2, cnh.getPontuacao());
            ps.setString(3,cnh.getTipo_carteira());
            ps.setString(4,cnh.getCpf());
            ps.setDate(5,new java.sql.Date(cnh.getData_1_habilitacao().getTime()));
            ps.setDate(6, new java.sql.Date(cnh.getEmissao().getTime()));
            ps.setDate(7, new java.sql.Date(cnh.getValidade().getTime()));
            ps.setString(8, cnh.getIdentidade());
            ps.setString(9, cnh.getNacionalidade());
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

    public boolean alterar(CNH cnh) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET "
                    +"tipo_carteira = ?, emissao = ?, "
                    + "validade = ?, nacionalidade = ? WHERE cnh_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,cnh.getTipo_carteira());
            ps.setDate(2, new java.sql.Date(cnh.getEmissao().getTime()));
            ps.setDate(3, new java.sql.Date(cnh.getValidade().getTime()));
            ps.setString(4, cnh.getNacionalidade());
            ps.setInt(5, cnh.getCnh_id());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterarPontuacao(CNH cnh) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET pontuacao = ? WHERE cnh_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cnh.getPontuacao());
            ps.setInt(2, cnh.getCnh_id());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CNH procurarPorPerfilId(Perfil perfil) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE perfil_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, perfil.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CNH obj = new CNH();
                obj.setCnh_id(rs.getInt(1));
                obj.setPerfil_id(rs.getInt(2));
                obj.setPontuacao(rs.getInt(3));
                obj.setTipo_carteira(rs.getString(4));
                obj.setCpf(rs.getString(5));
                obj.setData_1_habilitacao(rs.getDate(6));
                obj.setEmissao(rs.getDate(7));
                obj.setValidade(rs.getDate(8));
                obj.setIdentidade(rs.getString(9));
                obj.setNacionalidade(rs.getString(10));
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

    public boolean excluir(CNH cnh) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE cnh_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cnh.getCnh_id());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
