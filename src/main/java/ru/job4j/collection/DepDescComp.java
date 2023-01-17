package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] left = o1.split("/");
        String[] right = o2.split("/");
        for (int i = 0; i < Math.min(left.length, right.length); i++) {
            String tempLeft = left[i];
            String tempRight = right[i];
            for (int j = 0; j < tempLeft.length(); j++) {
                int compare = Character.compare(tempRight.charAt(j), tempLeft.charAt(j));
                if (i > 0 && compare != 0) {
                    return Character.compare(tempLeft.charAt(j), tempRight.charAt(j));
                } else if (compare != 0) {
                    return compare;
                }
            }
        }
        return Integer.compare(o1.length(), o2.length());
    }
}