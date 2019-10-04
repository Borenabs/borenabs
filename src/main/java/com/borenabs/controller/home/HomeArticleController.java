package com.borenabs.controller.home;

import com.borenabs.entity.Article;
import com.borenabs.entity.Comment;
import com.borenabs.entity.Tag;
import com.borenabs.entity.User;
import com.borenabs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

        //侧边栏标签
        List<Tag> allTags = tagService.tagList();
        model.addAttribute("allTags",allTags);

        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);

        //随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(10);
        model.addAttribute("randomArticleList",randomArticleList);
        return "/home/page/articleDetail";
    }
}
