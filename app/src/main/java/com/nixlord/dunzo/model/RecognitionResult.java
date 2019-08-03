
package com.nixlord.dunzo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nixlord.dunzo.model.Line;

public class RecognitionResult {


    private Integer page;

    private Double clockwiseOrientation;

    private Integer width;

    private Integer height;

    private String unit;

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
