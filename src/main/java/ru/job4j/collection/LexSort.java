package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] temp1 = left.split("\\.");
        String[] temp2 = right.split("\\.");
        int one = Integer.parseInt(temp1[0]);
        int two = Integer.parseInt(temp2[0]);
        return Integer.compare(one, two);
    }
}