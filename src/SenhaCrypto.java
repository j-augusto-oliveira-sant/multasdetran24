import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SenhaCrypto {
    private Cipher cipher;
    private String senha_secure;
    private SecretKey secretKey;

    SenhaCrypto(String senha) throws Exception {
        cipher = Cipher.getInstance("AES");
        this.senha_secure = encrypt(senha,secretKey);
    }
    SenhaCrypto(){};

    public String get_unsecure_senha() throws Exception {
        return  decrypt(senha_secure,secretKey);
    }

    private void generate_key() throws Exception{
        //secretKey é a chave para criptografar e descriptografar, algoritmo AES.
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        this.secretKey = keyGenerator.generateKey();
    }

    public String encrypt(String plainText, SecretKey secretKey)
            throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }
    public String decrypt(String encryptedText, SecretKey secretKey)
            throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }

    public String getSenha_secure() {
        return senha_secure;
    }
}
