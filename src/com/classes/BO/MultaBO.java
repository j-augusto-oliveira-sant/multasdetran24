package com.classes.BO;

import com.classes.DAO.MultaDAO;
import com.classes.DTO.Multa;

import java.util.List;

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

    /**
     *
     * @param tipo_multa gravidades: 4 gravissima,3 grave,2 media,1 leve
     * @return List lista de multas pela classificacao
     */
    public List<Multa> procurarMultasPorTipo(int tipo_multa){
        MultaDAO multaDAO = new MultaDAO();
        return multaDAO.procurarMultasPorTipo(tipo_multa);
    }

    public boolean excluir(Multa multa) {
        MultaDAO multaDAO = new MultaDAO();
        return multaDAO.excluir(multa);
    }

}
