package com.borenabs.service.impl;

import com.borenabs.entity.Category;
import com.borenabs.mapper.ArticleCategoryRefMapper;
import com.borenabs.mapper.CategoryMapper;
import com.borenabs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ArticleCategoryRefMapper articleCategoryRefMapper;
    @Override
    public int deleteByPrimaryKey(Integer categoryId) {
        return 0;
    }

    @Override
    public int insert(Category record) {
        return 0;
    }

    @Override
    public int insertSelective(Category record) {
        return categoryMapper.insertSelective(record);
    }

    @Override
    public Category selectByPrimaryKey(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
        return categoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return 0;
    }

    /**
     * 分类列表with文章数
     * */
    @Override
    public List<Category> listCategoryWithArticleCount() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.categoryList();
            for (int i = 0; i < categoryList.size(); i++) {
                Integer count = articleCategoryRefMapper.countArticleByCategoryId(categoryList.get(i).getCategoryId());
                categoryList.get(i).setArticleCount(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("根据文章获得分类列表失败, cause:{}", e);
        }
        return categoryList;
    }


}
