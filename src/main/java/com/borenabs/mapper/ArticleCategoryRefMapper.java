package com.borenabs.mapper;

import com.borenabs.entity.ArticleCategoryRef;

public interface ArticleCategoryRefMapper {

    int insert(ArticleCategoryRef record);

    int insertSelective(ArticleCategoryRef record);

    /**
     * 根据分类ID统计文章数
     * @param categoryId 分类ID
     * @return 文章数量
     */
    int countArticleByCategoryId(Integer categoryId);
}