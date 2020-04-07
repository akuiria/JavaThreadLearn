package com.company.simple;

//线程同步
public class ThreadSync extends Thread{
    static int count = 0;

    private static synchronized void increase(){
        count ++;
    }

    public ThreadSync(String name){
        super.setName(name);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++){
            increase();
            System.out.println(getName() + " invoke");

            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ThreadSync("t1");
        Thread t2 = new ThreadSync("t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);
    }
}
