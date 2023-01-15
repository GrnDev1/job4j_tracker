package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        for (int i = 0; i < left.length(); i++) {
            if (left.charAt(i) == right.charAt(i)) {
                continue;
            }
            return left.charAt(i) - right.charAt(i);
        }
        return left.length() - right.length();
    }
}