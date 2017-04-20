package com.expedia.eps.contentsystem.hotelreview.domain;

public class Token {

    private String word;

    private int position;

    public Token(String word, int position) {
        this.word = word;
        this.position = position;
    }

    public Token() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
