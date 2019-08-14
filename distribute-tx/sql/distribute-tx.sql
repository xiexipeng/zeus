
-- 账号表
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL,
  `phone` char(11) CHARACTER SET latin1 NOT NULL COMMENT '手机号',
  `deposit` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '账户余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_phone_uindex` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='账户表';


-- 订单表
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(20) NOT NULL COMMENT '订单编号',
  `amount` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '订单金额',
  `account_id` bigint(20) NOT NULL COMMENT '账户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_order_no_uindex` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2334 DEFAULT CHARSET=latin1 COMMENT='订单表'