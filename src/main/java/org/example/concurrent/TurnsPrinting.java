package org.example.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author buyu_6911
 * @version 2024/10/7 20:43
 * note:
 */
public class TurnsPrinting {
    int i = 1;

    public void print() {
        synchronized (this) {
            while (i <= 100) {
                System.out.println(Thread.currentThread().getName()  + " "+ i);
                i++;
                notifyAll();
                if (i <= 100) {
                    try {
                        wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            notifyAll();
        }
    }

    public static void main(String[] args) {
        TurnsPrinting t = new TurnsPrinting();
        new Thread(t::print).start();
        new Thread(t::print).start();

        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        objects.add(new Object());

    }
}