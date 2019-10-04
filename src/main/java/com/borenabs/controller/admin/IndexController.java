package com.borenabs.controller.admin;

import com.borenabs.entity.ArticleWithBLOBs;
import com.borenabs.entity.User;
import com.borenabs.service.ArticleService;
import com.borenabs.service.UserService;
import com.borenabs.untils.MyUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class IndexController {
    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    /**
     * 显示登录页面
     * */
    @RequestMapping("")
    public String loginPage(){
        return "/admin/login";
    }
    /**
     * 确认登录
     * */
    @RequestMapping("/login")
    public String AdminIndex(Model model){

        return "admin/public/framework";
    }
    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @return
     *
     */
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response)  {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        /**加密*/
        String passwd = MyUtils.strToMd5(password);
//        System.out.println("加密后的密文"+passwd);
        User user = userService.getUserByNameOrEmail(username);
//        System.out.println("数据库保存的密文"+user.getUserPass());
        if(user==null) {
            map.put("code",0);
            map.put("msg","用户名无效！");
        } else if(!user.getUserPass().equals(passwd)) {
            map.put("code",0);
            map.put("msg","密码错误！");
        } else {
            //登录成功
            map.put("code",1);
            map.put("msg","");
            //添加session
            request.getSession().setAttribute("user", user);
            //添加cookie
            if(rememberme!=null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            userService.updateByPrimaryKey(user);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session)  {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/admin";
    }
}
