import com.multasdetran24.database.ConectorMySQL;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

public class Perfil extends Usuario {
    private List<CRLV> veiculos = new ArrayList<CRLV>();

    Perfil(ConectorMySQL database, SecretKey key, String nome, boolean admin, String senha)  throws Exception  {
        super(database,key,nome, admin, senha);
    }

    public void adicionarVeiculo(CRLV veiculo) {
        veiculos.add(veiculo);
    }

    public void atualizarVeiculo() {
        //future
    }

    public void removerVeiculo(int index_veiculo) {
        //future
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Perfil{");
        sb.append("}\n");
        sb.append(super.toString());
        return sb.toString();
    }
}
