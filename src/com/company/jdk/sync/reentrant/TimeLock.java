package com.company.jdk.sync.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try{
            if(lock.tryLock(2, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName() +  " get lock");
                Thread.sleep(3000);
            }else{
                System.out.println(Thread.currentThread().getName() +  " get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
                System.out.println(Thread.currentThread().getName() +  " complete, unlock");
            }else{
                System.out.println(Thread.currentThread().getName() +  " different thread");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeLock time = new TimeLock();

        Thread t1 = new Thread(time, "t1");
        Thread t2 = new Thread(time, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("over");
    }
}
