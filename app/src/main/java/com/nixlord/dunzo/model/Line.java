
package com.nixlord.dunzo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Line {


    private List<Integer> boundingBox = null;

    private String text;

    private List<Word> words = null;

    public List<Integer> getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(List<Integer> boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

}
