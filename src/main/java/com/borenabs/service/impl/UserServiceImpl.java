package com.borenabs.service.impl;

import com.borenabs.entity.User;
import com.borenabs.mapper.UserMapper;
import com.borenabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    /**
     * 删除用户
     * */
    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }

    /**
     * 获得用户列表
     *
     * @return  用户列表
     */
    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

    /**
     * 通过用户名获取用户
     * @return 用户
     * */
    @Override
    public User selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

    /**
     * 通过邮箱获取用户
     * @return 用户
     * */
    @Override
    public User selectUserByEmail(String userEmail) {
        return userMapper.selectUserByEmail(userEmail);
    }

    @Override
    public void insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
    }
}
