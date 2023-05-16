/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsassignment;

/**
 *
 * @author tanya
 */
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static String encrypt(String plainText, String secretKey) throws Exception {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String ApplySpecialSyntaxOperations(String encryptedText, int startIndex, int endIndex, int num) {
        StringBuilder intermediateText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);

            if (i == startIndex) {
                intermediateText.append("&" + num + "{");
            }
            if (i >= startIndex && i <= endIndex) {
                if (Character.isLetter(currentChar)) {
                    char cipherChar = (char) (currentChar + num);
                    if (Character.isUpperCase(currentChar)) {
                        if (cipherChar > 'Z') {
                            cipherChar = (char) ('A' + (cipherChar - 'Z' - 1));
                        }
                    } else {
                        if (cipherChar > 'z') {
                            cipherChar = (char) ('a' + (cipherChar - 'z' - 1));
                        }
                    }
                    intermediateText.append(cipherChar);
                } else {
                    intermediateText.append(currentChar);
                }
                if (i == endIndex) {
                    intermediateText.append("}");
                }
            } else {
                intermediateText.append(currentChar);
            }
        }
        return intermediateText.toString();
    }

    public static String SpecialSyntaxOperations(String encryptedText, int num) {
        StringBuilder intermediateText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);

            if (currentChar == '&') {
                StringBuilder subtractedText = new StringBuilder();
                i += 3;
                while (encryptedText.charAt(i) != '}') {
                    char intermediateChar = encryptedText.charAt(i);
                    if (Character.isLetter(intermediateChar)) {
                        char currChar = (char) (intermediateChar - num);
                        char baseChar = Character.isUpperCase(currChar) ? 'A' : 'a';
                        currChar = (char) (baseChar + (currChar - baseChar + 26) % 26);
                        subtractedText.append(currChar);
                    } else {
                        subtractedText.append(intermediateChar);
                    }
                    i++;
                }
                intermediateText.append(subtractedText);
            } else {
                intermediateText.append(currentChar);
            }
        }
        return intermediateText.toString();
    }

    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

//    public static void main(String[] args) throws Exception {
//
//        String plainText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
//        String secretKey = "0123456789ABCDEF";
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the start index and end index: ");
//        int startIndex = scanner.nextInt();
//        int endIndex = scanner.nextInt();
//        System.out.print("Enter the number for the text inside \"{}\" to be subtracted: ");
//        int num = scanner.nextInt();
//
//        String AdEncryption = AESUtils.encrypt(plainText, secretKey);
//        System.out.println("Advanced Encryption: " + AdEncryption);
//
//        String encryptedText = ApplySpecialSyntaxOperations(AdEncryption, startIndex, endIndex, num);
//        System.out.println("encryptedText: " + encryptedText);
//
//        String intermediateText = SpecialSyntaxOperations(encryptedText, num);
//        System.out.println("Removed syntax: " + intermediateText);
//        String decryptedText = decrypt(intermediateText, secretKey);
//        System.out.println("Decrypted Text: " + decryptedText);
//
//        scanner.close();
//    }
}
