package com.borenabs.controller.admin;

import com.borenabs.entity.Category;
import com.borenabs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 后台分类列表
     * */
    @RequestMapping("")
    public String categoryList(Model model){
        List<Category> categoryList = categoryService.listCategoryWithArticleCount();
        model.addAttribute("categoryList",categoryList);
        return "admin/category/index";
    }
    /**
     * 后台添加分类
     * */
    @RequestMapping("/insertSubmit")
    public String categoryInsert(Category category){
        categoryService.insertSelective(category);
        return "redirect:/admin/category";
    }
    /**
     * 后台编辑分类(回显)
     * */
    @RequestMapping("/edit/{id}")
    public String categoryEdit(@PathVariable("id") Integer categoryId,Model model){
        Category category = categoryService.selectByPrimaryKey(categoryId);
        model.addAttribute("category",category);

        List<Category> categoryList = categoryService.listCategoryWithArticleCount();
        model.addAttribute("categoryList",categoryList);
//        categoryService.updateByPrimaryKeySelective(category);
        return "/admin/category/edit";
    }
    /**
     *后台编辑分类提交
     * */
    @RequestMapping("/editSubmit")
    public String categoryEditSubmit(Category category){
        categoryService.updateByPrimaryKeySelective(category);
        System.out.println(category.getCategoryId());
        return "redirect:/admin/category";
    }
    /**
     * 后台删除分类
     * */
    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer categoryId){
        categoryService.deleteByPrimaryKey(categoryId);
        return "redirect:/admin/category";
    }
}
