package com.bee.manager.dto;


import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Title: PermissionDTO
 *
 */
@Data
public class PostDTO {

    private Long postId;

    private Long publishUserId;

    private String publishUserName;

    private String postTitle;

    private Integer postCategoryId;

    private String postCategoryName;

    private Integer postStatus;

    private BigInteger postViews;

    private BigInteger postComments;

    private BigInteger postCollects;

    private Timestamp updateTime;

    private String createTime;


}
