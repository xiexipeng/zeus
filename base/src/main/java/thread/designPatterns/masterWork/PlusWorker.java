package thread.designPatterns.masterWork;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/27
 * @Time: 下午4:12
 * @Description: 自定义任务
 */
public class PlusWorker extends Worker {


	@Override
	protected Object handle(Object input) {
		Integer i = (Integer) input;
		return i * i * i;
	}
}