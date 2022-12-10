package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book first = new Book("First", 100);
        Book second = new Book("Second", 200);
        Book third = new Book("Third", 300);
        Book fourth = new Book("Clean code", 400);
        Book[] array = new Book[]{first, second, third, fourth};
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].getName() + "-" + array[i].getNumbers());
        }
        System.out.println();
        Book temp = array[0];
        array[0] = array[3];
        array[3] = temp;
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].getName() + "-" + array[i].getNumbers());
        }
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            if ("Clean code".equals(array[i].getName())) {
                System.out.println(array[i].getName() + "-" + array[i].getNumbers());
            }
        }
    }
}
