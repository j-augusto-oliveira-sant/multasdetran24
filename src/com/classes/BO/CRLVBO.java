package com.classes.BO;

import com.classes.DTO.CRLV;
import com.classes.DAO.CRLVDAO;

public class CRLVBO {
    public boolean inserir(CRLV veiculo) {
        CRLVDAO crlvdao = new CRLVDAO();
        return crlvdao.inserir(veiculo);
    }

    public boolean alterar(CRLV veiculo) {
        CRLVDAO crlvdao = new CRLVDAO();
        return crlvdao.alterar(veiculo);
    }

    public CRLV procurarPorId(CRLV veiculo) {
        CRLVDAO crlvdao = new CRLVDAO();
        return crlvdao.procurarPorId(veiculo);
    }

    public boolean excluir(CRLV veiculo) {
        CRLVDAO crlvdao = new CRLVDAO();
        return crlvdao.excluir(veiculo);
    }
}
