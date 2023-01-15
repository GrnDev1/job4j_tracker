package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sum = 0;
        int count = 0;
        for (Pupil currentPupil : pupils) {
            for (Subject currentSubject : currentPupil.subjects()) {
                sum += currentSubject.score();
                count++;
            }
        }
        return sum / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        double sum = 0;
        for (Pupil currentPupil : pupils) {
            for (Subject currentSubject : currentPupil.subjects()) {
                sum += currentSubject.score();
            }
            double averageScore = sum / currentPupil.subjects().size();
            list.add(new Label(currentPupil.name(), averageScore));
            sum = 0;
        }
        return list;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Pupil currentPupil : pupils) {
            for (Subject currentSubject : currentPupil.subjects()) {
                map.put(currentSubject.name(), map.getOrDefault(currentSubject.name(), 0) + currentSubject.score());
            }
        }
        for (String key : map.keySet()) {
            list.add(new Label(key, map.get(key) / map.size()));
        }
        return list;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        int sum = 0;
        for (Pupil currentPupil : pupils) {
            for (Subject currentSubject : currentPupil.subjects()) {
                sum += currentSubject.score();
            }
            list.add(new Label(currentPupil.name(), sum));
            sum = 0;
        }
        list.sort(Comparator.naturalOrder());
        return list.get(list.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Pupil currentPupil : pupils) {
            for (Subject currentSubject : currentPupil.subjects()) {
                map.put(currentSubject.name(), map.getOrDefault(currentSubject.name(), 0) + currentSubject.score());
            }
        }
        for (String key : map.keySet()) {
            list.add(new Label(key, map.get(key)));
        }
        list.sort(Comparator.naturalOrder());
        return list.get(list.size() - 1);
    }
}