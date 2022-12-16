package ru.job4j.poly;

public class PolyUsage {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        Vehicle train = new Train();
        Vehicle bus = new Bus();
        Vehicle[] array = {plane, train, bus};
        for (Vehicle a : array) {
            a.move();
        }
    }
}
