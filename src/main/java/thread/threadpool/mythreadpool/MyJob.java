package thread.threadpool.mythreadpool;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/27
 * @Time: 下午7:55
 * @Description: 自定义job
 */
public class MyJob implements Runnable{

	private String name;

	public MyJob(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		try {
			// 模拟任务的执行
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}