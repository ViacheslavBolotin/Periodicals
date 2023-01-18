package com.epam.bolotin.periodicals.model.db.entity;

import java.math.BigDecimal;

/**
 * @author: Viacheslav Bolotin
 * @date: 30.12.2022
 */
public class Account extends Entity{
    private long id;
    private long userId;
    private BigDecimal amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
