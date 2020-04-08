package com.company.jdk.sync.reentrant;

import java.util.concurrent.locks.ReentrantLock;

//公平锁
public class FairLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try{
                lock.lock();

                System.out.println(Thread.currentThread().getName() + " get lock");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();

        Thread t1 = new Thread(fairLock, "t1");
        Thread t2 = new Thread(fairLock, "t2");

        t1.start();
        t2.start();
    }
}
