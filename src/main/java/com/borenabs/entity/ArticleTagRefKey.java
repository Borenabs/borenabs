package com.borenabs.entity;

import lombok.Data;

@Data
public class ArticleTagRefKey {

    private Integer articleId;

    private Integer tagId;

    public ArticleTagRefKey(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public ArticleTagRefKey() {
    }
}