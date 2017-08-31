/**
 * @author fanlei
 *
 * 2017年8月31日上午10:11:43
 * MyThread
 */
package com.fanlei.thread;

/**
 *2.所以在Java内部，同一线程在调用自己类中其他synchronized方法/块或调用父类的synchronized方法/块都不会阻碍该线程的执行，
 *就是说同一线程对同一个对象锁是可重入的，而且同一个线程可以获取同一把锁多次，也就是可以多次重入。因为java线程是基于“每线程（per-thread）”，
 *而不是基于“每调用（per-invocation）”的（java中线程获得对象锁的操作是以每线程为粒度的，per-invocation互斥体获得对象锁的操作是以每调用作为粒度的）.
 *再来看看重入锁是怎么实现可重入性的，其实现方法是为每个锁关联一个线程持有者和计数器，当计数器为0时表示该锁没有被任何线程持有，
 *那么任何线程都可能获得该锁而调用相应的方法；当某一线程请求成功后，JVM会记下锁的持有线程，并且将计数器置为1；此时其它线程请求该锁，则必须等待；
 *而该持有锁的线程如果再次请求这个锁，就可以再次拿到这个锁，同时计数器会递增；当线程退出同步代码块时，计数器会递减，如果计数器为0，则释放该锁
 */
public class SyncExtends {
	
	static class Father{
		public int i = 10;
		
		public synchronized void operationSup(){
			try{
				i--;
				System.out.println("Father print i = " + i);
				Thread.sleep(100);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	static class Son extends Father{
		public synchronized void operationSub(){
			try{
				while(i > 0){
					i--;
					System.out.println("Son print i = " + i);
					Thread.sleep(100);
					this.operationSup(); //注意是Sup 不是Sub
				}
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String [] args){
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Son sub = new Son();
				sub.operationSub();
			}
		});
		
		t1.start();
	}

}
