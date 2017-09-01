/**
 * @author fanlei
 *
 * 2017年9月1日上午9:40:25
 * MyThread
 */
package com.fanlei.threadpool;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class WorkThread implements Runnable {
	
	private String command;
	private final BlockingQueue workQueue;
	
	public WorkThread(BlockingQueue workQueue){
		this.workQueue = workQueue;
	}
	
	@Override
	public void run(){
		
		System.out.println(Thread.currentThread().getName() + " Start.Time = " + new Date());
		
		processCommand();
		
		System.out.println(Thread.currentThread().getName() + " End.Time = " + new Date());
	}
	
	private void processCommand(){
		
		while(!workQueue.isEmpty()){
			try {
				
				System. out .println("工作线程" + Thread.currentThread().getName()+ "取到苹果编号为 ：" + workQueue.take());
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public String toString(){
		return this.command;
	}
	
}
