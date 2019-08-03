
package com.nixlord.dunzo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("recognitionResults")
    @Expose
    private List<RecognitionResult> recognitionResults = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RecognitionResult> getRecognitionResults() {
        return recognitionResults;
    }

    public void setRecognitionResults(List<RecognitionResult> recognitionResults) {
        this.recognitionResults = recognitionResults;
    }

}
