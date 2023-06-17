/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class SecuredTextConverter {

    private static final String regex = "&(\\d+)\\{([^}]+)\\}";
    private static final Pattern PATTERN = Pattern.compile(regex);

    public static void start() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                System.out.print("Advanced Encryption\n1.Encryption\n2.Decryption\nEnter your choice: ");
                choice = sc.nextInt();
                if (choice < 1 || choice > 2) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
                sc.nextLine();
            }
        }
        System.out.println();
        if (choice == 1) {
            encrypt();
        } else {
            decrypt();
        }
    }

    private static void encrypt() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String message = "";

        while (true) {
            try {
                System.out.println("Please enter the text you want to encrypt (Please do not enter '^','$', '@' and '()' ). You should also not enter pattern of '&num{text} e.g &5{Hi}:");
                message = sc.nextLine();
                Matcher matcher = PATTERN.matcher(message);
                if (matcher.find()) {
                    throw new IllegalArgumentException();
                }
                if (message.isBlank() || message.contains("^") || message.contains("$") || message.contains("(") || message.contains(")") || message.contains("@")) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
            }
        }
        System.out.println();
        String key = "";
        while (true) {
            try {
                System.out.print("Please enter your secret key: ");
                key = sc.nextLine();
                if (key.isBlank()) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
            }
        }
        // key = generateKey(message, key);
        System.out.println();
        StringBuilder encrypted = new StringBuilder();
        int keyIndex = 0;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    encrypted.append("^");
                }
                int base = 'a';
                int shift = key.charAt(keyIndex++ % key.length());
                int offset = (Character.toLowerCase(c) - base + shift) % 26;
                encrypted.append((char) (base + offset));
            } else if (Character.isDigit(c)) {
                int base = '0';
                int shift = key.charAt(keyIndex++ % key.length());
                int offset = (c - base + shift) % 10;
                encrypted.append((char) (base + offset));
            } else if (Character.isSpaceChar(c)) {
                encrypted.append("$");
            } else if (c == '\t') {
                encrypted.append("@");
            } else {
                encrypted.append(c);
            }
        }

        ArrayList<Integer> duplicate = new ArrayList<>();
        while (true) {
            try {
                System.out.println("Current Encrypted Text: " + encrypted.toString());
                System.out.println("Please enter the range of index where you want to invert text( Enter -1 for index 1 to exit ):");
                System.out.print("Index 1: ");
                int index1 = sc.nextInt();
                if (index1 == -1) {
                    break;
                }
                System.out.print("Index 2: ");
                int index2 = sc.nextInt();
                boolean isValid = index1 >= 0 && index1 < encrypted.length() && index2 >= 0 && index2 < encrypted.length() && index2 >= index1 && !duplicate.contains(index1) && !duplicate.contains(index2);
                if (!isValid) {
                    throw new IllegalArgumentException();
                }
                if (index1 != 0 && encrypted.charAt(index1 - 1) == '^') {
                    index1--;
                }
                if (encrypted.charAt(index2) == '^') {
                    index2++;
                }
                for (int i = index1; i <= index2 + 2; i++) {
                    duplicate.add(i);
                }
                String text = encrypted.substring(index1, index2 + 1);
                encrypted.replace(index1, index2 + 1, inverseText(text));
                encrypted.insert(index1, "(");
                encrypted.insert(index2 + 2, ")");
                System.out.println();
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.toString());
                System.out.println("Invalid Input!!. Please enter again.\n");
                sc.nextLine();
            }

        }
        sc.nextLine();
        System.out.println("");
        ArrayList<Integer> duplicateCaesar = new ArrayList<>();
        while (true) {
            try {
                System.out.println("Current Encrypted Text is :" + encrypted.toString());
                System.out.print("Do you want to continue with Caesar Cipher for a certain range of index? (Y/N): ");
                String ans = sc.nextLine();
                System.out.println();
                if (ans.equalsIgnoreCase("N")) {
                    break;
                } else if (!ans.equalsIgnoreCase("Y")) {
                    throw new IllegalArgumentException();
                }
                System.out.print("Please enter your shifting position: ");
                int shift = sc.nextInt();
                System.out.print("Insert the starting index for the range you want for Caesar Encryption: ");
                int index1 = sc.nextInt();
                System.out.print("Insert the ending index for the range you want for Caesar Encryption: ");
                int index2 = sc.nextInt();

                boolean isValid = index1 >= 0 && index1 < encrypted.length() && index2 >= 0 && index2 < encrypted.length() && index2 >= index1 && !duplicateCaesar.contains(index1) && !duplicateCaesar.contains(index2);
                if (!isValid) {
                    sc.nextLine();
                    throw new IllegalArgumentException();
                }
                String shiftingText = encrypted.substring(index1, index2 + 1);
                String shiftedText = ApplySpecialCondition(shiftingText, shift);
                encrypted.replace(index1, index2 + 1, shiftedText);
                for (int i = index1; i <= index2 + 4; i++) {
                    duplicateCaesar.add(i);
                }
                System.out.println();
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
                sc.nextLine();
            }
        }
        System.out.println("Encrypting.....");
        Thread.sleep(1000);
        System.out.println();
        System.out.println("Encrypted Text: " + encrypted.toString());
    }

    private static String ApplySpecialCondition(String shiftingText, int shift) {
        StringBuilder encrypted = new StringBuilder();
        encrypted.append("&").append(Integer.toString(shift)).append("{");
        encrypted.append(applyEncryptCaeser(shiftingText, shift));
        encrypted.append("}");
        return encrypted.toString();
    }

    private static String applyEncryptCaeser(String shiftingText, int shift) {
        StringBuilder shifted = new StringBuilder();
        for (char c : shiftingText.toCharArray()) {
            if (Character.isLetter(c)) {
                int base = 'a';
                int offset = (c - base + shift) % 26;
                shifted.append((char) (base + offset));
            } else if (Character.isDigit(c)) {
                int base = '0';
                int offset = (c - base + shift) % 10;
                shifted.append((char) (base + offset));
            } else {
                shifted.append(c);
            }
        }
        return shifted.toString();
    }

    private static void decrypt() {
        Scanner sc = new Scanner(System.in);
        String encryptedMessage = "";
        while (true) {
            try {
                System.out.println("Rules:\n1.'^' indicating character after this is capatalized\n2.'&' indicating space\n3.'()' indicating inverted text inside parenthesis.\n4.'@' indicating a tab character\n5.Sequence of &num{text} indicates that all of the characters in text hould be subtracted with num before decrypting it\n\nThe precedence of condition 5 is over condition 3.\nPlease make sure that you use these both conditions properly or else you will not get your desired output\n\nNo spaces, tabs and capital letters are allowed in the text.\n\nUSE THESE SYMBOLS CAUTIOUSLY");
                System.out.print("Please enter the text you want to decrypt : ");
                encryptedMessage = sc.nextLine();
                for (char c : encryptedMessage.toCharArray()) {
                    if (Character.isUpperCase(c) || Character.isSpaceChar(c) || c == '\t') {
                        throw new IllegalArgumentException();
                    }
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
            }
        }
        System.out.println("");
        String key = "";
        while (true) {
            try {
                System.out.print("Please enter your secret key: ");
                key = sc.nextLine();
                if (key.isBlank()) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
            }
        }
        System.out.println("");
        Matcher matcher = PATTERN.matcher(encryptedMessage);
        StringBuilder originalText = new StringBuilder(encryptedMessage);
        while (matcher.find()) {
            int shift = Integer.parseInt(matcher.group(1));
            String shiftedText = matcher.group(2);
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            String deshiftedText = applyDecryptString(shiftedText, shift);
            originalText.replace(startIndex, endIndex, deshiftedText);
            matcher = PATTERN.matcher(originalText);

        }
        String regexInverse = "\\(([^)]+)\\)";
        Pattern patternInverse = Pattern.compile(regexInverse);
        matcher = patternInverse.matcher(originalText);
        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            String inversedText = matcher.group(1);
            originalText.replace(startIndex, endIndex, inverseText(inversedText));
            matcher = patternInverse.matcher(originalText);
        }
        String oriText = originalText.toString();
        StringBuilder decrypted = new StringBuilder();
        StringBuilder reversed = new StringBuilder();
        int keyIndex = 0;
        boolean isUpperCase = false;
        boolean isInverted = false;
        for (char c : oriText.toCharArray()) {

            if (c == '^') {
                isUpperCase = true;
            } else if (c == '(') {
                isInverted = true;
            } else if (c == ')') {
                decrypted.append(reversed.reverse().toString());
                reversed.setLength(0);
                isInverted = false;
            } else if (Character.isLetter(c)) {
                int base = 'a';
                int shift = key.charAt(keyIndex++ % key.length());
                int offset = (c - base - shift) % 26;
                offset = (offset + 26) % 26;
                if (isInverted) {
                    if (isUpperCase) {
                        reversed.append(Character.toUpperCase((char) (base + offset)));
                        isUpperCase = false;
                    } else {
                        reversed.append((char) (base + offset));
                    }
                } else {
                    if (isUpperCase) {
                        decrypted.append(Character.toUpperCase((char) (base + offset)));
                        isUpperCase = false;
                    } else {
                        decrypted.append((char) (base + offset));
                    }
                }

            } else if (Character.isDigit(c)) {
                int base = '0';
                int shift = key.charAt(keyIndex++ % key.length());
                int offset = (c - base - shift) % 10;
                offset = (offset + 10) % 10;
                if (isInverted) {
                    reversed.append((char) (base + offset));
                } else {
                    decrypted.append((char) (base + offset));
                }
                isUpperCase = false;
            } else if (c == '$') {
                if (isInverted) {
                    reversed.append(" ");
                } else {
                    decrypted.append(" ");
                }
                isUpperCase = false;
            }else if(c=='@'){
                if (isInverted) {
                    reversed.append("\t");
                } else {
                    decrypted.append("\t");
                }
            } 
            else {
                if (isInverted) {
                    reversed.append(c);
                } else {
                    decrypted.append(c);
                }
                isUpperCase = false;
            }
        }

        System.out.println("Decrypted Text: " + decrypted.toString());
    }

    private static String applyDecryptString(String shiftingText, int shift) {
        StringBuilder shifted = new StringBuilder();
        for (char c : shiftingText.toCharArray()) {
            if (Character.isLetter(c)) {
                int base = 'a';
                int offset = (c - base - shift) % 26;
                offset = (offset + 26) % 26;
                shifted.append((char) (base + offset));
            } else if (Character.isDigit(c)) {
                int base = '0';
                int offset = (c - base - shift) % 10;
                offset = (offset + 10) % 10;
                shifted.append((char) (base + offset));
            } else {
                shifted.append(c);
            }
        }
        return shifted.toString();
    }

    /*public static String generateKey(String str, String key) {
        int s = countValidCharacters(str);
        int k = key.length();
        if (s == k) {
            return key;
        }
        if (s < k) {
            return key.substring(0, s);
        }
        String newKey = key;
        int i = k;
        while (i < s) {
            newKey += key.charAt(i % k);
            i++;
        }

        return newKey;
    }

    private static int countValidCharacters(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                count++;
            }
        }
        return count;
    }*/
    private static String inverseText(String text) {
        StringBuilder invertedText = new StringBuilder(text).reverse();

        int caretIndex = invertedText.indexOf("^");
        while (caretIndex != -1 && caretIndex > 0) {
            char charInFront = invertedText.charAt(caretIndex - 1);
            invertedText.setCharAt(caretIndex - 1, '^');
            invertedText.setCharAt(caretIndex, charInFront);

            caretIndex = invertedText.indexOf("^", caretIndex + 1);
        }
        return invertedText.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        String plaintext = "Advise Cao Cao to use The Chain Strategem, which is to chain his battleships with strong iron chains.";
        start();
    }
}
