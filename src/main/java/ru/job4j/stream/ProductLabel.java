package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        return products.stream()
                .flatMap(Stream::ofNullable)
                .filter(s -> s.getStandard() - s.getActual() >= 0)
                .filter(s -> s.getStandard() - s.getActual() <= 3)
                .map(s -> new Label(s.getName(), s.getPrice() * 0.5f))
                .map(Label::toString)
                .collect(Collectors.toList());
    }
}