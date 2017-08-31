/**
 * @author fanlei
 *
 * 2017年8月31日上午9:10:21
 * MyThread
 */
package com.fanlei.thread;

/**
 *嵌套调用关系synchronized的重入
 */
public class SyncReenTrant {
	
	public synchronized void method1(){
		System.out.println("method1...");
		method2();
	}
	
	public synchronized void method2(){
		System.out.println("method2...");
		method3();
	}
	
	public synchronized void method3(){
		System.out.println("method3...");
	}
	
	public static void main(String [] args){
		final SyncReenTrant sd = new SyncReenTrant();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sd.method1();
			}
		});
		
		t1.start();
	}
}
