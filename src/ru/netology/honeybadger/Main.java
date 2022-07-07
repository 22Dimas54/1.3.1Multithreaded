package ru.netology.honeybadger;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Tumbler tumbler = new Tumbler();
        Thread threadPlayer = new Thread(null, () -> tumbler.moveUser());
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
