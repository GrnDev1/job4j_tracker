package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.FindName;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.store.MemTracker;
import ru.job4j.tracker.store.Store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindNameTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "FindNameAction";
        tracker.add(new Item(name));
        FindName rep = new FindName(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(name);
        rep.execute(input, tracker);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(name);
    }

    @Test
    public void cancelExecute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "FindNameAction";
        String result = "Exception";
        tracker.add(new Item(name));
        FindName rep = new FindName(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(result);
        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + ln
                + "Заявки с именем: " + result + " не найдены." + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(name);
    }
}