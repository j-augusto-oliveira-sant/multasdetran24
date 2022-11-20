import java.util.Date;
import java.util.Calendar;

public class CRLV {
    private String tipo;
    private String cor_predominante;
    private String categoria;
    private String marca_modelo;
    private Date data_documento;
    private Date ano_fabricacao;
    private Date ano_modificacao;

    CRLV(String tipo, String cor_predominante, String categoria, String marca_modelo,
         Date data_documento, Date ano_fabricacao, Date ano_modificacao) {
        setTipo(tipo);
        setCor_predominante(cor_predominante);
        setCategoria(categoria);
        setMarca_modelo(marca_modelo);
        setData_documento(data_documento);
        setAno_fabricacao(ano_fabricacao);
        setAno_modificacao(ano_modificacao);
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

    public String getMarca_modelo() {
        return marca_modelo;
    }

    public void setMarca_modelo(String marca_modelo) {
        this.marca_modelo = marca_modelo;
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
        sb.append("tipo='").append(tipo).append('\'');
        sb.append(", cor_predominante='").append(cor_predominante).append('\'');
        sb.append(", categoria='").append(categoria).append('\'');
        sb.append(", marca_modelo='").append(marca_modelo).append('\'');
        sb.append(", data_documento=").append(data_documento);
        sb.append(", ano_fabricacao=").append(ano_fabricacao);
        sb.append(", ano_modificacao=").append(ano_modificacao);
        sb.append('}');
        return sb.toString();
    }
}
