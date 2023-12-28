package ru.job4j.tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HbmTrackerTest {
    private static Store tracker = new HbmTracker();

    @AfterEach
    public void clearItems() {
        var items = tracker.findAll();
        for (Item item : items) {
            tracker.delete(item.getId());
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item();
        item.setName("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenReplaceItemThenGetSameItem() {
        Item item1 = new Item();
        item1.setName("test1");
        tracker.add(item1);
        Item item2 = new Item();
        item2.setName("test2");
        tracker.replace(item1.getId(), item2);
        Item result = tracker.findById(item1.getId());
        assertThat(result.getName()).isEqualTo(item2.getName());
        assertThat(tracker.findAll().size()).isEqualTo(1);
    }

    @Test
    public void whenReplaceItemThenGetNothing() {
        Item item1 = new Item();
        item1.setName("test1");
        assertThat(tracker.replace(item1.getId(), item1)).isFalse();
    }

    @Test
    public void whenDeleteItemThenGetTrue() {
        Item item1 = new Item();
        item1.setName("test1");
        tracker.add(item1);
        assertThat(tracker.delete(item1.getId())).isTrue();
        assertThat(tracker.findAll().size()).isEqualTo(0);
    }

    @Test
    public void whenDeleteItemThenGetFalse() {
        Item item1 = new Item();
        item1.setName("test1");
        assertThat(tracker.delete(item1.getId())).isFalse();
    }

    @Test
    public void whenFindAllItemThenGetSameList() {
        Item item1 = new Item("1");
        tracker.add(item1);
        List<Item> result = tracker.findAll();
        assertThat(result).isEqualTo(List.of(item1));
    }

    @Test
    public void whenFindAllItemThenGetEmptyList() {
        assertThat(tracker.findAll()).isEmpty();
    }

    @Test
    public void whenFindByNameItemThenGetSame() {
        Item item1 = new Item("1");
        Item item3 = new Item("1");
        tracker.add(item1);
        tracker.add(item3);
        assertThat(tracker.findByName("1")).hasSize(2)
                .isEqualTo(List.of(item1, item3));
    }

    @Test
    public void whenFindByIdItemThenGetSame() {
        Item item1 = new Item("1");
        tracker.add(item1);
        assertThat(tracker.findById(item1.getId()))
                .isEqualTo(item1);
    }
}