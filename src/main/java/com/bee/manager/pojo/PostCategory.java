package com.bee.manager.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 帖子分类-实体类
 */
public class PostCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer postCategoryId;

    @Column(name = "category_name")
    private String postCategoryName;

    @Column(name = "category_rank")
    private Integer categoryRank;

    @Column(name = "is_deleted")
    private Byte isDeleted;

    @Column(name = "create_time")
    private Date createTime;

    public Integer getCategoryId() {
        return postCategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.postCategoryId = postCategoryId;
    }

    public String getCategoryName() {
        return postCategoryName;
    }

    public void setCategoryName(String postCategoryName) {
        this.postCategoryName = postCategoryName == null ? null : postCategoryName.trim();
    }

    public Integer getCategoryRank() {
        return categoryRank;
    }

    public void setCategoryRank(Integer categoryRank) {
        this.categoryRank = categoryRank;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryId=").append(postCategoryId);
        sb.append(", categoryName=").append(postCategoryName);
        sb.append(", categoryRank=").append(categoryRank);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}