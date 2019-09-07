package com.borenabs.controller;

import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/")
    public String AdminIndex(Model model){

        return "admin/public/framework";
    }
}
