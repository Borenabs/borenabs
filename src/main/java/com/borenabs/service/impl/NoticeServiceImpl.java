package com.borenabs.service.impl;

import com.borenabs.entity.Notice;
import com.borenabs.mapper.NoticeMapper;
import com.borenabs.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告的服务层
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    /**
     * 自动注入mapper接口
     */
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 列出所有的公告并在页面显示
     */
    @Override
    public List<Notice> queryAllNotice() {
        return noticeMapper.queryAllNotice();
    }

    @Override
    public int insertSelective(Notice notice) {
        return noticeMapper.insertSelective(notice);
    }

    @Override
    public int updateByPrimaryKeySelective(Notice notice) {
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }

    @Override
    public Notice selectByPrimaryKey(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteNotice(int id) {
        return noticeMapper.deleteByPrimaryKey(id);
    }


}
