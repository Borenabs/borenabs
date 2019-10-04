package com.borenabs.interceptor;

import com.borenabs.entity.Article;
import com.borenabs.entity.Category;
import com.borenabs.entity.Menu;
import com.borenabs.entity.User;
import com.borenabs.service.ArticleService;
import com.borenabs.service.CategoryService;
import com.borenabs.service.MenuService;
import com.borenabs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyanzhao
 */
@Component
public class HomeResourceInterceptor implements HandlerInterceptor {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /*@Autowired
    private LinkService linkService;*/

    /*@Autowired
    private OptionsService optionsService;*/

    @Autowired
    private MenuService menuService;

    /**
     * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws IOException {

        /**菜单显示*/
        List<Menu> menuList = menuService.menuList();
        request.setAttribute("menuList",menuList);
        /**导航栏分类*/
        List<Category> categoryList = categoryService.categorys();
        request.setAttribute("categoryList",categoryList);
        //获得网站概况
        List<String> siteBasicStatistics = new ArrayList<String>();
        siteBasicStatistics.add(articleService.countArticle() + "");
        siteBasicStatistics.add(articleService.countArticleComment() + "");
        siteBasicStatistics.add(categoryService.countCategory() + "");
        siteBasicStatistics.add(tagService.countTag() + "");
//        siteBasicStatistics.add(linkService.countLink(LinkStatus.NORMAL.getValue()) + "");
        siteBasicStatistics.add(articleService.countArticleView() + "");
        request.setAttribute("siteBasicStatistics", siteBasicStatistics);

        //最后更新的文章
        /*Article lastUpdateArticle = articleService.getLastUpdateArticle();
        request.setAttribute("lastUpdateArticle", lastUpdateArticle);*/

        //页脚显示
        //博客基本信息显示(Options)
        /*Options options = optionsService.getOptions();
        request.setAttribute("options", options);*/

        HttpSession session = request.getSession();
        if (session!=null){
            User user = (User) session.getAttribute("user");
            request.setAttribute("user",user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)  {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)  {

    }
}
