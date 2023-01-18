package com.epam.bolotin.periodicals.model.db.entity;

import java.math.BigDecimal;

/**
 * @author: Viacheslav Bolotin
 * @date: 30.12.2022
 */
public class Publication extends Entity{
    private long id;
    private String title;
    private long topicId;
    private BigDecimal price;
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topic_id) {
        this.topicId = topic_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
