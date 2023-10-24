package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindNameActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "FindNameAction";
        tracker.add(new Item(name));
        FindNameAction rep = new FindNameAction(out);
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
        FindNameAction rep = new FindNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(result);
        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + ln
                + "Заявки с именем: " + result + " не найдены." + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(name);
    }
}