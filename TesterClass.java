/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsassignment;

import java.util.Scanner;

public class TesterClass {

    public static void main(String[] args) throws Exception {
        String plainText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
        String secretKey = "0123456789ABCDEF";
        int shift = 7;

        //Basic Decryption
        System.out.println("Basic Decryption & Encryption");
        System.out.println("------------------------------");
        String decryptedIntermediateText = Decryption.SpecialSyntaxOperations(plainText);
        String decryptedText = Decryption.decrypt(decryptedIntermediateText, shift);
        System.out.println("Decrypted Text: " + decryptedText);

        //Basic Encryption
        String intermediateText = Encryption.applySpecialSyntaxOperations(decryptedText);
        String encryptedText = Encryption.encrypt(intermediateText, shift);
        System.out.println("Encrypted Text: " + encryptedText + "\n");

        //Advanced encryption
        System.out.println("Advanced Encryption");
        System.out.println("--------------------");
        String AESEncryption = AESUtils.encrypt(plainText, secretKey);
        System.out.println("Encrypted Text (AES): " + AESEncryption + "\n\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the start index and end index: ");
        int startIndex = scanner.nextInt();
        int endIndex = scanner.nextInt();

        System.out.print("Enter the number for the text inside \"{}\" to be subtracted: ");
        int num = scanner.nextInt();

        //Apply Syntax on selected text - &num{}
        System.out.println("\nSyntax Operation - &num{}");
        System.out.println("---------------------------");
        encryptedText = AESUtils.ApplySpecialSyntaxOperations(AESEncryption, startIndex, endIndex, num);
        System.out.println("Applied Syntax: " + encryptedText);

        //Syntax Operation - &num{}
        intermediateText = AESUtils.SpecialSyntaxOperations(encryptedText, num);
        System.out.println("Removed syntax: " + intermediateText);

        //Advanced Decryption
        System.out.println("\n\nAdvanced Decryption");
        System.out.println("--------------------");
        String AESDecryptedText = AESUtils.decrypt(intermediateText, secretKey);
        System.out.println("Decrypted Text (AES): " + AESDecryptedText);

        //Origin Text
        decryptedIntermediateText = Decryption.SpecialSyntaxOperations(AESDecryptedText);
        String OriText = Decryption.decrypt(decryptedIntermediateText, shift);
        System.out.println("Origin Text: " + OriText);

        scanner.close();
    }
}
