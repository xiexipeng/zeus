package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> V1.0 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午10:25
 * @Version 重入锁实践
 */
public class ReentrantLockPractie {

    private static ReentrantLock lock = new ReentrantLock();

    private static Runnable createTask() {
        return new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("locked " + Thread.currentThread().getName());
                                Thread.sleep(1000);
                            } finally {
                                lock.unlock();
                                System.out.println("unlocked " + Thread.currentThread().getName());
                            }
                            break;
                        } else {
                            System.out.println("unable to lock " + Thread.currentThread().getName());
                        }
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " is interrupted");
                    }
                }
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(createTask(), "Thread1");
        Thread thread2 = new Thread(createTask(), "Thread2");
        thread1.start();
        thread2.start();
        Thread.sleep(600);
        thread2.interrupt();
    }
}
