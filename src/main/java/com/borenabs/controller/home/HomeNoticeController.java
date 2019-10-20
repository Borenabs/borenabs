package com.borenabs.controller.home;

import com.borenabs.entity.Article;
import com.borenabs.entity.Notice;
import com.borenabs.entity.Tag;
import com.borenabs.service.ArticleService;
import com.borenabs.service.NoticeService;
import com.borenabs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeNoticeController {
    @Autowired
    NoticeService noticeService;

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;
    @RequestMapping(value = "/notice/{noticeId}")
    public String noticeDetail(@PathVariable("noticeId")Integer noticeId, Model model){
        Notice notice = noticeService.selectByPrimaryKey(noticeId);
        model.addAttribute("notice",notice);
        return "home/page/noticeDetail";
    }
}
