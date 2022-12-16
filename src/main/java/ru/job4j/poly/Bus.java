package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Drive on the bus");
    }

    @Override
    public void passengers(int count) {
        System.out.println("Количество: " + count);
    }

    @Override
    public double fuel(double count) {
        return count * 10;
    }
}
