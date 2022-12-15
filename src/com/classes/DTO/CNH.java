package com.classes.DTO;

import java.util.Date;

public class CNH {
    private int cnh_id;
    private int perfil_id;
    private int pontuacao=0;
    private String tipo_carteira;
    private String cpf;
    private Date data_1_habilitacao;
    private Date emissao;
    private Date validade;
    private String identidade;
    private String nacionalidade;

    private boolean ativa;

    public CNH(int perfil_id, int pontuacao, String tipo_carteira, String cpf,
               Date data_1_habilitacao, Date emissao, Date validade, String identidade, String nacionalidade, boolean ativa) {
        setPerfil_id(perfil_id);
        setPontuacao(pontuacao);
        setTipo_carteira(tipo_carteira);
        setCpf(cpf);
        setData_1_habilitacao(data_1_habilitacao);
        setEmissao(emissao);
        setValidade(validade);
        setIdentidade(identidade);
        setNacionalidade(nacionalidade);
        setAtiva(ativa);
    }

    public CNH(){}

    public int getCnh_id() {
        return cnh_id;
    }

    public void setCnh_id(int cnh_id) {
        this.cnh_id = cnh_id;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getTipo_carteira() {
        return tipo_carteira;
    }

    public void setTipo_carteira(String tipo_carteira) {
        this.tipo_carteira = tipo_carteira;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_1_habilitacao() {
        return data_1_habilitacao;
    }

    public void setData_1_habilitacao(Date data_1_habilitacao) {
        this.data_1_habilitacao = data_1_habilitacao;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(int perfil_id) {
        this.perfil_id = perfil_id;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CNH{");
        sb.append("cnh_id=").append(cnh_id);
        sb.append(", perfil_id=").append(perfil_id);
        sb.append(", pontuacao=").append(pontuacao);
        sb.append(", tipo_carteira='").append(tipo_carteira).append('\'');
        sb.append(", cpf='").append(cpf).append('\'');
        sb.append(", data_1_habilitacao=").append(data_1_habilitacao);
        sb.append(", emissao=").append(emissao);
        sb.append(", validade=").append(validade);
        sb.append(", identidade='").append(identidade).append('\'');
        sb.append(", nacionalidade='").append(nacionalidade).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
