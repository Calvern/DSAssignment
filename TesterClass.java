/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsassignment;

public class TesterClass {

    public static void main(String[] args) throws Exception {
        String plainText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
        String secretKey = "0123456789ABCDEF";
        int shift = 7;

        // Decryption
        String decryptedIntermediateText = Decryption.SpecialSyntaxOperations(plainText);
        String decryptedText = Decryption.decrypt(decryptedIntermediateText, shift);
        System.out.println("Decrypted Text: " + decryptedText);

        // Encryption
        String intermediateText = Encryption.applySpecialSyntaxOperations(decryptedText);
        String encryptedText = Encryption.encrypt(intermediateText, shift);
        System.out.println("Encrypted Text: " + encryptedText + "\n");

        //Advanced encryption
        String advancedEncryptedText = AESUtils.encrypt(plainText, secretKey);
        System.out.println("Encrypted Text in Advanced: " + advancedEncryptedText);

        //Advanced decryption
        String AdvancedDecryptedText = AESUtils.decrypt(advancedEncryptedText, secretKey);
        System.out.println("Decrypted Text in Advanced: " + decryptedText);
    }
}

//Enter the encrypted text: ^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.
//Enter the shift position: 7
//Decrypted Text: Advise Cao Cao to use The Chain Strategem, which is to chain his battleships with strong iron chains.

