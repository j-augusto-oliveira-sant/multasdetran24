package com.classes.DTO;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private boolean admin=false;

    public Usuario(int id, String nome, String senha, boolean admin){
        setId(id);
        setNome(nome);
        setSenha(senha);
        setAdmin(admin);
    }
    public Usuario(int id, String nome, String senha){
        setId(id);
        setNome(nome);
        setSenha(senha);
    }
    public Usuario(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("user_id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", senha='").append(senha).append('\'');
        sb.append(", admin=").append(admin);
        sb.append('}');
        return sb.toString();
    }
}
