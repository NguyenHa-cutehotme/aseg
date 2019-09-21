import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.io.File;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new demoase().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        FileManager fileManager= new FileManager();
        System.out.println( fileManager.countContent("stackjava.com.ifh")+"");
        fileManager.writeFile(fileManager.encryptFile(fileManager.readFile(new File("C:\\Users\\Admin 88\\Desktop\\destestout.txt")),"stack"),new File("C:\\Users\\Admin 88\\Desktop\\destestout1.txt"));
        System.out.println(fileManager.decryptedFile(fileManager.readFile(new File("C:\\Users\\Admin 88\\Desktop\\destestout1.txt")),"stack"));
//    }
//        String secretKey = "stackjava.com.ifh";
//        String originalString = "teamvietdev.com";
//        String encryptedString = encrypt(originalString, secretKey);
//        System.out.println("Encrypt: " + encryptedString);
//        String decryptedString = decrypt(encryptedString, secretKey);
//        System.out.println("Decrypt: " + decryptedString);
    }

    public static String decrypt(String strToDecrypt, String myKey) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = myKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static String encrypt(String strToEncrypt, String myKey) {
        try {

            byte[] key = myKey.getBytes("UTF-8");
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }



}
