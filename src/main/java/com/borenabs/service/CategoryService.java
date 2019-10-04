package com.borenabs.service;

import com.borenabs.entity.Category;

import java.util.List;

public interface CategoryService {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 分类列表with文章数
     * */
    public List<Category> listCategoryWithArticleCount();

    /**
     * 前台导航栏显示所有分类
     * */
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
