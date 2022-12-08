package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student alex = new Student();
        alex.setName("Popov Alexandr Sergeevich");
        alex.setGroup(22);
        alex.setDate(2020);
        System.out.println("Student: " + alex.getName() + System.lineSeparator()
                + "Group: " + alex.getGroup() + System.lineSeparator()
                + "Admission date: " + alex.getDate());
    }
}
