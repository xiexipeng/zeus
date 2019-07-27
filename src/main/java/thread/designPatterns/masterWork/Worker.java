package thread.designPatterns.masterWork;

import java.util.Map;
import java.util.Queue;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/27
 * @Time: 下午4:42
 * @Description: 工作线程
 */
public abstract class Worker implements Runnable {

	// 工作队列
	protected Queue<Object> workQueue;

	// 结果集
	protected Map<String,Object> resultMap;

	public void setWorkQueue(Queue<Object> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	abstract protected Object handle(Object input);

	@Override
	public void run() {
		while (true){
			Object input = workQueue.poll();
			if (input==null){
				break;
			}
			Object result = handle(input);
			resultMap.put(Integer.toString(input.hashCode()),result);
		}
	}
}