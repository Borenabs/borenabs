package com.borenabs.mapper;

import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
}