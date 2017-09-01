/**
 * @author fanlei
 *
 * 2017年9月1日上午9:48:44
 * MyThread
 */
package com.fanlei.threadpool;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ScheduledThreadPool {
	
	public static void main(String [] args) throws InterruptedException{
		
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		
		System.out.println("Current Time = " + new Date());
		
		//定义一个大小为5的任务队列
		BlockingQueue blockingQueue = new LinkedBlockingQueue<>(10);
		
		ProcedureThread procedureThread = new ProcedureThread(blockingQueue);
		scheduledThreadPool.schedule(procedureThread, 1, TimeUnit.SECONDS);
		for(int i = 0; i < 3; i++){
			Thread.sleep(1000);
			WorkThread worker = new WorkThread(blockingQueue);
			scheduledThreadPool.schedule(worker, 1, TimeUnit.SECONDS);
		}
		
		Thread.sleep(30000);
		scheduledThreadPool.shutdown();
		
		while(!scheduledThreadPool.isTerminated()){
			
		}
		System.out.println("Finished all threads");
	}

}
