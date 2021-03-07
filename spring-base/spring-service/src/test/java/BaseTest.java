import com.xxp.FrameworkApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p> spring boot测试基类 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午3:31
 * @Version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FrameworkApplication.class)
public class BaseTest {
}
