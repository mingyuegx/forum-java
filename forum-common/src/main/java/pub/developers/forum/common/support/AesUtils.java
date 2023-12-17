package pub.developers.forum.common.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class AesUtils {
    public static final String DEFAULT_CODING = "utf-8";

    public static String decrypt(String encrypted, String secretKey) throws Exception {
        try {
            byte[] keyBytes = secretKey.getBytes(DEFAULT_CODING);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(keyBytes);
            SecretKeySpec sKey = new SecretKeySpec(digest, "AES");
            Cipher dcipher = Cipher.getInstance("AES");
            dcipher.init(Cipher.DECRYPT_MODE, sKey);

            byte[] clearByte = dcipher.doFinal(toByte(encrypted));
            return new String(clearByte);
        } catch (Exception e) {
            System.out.println("decrypt error:" + e);
            return null;
        }

    }


    public static String encrypt(String content, String secretKey) throws Exception {
        byte[] input = content.getBytes(DEFAULT_CODING);

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(secretKey.getBytes(DEFAULT_CODING));
        SecretKeySpec skc = new SecretKeySpec(digest, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skc);

        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        return parseByte2HexStr(cipherText);
    }


    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }


    private static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String msg = "{\"id\":3,\"email\":\"mingyuegx@126.com\",\"role\":\"SUPER_ADMIN\"}";
        // 实际key请查看const变量文件
        String key = "One.More12345678One.More8765.onE";
        System.out.println("加密前：" + msg);
        String pwd = AesUtils.encrypt(msg, key);
        System.out.println("encrypt：" + pwd);
        System.out.println("decrypt：" + AesUtils.decrypt(pwd, key));
    }
}
