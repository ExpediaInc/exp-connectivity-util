package com.expedia.eps.contentsystem.hotelreview.domain;

import java.util.List;

public class Result {

    private String text;
    private List<Entity> entities;

    public Result(String text, List<Entity> entities) {
        this.text = text;
        this.entities = entities;
    }

    public Result() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
