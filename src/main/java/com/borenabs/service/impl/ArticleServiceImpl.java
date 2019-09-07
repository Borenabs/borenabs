package com.borenabs.service.impl;

import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.mapper.ArticleMapper;
import com.borenabs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int deleteByPrimaryKey(Integer articleId) {
        return 0;
    }

    @Override
    public int insert(ArticleWithBLOBs record) {
        return 0;
    }

    @Override
    public int insertSelective(ArticleWithBLOBs record) {
        return 0;
    }

    @Override
    public ArticleWithBLOBs selectByPrimaryKey(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public int updateByPrimaryKeySelective(ArticleWithBLOBs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Article record) {
        return 0;
    }
}
