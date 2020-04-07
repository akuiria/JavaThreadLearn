package com.company.simple;

//守护线程
public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("I'm alive");

                try{
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }
}
