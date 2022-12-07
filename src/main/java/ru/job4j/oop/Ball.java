package ru.job4j.oop;

public class Ball {
    public void tryRun(boolean condition) {
        String message = condition ? "Ball съеден" : "Ball сбежал";
        System.out.println(message);
    }
}