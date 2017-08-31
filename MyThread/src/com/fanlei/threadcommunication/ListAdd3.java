/**
 * @author fanlei
 *
 * 2017年8月31日下午3:01:04
 * MyThread
 */
package com.fanlei.threadcommunication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用wait、notify线程间通信，没法做到实时通信
 * 使用java.util.concurrent下的CountDownLatch实现实时通信 
 * countDownLatch.countDown();相当于lock.notify()；countDownLatch.await()相当于lock.wait();
 */
public class ListAdd3 {
	private volatile static List list = new ArrayList();

	public void add() {
		list.add("test.......");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {

		final ListAdd3 list2 = new ListAdd3();
		// final Object lock = new Object();
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// synchronized (lock) {
					System.out.println("t1启动..");
					for (int i = 0; i < 10; i++) {
						list2.add();
						System.out.println(
								"当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
						Thread.sleep(500);
						if (list2.size() == 5) {
							System.out.println("已经发出通知..");
							// lock.notify();
							countDownLatch.countDown();
						}
					}
					// }
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// synchronized (lock) {
				System.out.println("t2启动..");
				if (list2.size() != 5) {
					try {
						// lock.wait();
						countDownLatch.await();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(
						"当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
				throw new RuntimeException();
			}
			// }
		}, "t2");
		t2.start();
		t1.start();

	}

}
