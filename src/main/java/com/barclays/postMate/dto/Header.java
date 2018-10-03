package com.barclays.postMate.dto;

public class Header {

    private String key;
    private String value;
    private String description;

    public Header(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public Header() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
