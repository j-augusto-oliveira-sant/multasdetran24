package com.classes.DAO;

import com.classes.DTO.CRLV;
import com.classes.DTO.Perfil;
import com.classes.DTO.Usuario;
import com.classes.conexao.ConectorMySQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRLVDAO {

    final String NOMEDATABELA = "CRLV";

    public boolean inserir(CRLV veiculo) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (perfil_id,tipo,cor_predominante,categoria,modelo,data_documento" +
                    ",ano_fabricacao,ano_modificacao) VALUES (?,?,?,?,?,?,?,?);";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, veiculo.getPerfil_id());
            ps.setString(2, veiculo.getTipo());
            ps.setString(3,veiculo.getCor_predominante());
            ps.setString(4,veiculo.getCategoria());
            ps.setString(5,veiculo.getModelo());
            ps.setDate(6, new java.sql.Date(veiculo.getData_documento().getTime()));
            ps.setDate(7, new java.sql.Date(veiculo.getAno_fabricacao().getTime()));
            ps.setDate(8, new java.sql.Date(veiculo.getAno_modificacao().getTime()));
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

    public boolean alterar(CRLV veiculo) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET cor_predominante = ?,"
                    +" ano_modificacao = ? WHERE crlv_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,veiculo.getCor_predominante());
            ps.setDate(2, new java.sql.Date(veiculo.getAno_modificacao().getTime()));
            ps.setInt(3, veiculo.getCrlv_id());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CRLV procurarPorId(CRLV veiculo) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE crlv_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, veiculo.getCrlv_id());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CRLV obj = new CRLV();
                obj.setCrlv_id(rs.getInt(1));
                obj.setPerfil_id(rs.getInt(2));
                obj.setTipo(rs.getString(3));
                obj.setCor_predominante(rs.getString(4));
                obj.setCategoria(rs.getString(5));
                obj.setModelo(rs.getString(6));
                obj.setData_documento(rs.getDate(7));
                obj.setAno_fabricacao(rs.getDate(8));
                obj.setAno_modificacao(rs.getDate(9));
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


    public boolean excluir(CRLV veiculo) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE crlv_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, veiculo.getCrlv_id());
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
