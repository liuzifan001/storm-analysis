package com.jointsky.openTSDBClient.request;

/**
 * Created by zhaoyulonge 2017/10/26.
 * MyProject
 */
public class Filter {
    private String type;
    private String tagk;
    private String filter;
    private Boolean groupBy = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTagk() {
        return tagk;
    }

    public void setTagk(String tagk) {
        this.tagk = tagk;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Boolean getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(Boolean groupBy) {
        this.groupBy = groupBy;
    }
}
