package service;

import com.xxp.FrameworkApplication;
import com.xxp.domin.LockDemo;
import com.xxp.service.LockDemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/16 下午8:13
 * @Version
 */
@SpringBootTest(classes = FrameworkApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LockDemoServiceTest {

    @Autowired
    private LockDemoService lockDemoService;

    @Test
    public void testLockDemo() {
        LockDemo lockDemoById = lockDemoService.getLockDemoById(1L);
        System.out.println(lockDemoById);
    }

    @Test
    public void testDeadLock() throws InterruptedException {
        List<Long> idList = Arrays.asList(1L, 2L);
        Thread t1 = new Thread(() -> {
            Boolean lockDemoList = lockDemoService.getLockDemoList(idList);
            System.out.println(lockDemoList);
        });

        List<Long> idList2 = Arrays.asList(2L, 1L);
        Thread t2 = new Thread(() -> {
            Boolean lockDemoList = lockDemoService.getLockDemoList(idList2);
            System.out.println(lockDemoList);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void testLockDemoUpdate() {
        Integer integer = lockDemoService.updateById(1L);
        System.out.println(integer);
    }

    @Test
    public void testDeadLockUpdate() throws InterruptedException {
        List<Long> idList = Arrays.asList(1L, 2L);
        Thread t1 = new Thread(() -> {
            Boolean lockDemoList = lockDemoService.updateLockDemoList(idList);
            System.out.println(lockDemoList);
        });

        List<Long> idList2 = Arrays.asList(2L, 1L);
        Thread t2 = new Thread(() -> {
            Boolean lockDemoList = lockDemoService.updateLockDemoList(idList2);
            System.out.println(lockDemoList);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
