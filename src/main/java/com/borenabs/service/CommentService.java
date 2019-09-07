package com.borenabs.service;

import com.borenabs.dto.AdminCommentList;
import com.borenabs.entity.Comment;

import java.util.List;

public interface CommentService {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    /**
    * 获取评论列表 分页
    *
     * @return*/
    public List<AdminCommentList> listCommentByPage();
}
