
package com.nixlord.dunzo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecognitionResult {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("clockwiseOrientation")
    @Expose
    private Double clockwiseOrientation;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("lines")
    @Expose
    private List<Line> lines = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Double getClockwiseOrientation() {
        return clockwiseOrientation;
    }

    public void setClockwiseOrientation(Double clockwiseOrientation) {
        this.clockwiseOrientation = clockwiseOrientation;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

}
