package com.khoa.play.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class PasswordUtil {
    public static String createPassword(String clearString) throws Exception {
        if (clearString == null) {
            throw new Exception("empty.password");
        }
        return BCrypt.hashpw(clearString, BCrypt.gensalt());
    }

    public static boolean checkPassword(String candidate, String encryptedPassword) {
        if (candidate == null) {
            return false;
        }
        if (encryptedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(candidate, encryptedPassword);
    }
}
