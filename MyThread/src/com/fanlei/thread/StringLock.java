/**
 * @author fanlei
 *
 * 2017年8月31日下午1:43:20
 * MyThread
 */
package com.fanlei.thread;

/**
 *
 */
public class StringLock {
	
	public void method() {
		//synchronized ("字符串常量") {	//导致只能进来一个线程，另外一个线程获取不到
		synchronized ( new String("字符串常量")) {
			try {
				while(true){
					System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
					Thread.sleep(1000);		
					System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final StringLock stringLock = new StringLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				stringLock.method();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				stringLock.method();
			}
		},"t2");
		
		t1.start();
		t2.start();
	}

}
