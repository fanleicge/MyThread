/**
 * @author fanlei
 *
 * 2017年8月31日下午2:33:35
 * MyThread
 */
package com.fanlei.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *volatile关键字只有可见性，没有原子性。要实现原子性建议使用atomic类的系列对象，支持原子性操作
 */
public class VolatileNoAtomic extends Thread {

	//如果是 private static volatile int count 不具备原子性，最终的值不是10000
	private static AtomicInteger count = new AtomicInteger(0);

    private static void addCount() {
        for (int i = 0; i < 1000; i++) {
            count.incrementAndGet();
        }
        System.out.println(count);
    }

    public void run() {
        addCount();
    }

    public static void main(String[] args) {

        VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
        for (int i = 0; i < 10; i++) {
            arr[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
    }

}
