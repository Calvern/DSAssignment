/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TestCaesarCipher {

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

        System.out.println("Origin Text: Advise Cao Cao to use The Chain Strategem, which is to chain his battleships with strong iron chains.");
        System.out.println("Starting from now, please enter 'y' for yes and 'n' for no when answering the questions.");

//        System.out.println("Enter a text to be encrypted using cipher: ");
//        originTxt = scanner.next();
        // Encrypting the text
        System.out.println("Enter [y/n] to encrypt the text: ");
        String YOrN = scanner.next();
        while (!YOrN.equals("y") && !YOrN.equals("n")) {
            System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            YOrN = scanner.next();
        }
        if (YOrN.equals("y")) {
            Scanner sc = new Scanner(System.in);
            List<Integer> selectedIndexes = new LinkedList<>();
            System.out.println("Enter the index of the word that you wish to invert (Enter -1 to exit): ");
            int n;
            while (true) {
                try {
                    n = sc.nextInt();
                    if (n == -1) {
                        break; // Exit the loop if -1 is entered
                    }
                    if (n >= 0 && n < originTxt.length()) {
                        selectedIndexes.add(n);
                    } else {
                        System.out.println("Invalid input. Please enter a valid index within the range of 0 to " + (originTxt.length() - 1));
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer index.");
                    sc.next(); // Clear the invalid input from the scanner
                }
                System.out.println("Enter the index of the word that you wish to invert (Enter -1 to exit): ");
            }
            intermediateText = Encryption.applySpecialSyntaxOperations(originTxt, selectedIndexes);
            encryptedText = Encryption.encrypt(intermediateText, shift);
            System.out.println("Encrypted text: " + encryptedText);
        }

        // Decrypting the text
        System.out.println("Enter a text to decrypted using cipher: ");
        String cipherTxt = scanner.next();
        decryptedIntermediateText = Decryption.SpecialSyntaxOperations(cipherTxt);
        decryptedText = Decryption.decrypt(decryptedIntermediateText, shift);
        System.out.println("Decrypted Text: " + decryptedText);

        //AES encryption
        String useAES = "";
        while (!useAES.equals("y") && !useAES.equals("n")) {
            System.out.print("Would you like to use AES encryption? [y/n]: ");
            useAES = scanner.next();
            if (!useAES.equals("y") && !useAES.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }
        boolean AES = useAES.equals("y");
        String AESEncryption = "";
        if (AES) {
            AESEncryption = AESUtils.encrypt(cipherText, secretKey);
            System.out.println("Encrypted Text (AES): " + AESEncryption);
        }

        // Applying Caesar Cipher - &num{}
        String useCC = "";
        while (!useCC.equals("y") && !useCC.equals("n")) {
            System.out.print("Would you like to apply the Caesar Cipher for additional encryption - &num{}? [y/n]: ");
            useCC = scanner.next();
            if (!useCC.equals("y") && !useCC.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }

        // Applying Caesar Cipher
        while (useCC.equals("y")) {
            System.out.print("Enter the start index and end index: ");
            int startIndex = scanner.nextInt();
            int endIndex = scanner.nextInt();

            if (startIndex >= endIndex || startIndex < 0 || endIndex >= cipherText.length()) {
                System.out.println("Error: Invalid indices. Please enter valid start and end indices within the range of 0 to " + (cipherText.length() - 1));
                continue;
            }

            boolean isOverlapping = indexNumMap.entrySet().stream()
                    .anyMatch(entry -> (startIndex >= entry.getKey().getStartIndex() && startIndex <= entry.getKey().getEndIndex())
                    || (endIndex >= entry.getKey().getStartIndex() && endIndex <= entry.getKey().getEndIndex()));

            if (isOverlapping) {
                System.out.println("Error: The index is overlapped. Please re-enter.");
                continue;
            }

            System.out.print("Enter the number for the text inside the \"{...}\" to be subtracted: ");
            int num = scanner.nextInt();

            if (num < 0 || num > 26) {
                System.out.println("Error: The number must be between 0 and 26 (inclusive). Please re-enter.");
                continue;
            }

            indexNumMap.put(new IndexData(startIndex, endIndex, num), num);

            if (!AES) {
                AESEncryption = AESUtils.ApplySpecialSyntaxOperations(cipherText, startIndex, endIndex, num);
                encryptedText = AESEncryption;
            } else {
                AESEncryption = AESUtils.ApplySpecialSyntaxOperations(AESEncryption, startIndex, endIndex, num);
                encryptedText = AESEncryption;
            }

            System.out.println("Current encrypted text with Caesar Cipher: " + encryptedText);
            System.out.print("Would you like to apply the Caesar Cipher for additional encryption - &num{}? [y/n]: ");
            useCC = scanner.next();
        }

        String AESDecryptedText = "";
        System.out.print("Would you like to use AES decryption? [y/n]: ");
        String useDec = scanner.next();
        while (!useDec.equals("y") && !useDec.equals("n")) {
            System.out.print("Would you like to use AES decryption? [y/n]: ");
            useDec = scanner.next();
            if (!useDec.equals("y") && !useDec.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }
        if (useDec.equals("y")) { //Removal of Caesar Cipher - &num{}
            for (Map.Entry<IndexData, Integer> entry : indexNumMap.entrySet()) {
                IndexData indexData = entry.getKey();
                int startIndex = indexData.startIndex;
                int endIndex = indexData.endIndex;
                Integer number = entry.getValue();
//                System.out.println(number);
                encryptedText = AESUtils.SpecialSyntaxOperations(encryptedText, number);
                intermediateText = encryptedText;
                System.out.println("Current decipher text: " + intermediateText);
            }
            if (AES) { //AES Decryption
                AESDecryptedText = AESUtils.decrypt(intermediateText, secretKey);
                System.out.println("Current decrypted text: " + AESDecryptedText);
            }
        }

        //Origin Text
        String showOri = "";
        String oriText = "";
        while (!showOri.equals("y") && !showOri.equals("n")) {
            System.out.print("Would you like to obtain the origin text? [y/n]: ");
            showOri = scanner.next();
            if (!showOri.equals("y") && !showOri.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }

        if (showOri.equals("y")) {
            if (AES) {
                decryptedIntermediateText = Decryption.SpecialSyntaxOperations(AESDecryptedText);
                oriText = Decryption.decrypt(decryptedIntermediateText, shift);
                System.out.println("Origin Text: " + oriText + "\nEnd.");
            } else {
                decryptedIntermediateText = Decryption.SpecialSyntaxOperations(intermediateText);
                oriText = Decryption.decrypt(decryptedIntermediateText, shift);
                System.out.println("Origin Text: " + oriText + "\nEnd.");
            }
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

        public int getStartIndex() {
            return startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public int getNum() {
            return num;
        }

        @Override
        public int compareTo(IndexData other) {
            return Integer.compare(this.startIndex, other.startIndex);
        }
    }
}
//u+7dIvgWRI6ffc5yO3fO6N8fuO04l/HVfsUqcJjZ1Mjao6unonj1cw944xEckRVfGypcoxfTzNmzc1rbu8Y0m8kNCrywyu7um+7r0wAqCTvAUPzJnQtMUKLXhesFaCKIyq/CxNhP6xlfjwvKo8O9+w==
