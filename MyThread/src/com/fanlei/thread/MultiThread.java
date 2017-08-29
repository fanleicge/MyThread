/**
 * @author fanlei
 *
 * 2017年8月29日下午10:06:11
 * MyThread
 */
package com.fanlei.thread;

/**
 *
 */
public class MultiThread {

private static int num = 0;
	
	// 1.在静态方法上printNum（）加一个synchronized关键字修饰的话，那这个线程调用printNum()获得锁，就是这个类级别的锁。
	// 这时候无论你实例化出多少个对象m1,m2都是没有任何关系的
	public static synchronized void printNum(String tag){	

		if (tag.equals("a")){
			num = 100;
			System.out.println("tag a, set num = 100 over!");
		}else{
			num = 200;
			System.out.println("tag b, set num = 200 over!");
		}
		
		System.out.println("tag " + tag + ", num = " + num);
	}
	
	public static void main(String [] args){
		
		final MultiThread m1 = new MultiThread();
		final MultiThread m2 = new MultiThread();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				m1.printNum("a");
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				m2.printNum("b");
			}
		});
		
		t1.start();
		t2.start();
	}
}
