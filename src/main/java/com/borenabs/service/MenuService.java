package com.borenabs.service;

import com.borenabs.entity.Menu;

import java.util.List;

public interface MenuService {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**菜单列表*/
    List<Menu> menuList();
}
