package ru.netology.honeybadger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int NUMBER_OF_MOVES = 7;
        final int WAIT_PLAYER = 2000;
        final int WAIT_TOY = 2000;
        Tumbler tumbler = new Tumbler();
        Thread threadPlayer = new Thread(null, () -> tumbler.moveUser(NUMBER_OF_MOVES, WAIT_PLAYER));
        Thread threadToy = new Thread(null, () -> tumbler.checkTumbler(WAIT_TOY));

        threadPlayer.setName("Поток-пользователь");
        threadToy.setName("Поток-игрушка");

        threadPlayer.start();
        threadToy.start();
    }
}
