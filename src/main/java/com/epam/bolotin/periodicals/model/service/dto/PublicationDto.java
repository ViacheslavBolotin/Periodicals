package com.epam.bolotin.periodicals.model.service.dto;

import java.math.BigDecimal;

/**
 * @author: Viacheslav Bolotin
 * @date: 06.01.2023
 */
public class PublicationDto {

    private long id;
    private String title;
    private long topicId;
    private BigDecimal price;
    private String text;

    private String topicName;

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

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}
