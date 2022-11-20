import com.multasdetran24.database.ConectorMySQL;
import java.sql.ResultSet;
import javax.crypto.SecretKey;

public class Usuario {
    private final ConectorMySQL database;
    private SecretKey key_senha;
    private String senha;
    private String nome;
    private boolean admin;

    Usuario(ConectorMySQL database,SecretKey key, String nome, boolean admin, String senha) throws Exception {
        database.add_query("INSERT INTO usuario(nome,senha,is_admin) VALUES('"+nome+"','',false);");
        this.database = database;
        this.key_senha = key;
        setNome(nome);
        setAdmin(admin);
        setSenha(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        database.add_query("UPDATE usuario SET nome = '"+nome+"' WHERE nome = '"+getNome()+"';");
        this.nome = nome;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) throws Exception {
        database.add_query("UPDATE usuario SET is_admin = "+admin+" WHERE nome = '"+getNome()+"';");
        this.admin = admin;
    }

    public String getSenha() {
        return senha;
    }

    public String getUnsecuresenha() throws Exception {
        SenhaCrypto descripto = new SenhaCrypto();
        return descripto.decrypt(senha,key_senha);
    }

    public void setSenha(String senha) throws Exception {
        SenhaCrypto cryptosenha = new SenhaCrypto(senha);
        String secured_senha = cryptosenha.getSenha_secure();
        this.key_senha = cryptosenha.getSecretKey();
        database.add_query("UPDATE usuario SET senha = '"+secured_senha+"' WHERE nome = '"+getNome()+"';");
        this.senha = secured_senha;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("senha=").append(senha);
        sb.append(", nome=").append(nome);
        sb.append(", admin=").append(admin);
        sb.append('}');
        return sb.toString();
    }
}
