package com.borenabs.mapper;

import com.borenabs.entity.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> categoryList();

    List<Category> categorys();
    /**
     * 获得分类总数
     *
     * @return
     */
    Integer countCategory();

    /**
     * 通过文章id获取分类
     * */
    List<Category> selectArticleCategoryListByArticleId(Integer articleId);
}