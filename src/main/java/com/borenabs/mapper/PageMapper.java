package com.borenabs.mapper;

import com.borenabs.entity.Page;

public interface PageMapper {
    int deleteByPrimaryKey(Integer pageId);

    int insert(Page record);

    int insertSelective(Page record);

    Page selectByPrimaryKey(Integer pageId);

    int updateByPrimaryKeySelective(Page record);

    int updateByPrimaryKeyWithBLOBs(Page record);

    int updateByPrimaryKey(Page record);
}