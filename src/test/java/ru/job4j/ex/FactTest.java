package ru.job4j.ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactTest {

    @Test
    public void whenFactFirstException() {
        Fact first = new Fact();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    first.calc(-1);
                });
        assertThat(exception.getMessage()).isEqualTo("N could not be less then 0");
    }

    @Test
    public void whenFact5Then120() {
        Fact second = new Fact();
        int argument = 5;
        int expected = 120;
        int result = second.calc(argument);
        assertThat(result).isEqualTo(expected);
    }
}