package com.classes.BO;

import com.classes.DTO.CRLV;
import com.classes.DAO.CRLVDAO;
import com.classes.conexao.ConectorMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CRLVBO {
    public boolean inserir(CRLV veiculo) {
        CRLVDAO crlvdao = new CRLVDAO();
        return crlvdao.inserir(veiculo);
    }

    public boolean alterar(CRLV veiculo) {
        CRLVDAO crlvdao = new CRLVDAO();
        return crlvdao.alterar(veiculo);
    }

    public boolean excluir(CRLV veiculo) {
        CRLVDAO crlvdao = new CRLVDAO();
        return crlvdao.excluir(veiculo);
    }
}
