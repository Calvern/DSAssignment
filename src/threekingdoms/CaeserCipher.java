/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class CaeserCipher {

    public static void start() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                System.out.print("Caesar Cipher Encryption\n1.Encryption\n2.Decryption\nEnter your choice: ");
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

    private static void encrypt() {
        Scanner sc = new Scanner(System.in);
        String message = "";
        while (true) {
            try {
                System.out.print("Please enter the text you want to encrypt (Please do not enter '^', '$', '@' and '()' ): ");
                message = sc.nextLine();
                if (message.isBlank() || message.contains("^") || message.contains("$") || message.contains("(") || message.contains(")") || message.contains("@")) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
            }
        }
        System.out.println();
        int shift = -1;
        while (true) {
            try {
                System.out.print("Please enter the shifting position: ");
                shift = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
                sc.nextLine();
            }
        }
        System.out.println();
        StringBuilder encrypted = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    encrypted.append("^");
                }

                int base = 'a';
                int offset = (Character.toLowerCase(c) - base + shift) % 26;
                encrypted.append((char) (base + offset));
            } else if (Character.isDigit(c)) {
                int base = '0';
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
        System.out.println();
        System.out.println("Encrypted Text: " + encrypted.toString());
    }

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
        //StringBuilder inversed = new StringBuilder(text).reverse();

        //return inversed.toString();
        //inversed.append(")");
        //return inversed.toString();
    }

    private static void decrypt() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String encryptedMessage = "";
        while (true) {
            try {
                System.out.println("Rules:\n1.'^' indicating character after this is capatalized\n2.'&' indicating space\n3.'()' indicating inverted text inside parenthesis.\n4.'@' indicating a tab character\n\nThus, no spaces, tabs and capital letters are allowed in the text.\n\nUSE THESE SYMBOLS CAUTIOUSLY");
                System.out.print("Please enter the text you want to decrypt : ");
                encryptedMessage = sc.nextLine();
                for (char c : encryptedMessage.toCharArray()) {
                    if (Character.isUpperCase(c) || Character.isSpaceChar(c)||c=='\t') {
                        throw new IllegalArgumentException();
                    }
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
            }
        }
        System.out.println();
        int shift = -1;
        while (true) {
            try {
                System.out.print("Please enter the shifting position: ");
                shift = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again.\n");
                sc.nextLine();
            }
        }
        System.out.println();
        StringBuilder decrypted = new StringBuilder();
        StringBuilder reversed = new StringBuilder();
        boolean isUpperCase = false;
        boolean isInverted = false;
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = encryptedMessage.charAt(i);
            if (c == '^') {
                isUpperCase = true;
            } else if (c == '(') {
                isInverted = true;
            } else if (c == ')') {
                decrypted.append(reversed.reverse().toString());
                reversed.setLength(0);
                isInverted = false;
            } else if (c == '@') {
                decrypted.append("\t");
            } else if (Character.isLetter(c)) {
                int base = 'a';
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
            } else {
                if (isInverted) {
                    reversed.append(c);
                } else {
                    decrypted.append(c);
                }
                isUpperCase = false;
            }
        }
        System.out.println("Decrypting.....");
        System.out.println();
        Thread.sleep(1000);
        System.out.println("Decrypted Text: " + decrypted.toString());
    }

}
