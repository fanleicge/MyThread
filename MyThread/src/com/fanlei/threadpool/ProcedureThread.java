/**
 * @author fanlei
 *
 * 2017年9月1日上午11:02:44
 * MyThread
 */
package com.fanlei.threadpool;

import java.sql.Time;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ProcedureThread implements Runnable {
	
	private final BlockingQueue proQueue;
	
	public ProcedureThread(BlockingQueue proQueue){
		this.proQueue = proQueue;
	}
	
	@Override
	public void run(){
		for (int i = 0; i < 10; i++)
        {
			try {
				 System. out .println("生产者生产的苹果编号为 : " + i);  //放入十个苹果编号 为1到10
                 proQueue .put(i);
                 TimeUnit.SECONDS.sleep(1);

                } catch (InterruptedException  e) {
                    // TODO: handle exception
                	e.printStackTrace();

                }             

         }
	}

}
