package thread.designPatterns.comsumerProducer;

import java.util.Queue;

/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/4/14 下午2:34
 * @Version
 */
public class Consumer implements  Runnable {

    private int maxSize;
    private Queue<Integer> queue;

    public Consumer(int maxSize, Queue<Integer> queue) {
        this.maxSize = maxSize;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while (queue.isEmpty()){
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("queue is empty");
                }
                int i  = queue.remove();
                System.out.println("comsumer: "+i);
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
