package com.borenabs.mapper;

import com.borenabs.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> commentList();
    /**
     * 文章评论列表
     * @param articleId
     * @return List<Comment>
     * */
    List<Comment> listCommentByArticleId(Integer articleId);
}