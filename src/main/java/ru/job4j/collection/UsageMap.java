package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("parsentev@yandex.ru", "Arsentev");
        map.put("baev@yandex.ru", "Baev");
        map.put("ivanov@yandex.ru", "Ivanov");
        for (String key : map.keySet()) {
            System.out.print(key + ": " + map.get(key) + "\n");
        }
    }
}
