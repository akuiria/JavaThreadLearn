package com.company.jdk.sync.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockInterrupt implements Runnable{

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    private int lock;

    public ReenterLockInterrupt(int lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if(lock == 1){
                lock1.lockInterruptibly();

                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    lock2.lockInterruptibly();
                }
            }
            else if(lock == 2){
                lock2.lockInterruptibly();

                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){
                    lock1.lockInterruptibly();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ": 线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReenterLockInterrupt(1));
        Thread t2 = new Thread(new ReenterLockInterrupt(2));

        t1.start();
        t2.start();

        Thread.sleep(2000);

        t2.interrupt();
    }
}
