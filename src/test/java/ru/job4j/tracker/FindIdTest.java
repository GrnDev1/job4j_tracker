package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.FindId;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.store.MemTracker;
import ru.job4j.tracker.store.Store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindIdTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "FindIdAction";
        tracker.add(new Item(name));
        FindId rep = new FindId(out);
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
        FindId rep = new FindId(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(result);
        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find item by id ===" + ln
                + "Заявка с введенным id: " + result + " не найдена." + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(name);
    }
}