package com.borenabs.controller.home;

import com.borenabs.dto.JsonResult;
import com.borenabs.entity.Article;
import com.borenabs.entity.Comment;
import com.borenabs.entity.User;
import com.borenabs.service.ArticleService;
import com.borenabs.service.CommentService;
import com.borenabs.untils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class HomeCommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleService articleService;
    /**
     *添加评论
     */
    @ResponseBody
    @RequestMapping(value = "/comment",method = {RequestMethod.POST})
    public JsonResult InsertComment(HttpServletRequest request, Comment comment){
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        if (request.getSession().getAttribute("user")!=null){
            User user = (User)request.getSession().getAttribute("user");
            comment.setCommentRole(1); //博主
            comment.setCommentAuthorAvatar(user.getUserAvatar());
            comment.setCommentAuthorName(user.getUserName());
            comment.setCommentAuthorEmail(user.getUserEmail());
            comment.setCommentAuthorUrl(user.getUserUrl());
        }else {
            comment.setCommentRole(0);//游客
            comment.setCommentAuthorAvatar(MyUtils.getGravatar(comment.getCommentAuthorEmail()));
            comment.setCommentAuthorName(comment.getCommentAuthorName());
            comment.setCommentAuthorEmail(comment.getCommentAuthorEmail());
            comment.setCommentAuthorUrl(comment.getCommentAuthorUrl());
        }
        comment.setCommentContent(comment.getCommentContent());
        try{
            commentService.insert(comment);
            //获取当前被评论的文章
            Article article = articleService.selectByPrimaryKey(comment.getCommentArticleId());
            //增加文章评论数
            articleService.updateCommentCount(article.getArticleId());
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();
    }


}
