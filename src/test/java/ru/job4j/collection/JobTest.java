package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JobTest {
    @Test
    public void whenCompatorJobAscByName() {
        int rsl = new JobAscByName().compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenCompatorJobJobDescByName() {
        int rsl = new JobAscByName().compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenCompatorJobAscByPriority() {
        int rsl = new JobAscByPriority().compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenCompatorJobDescByPriority() {
        int rsl = new JobDescByPriority().compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenCompatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenCompatorByAscPriorityAndAscName() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        List<Job> list = new ArrayList<>();
        list.add(new Job("Roman", 20));
        list.add(new Job("Igor", 20));
        list.add(new Job("Boris", 30));
        list.add(new Job("Ivan", 30));
        list.sort(new JobAscByPriority().thenComparing(new JobAscByName()));
        List<Job> result = new ArrayList<>();
        result.add(new Job("Igor", 20));
        result.add(new Job("Roman", 20));
        result.add(new Job("Boris", 30));
        result.add(new Job("Ivan", 30));
        assertThat(list).isEqualTo(result);
    }
}