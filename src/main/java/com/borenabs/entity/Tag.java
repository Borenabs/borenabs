package com.borenabs.entity;

import lombok.Data;

@Data
public class Tag {
    private Integer tagId;

    private String tagName;

    private String tagDescription;

    public Tag(Integer tagId) {
        this.tagId = tagId;
    }

    public Tag(){}
}