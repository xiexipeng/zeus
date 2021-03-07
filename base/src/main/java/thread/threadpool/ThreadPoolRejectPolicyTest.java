package thread.threadpool;

import java.util.concurrent.*;

/**
 * @author: xiexipeng@u51.com
 * @create: 2020/03/16 17:30:15
 * @description: 线程池不同拒绝策略
 * @Version V1.0
 **/
public class ThreadPoolRejectPolicyTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,60L,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(2),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
//            try {
            final int index = i;
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+":开始执行任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
//            }catch (Exception e){
//                e.printStackTrace();
//            }
        }
        Thread.sleep(8000);
//        for (int i = 0; i < 10; i++) {
////            try {
//            threadPoolExecutor.execute(()->{
//                System.out.println("开始执行任务");
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
////            }catch (Exception e){
////                e.printStackTrace();
////            }
//        }
//        threadPoolExecutor.shutdown();
    }
}
