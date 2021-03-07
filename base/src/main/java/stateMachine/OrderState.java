package stateMachine;

/**
 * <p> 订单状态 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/6/28 下午5:09
 * @Version V1.0
 */
public enum  OrderState {

    /**
     * 待支付
     */
    WAIT_PAY,

    /**
     * 支付中
     */
    PAYING,

    /**
     * 已支付
     */
    PAIED,

    /**
     * 已完成
     */
    FINESHED,

    /**
     * 支付失败
     */
    PAY_FAILURE,

    /**
     * 已关闭
     */
//    CLOSED;

    /**
     * 部分支付
      */
    PARTIAL_PAY,

    //退款中
    REFUNDING,

    //退款失败
    REFUNDED_FALIURE,

    //已退款
    REFUNDED;
}
