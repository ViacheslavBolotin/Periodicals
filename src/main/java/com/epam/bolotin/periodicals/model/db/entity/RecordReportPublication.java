package com.epam.bolotin.periodicals.model.db.entity;

import java.math.BigDecimal;

/**
 * @author: Viacheslav Bolotin
 * @date: 26.01.2023
 */
public class RecordReportPublication extends Entity{

    private String topicName;
    private String title;
    private BigDecimal amount;
    private Long quantity;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ReportPublication{" +
                "topicName=" + topicName +
                ", title='" + title + '\'' +
                ", amount='" + amount +
                ", quantity='" + quantity +
                '}';
    }

}
