package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        DeleteAction rep = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Delete item ===" + ln + "Заявка удалена успешно." + ln);
        assertThat(tracker.findAll().size()).isEqualTo(0);
    }

    @Test
    public void cancelExecute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        DeleteAction rep = new DeleteAction(out);
        Input input = mock(Input.class);
        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Delete item ===" + ln + "Ошибка удаления заявки." + ln);
        assertThat(tracker.findAll().size()).isEqualTo(1);
    }
}