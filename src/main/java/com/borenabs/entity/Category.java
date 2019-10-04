package com.borenabs.entity;

import lombok.Data;

@Data
public class Category {
    private Integer categoryId;

    private Integer categoryPid;

    private String categoryName;

    private String categoryDescription;

    private Integer categoryOrder;

    private String categoryIcon;

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Category(){}
    /**
     * 文章数量(非数据库字段)
     */
    private Integer articleCount;
}