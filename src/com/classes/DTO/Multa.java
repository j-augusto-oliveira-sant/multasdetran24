package com.classes.DTO;

public class Multa {
    private int multa_id;
    private String codigo;
    private String descricao;
    private int tipo_multa;//gravidade 4 gravissima,3 grave,2 media,1 leve
    private int pontos;
    private double valor;
    private String base_legal;

    public Multa(String codigo, String descricao, int tipo_multa, int pontos, double valor, String base_legal) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipo_multa = tipo_multa;
        this.pontos = pontos;
        this.valor = valor;
        this.base_legal = base_legal;
    }

    public Multa(){}

    public int getMulta_id() {
        return multa_id;
    }

    public void setMulta_id(int multa_id) {
        this.multa_id = multa_id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo_multa() {
        return tipo_multa;
    }

    public void setTipo_multa(int tipo_multa) {
        this.tipo_multa = tipo_multa;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getBase_legal() {
        return base_legal;
    }

    public void setBase_legal(String base_legal) {
        this.base_legal = base_legal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Multa{");
        sb.append("multa_id=").append(multa_id);
        sb.append(", codigo='").append(codigo).append('\'');
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append(", tipo_multa=").append(tipo_multa);
        sb.append(", pontos=").append(pontos);
        sb.append(", valor=").append(valor);
        sb.append(", base_legal='").append(base_legal).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
