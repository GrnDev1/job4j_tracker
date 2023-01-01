package ru.job4j.early;

import static java.lang.Character.isDigit;

public class PasswordValidator {

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        if (!validateToUpperCase(password)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!validateToLowerCase(password)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!isNumeric(password)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!isCharacterOrNumeric(password)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        if (isContainsWords(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }
        return password;
    }

    private static boolean isContainsWords(String password) {
        String passwordToUpperCase = password.toUpperCase();
        String[] array = {"qwerty", "12345", "password", "admin", "user" };
        for (int i = 0; i < array.length; i++) {
            if (passwordToUpperCase.contains(array[i].toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCharacterOrNumeric(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (!isDigit(password.charAt(i)) && (password.charAt(i) < 'A' || password.charAt(i) > 'Z') && (password.charAt(i) < 'a' || password.charAt(i) > 'z')) {
                return true;
            }
        }

        return false;
    }

    private static boolean isNumeric(String password) {
        for (var i = 0; i < password.length(); i++) {
            if (isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean validateToLowerCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) != password.toUpperCase().charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validateToUpperCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                return true;
            }
        }
        return false;
    }
}