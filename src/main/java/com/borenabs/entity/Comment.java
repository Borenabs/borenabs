package com.borenabs.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Comment {

    private Integer commentId;

    private Integer commentPid;

    private String commentPname;

    private Integer commentArticleId;

    private String commentAuthorName;

    private String commentAuthorEmail;

    private String commentAuthorUrl;

    private String commentAuthorAvatar;

    private String commentContent;

    private String commentAgent;

    private String commentIp;

    private Date commentCreateTime;

    private Integer commentRole;


}