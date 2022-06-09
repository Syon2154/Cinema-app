package org.cinema.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashUtil {
    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATION = 65536;
    private static final int KEY_LENGTH = 128;

    private HashUtil() {

    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[32];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        KeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATION,
                KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            for (byte element : hash) {
                hashedPassword.append(String.format("%02x", element));
            }
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException("Can`t hash password!", ex);
        }
    }
}
