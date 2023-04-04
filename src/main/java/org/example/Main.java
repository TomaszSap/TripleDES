package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {
    private final static String CBC="DESede/CBC/PKCS5Padding";
    private final static String CFB="DESede/CFB/PKCS5Padding";
    private final static String OFB="DESede/OFB/PKCS5Padding";
    private final static String CTR="DESede/CTR/PKCS5Padding";
    private static String filePath="C:\\Users\\sapol\\OneDrive\\Pulpit\\file\\Rocky-8.7-x86_64-minimal.iso";
    private static String outputEncryptedFilePath="C:\\Users\\sapol\\OneDrive\\Pulpit\\file\\encrypted\\Rocky-8.7-x86_64-minimal.iso";
    private static String outputDecryptedFilePath="C:\\Users\\sapol\\OneDrive\\Pulpit\\file\\Decrypted\\Rocky-8.7-x86_64-minimal.iso";


    public static void main(String[] args) throws Exception {
        File inputFile = new File(filePath);
        File encryptedFile = new File(outputEncryptedFilePath);
        File decryptedFile=new File(outputDecryptedFilePath);
        TripleDES tripleDES=new TripleDES();
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("1 - ECB");
        System.out.println("2 - CBC");
        System.out.println("3 - CFB");
        System.out.println("4 - OFB");
        System.out.println("5 - CTR");
        // Reading data using readLine
        String name = reader.readLine();
        String mode=null;
        int value= Integer.parseInt(name);

        switch(value)
        {
            case '1':
            {
                mode=null;
            }
            case '2':
            {
                mode=CBC;
            }
            case '3':
            {
                mode=CFB;
            }
            case '4':
            {
                mode=OFB;
            }
            case '5':
            {
                mode=CTR;
            }
            default:break;
        }
        long before=System.currentTimeMillis();
        if(mode==null)
        {
             tripleDES.encryptFileECB(inputFile,encryptedFile,decryptedFile);
        }
            else
        {
            tripleDES.encryptFileOtherModes(mode,inputFile,encryptedFile,decryptedFile);

        }
        long after=System.currentTimeMillis();
        System.out.println(after-before);
    }
}