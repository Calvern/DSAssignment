/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsassignment;

/**
 *
 * @author tanya
 */
public class Decryption {

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter the encrypted text: ");
//        String encryptedText = scanner.nextLine();
//
//        System.out.print("Enter the shift position: ");
//        int shift = scanner.nextInt();
//
//        String intermediateText = SpecialSyntaxOperations(encryptedText);
//        String decryptedText = decrypt(intermediateText, shift);
//        System.out.println("Decrypted Text: " + decryptedText);
//    }
    public static String SpecialSyntaxOperations(String encryptedText) {
        StringBuilder intermediateText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);

            if (currentChar == '^') {
                intermediateText.append(Character.toUpperCase(encryptedText.charAt(++i)));
            } else if (currentChar == '$') {
                intermediateText.append(' ');
            } else if (currentChar == '(') {
                StringBuilder invertedText = new StringBuilder();

                while (encryptedText.charAt(++i) != ')') {
                    invertedText.append(encryptedText.charAt(i));
                }

                intermediateText.append(invertedText.reverse());
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
