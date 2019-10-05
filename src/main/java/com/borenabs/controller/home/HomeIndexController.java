package com.borenabs.controller.home;

import com.borenabs.dto.AdminCommentList;
import com.borenabs.entity.*;
import com.borenabs.service.*;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 前台首页Controller
 * */

@Controller
public class HomeIndexController {

    @Autowired
    ArticleService articleService;

    @Autowired
    MenuService menuService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    TagService tagService;

    @Autowired
    CategoryService categoryService;
    /**
     * 前台分页显示文章列表
     * */
    @RequestMapping(value = {"/","/home"})
    public String homeArticleList(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                                    @RequestParam(required = false,defaultValue = "10")Integer pageSize, Model model){

        /**博客显示*/
        PageInfo<ArticleWithBLOBs> pageInfo = articleService.homeArticleList(pageIndex,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageUrlPrefix","/home?pageIndex");
        /**公告*/
        List<Notice> noticeList = noticeService.noticeList();
        model.addAttribute("noticeList",noticeList);
        /**标签*/
        List<Tag> tagList = tagService.tagList();
        model.addAttribute("allTags",tagList);
        return "/home/index";
    }
}
