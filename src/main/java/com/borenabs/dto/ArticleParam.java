package com.borenabs.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticleParam {
    /**
     * 文章id
     * */
    private Integer articleId;
    /**
     * 文章标题
     * */
    private String articleTitle;
    /**
     *文章正文
     * */
    private String articleContent;
    /**
     *文章一级分类id
     * */
    private Integer articleParentCategoryId;
    /**
     *文章二级分类id
     * */
    private Integer articleChildCategoryId;
    /**
     *排序
     * */
    private Integer articleOrder;
    /**
     *文章状态
     * */
    private Integer articleStatus;
    /**
     *文章标签id
     * */
    private List<Integer> articleTagIds;

}
