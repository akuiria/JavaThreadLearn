package com.company.simple;

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){
                    //在线程中加入中断时的处理逻辑
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interrupted");
                        break;
                    }else{
                        System.out.println("running");
                    }

                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("interrupted when sleep");

                        Thread.currentThread().interrupt();
                    }

                    Thread.yield();
                }
            }
        };

        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
