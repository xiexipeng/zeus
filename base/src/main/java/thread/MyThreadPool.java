package thread;

import com.xxp.algorithms.queue.BlockQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xiexipeng
 * @create: 2021/03/30 16:43:05
 * @description: 线程池实现
 * @Version V1.0
 **/
@Slf4j
public class MyThreadPool extends AbstractExecutorService {

    private int coreThreadPoolNum;

    private int maxThreadPoolNum;

    private long keepLiveTime;

    private TimeUnit timeUnit;

    private BlockingQueue<Runnable> workQueue;

    private HashSet<Worker> workers = new HashSet();

    private final AtomicInteger workNum = new AtomicInteger(0);

    public MyThreadPool(int coreThreadPoolNum, int maxThreadPoolNum, long keepLiveTime, TimeUnit timeUnit, BlockingQueue<Runnable> workQueue) {
        this.coreThreadPoolNum = coreThreadPoolNum;
        this.maxThreadPoolNum = maxThreadPoolNum;
        this.keepLiveTime = keepLiveTime;
        this.timeUnit = timeUnit;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable command) {
        if (command == null) {
            log.warn("runnable task is null!");
            throw new RuntimeException("runnable task is null!");
        }
        int ctl = workNum.get();
        if (ctl < coreThreadPoolNum) {
            // TODO new worker
        } else if (!workQueue.offer(command)) {
            if (ctl < maxThreadPoolNum) {
                // TODO new worker
            }else {
                // TODO reject
            }
        }
    }

    private void addWork(Runnable command, boolean isCoreThread){
        Worker worker = new Worker(command);
        workers.add(worker);
        worker.currentThread.start();
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    private static class Worker implements Runnable {

        final Thread currentThread;
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
            this.currentThread = new Thread(this);
        }

        @Override
        public void run() {
            task.run();
        }
    }
}
