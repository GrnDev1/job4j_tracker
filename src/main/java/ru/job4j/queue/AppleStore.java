package ru.job4j.queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        Queue<Customer> temp = new LinkedList<>(queue);
        for (int i = 0; i < count - 1; i++) {
            temp.poll();
        }
        return temp.poll().name();
    }

    public String getLastUpsetCustomer() {
        Queue<Customer> temp = new LinkedList<>(queue);
        for (int i = 0; i < count; i++) {
            temp.poll();
        }
        return temp.poll().name();
    }
}