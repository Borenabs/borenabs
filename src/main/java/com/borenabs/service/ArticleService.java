package com.borenabs.service;

import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleService {

    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
    /**
     * 前台页面显示文章列表
     * @param pageIndex
     * @param pageSize
     * */
    PageInfo<ArticleWithBLOBs> homeArticleList(Integer pageIndex,Integer pageSize);

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
     * 分类查询文章列表(分页)
     * */
    PageInfo<ArticleWithBLOBs> categoryArticleList(Integer categoryId,Integer pageIndex,Integer pageSize);
    /**
     * 根据标签查询文章列表(分页)
     * */
    PageInfo<ArticleWithBLOBs> articleListByTag(Integer tagId, Integer pageIndex, Integer pageSize);
    /**
     * 回显文章By文章ID
     * */
    ArticleWithBLOBs getArticleById(Integer articleId);
    /**
     * 后台显示所有文章
     * */
    PageInfo<ArticleWithBLOBs> articleList(Integer pageIndex,Integer pageSize);
    /**
     * 前台显示留言板 article_status = 100;
     * */
    ArticleWithBLOBs showMessageBoard(Integer articleStatus);
    /**
     *留言时,查询留言板
     * */
    ArticleWithBLOBs selectMessageBoard(Integer articleId);
    /**
     * 统计留言数
     * */
    int countArticleMessage();
}
