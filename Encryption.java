/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsassignment;

import java.util.Random;

public class Encryption {

    public static String applySpecialSyntaxOperations(String plainText) {
        StringBuilder intermediateText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);

            if (currentChar == ',' || currentChar == '.') {
                intermediateText.append(currentChar);
            }
            if (Character.isLetter(currentChar)) {
                intermediateText.append(processLetter(currentChar));
            } else if (currentChar == ' ') {
                intermediateText.append("$");
            }
        }

        String inputString = intermediateText.toString();
        String[] words = inputString.split("\\$");

        int n = words.length / 10;
        Random rng = new Random();
        int randNum[] = new int[n];
        for (int i = 0; i < n; i++) {
            randNum[i] = rng.nextInt(words.length - 1);
            String selectedWord = words[randNum[i]];
            String invertedWord = invertWord(selectedWord);
            words[randNum[i]] = invertedWord;
        }

        intermediateText.setLength(0);
        int k = 0;
        for (int j = 0; j < words.length; j++) {
            if (j == randNum[k]) {
                intermediateText.append("(").append(words[j]).append(")");
                if (k != randNum.length - 1) {
                    k++;
                }
            } else {
                intermediateText.append(words[j]);
            }
            if (j < words.length - 1) {
                intermediateText.append("$");
            }
        }
        intermediateText.deleteCharAt(intermediateText.length() - 1);
        return intermediateText.toString();
    }

    public static String processLetter(char currentChar) {
        if (Character.isUpperCase(currentChar)) {
            return "^" + Character.toLowerCase(currentChar);
        } else {
            return String.valueOf(currentChar);
        }
    }

    public static String invertWord(String word) {
        StringBuilder invertedText = new StringBuilder(word).reverse();

        int caretIndex = invertedText.indexOf("^");
        if (caretIndex != -1 && caretIndex > 0) {
            char charInFront = invertedText.charAt(caretIndex - 1);
            invertedText.setCharAt(caretIndex - 1, '^');
            invertedText.setCharAt(caretIndex, charInFront);
        }
        return invertedText.toString();
    }

    public static String encrypt(String intermediateText, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < intermediateText.length(); i++) {
            char currentChar = intermediateText.charAt(i);

            if (Character.isLetter(currentChar)) {
                char encryptedChar = (char) (currentChar + shift);

                if (Character.isUpperCase(currentChar)) {
                    if (encryptedChar > 'Z') {
                        encryptedChar = (char) ('A' + (encryptedChar - 'Z' - 1));
                    }
                } else {
                    if (encryptedChar > 'z') {
                        encryptedChar = (char) ('a' + (encryptedChar - 'z' - 1));
                    }
                }

                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(currentChar);
            }
        }
        return encryptedText.toString() + ".";
    }
}
