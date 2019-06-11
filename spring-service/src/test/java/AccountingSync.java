/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/3/30 下午11:01
 * @Version
 */
public class AccountingSync implements Runnable {

    //共享资源(临界资源)
    static int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            increase();
            System.out.println(Thread.currentThread().getId()+"count:"+j+"i:"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AccountingSync instance = new AccountingSync();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
    /**
     * 输出结果:
     * 2000000
     */
}
