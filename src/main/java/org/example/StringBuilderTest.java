package org.example;

/**
 * @author buyu_6911
 * @version 2024/10/5 20:53
 * note:
 */
public class StringBuilderTest{
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1: 正在等待...");
                    lock.wait();  // 进入 WAITING 状态
                    System.out.println("Thread 1: 被唤醒");

//                     检查中断状态
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread 1: 中断标志已设置，退出");
                    }

                    // 执行其他操作
                    System.out.println("Thread 1: 正在执行后续操作");
                } catch (InterruptedException e) {
                    System.out.println("Thread 1: 在等待期间被中断");
                }
            }
        });
        t1.start();
        // 等待2秒后唤醒线程
        Thread.sleep(2000);
        synchronized (lock) {
            lock.notify();  // 唤醒 t1
            System.out.println("尝试中断");
            t1.interrupt();  // 中断已经被唤醒的线程
            Thread.sleep(2000);// 事实上等待主线程释放锁之后才相应中断
        }
    }
}
