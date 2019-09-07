package com.borenabs.controller;

import com.borenabs.dto.AdminCommentList;
import com.borenabs.entity.Comment;
import com.borenabs.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    /**
     * 评论页面显示
     * @author borenabs
     * */
    @RequestMapping("")
    public String commentList(Model model){
        List<AdminCommentList> commentList = commentService.listCommentByPage();
        model.addAttribute("pageInfo",commentList);
//        model.addAttribute("pageUrlPrefix","/admin/comment?pageIndex");
        return "admin/comment/index";
    }
}
