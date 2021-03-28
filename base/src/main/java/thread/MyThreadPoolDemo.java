package thread;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/24 00:18:58
 * @description: 线程池demo, url:https://www.yuque.com/chiang-zrssv/hr87io/ier2mf
 * @Version V1.0
 **/
@Data
public class MyThreadPoolDemo {
    private int core;

    private int maxThreadCount;

    private int capacity;

    private ArrayList<Thread> coreThreads;

    private volatile ArrayList<Thread> otherThreads;

    private ArrayBlockingQueue<Runnable> queue;

    AtomicBoolean coreFullStarted = new AtomicBoolean();

    AtomicInteger index = new AtomicInteger();

    RejectHandler rejectHandler;

    private AtomicInteger unCompleteTask = new AtomicInteger();

    private AtomicBoolean upperLimit = new AtomicBoolean();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Timer threadLifeCycleTask = new Timer();

    private long keepAliveTime;

    public MyThreadPoolDemo(int core, int maxThreadCount, int capacity, long keepAliveTime, RejectHandler rejectHandler) {
        this.core = core;
        this.maxThreadCount = maxThreadCount;
        this.capacity = capacity;
        this.coreThreads = new ArrayList<>();
        this.otherThreads = new ArrayList<>(maxThreadCount - core);
        this.queue = new ArrayBlockingQueue<>(capacity);
        this.rejectHandler = rejectHandler;
        this.keepAliveTime = keepAliveTime;
        threadLifeCycleTask(keepAliveTime);
    }

    private void threadLifeCycleTask(long keepAliveTime) {
        threadLifeCycleTask.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                MyThreadPoolDemo that = MyThreadPoolDemo.this;
                AtomicInteger unCompleteTask = that.getUnCompleteTask();
                /**
                 * 未完成任务小于核心线程数，且其他线程不为空，代表可以进行销毁，并GC回收内存
                 */
                int coreSize = that.getCoreThreads().size();
                int otherSize = that.getOtherThreads().size();
                if (unCompleteTask.get() < coreSize || unCompleteTask.get() < (coreSize + otherSize)) {
                    if (!that.getOtherThreads().isEmpty()) {
                        ArrayList<Thread> temp = new ArrayList<>();
                        for (Thread thread : otherThreads) {
                            Worker worker = (Worker) thread;
                            long currentTimeMillis = System.currentTimeMillis();
                            long time = worker.getLastUpdateTime().getTime();
                            System.out.println(worker.getName() + "当前时间戳=" + currentTimeMillis + "最后更新时间=" + time);
                            boolean recyclable = currentTimeMillis - time > keepAliveTime;
                            if (recyclable) {
                                temp.add(worker);
                            }
                        }
                        synchronized (otherThreads) {
                            System.out.println("即将销毁线程" + temp);
                            that.doTerminate(temp);
                            for (Thread thread : temp) {
                                otherThreads.remove(thread); //help GC
                            }
                        }
                    }
                }
            }
        }, 0L, keepAliveTime);
    }

    public void execute(Runnable task) {
        // 如果剩余容量为零，则不能将更多元素添加到BlockingQueue
        int i = queue.remainingCapacity();
        if (i == 0) {
            // 添加非核心线程
            boolean addSuccess = addOtherThread();
            if (!addSuccess) {
                rejectHandler.handler(this, task);
                System.out.println("队列已满，执行拒绝策略");
                return;
            }
        }
        if (!coreFullStarted.get()) {
            if (index.get() < core) {
                Worker worker = buildWorker();
                worker.setCore(true);
                coreThreads.add(worker);
                coreThreads.get(index.getAndIncrement()).start();
            } else {
                coreFullStarted.compareAndSet(false, true);
            }
        }
        int activeTasks = unCompleteTask.get();
        System.out.println("unCompleteTask = " + activeTasks);
        System.out.println("otherThreads = " + otherThreads.size());
        if (!upperLimit.get()) {
            //未完成任务减半还大于核心数位移4位
            if ((activeTasks >> 1) > (core << 4)) {
                System.out.println("未达到最大线程数，允许添加其他线程");
                addOtherThread();
            }
        } else {
            System.out.println("已达到最大线程数，无法添加其他线程");
        }
        boolean offer = queue.offer(task);
        if (!offer) {
            execute(task);
        } else {
            unCompleteTask.incrementAndGet();
        }

    }

    // 创建非核心线程
    private boolean addOtherThread() {
        synchronized (otherThreads) {
            if (otherThreads.size() < maxThreadCount - core) {
                Worker worker = buildWorker();
                otherThreads.add(worker);
                worker.start();
                System.out.println("核心线程数已满，开始添加其他线程");
                if (otherThreads.size() + coreThreads.size() == maxThreadCount) {
                    upperLimit.compareAndSet(false, true);
                }
                return true;
            }
            return false;
        }
    }

    public void terminate() {
        List<Thread> workers = new ArrayList<>();
        workers.addAll(otherThreads);
        workers.addAll(coreThreads);
        threadLifeCycleTask.cancel();
        doTerminate(workers);
    }

    private void doTerminate(List<Thread> workers) {
        if (workers.isEmpty()) {
            return;
        }
        workers.forEach(e -> e.interrupt());
        workers.clear();
    }


    private Worker buildWorker() {
        Worker worker = new Worker();
        worker.setQueue(queue);
        worker.setUnCompleteTask(unCompleteTask);
        worker.setName(worker.toString());
        return worker;
    }

    private interface RejectHandler {
        void handler(MyThreadPoolDemo threadPoolTests, Runnable task);
    }

    @Data
    private class Worker extends Thread {
        private BlockingQueue<Runnable> queue;

        private AtomicInteger unCompleteTask;

        private boolean core;

        private Date lastUpdateTime = new Date();

        private boolean done;

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            Runnable task = null;
            try {
                while (!thread.isInterrupted()) {
                    task = queue.take();
                    try {
                        done = false;
                        task.run();
                    } finally {
                        done = true;
                        int decrementAndGet = unCompleteTask.decrementAndGet();
                        System.out.println("decrementAndGet=" + decrementAndGet);
                        if (!this.core) {
                            System.out.println("lastUpdateTime修改前=" + lastUpdateTime);
                            lastUpdateTime = new Date();
                            System.out.println("lastUpdateTime修改后=" + lastUpdateTime);
                        }
                    }
                }
            } catch (InterruptedException e) {
                //当非核心线程正好被定时任务终止时恰好接到新任务，那么将任务丢回队列，需要判断是否执行完毕
                if (!this.core && !done) {
                    queue.add(task);
                }
                thread.interrupt();
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {
        MyThreadPoolDemo tests = new MyThreadPoolDemo(0, 4, 60, 5000L, (threadPoolTests, task) -> {
        });
        //和队列容量一致，否则无法唤醒
        CountDownLatch countDownLatch = new CountDownLatch(60);
        for (int i = 0; i < 200; i++) {
            tests.execute(() -> {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello world");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(tests.getCoreThreads());
        System.out.println(tests.getOtherThreads());
        Thread.sleep(12000);
        System.out.println("任务执行完毕，唤醒主线程");
        tests.terminate();
    }
}
