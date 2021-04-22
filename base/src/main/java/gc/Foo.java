package gc;

import java.lang.management.ManagementFactory;

/**
 * @Author: xiexipeng
 * @Date: 2019/6/26
 * @Time: 下午10:27
 * @Description:
 */
public class Foo {

	public static void main(String[] args) throws InterruptedException {

		String name = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println(name);
		// get pid
		String pid = name.split("@")[0];
		System.out.println("Pid is:" + pid);
		Thread.sleep(1000000L);
		Long[] array = new Long[256 * 1024 * 1024];
	}
}