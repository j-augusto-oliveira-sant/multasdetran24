package com.classes.DTO;

public class Perfil {
    private int id;
    private int user_id;
    private int cnh_id;

    public Perfil(int id, int user_id, int cnh_id){
        setId(id);
        setUser_id(user_id);
        setCnh_id(cnh_id);
    }
    public Perfil(){};

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

    public int getCnh_id() {
        return cnh_id;
    }

    public void setCnh_id(int cnh_id) {
        this.cnh_id = cnh_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Perfil{");
        sb.append("id=").append(id);
        sb.append(", user_id=").append(user_id);
        sb.append(", cnh_id=").append(cnh_id);
        sb.append('}');
        return sb.toString();
    }
}
