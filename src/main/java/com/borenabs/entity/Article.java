package com.borenabs.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Article {
    private Integer articleId;

    private Integer articleUserId;

    private String articleTitle;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Integer articleIsComment;

    private Integer articleStatus;

    private Integer articleOrder;

    private Date articleUpdateTime;

    private Date articleCreateTime;
    /**
     * 非数据库字段
     * */
    List<Category> categoryList;
}