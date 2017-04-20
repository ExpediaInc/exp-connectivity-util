package com.expedia.eps.contentsystem.hotelreview.domain;

import java.util.List;

public class Entity {

    private List<Token> tokens;

    private String type;

    private double sentiment;

    public Entity(List<Token> tokens, String type, double sentiment) {
        this.tokens = tokens;
        this.type = type;
        this.sentiment = sentiment;
    }

    public Entity() {
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSentiment() {
        return sentiment;
    }

    public void setSentiment(double sentiment) {
        this.sentiment = sentiment;
    }
}
