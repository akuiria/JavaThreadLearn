/*
CountDownLatch：
    让线程等待直到倒计时结束，再开始执行
 */
package com.company.jdk.sync.reentrant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try{
            Thread.sleep(3000);
            System.out.println("check complete");

            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++){
            exec.submit(demo);
        }

        end.await();
        System.out.println("fire");
        exec.shutdown();
    }
}
