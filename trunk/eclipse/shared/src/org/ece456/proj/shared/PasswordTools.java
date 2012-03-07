package org.ece456.proj.shared;

import java.util.Arrays;

public class PasswordTools {

    public static long createPasswordHash(String username, String password) {
        String salted = username + password;
        return Arrays.hashCode(salted.toCharArray());
    }
}
