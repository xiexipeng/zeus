package jvm.gc;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiexipeng
 * @Date: 2019/9/20
 * @Time: 上午12:12
 * @Description:
 */
public class StopWorldTest {

	public static class MyThread extends Thread {

		Map<Long, byte[]> map = new HashMap<>();

		@Override
		public void run() {
			try {
				while (true) {
					if (map.size() * 512 / 1024 / 1024 >= 400) {
						map.clear();
						System.out.println("clean map");
					}
					byte[] b1;
					for (int i = 0; i < 100; i++) {
						b1 = new byte[512];
						map.put(System.nanoTime(), b1);
					}
					Thread.sleep(1);
				}
			} catch (Exception e) {

			}
		}
	}

	public static class PrintThread extends Thread {

		public static final long startTime = System.currentTimeMillis();

		@Override
		public void run() {
			try {
				while (true) {
					long t = System.currentTimeMillis() - startTime;
					System.out.println(t / 1000 + "." + t % 1000);
					Thread.sleep(100);
				}
			} catch (Exception e) {

			}
		}
	}

	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		PrintThread printThread = new PrintThread();
		myThread.start();
		printThread.start();
		String s = "ss";
		s.length();
	}

}