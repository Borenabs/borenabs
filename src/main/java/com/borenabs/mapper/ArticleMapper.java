package com.borenabs.mapper;

import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
    /**
     * 前台页面显示文章列表
     * */
    List<ArticleWithBLOBs> homeArticleList();
    /**
     * 后台显示所有文章
     * */
    List<ArticleWithBLOBs> articleList();

    /**统计文章数*/
    Integer countArticle();

    /**
     * 获取评论总数
     *
     * @return 数量
     */
    Integer countArticleComment();
    /**
     * 获得浏览量总数
     *
     * @return 数量
     */
    Integer countArticleView();

    /**
     * 获取下一篇文章
     * */
    Article getAfterArticle(Integer articleId);

    /**
     * 获取上一篇文章
     * */
    Article getPreArticle(Integer articleId);

    /**
     * 获得热评文章
     * */
    List<Article> listArticleByCommentCount(Integer limit);

    /**
     * 获得随机文章
     * */
    List<Article> listRandomArticle(Integer limit);
    /**
     *增加文章评论数
     * */
    void updateCommentCount(Integer articleId);

    /**
     * 根据分类查询文章
     * */
    List<ArticleWithBLOBs> categoryArticleList(Integer categoryId);

    /**
     * 根据标签查询文章
     * */
    List<ArticleWithBLOBs> articleListByTag(Integer tagId);
    /**
     * 回显文章By文章ID
     * */
    ArticleWithBLOBs getArticleById(Integer articleId);
}