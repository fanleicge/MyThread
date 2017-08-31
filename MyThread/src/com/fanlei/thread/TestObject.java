/**
 * @author fanlei
 *
 * 2017年8月30日下午7:38:31
 * MyThread
 */
package com.fanlei.thread;

/**
 *
 */
public class TestObject {
	
	public synchronized  void method1(){
		
		try {
			
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void method2(){
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String [] args){
		
		final TestObject testObject = new TestObject();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				testObject.method1();
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				testObject.method2();
			}
		}, "t2");
		
		t1.start();
		t2.start();
		
		
	}

}
