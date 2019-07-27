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

		ExecutorService executorService = Executors.newFixedThreadPool(10);
	}
}