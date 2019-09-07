package com.borenabs.controller;

import com.borenabs.entity.Notice;
import com.borenabs.service.NoticeService;
import com.borenabs.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * 主要用于博客公告的管理
 * 有列出所有公告功能
 * 删除公告功能
 * 添加公告功能
 *查询一个功能
 */
@Controller
@RequestMapping("/admin/notice")
public class NoticeController {

    /**
     * 自动注入服务接口
     */
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("")
    public String listNotice(Model model){
        /**
         * 调用服务层的列出所有方法
         */
        List<Notice> noticeList = noticeService.queryAllNotice();
        model.addAttribute("noticeList",noticeList);
        return "admin/notice/index";
    }

    /**
     * 处理添加页面提交的数据
     */
    @RequestMapping("/insertSubmit")
    public String insertNoticeSubmit(Notice notice){
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        notice.setNoticeStatus(1);
        /**
         * 优先级可以在页面设置
         */
       notice.setNoticeOrder(1);
       int info = noticeService.insertSelective(notice);
        if (info>0){
            System.out.println("success");
        }
        return "redirect:/admin/notice";
    }

    /**
     * 跳转公告添加页面
     */
    @RequestMapping("/insert")
    public String insertNotice(){

        return "admin/notice/insert";
    }

    /**
     * 编辑公告
     * 得到一个数据并将数据发送到编辑页面
     */
    @RequestMapping(value = "/edit/{id}")
    public String selectByPrimaryKey(@PathVariable("id") Integer id,Model model){
        Notice notice = noticeService.selectByPrimaryKey(id);
        model.addAttribute("notice",notice);
        return "admin/notice/edit";
    }

    /**
     * 处理编辑页面提交的数据
     */
    @RequestMapping(value = "/editSubmit")
    public String updateNoticeById(Notice notice){

        notice.setNoticeUpdateTime(new Date());
        int info = noticeService.updateByPrimaryKeySelective(notice);
        return "redirect:/admin/notice";
    }

    /**
     * 删除一条公告数据
     */

    @RequestMapping("/delete/{id}")
    public String deleteNotice(@PathVariable("id") int id){
        int info = noticeService.deleteNotice(id);
        return "redirect:/admin/notice";
    }
}
