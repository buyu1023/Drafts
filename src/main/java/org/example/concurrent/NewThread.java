package org.example.concurrent;

/**
 * @author buyu_6911
 * @version 2024/10/27 11:35
 * note:
 */
public class NewThread {
    static String s1;
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
            s1 = "111";
        });
        t1.start();
        t1.join();
        System.out.println(s1);
    }
}
