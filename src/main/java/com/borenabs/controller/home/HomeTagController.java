package com.borenabs.controller.home;

import com.borenabs.entity.Article;
import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.entity.Tag;
import com.borenabs.service.ArticleService;
import com.borenabs.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeTagController {
    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;
    /**
     * 根据标签显示文章
     * */
    @RequestMapping("/tag/{tagId}")
    public String tagArticleList(@PathVariable("tagId") Integer tagId,
                                 @RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                                 @RequestParam(required = false,defaultValue = "10")Integer pageSize, Model model){
        //该标签信息
        Tag tag = tagService.selectByPrimaryKey(tagId);
        if (tag == null) {
            return "redirect:/404";
        }
        model.addAttribute("tag", tag);

        PageInfo<ArticleWithBLOBs> articleListByTagPageInfo = articleService.articleListByTag(tagId,pageIndex,pageSize);
        model.addAttribute("pageInfo",articleListByTagPageInfo);
        model.addAttribute("pageUrlPrefix", "/tag/"+pageIndex+"?pageIndex");

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.tagList();
        model.addAttribute("allTags", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(10);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "/home/page/articleListByTag";
    }
}
