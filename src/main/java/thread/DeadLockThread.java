package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: xiexipeng
 * @Date: 2019/8/25
 * @Time: 下午10:22
 * @Description: 线程死锁
 */
public class DeadLockThread {

	private  static final ReentrantLock lock1 = new ReentrantLock();
	private static final  ReentrantLock lock2 = new ReentrantLock();

	public static void main(String[] args) {
		new Thread(()->{
			try {
				lock1.lock();
				Thread.sleep(500);
				lock2.lock();
			}catch (InterruptedException e){
				e.printStackTrace();
			}finally {
				lock1.unlock();
			}
		}).start();

		new Thread(()->{
			try {
				lock2.lock();
				Thread.sleep(500);
				lock1.lock();
			}catch (InterruptedException e){
				e.printStackTrace();
			}finally {
				lock2.unlock();
			}
		}).start();
	}
}