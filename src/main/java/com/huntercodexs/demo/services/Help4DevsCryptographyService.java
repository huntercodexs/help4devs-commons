package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Slf4j
@Service
public class Help4DevsCryptographyService {

    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;
    public static final String ENCRYPTION_DES_SCHEME = "DESede";

    /**
     /**
     * <h6 style="color: #FFFF00; font-size: 11px">encryptAesCbc256</h6>
     *
     * <p style="color: #CDCDCD">Data Encrypt using the algorithm AES-256-CBC</p>
     *
     * @param strToEncrypt (String: Data to encrypt)
     * @param secretKey (String: Secret Key to Data encrypt)
     * @param salt (String: Salt to apply in the Secret Key)
     * @return String (Data Encrypted)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String encryptAesCbc256(String strToEncrypt, String secretKey, String salt) {
        try {

            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

            byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
            byte[] encryptedData = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, encryptedData, 0, iv.length);
            System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

            return Base64.getEncoder().encodeToString(encryptedData);

        } catch (Exception e) {
            System.out.println("Encrypt Exception: " + e.getMessage());
            return null;
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">decryptAesCbc256</h6>
     *
     * <p style="color: #CDCDCD">Data Decrypt using the algorithm AES-256-CBC</p>
     *
     * @param strToDecrypt (String: Data to decrypt)
     * @param secretKey (String: Secret Key to Data decrypt)
     * @param salt (String: Salt to apply in the Secret Key)
     * @return String (Data Decrypted)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String decryptAesCbc256(String strToDecrypt, String secretKey, String salt) {
        try {

            byte[] encryptedData = Base64.getDecoder().decode(strToDecrypt);
            byte[] iv = new byte[16];
            System.arraycopy(encryptedData, 0, iv, 0, iv.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

            byte[] cipherText = new byte[encryptedData.length - 16];
            System.arraycopy(encryptedData, 16, cipherText, 0, cipherText.length);

            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println("Decrypt Exception: " + e.getMessage());
            return null;
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">encrypt3desEde</h6>
     *
     * <p style="color: #CDCDCD">Data Encrypt using the algorithm 3DES-EDE</p>
     *
     * @param inputClear (String: Data to encrypt)
     * @param secretKey (String: Secret Key to Data encrypt)
     * @return String (Data Encrypted)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String encrypt3desEde(String inputClear, String secretKey) {
        try {

            byte[] arrayBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ENCRYPTION_DES_SCHEME);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_DES_SCHEME);
            SecretKey key = skf.generateSecret(ks);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = inputClear.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedText = cipher.doFinal(plainText);
            return new String(Base64.getEncoder().encode(encryptedText));

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">decrypt3DesEde</h6>
     *
     * <p style="color: #CDCDCD">Data Decrypt using the algorithm 3DES-EDE</p>
     *
     * @param inputCipher (String: Data to decrypt)
     * @param secretKey (String: Secret Key to Data decrypt)
     * @return String (Data Decrypted)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String decrypt3DesEde(String inputCipher, String secretKey) {
        try {

            byte[] arrayBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            KeySpec ks = new DESedeKeySpec(arrayBytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ENCRYPTION_DES_SCHEME);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_DES_SCHEME);
            SecretKey key = skf.generateSecret(ks);

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.getDecoder().decode(inputCipher);
            byte[] plainText = cipher.doFinal(encryptedText);
            return new String(plainText);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
