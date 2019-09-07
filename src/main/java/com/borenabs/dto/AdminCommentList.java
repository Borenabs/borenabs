package com.borenabs.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdminCommentList {
    private Integer commentId;

    private Integer commentPid;

    private String commentPname;

    private Integer commentArticleId;

    private String commentAuthorName;

    private String commentAuthorAvatar;

    private String commentContent;

    private Date commentCreateTime;

    private Integer articleId;

    private String articleTitle;

    private Integer articleUserId;
}

