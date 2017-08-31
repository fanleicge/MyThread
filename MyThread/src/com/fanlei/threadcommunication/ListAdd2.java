/**
 * @author fanlei
 *
 * 2017年8月31日下午2:51:02
 * MyThread
 */
package com.fanlei.threadcommunication;

import java.util.ArrayList;
import java.util.List;

/**
 *wait和notify必须配合synchronized关键字使用
 *wait方法释放锁，notify方法不释放锁
 */
public class ListAdd2 {
	private volatile static List list = new ArrayList();

    public void add() {
        list.add("test.......");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd2 list2 = new ListAdd2();
        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        System.out.println("t1启动..");
                        for (int i = 0; i < 10; i++) {
                            list2.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                            Thread.sleep(500);
                            if (list2.size() == 5) {
                                System.out.println("已经发出通知..");
                                lock.notify();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("t2启动..");
                    if (list2.size() != 5) {
                        try {
                            //t2线程，拿到了锁，但是size不等于5,所以lock.wait(),释放了锁，然后t1得到t2释放的锁。
                            System.out.println("size() != 5,t2 wait释放锁！..");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                    throw new RuntimeException();
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }

}
