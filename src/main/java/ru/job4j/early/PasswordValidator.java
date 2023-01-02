package ru.job4j.early;

import static java.lang.Character.*;

public class PasswordValidator {

    public static String validate(String password) {
        boolean validateToUpperCase = false;
        boolean isNumeric = false;
        boolean validateToLowerCase = false;
        boolean isCharacterOrNumeric = false;

        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        for (int i = 0; i < password.length(); i++) {
            if (isLetter(password.charAt(i)) && password.charAt(i) == password.toUpperCase().charAt(i)) {
                validateToUpperCase = true;
            }
            if (isAlphabetic(password.charAt(i)) && password.charAt(i) == password.toLowerCase().charAt(i)) {
                validateToLowerCase = true;
            }
            if (isDigit(password.charAt(i))) {
                isNumeric = true;
            }
            if (!isDigit(password.charAt(i)) && !isLetter(password.charAt(i))) {
                isCharacterOrNumeric = true;
            }
        }
        if (!validateToUpperCase) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!validateToLowerCase) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!isNumeric) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!isCharacterOrNumeric) {
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
}