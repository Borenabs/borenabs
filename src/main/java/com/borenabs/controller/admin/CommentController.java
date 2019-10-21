package com.borenabs.controller.admin;

import com.borenabs.dto.AdminCommentList;
import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.entity.Comment;
import com.borenabs.entity.User;
import com.borenabs.service.ArticleService;
import com.borenabs.service.CommentService;
import com.borenabs.service.UserService;
import com.borenabs.untils.MyUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;
    /**
     * 评论页面
     *
     * @param pageIndex 页码
     * @param pageSize  页大小
     * @return modelAndView
     */
    @RequestMapping("")
    public ModelAndView commmentList(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                     @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        PageInfo<AdminCommentList> pageInfo = commentService.listCommentByPage(pageIndex,pageSize);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("pageUrlPrefix","/admin/comment?pageIndex");
        mv.setViewName("/admin/comment/index");
        return mv;
    }
    /**
     * 删除评论
     * @param id 批量ID
     * */
    @RequestMapping(value = "/delete/{id}")
    public void deleteComment(@PathVariable("id") Integer id) {
        Comment comment = commentService.selectByPrimaryKey(id);
        //删除评论
        commentService.deleteByPrimaryKey(id);
        //删除其子评论
        List<Comment> childCommentList = commentService.childCommentList(id);
        for (int i = 0; i < childCommentList.size(); i++) {
            commentService.deleteByPrimaryKey(childCommentList.get(i).getCommentId());
        }
        //更新文章的评论数
        Article article = articleService.selectByPrimaryKey(comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }
    /**
     * 回复评论
     * @param id
     * @return
     * */
    @RequestMapping(value = "/reply/{id}")
    public String replyCommentView(@PathVariable("id") Integer id, Model model) {
        Comment comment = commentService.selectByPrimaryKey(id);
        model.addAttribute("comment", comment);
        return "admin/comment/reply";
    }
    /**
     * 评论回复提交
     * */
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replyCommentSubmit(HttpServletRequest request, Comment comment) {
        //文章评论数+1
        ArticleWithBLOBs article = articleService.selectByPrimaryKey(comment.getCommentArticleId());
        System.out.println(article);
        article.setArticleCommentCount(article.getArticleCommentCount() + 1);
        System.out.println("文章评论数"+article.getArticleCommentCount());
        articleService.updateCommentCount(article.getArticleId());
        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        //设置头像
        User user = (User)request.getSession().getAttribute("user");
        comment.setCommentAuthorAvatar(user.getUserAvatar());
        commentService.insert(comment);
        return "redirect:/admin/comment";
    }
}
