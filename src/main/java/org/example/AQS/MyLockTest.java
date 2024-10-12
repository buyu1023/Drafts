package org.example.AQS;

/**
 * @author buyu_6911
 * @version 2024/10/11 23:37
 * note:
 */
public class MyLockTest {
    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 0; i < 3; i++) {
            new Thread(test).start();
        }
    }

   static class Test implements Runnable {
        MyMutexLock lock = new MyMutexLock();
        int num = 0, count = 0;
        @Override
        public void run() {
            System.out.println("This is " + Thread.currentThread().getName() + " trying lock");
            lock.lock();
            try {
                count++;
                if (count < 2) {
                    run();
                }
                System.out.println("This is " + Thread.currentThread().getName() + " going to lock");
                Thread.sleep(100);
                System.out.println("Working : " + ++num);
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
                count--;
            }
        }
    }
}
