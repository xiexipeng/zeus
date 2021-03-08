package jvm.gc;

/**
 * @Author: xiexipeng
 * @Date: 2019/9/21
 * @Time: 下午9:39
 * @Description:
 */
public class PutInEden {

	public static void main(String[] args) throws InterruptedException {
		byte[] b1, b2, b3, b4;
		b1 = new byte[1024 * 1024];
		b2 = new byte[1024 * 1024];
		b3 = new byte[1024 * 1024];
		b4 = new byte[1024 * 1024];
		Thread.sleep(1000000);
	}
}