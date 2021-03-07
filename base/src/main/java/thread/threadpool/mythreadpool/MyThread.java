package thread.threadpool.mythreadpool;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/22
 * @Time: 下午11:53
 * @Description: 线程池中的线程
 */
public class MyThread extends Thread {

    private ThreadPool threadPool;

    private Runnable target;

    // 线程池是否关闭
    private boolean isShoutDown = false;

    // 任务是否执行完毕
    private boolean isIdle = false;

    public MyThread(Runnable target, String name, ThreadPool threadPool) {
        super(name);
        this.target = target;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while (!isShoutDown) {
            isIdle = false;
            if (target != null) {
                target.run();
            }
            isIdle = true;
            threadPool.repool(this);
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isIdle = false;
        }
    }

    public synchronized void setTarget(Runnable target) {
        this.target = target;
        notifyAll();
    }

    public synchronized void shutDown() {
        isShoutDown = true;
        notifyAll();
    }
}