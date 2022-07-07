package ru.netology.honeybadger;

public class Main {
    final static int NUMBER_OF_MOVES = 7;
    final static int WAIT_PLAYER = 3000;

    public static void main(String[] args) throws InterruptedException {

        Tumbler tumbler = new Tumbler();
        Thread threadPlayer = new Thread(null, () -> tumbler.moveUser(NUMBER_OF_MOVES, WAIT_PLAYER));
        Thread threadToy = new Thread(null, () -> tumbler.checkTumbler());

        threadPlayer.setName("Поток-пользователь");
        threadToy.setName("Поток-игрушка");

        threadPlayer.start();
        threadToy.start();

        threadPlayer.join();
        threadToy.interrupt();
        System.out.println(Thread.currentThread().getName() + " ехидно смеется! Бессмысленная битва окончена!");
    }
}
