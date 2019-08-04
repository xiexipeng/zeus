package thread;

/**
 * @Author: xiexipeng
 * @Date: 2019/8/4
 * @Time: 下午9:07
 * @Description: 线程可见性分析
 */
public class Visibility2 {

	private static boolean isExit;

	private void tryExit(){
		if (isExit == !isExit){
			System.exit(0);
		}
	}

	private void swapValue(){
		isExit = !isExit;
	}

	public static void main(String[] args) throws InterruptedException {
		Visibility2 visibility2 = new Visibility2();
		new Thread(()->{
			System.out.println("main thread is running");
			while (true){
				visibility2.tryExit();
			}
		}).start();

		new Thread(()->{
			System.out.println("swap thread is running");
			while (true){
				visibility2.swapValue();
			}
		}).start();
		Thread.sleep(100000);
	}
}