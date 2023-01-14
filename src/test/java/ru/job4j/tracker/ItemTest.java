package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    public void whenItemAscByName() {
        List<Item> list = Arrays.asList(
                new Item("Roman"),
                new Item("Boris"),
                new Item("Igor")
        );

        List<Item> expected = Arrays.asList(
                new Item("Boris"),
                new Item("Igor"),
                new Item("Roman")
        );
        list.sort(new ItemAscByName());
        assertThat(list).isEqualTo(expected);
    }

    @Test
    public void whenItemDescByName() {
        List<Item> list = Arrays.asList(
                new Item("Roman"),
                new Item("Boris"),
                new Item("Igor")
        );

        List<Item> expected = Arrays.asList(
                new Item("Roman"),
                new Item("Igor"),
                new Item("Boris")
        );
        list.sort(new ItemDescByName());
        assertThat(list).isEqualTo(expected);
    }

}