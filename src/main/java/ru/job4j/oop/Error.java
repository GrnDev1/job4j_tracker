package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.print("Active: " + active + "; ");
        System.out.print("Status: " + status + "; ");
        System.out.println("Message: " + message + "; ");
    }

    public static void main(String[] args) {
        Error error = new Error();
        Error mistake = new Error(true, 2, "Hello");
        error.printInfo();
        mistake.printInfo();
    }
}
