/*
重入锁：显示控制加锁和释放锁

lock():获得锁，如果锁已经被占用，则等待

lockInterruptibly():获得锁，但优先响应中断

tryLock():尝试获得锁，不等待，直接返回

tryLock(time, unit):在给定的时间内尝试获得锁

unLock():释放锁
 */
package com.company.jdk.sync.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class BaseReenterLock implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();

    private static int count = 0;

    @Override
    public void run() {
        for(int j = 0; j < 20; j++){
            lock.lock();

            try{
                count++;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new BaseReenterLock());
        Thread t2 = new Thread(new BaseReenterLock());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);
    }
}
