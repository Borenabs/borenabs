package com.borenabs.service.impl;

import com.borenabs.dto.AdminCommentList;
import com.borenabs.entity.Comment;
import com.borenabs.mapper.AdminCommentListMapper;
import com.borenabs.mapper.ArticleMapper;
import com.borenabs.mapper.CommentMapper;
import com.borenabs.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    AdminCommentListMapper adminCommentListMapper;
    @Override
    public int deleteByPrimaryKey(Integer commentId) {
        return 0;
    }

    @Override
    public int insert(Comment record) {
        return 0;
    }

    @Override
    public int insertSelective(Comment record) {
        return 0;
    }

    @Override
    public Comment selectByPrimaryKey(Integer commentId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return 0;
    }

    /**
     * 获取评论列表 分页
     *
     * @return*/
    @Override
    public PageInfo<AdminCommentList> listCommentByPage(Integer pageIndex, Integer pageSize) {
         //分页
        PageHelper.startPage(pageIndex,pageSize);
        List<AdminCommentList> commentList = adminCommentListMapper.adminCommentList();
        PageInfo<AdminCommentList> pageInfo = new PageInfo<>(commentList,5);
        return pageInfo;
    }

}
