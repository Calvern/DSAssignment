/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsassignment;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TesterClass {

    public static void main(String[] args) throws Exception {
        TreeMap<IndexData, Integer> indexNumMap = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        String originTxt = "Advise Cao Cao to use The Chain Strategem, which is to chain his battleships with strong iron chains.";
        String cipherText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
        String secretKey = "0123456789ABCDEF";
        int shift = 7;

        String decryptedText = "";
        String encryptedText = "";
        String intermediateText = "";
        String decryptedIntermediateText = "";
        System.out.println("Given the text to encrypt and decrypt.");
        System.out.println("Origin Text: Advise Cao Cao to use The Chain Strategem, which is to chain his battleships with strong iron chains.");
        System.out.println("Encrypted Text: ^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.");
//        System.out.print("Enter 'E/D' to encrypt or decrypt the text: ");
//        String s = scanner.next();
//        if (s.equals("E")) {
        intermediateText = Encryption.applySpecialSyntaxOperations(originTxt);
        encryptedText = Encryption.encrypt(intermediateText, shift);
//        System.out.println("Encrypted Text: " + encryptedText + "\n");

//        } else if (s.equals("D")) {
        decryptedIntermediateText = Decryption.SpecialSyntaxOperations(cipherText);
        decryptedText = Decryption.decrypt(decryptedIntermediateText, shift);
        System.out.println("Decrypted Text: " + decryptedText);
//        }

        //AES encryption
        String AESEncryption = "";
        System.out.println("Starting from now, please enter 'y' for yes and 'n' for no when answering the questions.");
        System.out.print("Do you want to use AES encryption? [y/n]: ");//1
        String useAES = scanner.next();
        boolean AES = false;
        if (useAES.equals("y")) {
            AES = true;
            AESEncryption = AESUtils.encrypt(cipherText, secretKey);
            System.out.println("Encrypted Text (AES): " + AESEncryption);
        }

        System.out.print("Do you want to use Caesar Cipher for further encryption - &num{}? [y/n]:");//2
        String useCC = scanner.next();
        while (useCC.equals("y")) {
            System.out.print("Enter the start index and end index: ");
            int startIndex = scanner.nextInt();
            int endIndex = scanner.nextInt();

            System.out.print("Enter the number for the text inside the \"{...}\" to be subtracted: ");
            int num = scanner.nextInt();
            indexNumMap.put(new IndexData(startIndex, endIndex, num), num);
            AESEncryption = AESUtils.ApplySpecialSyntaxOperations(AESEncryption, startIndex, endIndex, num);
            encryptedText = AESEncryption;
            System.out.print("Do you want to use Caesar Cipher for further encryption - &num{}? [y/n]: ");
            useCC = scanner.next();
        }
        //Applying Caesar Cipher - &num{}
        String AESDecryptedText = "";
        System.out.println("Current encrypted text with Caesar Cipher: " + encryptedText);
        System.out.print("Do you want to use AES decryption? [y/n]: ");//3
        String useDec = scanner.next();
        if (useDec.equals("y")) {
            //Removal of Caesar Cipher - &num{}
            for (Map.Entry<IndexData, Integer> entry : indexNumMap.entrySet()) {
                IndexData indexData = entry.getKey();
                Integer number = entry.getValue();
                encryptedText = AESUtils.SpecialSyntaxOperations(encryptedText, number);
                intermediateText = encryptedText;
            }
            if (AES) { //AES Decryption
                System.out.println("Current decrypted text: " + intermediateText);
                AESDecryptedText = AESUtils.decrypt(intermediateText, secretKey);
                System.out.println("Current decrypted text: " + AESDecryptedText);
            }
        }

        //Origin Text
        String oriText = "";
        System.out.print("Do you want to obtain the origin text? [y/n]: ");//4
        String showOri = scanner.next();
        if (showOri.equals("y")) {
            decryptedIntermediateText = Decryption.SpecialSyntaxOperations(cipherText);
            oriText = Decryption.decrypt(decryptedIntermediateText, shift);
            System.out.println("Origin Text: " + oriText + "\nEnd.");
        } else {
            System.out.println("End.");
        }

        scanner.close();
    }

    static class IndexData implements Comparable<IndexData> {

        int startIndex;
        int endIndex;
        int num;

        public IndexData(int startIndex, int endIndex, int num) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.num = num;
        }

        @Override
        public int compareTo(IndexData other) {
            return Integer.compare(this.startIndex, other.startIndex);
        }
    }
}
//u+7dIvgWRI6ffc5yO3fO6N8fuO04l/HVfsUqcJjZ1Mjao6unonj1cw944xEckRVfGypcoxfTzNmzc1rbu8Y0m8kNCrywyu7um+7r0wAqCTvAUPzJnQtMUKLXhesFaCKIyq/CxNhP6xlfjwvKo8O9+w==
