package com.borenabs.controller.admin;

import com.borenabs.entity.Tag;
import com.borenabs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/admin/tag")
public class TagController {
    /**
     * 注入tagservice以及tag与文章的中间表
     * 可能遇到的错误，没有找到自动注入，原因:在service接口没有使用@Service注解
     */
    @Autowired
    private TagService tagService;

    /**
     * 使用返回视图的方式
     * 一共有三种，不太了解
     */
    @RequestMapping("")
    public String tagIndex(Model model){
        /** 从数据库查询相关数据
         *  1.得到标签的全部数据
         *  2.从中间表中得到标签绑定的文章数
         *  3.利用model返回数据到前端（有点模糊）
         *  这里有个问题：在数据库中没用count这个字段，但是这里要用到，那问题来了，该如何处理喃
         *  1.加入一个集合，那就要分两步
         *      1.得到标签表里的标签数据
         *      2.再遍历集合得到标签id查询中间表里的文章数，
         *      3.建立一个新集合存放新数据
         *  2.再mapper。xml文件中设立一个包含count的返回结果集
         *
         */
        List<Tag> tagList = tagService.tagList();
        model.addAttribute("tagList",tagList);
        /**  */
        /**  */
        /** 返回除了配置的前缀以及后缀的路径部分 */
        return "admin/tag/index";
    }

    /** 添加方法 */
    @RequestMapping(value = "/insertSubmit")
    public String insertSubmit(Tag tag){
       /** 调用业务 */
        int info = tagService.insertSelective(tag);
        /**
         * 重定向到/admin/tag
         */
        return "redirect:/admin/tag";
    }
    /**
     * 删除业务
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteByPrimaryKey(@PathVariable("id") int id,Model model){

        int info = tagService.deleteByPrimaryKey(id);

        return "redirect:/admin/tag";
    }
    /**
     * 编辑标签
     * 查询一个用于修改
     */
    @RequestMapping(value = "/edit/{id}")
    public String queryTag(@PathVariable("id") int id,Model model){
        /**
         * 这里有一个问题就是必须要再全查一次标签以及中间表
         * 没有学一部刷新，也就必须再操作一次数据库
         * 以后再改进
         */
        /**
         * 返回数据
         */
        Tag tag = tagService.selectByPrimaryKey(id);
        List<Tag> tagList = tagService.tagList();

        model.addAttribute("tagList",tagList);
        model.addAttribute("tag",tag);

        return "admin/tag/edit";
    }

    /**
     * 处理编辑结果
     */
    @RequestMapping(value = "/editSubmit")
    public String updateByPrimaryKey(Tag tag){
        System.out.println(tag.getTagId());
        int info = tagService.updateByPrimaryKey(tag);
        if (info>0){
            System.out.println("success");
        }
        return "redirect:/admin/tag";
    }
}
