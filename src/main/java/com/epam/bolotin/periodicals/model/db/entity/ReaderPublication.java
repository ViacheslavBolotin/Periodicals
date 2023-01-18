package com.epam.bolotin.periodicals.model.db.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author: Viacheslav Bolotin
 * @date: 30.12.2022
 */
public class ReaderPublication extends Entity{
    private long id;
    private long publicationId;
    private long userId;
    private BigDecimal price;

    private Timestamp subscribeDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Timestamp subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    @Override
    public String toString() {
        return "ReaderPublication{" +
                "id=" + id +
                ", publicationId=" + publicationId +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}
