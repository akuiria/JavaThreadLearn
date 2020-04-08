/*
CyclicBarrier:
每有固定数量的线程执行完成后，计数器会归零，重新进行计数
 */
package com.company.jdk.sync.reentrant;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo implements Runnable {
    static CyclicBarrier cyclic = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("5 threads are completed");
        }
    });

    private CyclicBarrier barrier;

    public CyclicBarrierDemo(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getId() + " start work");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId() + " end work");

            barrier.await();

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++){
            exec.submit(new CyclicBarrierDemo(cyclic));
        }

        exec.shutdown();
    }
}
