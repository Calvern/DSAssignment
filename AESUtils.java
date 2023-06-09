/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

/**
 *
 * @author tanya
 */
import java.util.Base64;
import java.util.Stack;
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
        if (startIndex < 0 && endIndex >= encryptedText.length()) {
            return "-1";
        }
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
                            cipherChar = (char) ('A' + (cipherChar - 'Z' - 1) % 26);
                        }
                    } else {
                        if (cipherChar > 'z') {
                            cipherChar = (char) ('a' + (cipherChar - 'z' - 1) % 26);
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
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);

            if (currentChar == '&') {
                if (num >= 10) {
                    i += 3;
                    stack.push(encryptedText.charAt(i));
                    i++;
                } else {
                    i += 2;
                    stack.push(encryptedText.charAt(i));
                    i++;
                }
            }

            if (!stack.isEmpty()) {
                StringBuilder subtractedText = new StringBuilder();
                while (!stack.isEmpty()) {
                    if (encryptedText.charAt(i) == '}' && stack.peek() == '{') {
                        stack.pop();
                        break;
                    }
                    char intermediateChar = encryptedText.charAt(i);
                    if (Character.isLetter(intermediateChar)) {
                        char currChar = (char) (intermediateChar - num);
                        char baseChar = Character.isUpperCase(intermediateChar) ? 'A' : 'a';
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

//
//    public static String SpecialSyntaxOperations(String encryptedText, int num) {
//        StringBuilder intermediateText = new StringBuilder();
//        boolean isGrp = true; // Move this outside the loop
//        for (int i = 0; i < encryptedText.length(); i++) {
//            char currentChar = encryptedText.charAt(i);
//
//            if (currentChar == '&' && isGrp) {
//                StringBuilder subtractedText = new StringBuilder();
//                i += 3;
//                if (num >= 10) {
//                    i++;
//                }
//                while (encryptedText.charAt(i) != '}') {
//                    char intermediateChar = encryptedText.charAt(i);
//                    if (Character.isLetter(intermediateChar)) {
//                        char currChar = (char) (intermediateChar - num);
//                        char baseChar = Character.isUpperCase(intermediateChar) ? 'A' : 'a';
//                        currChar = (char) (baseChar + (currChar - baseChar + 26) % 26);
//                        subtractedText.append(currChar);
//                    } else {
//                        subtractedText.append(intermediateChar);
//                    }
//                    i++;
//                }
//                intermediateText.append(subtractedText);
//                if (encryptedText.charAt(i) == '}') {
//                    isGrp = false;
//                }
//            } else {
//                intermediateText.append(currentChar);
//            }
//        }
//
//        return intermediateText.toString();
//    }
    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
