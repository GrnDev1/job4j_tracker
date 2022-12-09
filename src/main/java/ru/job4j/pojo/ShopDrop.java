package ru.job4j.pojo;

public class ShopDrop {
    public static Product[] delete(Product[] products, int index) {

        for (int i = index; i < products.length; i++) {
            if (i == products.length - 1) {
                products[i] = null;
                break;
            }
            products[i] = products[i + 1];
        }
        return products;
    }
}