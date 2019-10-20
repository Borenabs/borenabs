package com.borenabs.interceptor;
import com.borenabs.entity.Article;
import com.borenabs.entity.Tag;
import com.borenabs.service.ArticleService;
import com.borenabs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class HomeSidebarInterceptor implements HandlerInterceptor {
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(7);
        request.setAttribute("randomArticleList",randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(7);
        request.setAttribute("mostCommentArticleList", mostCommentArticleList);
        //标签列表显示
        List<Tag> allTagList = tagService.tagList();
        request.setAttribute("allTags", allTagList);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
