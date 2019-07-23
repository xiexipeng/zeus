package thread.module;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/4/14 下午2:29
 * @Version
 */
public class TestMain {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Producer producer = new Producer(10,queue);
        Consumer consumer = new Consumer(8,queue);
        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);
        thread1.start();
        thread2.start();

    }
}
