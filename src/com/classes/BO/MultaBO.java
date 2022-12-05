package com.classes.BO;

import com.classes.DAO.MultaDAO;
import com.classes.DTO.Multa;

public class MultaBO {

    public boolean inserir(Multa multa) {
        MultaDAO multaDAO = new MultaDAO();
        return multaDAO.inserir(multa);
    }

    public boolean alterar(Multa multa) {
        MultaDAO multaDAO = new MultaDAO();
        return multaDAO.alterar(multa);
    }

    public Multa procurarPorId(Multa multa) {
        MultaDAO multaDAO = new MultaDAO();
        return multaDAO.procurarPorId(multa);
    }

    public boolean excluir(Multa multa) {
        MultaDAO multaDAO = new MultaDAO();
        return multaDAO.excluir(multa);
    }

}
