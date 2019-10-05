package com.borenabs.controller.admin;

import com.borenabs.dto.ArticleParam;
import com.borenabs.entity.*;
import com.borenabs.mapper.ArticleCategoryRefMapper;
import com.borenabs.mapper.ArticleTagRefMapper;
import com.borenabs.service.ArticleService;
import com.borenabs.service.CategoryService;
import com.borenabs.service.TagService;
import com.borenabs.untils.MyUtils;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleCategoryRefMapper categoryRefMapper;

    @Autowired
    ArticleTagRefMapper tagRefMapper;

    /**
     * 后台首页,显示全部文章
     * */
    @RequestMapping("")
    public String index(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                        Model model){
        PageInfo<ArticleWithBLOBs> pageInfo = articleService.articleList(pageIndex,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageUrlPrefix","/admin/article?pageIndex");
        return "admin/article/index";
    }

    /**
     * 后台发布文章页面
     * */
    @RequestMapping("/publish")
    public String publishArticlePage(Model model){
        List<Category> categoryList = categoryService.categorys();
        List<Tag> tagList = tagService.tagList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        return "/admin/article/insert";
    }

    /**
     * 后台发布文章提交
     * */
    @RequestMapping("/publishSubmit")
    public String publishSubmit(ArticleParam articleParam, HttpSession session){
        ArticleWithBLOBs article = new ArticleWithBLOBs();
        /**设置文章作者id*/
        User user = (User) session.getAttribute("user");
        if (user!=null){
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());
        /**摘要*/
        int summaryLength = 30;
        /**去除html标签*/
        String summary = MyUtils.delHTMLTag(articleParam.getArticleContent());
        if (summary.length()>summaryLength){
            article.setArticleSummary(summary.substring(0,summaryLength));
        }else{
            article.setArticleSummary(summary);
        }
        /**正文*/
        article.setArticleContent(articleParam.getArticleContent());
        /**状态*/
        article.setArticleStatus(articleParam.getArticleStatus());
        /**文章创建时间*/
        article.setArticleCreateTime(new Date());
        /**文章更新时间*/
        article.setArticleUpdateTime(new Date());
        /**
         * 添加文章并返回文章id
         * */
        articleService.insert(article);
        int articleId = article.getArticleId();

        /**设置文章分类*/
        if (articleParam.getArticleChildCategoryId()!=null){
            ArticleCategoryRef categoryRefChild = new ArticleCategoryRef(articleId,articleParam.getArticleChildCategoryId());
            ArticleCategoryRef categoryRefParent = new ArticleCategoryRef(articleId,articleParam.getArticleParentCategoryId());
            categoryRefMapper.insert(categoryRefParent);
            categoryRefMapper.insert(categoryRefChild);
        }else {
            ArticleCategoryRef categoryRefParent = new ArticleCategoryRef(articleId,articleParam.getArticleParentCategoryId());
            categoryRefMapper.insert(categoryRefParent);
        }

        /**设置文章标签*/
        List<Integer> tagList = articleParam.getArticleTagIds();
        if (tagList!=null){
            for (int i = 0;i<tagList.size();i++){
                ArticleTagRefKey articleTagRefKey = new ArticleTagRefKey(articleId,tagList.get(i));
                tagRefMapper.insert(articleTagRefKey);
            }
        }
        return "redirect:/admin/article";
    }

    /**
     * 后台编辑文章页面(回显)
     * */
    @RequestMapping("/edit/{articleId}")
    public String articleEditPage(@PathVariable("articleId")Integer articleId , Model model){
        /**回显文章*/
        ArticleWithBLOBs article = articleService.getArticleById(articleId);
        model.addAttribute("article",article);
        /**回显分类*/
        List<Category> categoryList = categoryService.categorys();
        model.addAttribute("categoryList",categoryList);

        List<Category> articleCategoryList = categoryService.selectArticleCategoryListByArticleId(articleId);
        model.addAttribute("articleCategoryList",articleCategoryList);
        /**回显标签*/
        List<Tag> tagList = tagService.tagList();
        model.addAttribute("tagList",tagList);

        List<Tag> articleTagList = tagService.selectArticleTagListByArticleId(articleId);
        model.addAttribute("articleTagList",articleTagList);
        return "/admin/article/edit";
    }
    /**
     * 编辑文章提交
     * */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String articleEditSubmit(ArticleParam articleParam){
        ArticleWithBLOBs article = new ArticleWithBLOBs();
        article.setArticleTitle(articleParam.getArticleTitle());
        /**摘要*/
        int summaryLength = 30;
        /**去除html标签*/
        String summary = MyUtils.delHTMLTag(articleParam.getArticleContent());
        if (summary.length()>summaryLength){
            article.setArticleSummary(summary.substring(0,summaryLength));
        }else{
            article.setArticleSummary(summary);
        }
        /**正文*/
        article.setArticleContent(articleParam.getArticleContent());
        /**状态*/
        article.setArticleStatus(articleParam.getArticleStatus());
        /**文章更新时间*/
        article.setArticleUpdateTime(new Date());
        /**文章id*/
        int articleId = articleParam.getArticleId();
        article.setArticleId(articleId);
        articleService.updateByPrimaryKeyWithBLOBs(article);

        /**删除文章分类*/
        categoryRefMapper.deleteArticleCategoryByArticleId(articleId);
        /**设置文章分类*/
        if (articleParam.getArticleChildCategoryId()!=null){
            /**重新插入文章分类*/
            ArticleCategoryRef categoryRefChild = new ArticleCategoryRef(articleId,articleParam.getArticleChildCategoryId());
            ArticleCategoryRef categoryRefParent = new ArticleCategoryRef(articleId,articleParam.getArticleParentCategoryId());
            categoryRefMapper.insert(categoryRefParent);
            categoryRefMapper.insert(categoryRefChild);
        }else {
            ArticleCategoryRef categoryRefParent = new ArticleCategoryRef(articleId,articleParam.getArticleParentCategoryId());
            categoryRefMapper.insert(categoryRefParent);
        }
        /**设置文章标签*/
        List<Integer> tagList = articleParam.getArticleTagIds();
        if (tagList!=null){
            /**删除文章标签*/
            tagRefMapper.deleteByArticleId(articleId);
            for (int i = 0;i<tagList.size();i++){
                ArticleTagRefKey articleTagRefKey = new ArticleTagRefKey(articleId,tagList.get(i));
                tagRefMapper.insert(articleTagRefKey);
            }
        }
        return "redirect:/admin/article";
    }

    /**后台删除文章*/
    @RequestMapping(value = "/delete/{articleId}",method = RequestMethod.POST)
    public String articleDelete(@PathVariable("articleId")Integer articleId){
        /**删除*/
        articleService.deleteByPrimaryKey(articleId);
        return "redirect:/admin/article";
    }
}
