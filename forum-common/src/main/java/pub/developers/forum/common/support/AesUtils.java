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
        String msg = "imSystem";
        String msg2 = "imSystemPassword-1703514800";
        //String msg3= "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyZDIwYjE4MGIyNjQ0NGQ4OWY5YWQ3Y2MwM2Q4ZGUyMSIsInN1YiI6IlN5c3RlbV9pbVN5c3RlbSIsImlzcyI6ImdvZG93IiwiaWF0IjoxNzAyODIxMzgzLCJleHAiOjE3MDU0MTMzODN9.15aqIvsVl15kMiP-STPhIR30no4a8NjbtJcBtHBGFTg";
        // 实际key请查看const变量文件
        String key = "One.More12345678One.More8765.onE";
        //System.out.println("加密前：" + msg);
        String pwd = AesUtils.encrypt(msg, key);
        String pwd2 = AesUtils.encrypt(msg2, key);
        System.out.println("encrypt：" + pwd);
        System.out.println("encrypt2：" + pwd2);
        //String decrypt = AesUtils.decrypt(msg3, key);
        //System.out.println("decrypt:" + decrypt);

    }
}
