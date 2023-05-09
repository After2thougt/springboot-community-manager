package com.bee.manager.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "tb_bbs_user")
public class BaseAdminUser {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 系统用户名称
     */
    @Column(name = "sys_user_name")
    private String sysUserName;

    /**
     * 系统用户密码
     */
    @Column(name = "sys_user_pwd")
    private String sysUserPwd;

    /**
     * 角色
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 性别
     */
    private String gender;

    /**
     * 邮箱帐号
     */
    @Column(name = "user_email")
    private String userEmail;


    /**
     * 状态（0：有效；1：无效）
     */
    @Column(name = "user_status")
    private Integer userStatus;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "head_img_url")
    private String headImgUrl;

    private String location;

    private String introduce;


    /**
     * 登记时间
     */
    @Column(name = "reg_time")
    private Date regTime;
    
    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取系统用户名称
     *
     * @return sys_user_name - 系统用户名称
     */
    public String getSysUserName() {
        return sysUserName;
    }

    /**
     * 设置系统用户名称
     *
     * @param sysUserName 系统用户名称
     */
    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    /**
     * 获取系统用户密码
     *
     * @return sys_user_pwd - 系统用户密码
     */
    public String getSysUserPwd() {
        return sysUserPwd;
    }

    /**
     * 设置系统用户密码
     *
     * @param sysUserPwd 系统用户密码
     */
    public void setSysUserPwd(String sysUserPwd) {
        this.sysUserPwd = sysUserPwd;
    }

    /**
     * 获取角色
     *
     * @return role_id - 角色
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色
     *
     * @param roleId 角色
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取注册时间
     *
     * @return reg_time - 注册时间
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * 设置注册时间
     *
     * @param regTime 注册时间
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * 获取状态（0：无效；1：有效）
     *
     * @return user_status - 状态（0：无效；1：有效）
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置状态（0：无效；1：有效）
     *
     * @param userStatus 状态（0：无效；1：有效）
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "BaseAdminUser{" +
                "id=" + id +
                ", sysUserName='" + sysUserName + '\'' +
                ", sysUserPwd='" + sysUserPwd + '\'' +
                ", roleId=" + roleId +
                ", gender='" + gender + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userStatus=" + userStatus +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", regTime='" + regTime + '\'' +
                '}';
    }
}