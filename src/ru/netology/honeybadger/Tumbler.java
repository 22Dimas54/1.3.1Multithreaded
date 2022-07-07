package ru.netology.honeybadger;

import java.util.concurrent.atomic.AtomicBoolean;

public class Tumbler {
    private final static int NUMBER_OF_MOVES = 7;
    private final static int WAIT_PLAYER = 3000;
    private AtomicBoolean tumblerAtomicBoolean = new AtomicBoolean();

    public void makeChange(Boolean aBoolean) {
        System.out.printf("%s взвел тумблер в положение %s\n", Thread.currentThread().getName(), !tumblerAtomicBoolean.getAndSet(aBoolean));
    }

    public void checkTumbler() {
        while (!Thread.currentThread().isInterrupted()) {
            if (tumblerAtomicBoolean.get()) {
                makeChange(false);
            }
        }
    }

    public void moveUser() {
        int count = 0;
        while (count < NUMBER_OF_MOVES) {
            if (!tumblerAtomicBoolean.get()) {
                try {
                    Thread.sleep(WAIT_PLAYER);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                makeChange(true);
                count++;
            }
        }
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().getName() + " прекратил попытки выиграть!");
    }
}
