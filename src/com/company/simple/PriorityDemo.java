package com.company.simple;

//线程的优先级
public class PriorityDemo {
    public static class HighThread extends Thread{
        static int count = 0;
        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count ++;
                    if(count > 10000000){
                        System.out.println("high thread complete");

                        break;
                    }
                }
            }
        }
    }

    public static class LowThread extends Thread{
        static int count = 0;
        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count ++;
                    if(count > 10000000){
                        System.out.println("low thread complete");

                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread high = new HighThread();
        Thread low = new LowThread();

        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);

        low.start();
        high.start();
    }
}
