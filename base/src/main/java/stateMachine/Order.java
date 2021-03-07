package stateMachine;

/**
 * <p> 订单类 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/6/28 下午5:12
 * @Version V1.0
 */
public class Order {

    private Long orderId;

    private Long amount;

    private Long paidAmount;

    private OrderState orderState;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
    }
}
