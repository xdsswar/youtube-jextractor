package com.github.kotvertolet.pojo.youtubeInnerData;

import com.google.gson.annotations.SerializedName;


public class IndexRange {

    @SerializedName("start")
    private String start;

    @SerializedName("end")
    private String end;

    public void setStart(String start) {
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return
                "IndexRange{" +
                        "start = '" + start + '\'' +
                        ",end = '" + end + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndexRange)) return false;

        IndexRange that = (IndexRange) o;

        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        return end != null ? end.equals(that.end) : that.end == null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}