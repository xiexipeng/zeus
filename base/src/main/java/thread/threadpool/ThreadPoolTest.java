package thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/24
 * @Time: 下午9:52
 * @Description:
 */
public class ThreadPoolTest {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 100; i++) {
			executorService.execute(new Thread("Mythread_"+i){
				@Override
				public void run() {
					System.out.println("Object");
				}
			});
		}
	}
}