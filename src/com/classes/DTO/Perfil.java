package com.classes.DTO;

import com.classes.BO.PerfilBO;
import java.util.List;

public class Perfil {
    private int id;
    private int user_id;

    public Perfil(int id, int user_id){
        setId(id);
        setUser_id(user_id);
    }
    public Perfil(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<CRLV> montarVeiculos(){
        PerfilBO perfilBO = new PerfilBO();
        return perfilBO.procurarVeiculos(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Perfil{");
        sb.append("id=").append(id);
        sb.append(", user_id=").append(user_id);
        sb.append('}');
        return sb.toString();
    }
}
