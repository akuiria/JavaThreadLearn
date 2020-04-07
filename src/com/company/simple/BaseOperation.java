/*
* 学习并行必须要知道的概念：
*  同步（synchronous）：等待方法调用结束之后才能继续后面的操作
*  异步（asynchronous）：方法在另一个线程中执行，调用者可以继续后面的操作，如果需要返回结果，异步调用结束后会通知调用者
*
*  并发（concurrency）：多个任务交替执行
*  并行（parallelism）：真正的同时执行
*
*  临界区：多个线程都可以访问的公共资源，一旦被某个线程占用，其他线程只能等待
*  阻塞（blocking）：临界区被占用时，其他线程被挂起
*  非阻塞（non-blocking）：线程不会影响其他线程的工作
*
*  死锁（deadlock）：一个或者多个线程无法获得需要的资源，一直无法执行
*  饥饿（starvation）：有可能在未来解决的死锁（例如高优先级的线程完成了任务）
*  活锁（livelock）：线程之间相互谦让，资源在两个线程之间跳动，导致没有大家任何一个都不能同时拿到所有的资源而正常执行
*
*  并发级别：
*  阻塞：synchronized
*  无饥饿：线程如果有优先级，会导致饥饿
*  无障碍：线程都可以随便访问数据，如果数据出错，必须进行回滚，依赖一个“一致性标志”来实现
*  无锁：要求一个线程可以在有限步之内完成操作。线程不断尝试进行数据的修改，直到修改成功，否则会出现类似饥饿的现象
*  无等待：要求所有线程必须在有限步之内完成。典型结构RCU（Read-Copy-Update）,读取数据不加控制，写入数据时先复制，
*       修改副本中的数据，完成后在合适的时机回写数据
*
*  Amdahl 定律
*           加速比定义：加速比 = 优化前系统耗时 / 优化后系统耗时
*           仅增加 CPU 个数不一定有作用，需要提高系统内并行化模块的比重
*  Gustafson 定律
*           并行化比例大，加速比就是处理器的个数，只要增加处理器，速度就能加快
*
*  Java 的内存模型（JMM）
*   原子性（atomicity）：
*   可见性（visibility）：
*   有序性（ordering）：
*/

package com.company.simple;

public class BaseOperation {
    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("current thread: " + Thread.currentThread()));

        System.out.println("main thread: " + Thread.currentThread());

        t.start();
    }
}
