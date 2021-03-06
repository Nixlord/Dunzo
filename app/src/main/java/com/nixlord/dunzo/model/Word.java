
package com.nixlord.dunzo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word {

    @SerializedName("boundingBox")
    @Expose
    private List<Integer> boundingBox = null;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("confidence")
    @Expose
    private String confidence;

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

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

}
