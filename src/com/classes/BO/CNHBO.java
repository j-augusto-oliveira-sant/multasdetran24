package com.classes.BO;

import com.classes.DAO.CNHDAO;
import com.classes.DTO.CNH;
import com.classes.DTO.Perfil;

public class CNHBO {
    public boolean inserir(CNH cnh) {
        CNHDAO cnhdao = new CNHDAO();
        return cnhdao.inserir(cnh);
    }

    public boolean alterar(CNH cnh) {
        CNHDAO cnhdao = new CNHDAO();
        return cnhdao.alterar(cnh);
    }

    public CNH procurarPorPerfilId(Perfil perfil) {
        CNHDAO cnhdao = new CNHDAO();
        return cnhdao.procurarPorPerfilId(perfil);
    }

    public boolean excluir(CNH cnh) {
        CNHDAO cnhdao = new CNHDAO();
        return cnhdao.excluir(cnh);
    }
}
