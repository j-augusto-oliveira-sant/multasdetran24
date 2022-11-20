import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class ServerKey {

    private void generate_key() throws Exception{
        //secretKey é a chave para criptografar e descriptografar, algoritmo AES.
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        this.secretKey = keyGenerator.generateKey();
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
