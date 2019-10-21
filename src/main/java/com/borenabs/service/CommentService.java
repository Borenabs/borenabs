package com.borenabs.service;

import com.borenabs.dto.AdminCommentList;
import com.borenabs.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    /**
     * 评论列表,分页显示;
     *
     * */
    PageInfo<AdminCommentList> listCommentByPage(Integer pageIndex, Integer pageSize);

    /**
     * 文章评论列表
     * @param articleId
     * @return List<Comment>
     * */
    List<Comment> listCommentByArticleId(Integer articleId);
    /**
     * 查询子评论
     * */
    List<Comment> childCommentList(Integer commentPid);
}
