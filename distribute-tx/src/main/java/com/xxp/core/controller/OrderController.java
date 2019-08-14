package com.xxp.core.controller;

import com.xxp.core.domain.Order;
import com.xxp.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 订单controller </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/10 下午8:18
 * @Version V1.0
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public void createOrder(@RequestBody Order order){
        orderService.createOrder(order);
    }
}
