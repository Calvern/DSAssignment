/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

/**
 *
 * @author tanya
 */
public class Decryption {

    public static String SpecialSyntaxOperations(String encryptedText) {
        StringBuilder intermediateText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);

            if (currentChar == '^') {
                intermediateText.append(Character.toUpperCase(encryptedText.charAt(++i)));
            } else if (currentChar == '$') {
                intermediateText.append(' ');
            } else if (currentChar == '(') {
                int openCount = 1;
                StringBuilder invertedText = new StringBuilder();

                while (openCount > 0) {
                    char nextChar = encryptedText.charAt(++i);
                    if (nextChar == '(') {
                        openCount++;
                    } else if (nextChar == ')') {
                        openCount--;
                    }
                    if (openCount > 0) {
                        invertedText.append(nextChar);
                    }
                }
                String decryptedSubText = SpecialSyntaxOperations(invertedText.toString()); // Recursively decrypt the nested parentheses
                intermediateText.append(new StringBuilder(decryptedSubText).reverse());
            } else {
                intermediateText.append(currentChar);
            }
        }
        return intermediateText.toString();
    }

    public static String decrypt(String intermediateText, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < intermediateText.length(); i++) {
            char currentChar = intermediateText.charAt(i);

            if (Character.isLetter(currentChar)) {
                char decryptedChar = (char) (currentChar - shift);

                if (Character.isUpperCase(currentChar)) {
                    if (decryptedChar < 'A') {
                        decryptedChar = (char) ('Z' - ('A' - decryptedChar - 1));
                    }
                } else {
                    if (decryptedChar < 'a') {
                        decryptedChar = (char) ('z' - ('a' - decryptedChar - 1));
                    }
                }
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(currentChar);
            }
        }
        return decryptedText.toString();
    }
}

//Enter the encrypted text: ^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.
//Enter the shift position: 7
//Decrypted Text: Advise Cao Cao to use The Chain Strategem, which is to chain his battleships with strong iron chains.
