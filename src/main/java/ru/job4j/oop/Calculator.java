package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int a) {
        return x + a;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int a) {
        return a - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int a) {
        return sum(a) + multiply(a) + minus(a) + divide(a);
    }

    public static void main(String[] args) {
        System.out.println(sum(5));
        Calculator calculator = new Calculator();
        System.out.println(calculator.multiply(5));
        System.out.println(minus(5));
        System.out.println(calculator.divide(5));
        System.out.println(calculator.sumAllOperation(5));
    }
}