/**
 * @author fanlei
 *
 * 2017年9月1日上午8:49:33
 * MyThread
 */
package com.fanlei.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ThreadPool {
	
	private static void startThreadPool(ExecutorService threadPool){
		
		for(int i = 1; i < 5; i++){
			final int taskId = i;
			threadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(int i = 1; i < 5; i++){
						
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("当前线程 : " + Thread.currentThread().getName() + "第" + taskId + "次任务的第" + i + "次执行");
					}
				}
			});
		}
		threadPool.shutdown();// 关闭线程池
	}
	
	public static void main(String [] args){
		
		//创建可容纳三个线程的线程池
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		
		// 线程池的大小会根据执行的任务数动态分配  
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();  
		
		// 创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();  
		
		// 效果类似于Timer定时器  
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);


		//startThreadPool(fixedThreadPool);
		//startThreadPool(cachedThreadPool);
//		for(int i = 0; i < 2; i++){
//			startThreadPool(singleThreadPool);
//		}
//		singleThreadPool.shutdown(); //循环调用的话也得在最后关闭线程池
//		fixedThreadPool.shutdown();
		
		startThreadPool(scheduledThreadPool);
		
	}

}
