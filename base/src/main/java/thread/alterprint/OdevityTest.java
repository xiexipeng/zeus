package thread.alterprint;

/**
 * <p> 交替打印奇偶数 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/4/17 下午11:06
 * @Version V1.0
 */
public class OdevityTest {

    private static  int n;

    public static void main(String[] args) {
        OdevityTest odevityTest = new OdevityTest();
        Thread t1 = new Thread("thread1") {
            @Override
            public void run() {
                while (n <= 100) {
                    synchronized (odevityTest) {
                        if (n % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + ": " + n);
                            n++;
                            odevityTest.notify();
                        }
                        try {
                            odevityTest.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Thread t2 = new Thread("thread2") {
            @Override
            public void run() {
                while (n <= 100) {
                    synchronized (odevityTest) {
                        if (n % 2 != 0) {
                            System.out.println(Thread.currentThread().getName() + ": " + n);
                            n++;
                            odevityTest.notify();
                        }
                        try {
                            odevityTest.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}
