package thread.threadpool.mythreadpool;

/**
 * @Author: xiexipeng
 * @Date: 2019/7/27
 * @Time: 下午10:03
 * @Description:
 */
public class ThreadPoolTestMain {

    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPool.getThreadPool();
        for (int i = 0; i < 1000; i++) {
            threadPool.start(new MyJob("MyJob_" + i));
        }
//        threadPool.shutdown();
    }
}