/**
 * @author fanlei
 *
 * 2017年8月31日下午6:39:03
 * MyThread
 */
package com.fanlei.threadcommunication;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *BlockingQueue：顾名思义阻塞队列，首先它是一个队列，并且支持阻塞的机制，阻塞的放入和得到数据。
 *我们要实现LinkedBlockingQueue下面两个简单的方法put和get和take。
 *put(aObject):把一个对象aobject加到BlockingQueue里，如果BlockQueue没有空间，则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续。
 *take：取走BlockingQueue里排在首位的对象，若BlockingQueue为空，阻断进入等待状态，直到BlockingQueue有新的数据被加入。
 */
public class ImitateQueue {

	// 承载元素的集合
		private final LinkedList<Object> list = new LinkedList<Object>();

		// 计数器进行计数
		private final AtomicInteger count = new AtomicInteger(0);

		// 制定元素的上限和下限
		private final int maxSize;
		private final int minSize = 0;

		// 初始化一个对象用于加锁
		private final Object lock = new Object();

		public ImitateQueue(int maxSize) {
			this.maxSize = maxSize;
		}

		// 把一个对象aobject加到BlockingQueue里，如果BlockQueue没有空间，则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续增加
		public void put(Object obj) {
			synchronized (lock) {
				while (count.get() == maxSize) {
					try {
						// 当队列中数据塞满，线程等待
						lock.wait();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				list.add(obj);
				// 计数器自增
				count.getAndIncrement();
				System.out.println(" 元素 " + obj + " 被添加 ");
				// 唤醒之前等待阻塞的take方法线程取数据
				lock.notify();

			}
		}

		// 取走BlockingQueue里排在首位的对象，若BlockingQueue为空，阻断进入等待状态，直到BlockingQueue有新的数据被加入
		public Object take() {
			Object temp = null;
			synchronized (lock) {
				while (count.get() == minSize) {
					try {
						// 当队列中的元素，取完，该线程等待
						lock.wait();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 计数器递减
				count.getAndDecrement();
				// 取出元素
				temp = list.removeFirst();
				System.out.println(" 元素 " + temp + " 被消费 ");
				// 唤醒之前阻塞的put方法把元素放进去
				lock.notify();
			}
			return temp;
		}

		public int size() {
			return count.get();
		}

		public static void main(String[] args) throws Exception {

			final ImitateQueue m = new ImitateQueue(5);
			m.put("a");
			m.put("b");
			m.put("c");
			m.put("d");
			m.put("e");
			System.out.println("当前元素个数：" + m.size());
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					m.put("h");
					m.put("i");
				}
			}, "t1");

			t1.start();
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Object t1 = m.take();
						System.out.println("被取走的元素为：" + t1);
						Thread.sleep(1000);
						Object t2 = m.take();
						System.out.println("被取走的元素为：" + t2);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "t2");

			// 休眠2秒钟
			TimeUnit.SECONDS.sleep(2);
			t2.start();

		}

}
