package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helpers {
    public static String encryptPassword(String password){
        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            // Crap, no sha1 encryption. Can't happen, except for some strange server setup.
        }

        sha1.reset();
        sha1.update(password.getBytes());
        byte[] result = sha1.digest();

        StringBuilder hexString = new StringBuilder();
        for (int i=0; i<result.length; i++) {
            hexString.append(Integer.toHexString(0xFF & result[i]));
        }
        password = hexString.toString();
        return password;
    }
}
