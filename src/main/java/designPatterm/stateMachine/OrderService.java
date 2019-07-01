package designPatterm.stateMachine;

/**
 * <p> 订单服务 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/6/28 下午5:14
 * @Version V1.0
 */
public class OrderService {

    public Order create(){
        Order order = new Order();
        order.setOrderState(OrderState.WAIT_PAY);
        return order;
    }

    /**
     * 支付操作
     * @param order
     */
    public void payV1(Order order){
        if (!order.getOrderState().equals(OrderState.WAIT_PAY)){
            throw new RuntimeException("状态异常");
        }
        order.setOrderState(OrderState.PAYING);
    }

    /**
     * 设置支付结果，支付成功更新为完成，支付失败更新为关闭
     * @param order
     * @param isPaySuccess
     */
    public void doPayV1(Order order, Boolean isPaySuccess){
        if (!order.getOrderState().equals(OrderState.PAYING)){
            throw new RuntimeException("状态异常");
        }
        if (isPaySuccess){
            order.setOrderState(OrderState.PAIED);
        }else {
            order.setOrderState(OrderState.PAY_FAILURE);
        }
    }

    /**
     * 支付操作
     * @param order
     */
    public void payV2(Order order){
        if (!order.getOrderState().equals(OrderState.WAIT_PAY)||!order.getOrderState().equals(OrderState.PARTIAL_PAY)){
            throw new RuntimeException("状态异常");
        }
        order.setOrderState(OrderState.PAYING);
    }

    /**
     * 添加部分支付版本
     * 设置支付结果，支付成功更新为完成，支付失败更新为关闭
     * @param order
     * @param isPaySuccess
     */
    public void doPayV2(Order order, Boolean isPaySuccess){
        if (!order.getOrderState().equals(OrderState.PAYING)||!order.getOrderState().equals(OrderState.PARTIAL_PAY)){
            throw new RuntimeException("状态异常");
        }
        if (isPaySuccess){
            if (order.getPaidAmount().equals(order.getAmount())){
                order.setOrderState(OrderState.PAIED);
            }else {
                order.setOrderState(OrderState.PARTIAL_PAY);
            }
        }else {
            order.setOrderState(OrderState.PAY_FAILURE);
        }
    }

    /**
     * 退款
     * @param order
     */
    public void refund(Order order){
        if (!order.getOrderState().equals(OrderState.PAIED)){
            throw new RuntimeException("状态异常");
        }
        order.setOrderState(OrderState.REFUNDING);
    }

    /**
     * 设置退款结果
     * @param order
     */
    public void doRefund(Order order, Boolean isRefunded){
        if (!order.getOrderState().equals(OrderState.REFUNDING)){
            throw new RuntimeException("状态异常");
        }
        if (isRefunded){
            order.setOrderState(OrderState.REFUNDED);
        }else {
            order.setOrderState(OrderState.REFUNDED_FALIURE);
        }
    }
}
