package com.borenabs.service;

import com.borenabs.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 删除用户
     * */
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    /**
     * 修改用户信息
     * @param User
     * */
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 获得用户列表
     *
     * @return  用户列表
     */
    List<User> listUser();

    /**
     * 通过用户名获取用户
     * @return 用户
     * */
    User selectUserByName(String userName);

    /**
     * 通过邮箱获取用户
     * @return 用户
     * */
    User selectUserByEmail(String userEmail);

    /**
     * 添加用户
     * @param user 用户
     */
    void insertUser(User user);

    /**
     * 根据用户名和邮箱查询用户
     *
     * @param str 用户名或Email
     * @return 用户
     */
    User getUserByNameOrEmail(String str);
}
