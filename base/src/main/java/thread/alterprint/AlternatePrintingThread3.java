package thread.alterprint;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/22
 * @Time: 下午11:07
 * @Description: AtomicInteger 实现多个线程交替打印字符串
 */
public class AlternatePrintingThread3 {

	private static AtomicInteger x = new AtomicInteger(0);

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			while (x.get() < 10) {
				while (x.get() % 2 == 0) {
					System.out.println("A");
					x.incrementAndGet();
				}
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			while (x.get() < 10) {
				while (x.get() % 2 == 1) {
					System.out.println("B");
					x.incrementAndGet();
				}
			}
		});
		t2.start();
	}
}