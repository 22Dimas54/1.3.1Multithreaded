package ru.netology.honeybadger;

public class Tumbler {
    private final static int NUMBER_OF_MOVES = 7;
    private final static int WAIT_PLAYER = 3000;
    private volatile boolean tumblerAtomicBoolean;

    public void makeChange(Boolean aBoolean) {
        System.out.printf("%s взвел тумблер в положение %s\n", Thread.currentThread().getName(), tumblerAtomicBoolean=aBoolean);
    }

    public void checkTumbler() {
        while (!Thread.currentThread().isInterrupted()) {
            if (tumblerAtomicBoolean) {
                makeChange(false);
            }
        }
    }

    public void moveUser() {
        int count = 0;
        while (count < NUMBER_OF_MOVES) {
            if (!tumblerAtomicBoolean) {
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
