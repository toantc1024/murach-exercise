package com.murach.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordUtil {

    private static final int SALT_LENGTH = 16; // 16 bytes
    private static final int HASH_LENGTH = 64; // 64 bytes
    private static final int ITERATIONS = 10000; // PBKDF2 iterations
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    /**
     * Generates a salted hash for the given password.
     *
     * @param password The plain text password.
     * @return The hashed password in the format: base64(salt):base64(hash).
     * @throws NoSuchAlgorithmException If the algorithm is not available.
     * @throws InvalidKeySpecException  If the key specification is invalid.
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Generate a random salt
        byte[] salt = generateSalt();

        // Generate the hash
        byte[] hash = generateHash(password.toCharArray(), salt);

        // Return the salt and hash as a single string
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Validates a password against a stored salted hash.
     *
     * @param password        The plain text password.
     * @param storedHashSalt  The stored salted hash in the format: base64(salt):base64(hash).
     * @return True if the password is valid, false otherwise.
     * @throws NoSuchAlgorithmException If the algorithm is not available.
     * @throws InvalidKeySpecException  If the key specification is invalid.
     */
    public static boolean validatePassword(String password, String storedHashSalt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Split the stored hash into salt and hash
        String[] parts = storedHashSalt.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid stored hash format.");
        }

        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] storedHash = Base64.getDecoder().decode(parts[1]);

        // Generate a hash from the provided password and the stored salt
        byte[] hash = generateHash(password.toCharArray(), salt);

        // Compare the hashes byte by byte
        return slowEquals(storedHash, hash);
    }

    /**
     * Generates a random salt.
     *
     * @return A random salt.
     */
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Generates a hash using PBKDF2 with HMAC-SHA256.
     *
     * @param password The password to hash.
     * @param salt     The salt to use.
     * @return The generated hash.
     * @throws NoSuchAlgorithmException If the algorithm is not available.
     * @throws InvalidKeySpecException  If the key specification is invalid.
     */
    private static byte[] generateHash(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, HASH_LENGTH * 8);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        return factory.generateSecret(spec).getEncoded();
    }

    /**
     * Compares two byte arrays in constant time to prevent timing attacks.
     *
     * @param a The first byte array.
     * @param b The second byte array.
     * @return True if both arrays are equal, false otherwise.
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
