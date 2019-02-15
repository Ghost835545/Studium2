package com.example.ivan.loginapp.activity;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Security {
    public static String encryptPass(String password) {
        try {
            byte[] keyStart = "1dj3fcdbgh4jf".getBytes(StandardCharsets.UTF_8);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr);
            SecretKey skey = kgen.generateKey();
            byte[] key = skey.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            String sumPass = Base64.encodeToString(encrypted, Base64.NO_PADDING) + Base64.encodeToString(key, Base64.NO_PADDING);
            String[] string = sumPass.split("\n");
            String str = "";
            for (String s : string) {
                if (!s.isEmpty()) str += s;

            }
            return str;
        } catch (Exception e) {
            Log.d("encryptPass", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptPass(String encrypted)   {
        try {
            String key = encrypted.substring(encrypted.length() - 22);
            String password = encrypted.substring(0, encrypted.length() - 22);
            SecretKeySpec skeySpec = new SecretKeySpec(Base64.decode(key, Base64.NO_PADDING), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypted = cipher.doFinal(Base64.decode(password, Base64.NO_PADDING));
            return new String(decrypted, StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
