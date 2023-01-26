package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        return products.stream()
                .filter(s -> s.getStandard() - s.getActual() >= 0)
                .filter(s -> s.getStandard() - s.getActual() <= 3)
                .map(s -> new Label(s.getName(), s.getPrice() / 2).toString())
                .collect(Collectors.toList());
    }
}