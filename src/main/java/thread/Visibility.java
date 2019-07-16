package thread;

/**
 * <p> 可见性 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/6 下午6:09
 * @Version V1.0
 */
public class Visibility {

    public static boolean stop;

    public static volatile boolean vStop;

    public static void main(String[] args) throws InterruptedException {
//        unVisibility();
//        visibility();
//        visibilityNoVolatile();
//        visibilityNoVolatile100();
        visibilityNoVolatileByIncr();
    }

    public static void unVisibility() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            while (!stop) {

            }
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t1.start();
        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            stop = true;
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t2.start();
    }

    public static void visibility() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            while (!vStop) {

            }
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t1.start();
        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            vStop = true;
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t2.start();
    }


    public static void visibilityNoVolatile() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            while (!stop) {

            }
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t1.start();
        Thread.sleep(10);

        Thread t2 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            stop = true;
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t2.start();
    }



    public static void visibilityNoVolatile100() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            while (!stop) {

            }
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t1.start();
        Thread.sleep(100);

        Thread t2 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            stop = true;
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t2.start();
    }


    public static void visibilityNoVolatileByIncr() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            int i =0;
            while (!stop) {
                System.out.println(i++);
            }
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t1.start();
        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            System.out.printf("Thread %s is running...\n", Thread.currentThread().getName());
            stop = true;
            System.out.printf("Thread %s is end...\n", Thread.currentThread().getName());
        });
        t2.start();
    }
}
