package com.xxp.core.service;

import com.xxp.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> 订单服务 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/5 下午10:32
 * @Version V1.0
 */
@Service
public class OrderService {

    @Autowired
    @Qualifier("accountJdbcTemplate")
    private JdbcTemplate accountJdbcTemplate;

    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    private static final String SQL_UPDATE_DEPOSIT = "update account set deposit = deposit - ? where id = ?";

    private static final String SQL_INSERT_ORDER = "insert into `order` (id, order_no, amount, account_id) values (?, ?, ?, ?)";

    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Order order) {
        accountJdbcTemplate.update(SQL_UPDATE_DEPOSIT, order.getAmount(), order.getAccountId());
        orderJdbcTemplate.update(SQL_INSERT_ORDER, order.getId(), order.getOrderNo(), order.getAmount(), order.getAccountId());
    }
}
