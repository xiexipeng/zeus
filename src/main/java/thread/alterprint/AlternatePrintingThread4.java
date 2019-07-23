package thread.alterprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/22
 * @Time: 下午11:10
 * @Description: lock 实现多个线程交替打印字符串
 */
public class AlternatePrintingThread4 {

	private static ReentrantLock lock = new ReentrantLock();

	private static volatile int i;

	public static void main(String[] args) {
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		TestThread t1 = new TestThread("A", lock, condition1, condition2);
		t1.start();
		TestThread t2 = new TestThread("B", lock, condition2, condition3);
		t2.start();
		TestThread t3 = new TestThread("C", lock, condition3, condition1);
		t3.start();
	}

	static class TestThread extends Thread {

		private final String s;
		private Lock lock;
		private Condition condition;
		private Condition nextCondition;

		public TestThread(String s, Lock lock, Condition condition, Condition nextCondition) {
			this.s = s;
			this.lock = lock;
			this.condition = condition;
			this.nextCondition = nextCondition;
		}

		@Override
		public void run() {
			lock.lock();
			try {
				while (i < 12) {

					System.out.println(s);
					i++;
					nextCondition.signal();
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}finally {
				lock.unlock();
			}
		}
	}
}