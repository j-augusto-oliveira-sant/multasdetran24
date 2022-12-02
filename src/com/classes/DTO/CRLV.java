package com.classes.DTO;
import java.util.Date;

public class CRLV {
    int crlv_id;
    int perfil_id;//FOREIGN KEY
    String tipo;
    String cor_predominante;
    String categoria;
    String modelo;
    Date data_documento;
    Date ano_fabricacao;
    Date ano_modificacao;

    public CRLV(){}

    public int getCrlv_id() {
        return crlv_id;
    }

    public void setCrlv_id(int crlv_id) {
        this.crlv_id = crlv_id;
    }

    public int getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(int perfil_id) {
        this.perfil_id = perfil_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor_predominante() {
        return cor_predominante;
    }

    public void setCor_predominante(String cor_predominante) {
        this.cor_predominante = cor_predominante;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getData_documento() {
        return data_documento;
    }

    public void setData_documento(Date data_documento) {
        this.data_documento = data_documento;
    }

    public Date getAno_fabricacao() {
        return ano_fabricacao;
    }

    public void setAno_fabricacao(Date ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
    }

    public Date getAno_modificacao() {
        return ano_modificacao;
    }

    public void setAno_modificacao(Date ano_modificacao) {
        this.ano_modificacao = ano_modificacao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CRLV{");
        sb.append("crlv_id=").append(crlv_id);
        sb.append(", perfil_id=").append(perfil_id);
        sb.append(", tipo='").append(tipo).append('\'');
        sb.append(", cor_predominante='").append(cor_predominante).append('\'');
        sb.append(", categoria='").append(categoria).append('\'');
        sb.append(", modelo='").append(modelo).append('\'');
        sb.append(", data_documento=").append(data_documento);
        sb.append(", ano_fabricacao=").append(ano_fabricacao);
        sb.append(", ano_modificacao=").append(ano_modificacao);
        sb.append('}');
        return sb.toString();
    }
}
