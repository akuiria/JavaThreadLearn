package com.company.jdk.sync.reentrant;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition cond = lock.newCondition();

    @Override
    public void run() {
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": get lock");

            cond.await();
            System.out.println(Thread.currentThread().getName() + ": is going on");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();

            System.out.println(Thread.currentThread().getName() + ": unlock");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition rt = new ReenterLockCondition();

        Thread t = new Thread(rt);

        t.start();
        Thread.sleep(2000);

        lock.lock();
        cond.signal();
        lock.unlock();
    }
}
