package com.borenabs.controller.home;

import com.alibaba.fastjson.JSON;
import com.borenabs.dto.JsonResult;
import com.borenabs.entity.*;
import com.borenabs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class HomeArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/article/{articleId}")
    public String articleDetail(@PathVariable("articleId") Integer articleId , Model model){
        //文章信息
        Article article = articleService.selectByPrimaryKey(articleId);
        if (article==null){
            return "/home/error/404";
        }
        model.addAttribute("article",article);
        //作者信息
        User user = userService.selectByPrimaryKey(article.getArticleUserId());
        model.addAttribute("user",user);

        //评论列表
        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList",commentList);

        //获取下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId);
        model.addAttribute("afterArticle", afterArticle);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId);
        model.addAttribute("preArticle", preArticle);

        return "/home/page/articleDetail";
    }
    /**
     *文章访问量
     */
    @ResponseBody
    @RequestMapping(value = "/article/view/{articleId}",method = RequestMethod.POST)
    public String articleViews(@PathVariable("articleId") Integer articleId){
        ArticleWithBLOBs article = articleService.selectByPrimaryKey(articleId);
        Integer articleViewsCount  = 0;
        articleViewsCount = article.getArticleViewCount()+1;
        article.setArticleViewCount(articleViewsCount);
        articleService.updateByPrimaryKeySelective(article);
        return JSON.toJSONString(articleViewsCount);
    }
}
