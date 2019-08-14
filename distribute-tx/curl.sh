## update account deposit error
curl -X POST -d '{"orderNo":"error1","amount":10,"accountId":1}' -H 'Content-Type:application/json' http://localhost:8081/order/createOrder

## create order error
curl -X POST -d '{"orderNo":"error2","amount":10,"accountId":1}' -H 'Content-Type:application/json' http://localhost:8081/order/createOrder