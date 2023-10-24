package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindIdActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "FindIdAction";
        tracker.add(new Item(name));
        FindIdAction rep = new FindIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        rep.execute(input, tracker);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(name);
    }

    @Test
    public void cancelExecute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "FindIdAction";
        int result = 3;
        tracker.add(new Item(name));
        FindIdAction rep = new FindIdAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(result);
        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find item by id ===" + ln
                + "Заявка с введенным id: " + result + " не найдена." + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(name);
    }
}