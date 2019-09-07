package com.borenabs.service;

import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.entity.User;

import java.util.List;

public interface ArticleService {

    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

}
