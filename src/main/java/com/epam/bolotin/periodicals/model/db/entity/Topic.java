package com.epam.bolotin.periodicals.model.db.entity;

/**
 * @author: Viacheslav Bolotin
 * @date: 30.12.2022
 */
public class Topic extends Entity{
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }
}
