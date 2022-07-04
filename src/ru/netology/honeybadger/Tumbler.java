package ru.netology.honeybadger;

import java.util.concurrent.atomic.AtomicBoolean;

public class Tumbler {
    private AtomicBoolean tumblerAtomicBoolean = new AtomicBoolean();
    private AtomicBoolean gameOver = new AtomicBoolean();

    public void makeChange(Boolean aBoolean) {
        System.out.printf("%s взвел тумблер в положение %s\n", Thread.currentThread().getName(), !tumblerAtomicBoolean.getAndSet(aBoolean));
    }

    public void checkTumbler(int waitToy) {
        while (!Thread.currentThread().isInterrupted()) {
            if (tumblerAtomicBoolean.get()) {
                try {
                    Thread.sleep(waitToy);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                makeChange(false);
            }
            if (gameOver.get()) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " ехидно смеется! Бессмысленная битва окончена!");
            }
        }
    }

    public void moveUser(int numberOfMoves, int waitPlayer) {
        int count = 0;
        while (count < numberOfMoves) {
            if (!tumblerAtomicBoolean.get()) {
                try {
                    Thread.sleep(waitPlayer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                makeChange(true);
                count++;
            }
        }
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().getName() + " прекратил попытки выиграть!");
        gameOver.set(Thread.currentThread().isInterrupted());
    }
}
