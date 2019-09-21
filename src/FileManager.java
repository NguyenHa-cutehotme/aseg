import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Base64;

public class FileManager {

    String readFile(File file) {
        StringBuilder result = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(file);

            int b;
            while ((b = inputStream.read()) != -1) {
                result.append((char) b);
            }

            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    void writeFile(String content, File file) {
        FileOutputStream outputStream;
        byte[] byteContent = content.getBytes();
        try {

            outputStream = new FileOutputStream(file);
            outputStream.write(byteContent);
            outputStream.close();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String encryptFile(String s, String key) {
long time=System.currentTimeMillis();
        String encrypted = "";
        try {
            int newLength = countContent(key);
            byte[] keys = key.getBytes("UTF-8");
            keys = Arrays.copyOf(keys, newLength);
            SecretKeySpec skeySpec = new SecretKeySpec(keys, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] byteEncrypted;
            byteEncrypted = cipher.doFinal(s.getBytes());
            encrypted = Base64.getEncoder().encodeToString(byteEncrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        long millis=System.currentTimeMillis();
        millis= millis-time;
        System.out.println("Time ma hoa la:"+millis);
        return encrypted;
    }

    String decryptedFile(String s, String key) {
        try {
            int newLength = countContent(key);
            byte[] keys = key.getBytes("UTF-8");
            keys = Arrays.copyOf(keys, newLength);
            SecretKeySpec secretKey = new SecretKeySpec(keys, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(s.getBytes())));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    int countContent(String key) {

        if (17 <= key.length() || key.length() <= 24) {
            return 24;
        }
        if (key.length() >= 25) {
            return 32;
        }
        return 16;
    }
}
