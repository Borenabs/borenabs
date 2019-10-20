package com.borenabs.controller.home;

import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.entity.Category;
import com.borenabs.entity.Tag;
import com.borenabs.service.ArticleService;
import com.borenabs.service.CategoryService;
import com.borenabs.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 根据分类查询文章
 * */
@Controller
public class HomeCategoryController {
    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TagService tagService;
    /**
     * 根据分类查询文章
     * */
    @RequestMapping(value = "/category/{categoryId}")
    public String ArticleListByCategory(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                                        @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                        @PathVariable("categoryId") Integer categoryId , Model model){
        Category category = categoryService.selectByPrimaryKey(categoryId);
        if (category==null){
            return "/home/error/404";
        }
        model.addAttribute("category",category);
        PageInfo<ArticleWithBLOBs> categoryArticleList = articleService.categoryArticleList(categoryId,pageIndex,pageSize);
        model.addAttribute("pageInfo", categoryArticleList);
        model.addAttribute("pageUrlPrefix", "/category/"+pageIndex+"?pageIndex");
        return "/home/page/articleListByCategory";
    }
}
