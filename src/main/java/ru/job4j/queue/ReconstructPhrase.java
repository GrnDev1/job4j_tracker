package ru.job4j.queue;

import java.util.Deque;
import java.util.LinkedList;

public class ReconstructPhrase {

    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        Deque<Character> elements = new LinkedList<>(evenElements);
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < evenElements.size(); i++) {
            if (i % 2 == 0) {
                temp.append(elements.poll());
            } else {
                elements.poll();
            }
        }
        return String.valueOf(temp);
    }

    private String getDescendingElements() {
        Deque<Character> elements = new LinkedList<>(descendingElements);
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < descendingElements.size(); i++) {
                temp.append(elements.pollLast());
        }
        return String.valueOf(temp);
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}