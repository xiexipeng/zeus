package thread.designPatterns.comsumerProducer;

import java.util.Queue;
import java.util.Random;

/**
 * <p> 生产者 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/4/14 下午2:20
 * @Version
 */
public class Producer implements Runnable {

    private int maxSize;
    private Queue<Integer> queue;

    public Producer(int maxSize, Queue<Integer> queue) {
        this.maxSize = maxSize;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while (maxSize == queue.size()){
                    System.out.println("queue is full");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int i  = random.nextInt();
                queue.add(i);
                System.out.println("queue size is :"+queue.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.notifyAll();
            }
        }
    }

}
