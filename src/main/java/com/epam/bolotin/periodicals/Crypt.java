package com.epam.bolotin.periodicals;

public class Crypt {

    private Crypt() {
        throw new IllegalStateException("Utility class");
    }
    public static boolean checkPassword(String unCrypt, String crypt) {

        return Crypt.hashPassword(unCrypt).equals(crypt);
    }

    public static String hashPassword(String value) {

        StringBuilder sb = new StringBuilder(value);
        StringBuilder result = new StringBuilder();
        for (int i = value.length()-1; i >=0; i--) {
            result.append(sb.codePointAt(i));
        }
        return result.toString();
    }
}
