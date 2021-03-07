package gc;

/**
 * @Author: xiexipeng
 * @Date: 2019/9/16
 * @Time: 下午11:34
 * @Description: 测试线程栈内存大小，设置线程栈大小：-Xss1M
 */
public class TestXss {

	public static class MyThread extends Thread {

		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int i = 0;
		try {
			for (i = 0; i < 10000; i++) {
				new MyThread().start();
			}
		} catch (OutOfMemoryError error) {
			System.out.println("count thread is " + i);
		}
	}
}