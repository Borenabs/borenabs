package com.borenabs.controller;

import com.borenabs.dto.AdminCommentList;
import com.borenabs.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    /*@RequestMapping("")
    public String commentList(Model model){
        List<AdminCommentList> commentList = commentService.listCommentByPage();
        model.addAttribute("pageInfo",commentList);
//        model.addAttribute("pageUrlPrefix","/admin/comment?pageIndex");
        return "admin/comment/index";
    }*/

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

}
