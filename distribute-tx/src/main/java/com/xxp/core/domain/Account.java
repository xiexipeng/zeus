package com.xxp.core.domain;

import java.math.BigDecimal;

/**
 * <p> 账户表 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/5 下午10:27
 * @Version V1.0
 */
public class Account {

    private Long id;

    private String name;

    private String phone;

    private BigDecimal deposit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", deposit=" + deposit +
                '}';
    }
}
