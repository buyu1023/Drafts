package org.example;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author buyu_6911
 * @version 2024/10/2 17:26
 * note:
 */
public class WaitNotifyTest {
    private final Queue<String> queue = new LinkedList<>();
    private final int LIMIT = 100;  // 队列容量上限
    private final Object lock = new Object();  // 共享的锁对象

    public void put(String message) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": wake up");
        synchronized(lock) {  // 使用相同的锁对象同步
            // 如果队列已满，等待
            while (queue.size() == LIMIT) {
                lock.wait();  // 释放锁并进入等待状态，直到有空间
            }
            // 添加消息到队列
            queue.add(message);
            System.out.println("Produced: " + message + " size: " +  queue.size());
            // 唤醒消费者
            lock.notifyAll();
        }
    }

    public void take() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": wake up");
        synchronized(lock) {  // 使用相同的锁对象同步
            // 如果队列为空，等待
            while (queue.isEmpty()) {
                lock.wait();  // 释放锁并进入等待状态，直到有数据
            }
            // 从队列中取出消息
            String message = queue.poll();
            System.out.println("Consumed: " + message + " size: " +  queue.size());
            // 唤醒生产者
            lock.notifyAll();
        }
    }
}

class ProducerConsumerExample {
    public static void main(String[] args) {
        WaitNotifyTest drop = new WaitNotifyTest();

        // 生产者线程
        Thread producer1 = new Thread(() -> {
            try {
                for (int i = 100; i < 200; i++) {
                    drop.put("Message " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread producer2 = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    drop.put("Message " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 200; i++) {
                    drop.take();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 启动线程
        producer1.start();
        producer2.start();
        consumer.start();
    }
}