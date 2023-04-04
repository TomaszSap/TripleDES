package org.example;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TripleDES {
    private final static String ALGORITHM="DESede";
    private final static String ECB="DESede/ECB/PKCS5Padding";
    private final static String CBC="DESede/CBC/PKCS5Padding";
    private final static String CFB="DESede/CFB/PKCS5Padding";
    private final static String OFB="DESede/OFB/PKCS5Padding";
    private final static String CTR="DESede/CTR/PKCS5Padding";
    private byte[] initializationVector;
    private  byte[] keyBytes;

    TripleDES()
    {
        generateIV();
        generate3DESKey();
    }


    public void encryptFileECB(File inputFile, File outputFile,File decryptedFile) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ECB);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Otwórz plik wejściowy i plik wyjściowy
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        // Utwórz strumień CipherOutputStream i zapisz zaszyfrowane dane do pliku wyjściowego
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);

        byte[] buffer = new byte[4096];
        int bytesRead;

        // Czytaj dane z pliku wejściowego i zapisz zaszyfrowane dane do pliku wyjściowego
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            cipherOutputStream.write(buffer, 0, bytesRead);
        }

        // Zamknij strumienie
        inputStream.close();
        cipherOutputStream.flush();
        cipherOutputStream.close();
        decryptFileECB(outputFile,decryptedFile);
    }
    // Funkcja deszyfrująca plik TripleDES
    public void decryptFileECB(File inputFile, File outputFile) throws Exception {
        // Utwórz obiekt klasy Cipher i inicjuj go w trybie ECB
        Cipher cipher = Cipher.getInstance(ECB);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        // Utwórz strumień CipherInputStream i odczytaj zaszyfrowane dane z pliku wejściowego
        CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);

        byte[] buffer = new byte[4096];
        int bytesRead;

        // Czytaj zaszyfrowane dane z pliku wejściowego i zapisz je do pliku wyjściowego
        while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // Zamknij strumienie
        cipherInputStream.close();
        outputStream.flush();
        outputStream.close();
    }
    public void encryptFileOtherModes(String mode,File inputFile, File outputFile,File decryptedFile) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(mode);
        IvParameterSpec ivParams = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
        // Otwórz plik wejściowy i plik wyjściowy
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        // Utwórz strumień CipherOutputStream i zapisz zaszyfrowane dane do pliku wyjściowego
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);

        byte[] buffer = new byte[8192];
        int bytesRead;

        // Czytaj dane z pliku wejściowego i zapisz zaszyfrowane dane do pliku wyjściowego
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            cipherOutputStream.write(buffer, 0, bytesRead);
        }
        // Zamknij strumienie
        inputStream.close();
        cipherOutputStream.flush();
        cipherOutputStream.close();
        decryptFileOtherModes(mode,outputFile,decryptedFile);
    }
    public void decryptFileOtherModes(String mode,File inputFile, File outputFile) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        IvParameterSpec ivParams = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
        // Otwórz plik wejściowy i plik wyjściowy
        FileInputStream inputStream = new FileInputStream(inputFile);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        // Utwórz strumień CipherOutputStream i zapisz zaszyfrowane dane do pliku wyjściowego
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);

        byte[] buffer = new byte[8192];
        int bytesRead;

        // Czytaj dane z pliku wejściowego i zapisz zaszyfrowane dane do pliku wyjściowego
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            cipherOutputStream.write(buffer, 0, bytesRead);
        }

        // Zamknij strumienie
        inputStream.close();
        cipherOutputStream.flush();
        cipherOutputStream.close();
    }
    public void generateIV() {
        SecureRandom random = new SecureRandom();

        byte[] iv = new byte[8];
        random.nextBytes(iv);

        initializationVector=iv;
    }

    public void generate3DESKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            // 24 bajty
            keyGenerator.init(168);
            SecretKey secretKey = keyGenerator.generateKey();

            keyBytes = secretKey.getEncoded();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
