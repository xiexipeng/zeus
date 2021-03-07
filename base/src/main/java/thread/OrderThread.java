package thread;

/**
 * <p> 顺序线程 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/2 下午4:26
 * @Version V1.0
 */
public class OrderThread {

    public static void main(String[] args) throws InterruptedException {
        OrderThread orderThread = new OrderThread();
        Thread t1 = new Thread(()->{
            System.out.println(String.format("Current %s is running, thread state is %s",Thread.currentThread().getName(), Thread.currentThread().isAlive()+""));
            while (true){
                synchronized (Thread.currentThread()){
                    System.out.println("main thread is notify....");
                    Thread.currentThread().notifyAll();
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        Thread t2 = new Thread(()->{
            System.out.println(String.format("Current %s is running",Thread.currentThread().getName()));
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(()->{
            System.out.println(String.format("Current %s is running",Thread.currentThread().getName()));
        });
        t1.start();
//        while (t1.isAlive()){
//
//        }

        t2.start();
//        while (t1.isAlive()){
//
//        }
        t2.join();
        t3.start();
////        while (t1.isAlive()){
////
////        }
        t3.join();
        System.out.printf("sss\n","");
    }

    public static void threadStartAndWait(Thread thread){
        thread.start();
        while (thread.isAlive()){
            synchronized (thread){
                try {
                    thread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
