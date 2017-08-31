/**
 * @author fanlei
 *
 * 2017年8月31日下午2:15:06
 * MyThread
 */
package com.fanlei.thread;

/**
 *当使用一个对象进行加锁的时候，要注意对象本身发生变化的时候，那么持有的锁就不同。如果对象本身不发生改变，那么依然是同步的，即使是对象的属性发生了变化。
 */
public class ChangeLock {
	
	private  String lock = "lock";

    private void method() {
        synchronized (lock) {
            try {
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "开始");
                System.out.println("lock = " + lock);
                // 这里把锁的内容改变了，因此t1,t2线程基本同时进来，而不是t1休眠2秒后，t2进来
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程 : " + Thread.currentThread().getName() + "结束");
                System.out.println("lock = " + lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final ChangeLock changeLock = new ChangeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }


}
