package com.borenabs.service.impl;

import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.entity.Category;
import com.borenabs.mapper.ArticleMapper;
import com.borenabs.mapper.CategoryMapper;
import com.borenabs.service.ArticleService;
import com.borenabs.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements  ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer articleId) {
        return articleMapper.deleteByPrimaryKey(articleId);
    }

    /**
     * 添加文章
     * */
    @Override
    public int insert(ArticleWithBLOBs record) {
        return articleMapper.insert(record);
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
        return articleMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Article record) {
        return 0;
    }
    /**
     * 前台页面显示文章列表
     * @param pageIndex
     * @param pageSize
     * */
    @Override
    public PageInfo<ArticleWithBLOBs> homeArticleList(Integer pageIndex,Integer pageSize){
        //分页
        PageHelper.startPage(pageIndex,pageSize);
        List<ArticleWithBLOBs> homeArticleList = articleMapper.homeArticleList();
        PageInfo<ArticleWithBLOBs> pageInfo = new PageInfo<>(homeArticleList,5);
        return pageInfo;
    }

    @Override
    public Integer countArticle() {
        return articleMapper.countArticle();
    }

    @Override
    public Integer countArticleComment() {
        return articleMapper.countArticleComment();
    }
    /**
     * 获得浏览量总数
     *
     * @return 数量
     */
    @Override
    public Integer countArticleView() {
        return articleMapper.countArticleView();
    }

    /**
     * 获取下一篇文章
     * */
    @Override
    public Article getAfterArticle(Integer articleId) {
        return articleMapper.getAfterArticle(articleId);
    }

    /**
     * 获取上一篇文章
     * */
    @Override
    public Article getPreArticle(Integer articleId) {
        return articleMapper.getPreArticle(articleId);
    }
    /**
     * 获得热评文章
     * */
    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleMapper.listArticleByCommentCount(limit);
    }
    /**
     * 获得随机文章
     * */
    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return articleMapper.listRandomArticle(limit);
    }

    /**
     *增加文章评论数
     * */
    @Override
    public void updateCommentCount(Integer articleId) {
        articleMapper.updateCommentCount(articleId);
    }

    @Override
    public PageInfo<ArticleWithBLOBs> categoryArticleList(Integer categoryId,Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<ArticleWithBLOBs> categoryArticleList = articleMapper.categoryArticleList(categoryId);
        PageInfo<ArticleWithBLOBs> pageInfo = new PageInfo<>(categoryArticleList,5);
        return pageInfo;
    }

    @Override
    public PageInfo<ArticleWithBLOBs> articleListByTag(Integer tagId, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<ArticleWithBLOBs> tagArticleList = articleMapper.articleListByTag(tagId);
        PageInfo<ArticleWithBLOBs> pageInfo = new PageInfo<>(tagArticleList,5);
        return pageInfo;
    }
    /**
     * 回显文章By文章ID
     * */
    @Override
    public ArticleWithBLOBs getArticleById(Integer articleId) {
        return articleMapper.getArticleById(articleId);
    }
    /**
     * 后台显示所有文章
     * */
    @Override
    public PageInfo<ArticleWithBLOBs> articleList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<ArticleWithBLOBs> adminArticleList = articleMapper.articleList();
        /**封装该片文章的分类列表*/
        for (ArticleWithBLOBs article : adminArticleList){
            article.setCategoryList(categoryMapper.selectArticleCategoryListByArticleId(article.getArticleId()));
        }
        PageInfo<ArticleWithBLOBs> pageInfo = new PageInfo<>(adminArticleList);
        return pageInfo;
    }
    /**
     * 查询留言板 article_status = 100;
     * */
    @Override
    public ArticleWithBLOBs showMessageBoard(Integer articleStatus) {
        return articleMapper.showMessageBoard(articleStatus);
    }
    /**
     *留言时,查询留言板
     * */
    @Override
    public ArticleWithBLOBs selectMessageBoard(Integer articleId) {
        return articleMapper.selectMessageBoard(articleId);
    }


}
