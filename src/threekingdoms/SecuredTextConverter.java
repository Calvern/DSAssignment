/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

/**
 *
 * @author user
 */
public class SecuredTextConverter {

    public static void main(String[] args) {
        String plaintext = "Hello, World!";
        String key = "secretkey";

        String encrypted = encryptText(plaintext, key);
        System.out.println("Encrypted text: " + encrypted);

        //String decrypted = decryptText(encrypted, key);
        //System.out.println("Decrypted text: " + decrypted);
    }

    public static String encryptText(String str, String key) {
        
        key = generateKey(str, key);
        String res = "";

        for (int i = 0; i < str.length(); i++) {
            char c=str.charAt(i);
       
            if(Character.isLetter(c)){
                if(Character.isUpperCase(c)){
                    res+="^";
                 
                }
                int base='a';
                int offset= (Character.toLowerCase(str.charAt(i))+ key.charAt(i)) % 26;
                res+=(char)(base+offset);
            }else if(Character.isSpaceChar(c)){
                res+="$";
            }else{
                res+=str.charAt(i);
            }
            /*if(!Character.isAlphabetic(str.charAt(i))){
                res += str.charAt(i);
                continue;
            }
            int c = (str.charAt(i) + key.charAt(i)) % 26; // mod 26 to convert the number in to range 0 - 25
            c += 'A';
            res += (char)c;
        }
        return res;*/
        }
        return res;
    }

    public static String decryptText(String encrypt, String key) {
        encrypt = encrypt.toUpperCase();
        key = generateKey(encrypt, key);
        String res = "";
        for (int i = 0; i < encrypt.length(); i++) {
            if (!Character.isAlphabetic(encrypt.charAt(i))) {
                res += encrypt.charAt(i);
                continue;
            }
            int c = (encrypt.charAt(i) - key.charAt(i) + 26) % 26;
            c += 'A';
            res += (char) c;
        }
        return res.toLowerCase();
    }

    public static String generateKey(String str, String key) {
        key = key.toUpperCase();
        int s = str.length();
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
}
