package org.example.concurrent;

/**
 * @author buyu_6911
 * @version 2024/10/30 2:29
 * note:
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        syncClass syncClass = new syncClass();

        new Thread(syncClass::waiting).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        new Thread(syncClass::add).start();
    }

    public static class syncClass {
        int i = 0;

        public synchronized void add() {
            System.out.println(++i);
        }

        public synchronized void waiting() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
