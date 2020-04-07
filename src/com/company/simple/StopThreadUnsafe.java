package com.company.simple;
/*
终止线程不推荐使用stop方法
 */
public class StopThreadUnsafe {
    public static final User u = new User();
    public static class User{
        private int id;
        private String name;

        public User(){
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ChangeObjectThread extends Thread{
        //增加一个停止的标志和方法
        volatile boolean stop = false;

        public void stopMe(){
            stop = true;
        }

        @Override
        public void run() {
            while(true){
                //检测是否被停止
                if(stop){
                    System.out.println("exit by stop me");
                    break;
                }

                synchronized (u){
                    int v = (int)(System.currentTimeMillis() / 1000);
                    u.setId(v);

                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }else{
                        System.out.println("data is correct");
                    }
                }
                Thread.yield();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();

        while(true){
            //Thread t = new ChangeObjectThread();
            ChangeObjectThread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(1500);
            //t.stop();
            t.stopMe();
        }
    }
}
