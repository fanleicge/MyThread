/**
 * @author fanlei
 *
 * 2017年8月31日上午8:39:55
 * MyThread
 */
package com.fanlei.thread;

/**
 *
 */
public class DirtyRead {
	
	private String userName = "fanlei";
	private String password = "123456";
	
	public synchronized void setValue(String userName, String password){
		 
		this.userName = userName;
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.password = password;
		
		System.out.println("setValue最终结果：userName = " + userName + " , password = " + password);
	}
	
	public synchronized void getValue(){
		System.out.println("getValue方法得到：userName = " + this.userName + " , password = " + this.password);
	}
	
	public static void main(String [] args) throws InterruptedException{
		
		DirtyRead dRead = new DirtyRead();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				dRead.setValue("张三", "654321");
			}
		});
		
		
		//t2 与t1用同一个对象，谁先获得锁不一定
//		Thread t2 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				dRead.getValue();
//			}
//		});
		
		t1.start();
		//t2.start();
		//如果不加sleep 主线程会先进入dread,造成脏读，加上sleep t1如果先获得锁，那么主线程要等待锁释放才能取值不会脏读（当然getValue必须加上synchronized）
		Thread.sleep(1000); 
		dRead.getValue();
		
		
		//1.synchronized,它拥有强制原子性的内置锁机制,是一个重入锁,所以在使用synchronized时,当一个线程请求得到一个对象锁后再次请求此对象锁,
		//    可以再次得到该对象锁,就是说在一个synchronized方法/块的内部调用本类的其他synchronized方法/块时，是永远可以拿到锁。
		//2.当线程请求一个由其它线程持有的对象锁时，该线程会阻塞，而当线程请求由自己持有的对象锁时，如果该锁是重入锁,请求就会成功,否则阻塞.

	}
			

}
