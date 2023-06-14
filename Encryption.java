/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.Collections;
import java.util.List;

public class Encryption {

    public static String applySpecialSyntaxOperations(String plainText, List<Integer> selectedIndexes) {
        StringBuilder intermediateText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);

            if (Character.isLetter(currentChar)) {
                intermediateText.append(processLetter(currentChar));
            } else if (currentChar == ' ') {
                intermediateText.append("$");
            } else {
                intermediateText.append(currentChar);
            }
        }

        String inputString = intermediateText.toString();
        String[] words = inputString.split("\\$");

        intermediateText.setLength(0);

        for (int i = 0; i < words.length; i++) {
            if (selectedIndexes.contains(i)) {
                if (Collections.frequency(selectedIndexes, i) > 1) {
                    int repetitionCount = Collections.frequency(selectedIndexes, i);
                    StringBuilder repeatedParentheses = new StringBuilder();
                    StringBuilder repeatedReversedParentheses = new StringBuilder();
                    for (int j = 0; j < repetitionCount; j++) {
                        repeatedParentheses.append("(");
                    }

                    String word = words[i];
                    if (!word.startsWith("(")) {
                        String invertedWord = "";
                        for (int j = 0; j < repetitionCount; j++) {
                            invertedWord = invertWord(word);
                            word = invertWord(word);
                        }
                        intermediateText.append(repeatedParentheses).append(invertedWord);
                        words[i] = repeatedParentheses + invertedWord; // Update the word in the words array
                    } else {
                        intermediateText.append(word);
                    }

                    for (int j = 0; j < repetitionCount; j++) {
                        repeatedReversedParentheses.append(")");
                    }
                    intermediateText.append(repeatedReversedParentheses);
                } else {
                    String word = words[i];
                    if (!word.startsWith("(")) {
                        String invertedWord = invertWord(word);
                        intermediateText.append("(").append(invertedWord).append(")");
                        words[i] = "(" + invertedWord + ")"; // Update the word in the words array
                    } else {
                        intermediateText.append(word);
                    }
                }

                selectedIndexes.remove((Integer) i); // Remove the processed index
            } else {
                intermediateText.append(words[i]);
            }

            if (i < words.length - 1) {
                intermediateText.append("$");
            }
        }
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
        return encryptedText.toString();
    }

//    public static void main(String[] args) {
//        String intermediateText = Encryption.applySpecialSyntaxOperations("ab cd ef");
//        String encryptedText = Encryption.encrypt(intermediateText, 7);
//        System.out.println("Encrypted: " + encryptedText);
//    }
}
