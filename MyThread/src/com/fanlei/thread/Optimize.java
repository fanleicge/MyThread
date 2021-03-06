/**
 * @author fanlei
 *
 * 2017年8月31日下午1:28:33
 * MyThread
 */
package com.fanlei.thread;

/**
 *使用synchronized声明的方法在某些情况下，是有弊端的，比如A线程调用同步的方法执行一个很长时间的任务,那么B线程就必须等待很长的时间才可以执行，
 *这样情况下可以使用synchronize的去优化代码执行时间，也就是我们通常所说的减小锁的粒度。
 */
public class Optimize {

	public void doLongTimeTask() {
       
		try {

            System.out.println("当前线程开始：" + Thread.currentThread().getName() + ", 正在执行一个较长时间的业务操作，其内容不需要同步");
            Thread.sleep(2000);
            // 使用synchronized代码块减小锁的粒度，提高性能
            synchronized (this) {
                System.out.println("当前线程：" + Thread.currentThread().getName() + ", 执行同步代码块，对其同步变量进行操作");
                Thread.sleep(1000);
            }
            System.out.println("当前线程结束：" + Thread.currentThread().getName() + ", 执行完毕");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       
    	final Optimize otz = new Optimize();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                otz.doLongTimeTask();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                otz.doLongTimeTask();
            }
        }, "t2");
        t1.start();
        t2.start();
    }

}
