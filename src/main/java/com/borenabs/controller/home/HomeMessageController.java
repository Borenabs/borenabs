package com.borenabs.controller.home;

import com.borenabs.dto.JsonResult;
import com.borenabs.entity.*;
import com.borenabs.service.ArticleService;
import com.borenabs.service.CommentService;
import com.borenabs.service.TagService;
import com.borenabs.service.UserService;
import com.borenabs.untils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class HomeMessageController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    TagService tagService;
    /**
     * 显示留言板
     * */
    @RequestMapping("/message")
    public String messageBoard(Model model){
        /**留言板状态 100*/
        ArticleWithBLOBs messageBoard = articleService.showMessageBoard(100);

        //留言板信息
        if (messageBoard==null){
            return "/home/error/404";
        }
        model.addAttribute("messageBoard",messageBoard);
        //作者信息
        User user = userService.selectByPrimaryKey(messageBoard.getArticleUserId());
        model.addAttribute("user",user);

        //评论列表
        List<Comment> commentList = commentService.listCommentByArticleId(messageBoard.getArticleId());
        model.addAttribute("commentList",commentList);

        //侧边栏标签
        List<Tag> allTags = tagService.tagList();
        model.addAttribute("allTags",allTags);

        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);

        //随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(10);
        model.addAttribute("randomArticleList",randomArticleList);
        return "/home/page/message";
    }

    /**
     * 留言
     * */
    @ResponseBody
    @RequestMapping(value = "/submitMessage",method = {RequestMethod.POST})
    public JsonResult InsertComment(HttpServletRequest request, Comment comment){
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        if (request.getSession().getAttribute("user")!=null){
            comment.setCommentRole(1); //博主
        }else {
            comment.setCommentRole(0);//游客
        }
        comment.setCommentAuthorAvatar(MyUtils.getGravatar(comment.getCommentAuthorEmail()));
        comment.setCommentContent(comment.getCommentContent());
        comment.setCommentAuthorName(comment.getCommentAuthorName());
        comment.setCommentAuthorEmail(comment.getCommentAuthorEmail());
        comment.setCommentAuthorUrl(comment.getCommentAuthorUrl());
        try{
            commentService.insert(comment);
            //获取留言板
            Article article = articleService.selectMessageBoard(comment.getCommentArticleId());
            //增加文章评论数
            articleService.updateCommentCount(article.getArticleId());
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();
    }
}
