package com.epam.bolotin.periodicals.model.service.dto;

import java.math.BigDecimal;

/**
 * @author: Viacheslav Bolotin
 * @date: 30.12.2022
 */
public class AccountDto {
    private long id;
    private long userId;
    private BigDecimal amount;
    private String userName;
    private String fullName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + getId() +
                ", userId=" + getUserId() +
                ", user=" + userName +
                ", fullName=" + fullName +
                '}';
    }
}
