package thread;

/**
 * <p> 异常线程 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/2 下午8:47
 * @Version V1.0
 */
public class ExceptionThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->{
            int i = 1/0;
        });
        thread.start();
        Thread.sleep(2000);
        System.out.println("....");
    }
}
