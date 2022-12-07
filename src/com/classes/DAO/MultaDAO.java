package com.classes.DAO;

import com.classes.DTO.CRLV;
import com.classes.DTO.Multa;
import com.classes.conexao.ConectorMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MultaDAO {

    final String NOMEDATABELA = "multa";

    public boolean inserir(Multa multa) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (codigo,descricao,tipo_multa,pontos,valor,base_legal)" +
                    " VALUES (?,?,?,?,?,?);";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, multa.getCodigo());
            ps.setString(2, multa.getDescricao());
            ps.setInt(3, multa.getTipo_multa());
            ps.setInt(4, multa.getPontos());
            ps.setDouble(5, multa.getValor());
            ps.setString(6, multa.getBase_legal());
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

    public boolean alterar(Multa multa) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET codigo = ?,"
                    +" descricao = ?, tipo_multa = ?, pontos = ?, valor = ?, base_legal ? WHERE crlv_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, multa.getCodigo());
            ps.setString(2, multa.getDescricao());
            ps.setInt(3, multa.getTipo_multa());
            ps.setInt(4, multa.getPontos());
            ps.setDouble(5, multa.getValor());
            ps.setString(6, multa.getBase_legal());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param tipo_multa gravidades: 4 gravissima,3 grave,2 media,1 leve
     * @return
     */
    public List<Multa> procurarMultasPorTipo(int tipo_multa){
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM multa WHERE tipo_multa = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tipo_multa);
            ResultSet rs = ps.executeQuery();
            return montarLista(rs);
        } catch (Exception e) {
            //System.err.println("Erro: " + e.toString());
            //e.printStackTrace();
            return null;
        }
    }

    private List<Multa> montarLista(ResultSet rs) {
        List<Multa> listObj = new ArrayList<Multa>();
        try {
            while (rs.next()) {
                Multa obj = new Multa();
                obj.setMulta_id(rs.getInt(1));
                obj.setCodigo(rs.getString(2));
                obj.setDescricao(rs.getString(3));
                obj.setTipo_multa(rs.getInt(4));
                obj.setPontos(rs.getInt(5));
                obj.setValor(rs.getDouble(6));
                obj.setBase_legal(rs.getString(7));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public Multa procurarPorId(Multa multa) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE multa_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, multa.getMulta_id());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Multa obj = new Multa();
                obj.setMulta_id(rs.getInt(1));
                obj.setCodigo(rs.getString(2));
                obj.setDescricao(rs.getString(3));
                obj.setTipo_multa(rs.getInt(4));
                obj.setPontos(rs.getInt(5));
                obj.setValor(rs.getDouble(6));
                obj.setBase_legal(rs.getString(7));
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


    public boolean excluir(Multa multa) {
        try {
            Connection conn = ConectorMySQL.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE multa_id = ?;";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, multa.getMulta_id());
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
