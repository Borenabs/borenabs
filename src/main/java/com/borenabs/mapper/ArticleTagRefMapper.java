package com.borenabs.mapper;

import com.borenabs.entity.ArticleTagRefKey;

public interface ArticleTagRefMapper {
    int deleteByPrimaryKey(ArticleTagRefKey key);

    int insert(ArticleTagRefKey record);

    int insertSelective(ArticleTagRefKey record);

    int deleteByArticleId(Integer articleId);
}