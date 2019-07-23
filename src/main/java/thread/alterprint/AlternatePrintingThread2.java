package thread.alterprint;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/22
 * @Time: 下午10:45
 * @Description: volatile 实现多个线程交替打印字符串
 */
public class AlternatePrintingThread2 {

	private static volatile int i = 0;

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			while (i < 12) {
				while (i % 3 == 0) {
					System.out.println("A");
					i++;
				}
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			while (i < 12) {
				while (i % 3 == 1) {
					System.out.println("B");
					i++;
				}
			}
		});
		t2.start();

		Thread t3 = new Thread(() -> {
			while (i < 12) {
				while (i % 3 == 2) {
					System.out.println("C");
					i++;
				}
			}
		});
		t3.start();
	}

}