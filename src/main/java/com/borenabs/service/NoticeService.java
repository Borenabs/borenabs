package com.borenabs.service;

import com.borenabs.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> queryAllNotice();

    int insertSelective(Notice notice);

    int updateByPrimaryKeySelective(Notice notice);

    Notice selectByPrimaryKey(Integer id);

    int deleteNotice(int id);

    /**
     * 前台显示公告列表
     * */
    List<Notice> noticeList();
}
