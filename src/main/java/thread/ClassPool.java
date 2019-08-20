package thread;

import java.util.concurrent.Semaphore;

/**
 * <p> 对象池 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/20 下午10:24
 * @Version V1.0
 */
public class ClassPool {

    private static final int MAX_AVALIABLE = 100;

    private final Semaphore avaliable = new Semaphore(MAX_AVALIABLE,true);
}
