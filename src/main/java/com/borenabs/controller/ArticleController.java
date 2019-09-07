package com.borenabs.controller;

import com.borenabs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("example",articleService.selectByPrimaryKey(11));
        return "admin/article/index";
    }
}
