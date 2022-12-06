package ru.job4j.oop;

public class Ball {
    public void tryRun(boolean condition) {
        String message = condition ? "Ball съеден" : "Ball сбежал";
        System.out.println(message);
    }
}

class Fox {
    public void tryEat(Ball ball) {
        ball.tryRun(true);
    }
}

class Hare {
    public void tryEat(Ball ball) {
        ball.tryRun(false);
    }
}

class Wolf {
    public void tryEat(Ball ball) {
        ball.tryRun(false);
    }
}

class BallStory {
    public static void main(String[] args) {
        Ball ball = new Ball();
        Fox fox = new Fox();
        Hare hare = new Hare();
        Wolf wolf = new Wolf();
        fox.tryEat(ball);
        hare.tryEat(ball);
        wolf.tryEat(ball);
    }
}
