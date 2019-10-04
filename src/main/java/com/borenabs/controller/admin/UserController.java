package com.borenabs.controller.admin;

import com.borenabs.entity.User;
import com.borenabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserService userService;
    /**
    * 后台显示全部用户
    * */
    @RequestMapping("")
    public ModelAndView userList(){
        ModelAndView mv  = new ModelAndView();
        List<User> userList = userService.listUser();
        mv.addObject("userList",userList);
        mv.setViewName("/admin/user/index");
        return mv;
    }
    /**
    * 后台添加用户页面显示
    * */
    @RequestMapping("/insert")
    public ModelAndView insert(){
        ModelAndView mv  = new ModelAndView();
        mv.setViewName("/admin/user/insert");
        return mv;
    }
    /**
     * 后台添加用户提交
     * */
    @RequestMapping("/insertSubmit")
    public String insertSubmit(User user){
        User user2 = userService.selectUserByName(user.getUserName());
        User user3 = userService.selectUserByEmail(user.getUserEmail());
        if(user2==null&&user3==null) {
            user.setUserRegisterTime(new Date());
            user.setUserStatus(1);
            userService.insertUser(user);
        }
        return "redirect:/admin/user";
    }
    /**
     * 后台编辑用户页面显示
     * */
    @RequestMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") Integer id){
        ModelAndView mv  = new ModelAndView();
        mv.addObject("user",userService.selectByPrimaryKey(id));
        mv.setViewName("/admin/user/edit");
        return mv;
    }
    /**
     *后台编辑用户提交
     * */
    @RequestMapping("/editSubmit")
    public String editSubmit(User user){
        userService.updateByPrimaryKeySelective(user);
        return "redirect:/admin/user";
    }

    /**
     * 后台删除用户
     * */
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteByPrimaryKey(id);
        return "redirect:/admin/user";
    }
}
