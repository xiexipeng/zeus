package thread.alterprint;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/22
 * @Time: 下午9:52
 * @Description: wait()/notify() 两个线程交替打印字符串
 */
public class AlternatePrintingThread1 {

	private volatile static int i =0;

	public static void main(String[] args) throws InterruptedException {
		AlternatePrintingThread1 test = new AlternatePrintingThread1();
		TestThread t1 = new TestThread("A", test);
		TestThread t2 = new TestThread("B", test);
		t1.start();
		t2.start();
	}

	public static class TestThread extends Thread {

		private final String s;

		private AlternatePrintingThread1 test;

		public TestThread(String s, AlternatePrintingThread1 test) {
			this.s = s;
			this.test = test;
		}

		@Override
		public void run() {
			while (i<10) {
				synchronized (test) {
					System.out.println(s);
					i++;
					test.notify();
					try {
						test.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

}