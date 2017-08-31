/**
 * @author fanlei
 *
 * 2017年8月31日上午10:27:50
 * MyThread
 */
package com.fanlei.thread;

/**
 *1.这里的对象锁只有一个,就是child对象的锁,当执行child.doSomething时，该线程获得child对象的锁，
 *在doSomething方法内执行doAnotherThing时再次请求child对象的锁，因为synchronized是重入锁，所以可以得到该锁，
 *继续在doAnotherThing里执行父类的doSomething方法时第三次请求child对象的锁，同理可得到，如果不是重入锁的话，那这后面这两次请求锁将会被一直阻塞，
 *从而导致死锁
 */
public class Son extends Father {
	
	public synchronized void doSomething (){
		System.out.println("child.doSomething()");
		
		doAnotherThing();
	}
	
	private synchronized void doAnotherThing(){
		super.doSomething();
		System.out.println("child.doAnotherThing()");
	}
	
	public static void main(String [] args){
		Son child = new Son();
		child.doSomething();
	}

}
 
class Father{
	public synchronized void doSomething(){
		System.out.println("father.doSomething()");
	}
}
