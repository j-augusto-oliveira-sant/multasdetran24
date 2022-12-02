package com.classes.DAO;

import com.classes.DTO.CRLV;
import com.classes.DTO.Usuario;
import com.classes.conexao.ConectorMySQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class CRLVDAO {

    final String NOMEDATABELA = "CRLV";

    public boolean inserir(CRLV veiculo) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (tipo,cor_predominante,categoria,modelo,data_documento" +
                    ",ano_fabricacao,ano_modificacao) VALUES (?,?,?,?,?,?,?);";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, veiculo.getTipo());
            ps.setString(2,veiculo.getCor_predominante());
            ps.setString(3,veiculo.getCategoria());
            ps.setString(4,veiculo.getModelo());
            ps.setDate(5, new java.sql.Date(veiculo.getData_documento().getTime()));
            ps.setDate(6, new java.sql.Date(veiculo.getAno_fabricacao().getTime()));
            ps.setDate(7, new java.sql.Date(veiculo.getAno_modificacao().getTime()));
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
            String sql = "UPDATE " + NOMEDATABELA + " SET tipo = ? cor_predominante = ?"
                    +" categoria = ? modelo = ? data_documento = ?"
                    +" ano_fabricacao = ? ano_modificacao = ? WHERE crlv_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, veiculo.getTipo());
            ps.setString(2,veiculo.getCor_predominante());
            ps.setString(3,veiculo.getCategoria());
            ps.setString(4,veiculo.getModelo());
            ps.setDate(5, new java.sql.Date(veiculo.getData_documento().getTime()));
            ps.setDate(6, new java.sql.Date(veiculo.getAno_fabricacao().getTime()));
            ps.setDate(7, new java.sql.Date(veiculo.getAno_modificacao().getTime()));
            ps.setInt(8, veiculo.getCrlv_id());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
