package thread.threadpool.mythreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/27
 * @Time: 下午7:59
 * @Description: 线程池
 */
public class ThreadPool {

    private static ThreadPool threadPool = null;

    private List<MyThread> idleThreads;

    private int threadCounter;
    private boolean isShutDown = false;

    private ThreadPool() {
//        this.idleThreads = new Vector<>(5);
        this.threadCounter = 0;
    }

    public synchronized static ThreadPool getThreadPool() {
        if (threadPool == null) {
            threadPool = new ThreadPool();
        }
        return threadPool;
    }

    protected synchronized void repool(MyThread myThread) {
        if (!isShutDown) {
            idleThreads.add(myThread);
        } else {
            myThread.shutDown();
        }
    }

    public synchronized void start(Runnable target) {
        MyThread myThread = null;
        if (idleThreads.size() > 0) {
            int lastSize = idleThreads.size() - 1;
            myThread = idleThreads.get(lastSize);
            idleThreads.remove(lastSize);
            myThread.setTarget(target);
        } else {
            threadCounter++;
            myThread = new MyThread(target, "MyThread_" + threadCounter, threadPool);
            myThread.start();
        }
    }

    public synchronized void shutdown() {
        isShutDown = true;
        for (int i = 0; i < idleThreads.size(); i++) {
            MyThread thread = idleThreads.get(i);
            thread.shutDown();
        }
    }

}