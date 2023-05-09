package com.bee.manager.pojo;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Table(name = "tb_bbs_post")
public class Post {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "publish_user_id")
    private Long publishUserId;

    @Column(name = "publish_user_name")
    private String publishUserName;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_category_id")
    private Integer postCategoryId;

    @Column(name = "post_category_name")
    private String postCategoryName;

    @Column(name = "post_status")
    private Integer postStatus;

    @Column(name = "post_views")
    private BigInteger postViews;

    @Column(name = "post_comments")
    private BigInteger postComments;

    @Column(name = "post_collects")
    private BigInteger postCollects;

    @Column(name = "last_update_time")
    private Timestamp updateTime;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "post_content")
    private String postContent;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }


    public Integer getPostCategoryId() {
        return postCategoryId;
    }

    public void setPostCategoryId(Integer postCategoryId) {
        this.postCategoryId = postCategoryId;
    }

    public String getPostCategoryName() {
        return postCategoryName;
    }

    public void setPostCategoryName(String postCategoryName) {
        this.postCategoryName = postCategoryName;
    }

    public Integer getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }

    public BigInteger getPostViews() {
        return postViews;
    }

    public void setPostViews(BigInteger postViews) {
        this.postViews = postViews;
    }

    public BigInteger getPostComments() {
        return postComments;
    }

    public void setPostComments(BigInteger postComments) {
        this.postComments = postComments;
    }

    public BigInteger getPostCollects() {
        return postCollects;
    }

    public void setPostCollects(BigInteger postCollects) {
        this.postCollects = postCollects;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postId=").append(postId);
        sb.append(", publishUserId=").append(publishUserId);
        sb.append(", publishUserName=").append(publishUserName);
        sb.append(", postTitle=").append(postTitle);
        sb.append(", postCategoryId=").append(postCategoryId);
        sb.append(", postCategoryName=").append(postCategoryName);
        sb.append(", postStatus=").append(postStatus);
        sb.append(", postViews=").append(postViews);
        sb.append(", postComments=").append(postComments);
        sb.append(", postCollects=").append(postCollects);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", postContent=").append(postContent);
        sb.append("]");
        return sb.toString();
    }
}
