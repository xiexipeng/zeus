import com.xxp.core.domain.Order;
import com.xxp.core.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * <p> 订单服务测试用例 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/5 下午11:14
 * @Version V1.0
 */
public class OrderServiceTests extends BaseTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testCreateOrder(){
        Order order = new Order();
        order.setId(2333L);
        order.setOrderNo("X123");
        order.setAmount(new BigDecimal(10));
        order.setAccountId(1L);
        orderService.createOrder(order);
    }
}
