//package toDoApplication.utils;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.util.Base64;
//
//public class Decrypt{
//    private final SecretKey secretKey;
//
//    public AESEncryption() throws Exception {
//        this.secretKey = generateSecretKey();
//    }
//    public String encrypt(String plaintext) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
//        return Base64.getEncoder().encodeToString(encryptedBytes);
//    }
//
//    public String decrypt(String encryptedText) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
//        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
//        return new String(decryptedBytes);
//    }
//
//    private SecretKey generateSecretKey() throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(256);
//        return keyGenerator.generateKey();
//
//            }
//}
