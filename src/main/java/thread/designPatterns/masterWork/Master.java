package thread.designPatterns.masterWork;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/27
 * @Time: 下午4:18
 * @Description: 主线程，保存、分配任务，保存任务计算结果
 */
public class Master {

	// 任务队列
	protected Queue<Object> workQueue = new ConcurrentLinkedQueue<>();

	// 线程队列
	protected Map<String, Thread> threadMap = new HashMap<>();

	// 结果集
	protected Map<String, Object> resultMap = new ConcurrentHashMap<>();

	public Master(Worker worker, int countWorker) {
		worker.setWorkQueue(workQueue);
		worker.setResultMap(resultMap);
		for (int i = 0; i < countWorker; i++) {
			threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
		}
	}

	public boolean isComplete() {
		for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
			if (entry.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}
		return true;
	}

	public void submit(Object job) {
		workQueue.add(job);
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void execute() {
		for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
			entry.getValue().start();
		}
	}
}