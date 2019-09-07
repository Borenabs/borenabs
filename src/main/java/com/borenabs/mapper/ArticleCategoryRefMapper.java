package com.borenabs.mapper;

import com.borenabs.entity.ArticleCategoryRef;

public interface ArticleCategoryRefMapper {
    int insert(ArticleCategoryRef record);

    int insertSelective(ArticleCategoryRef record);
}