package com.bee.manager.dto;

;import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * AdminUserDTO
 *
 */
@Data
public class AdminUserDTO {

    private Long id;

    private String sysUserName;

    private String sysUserPwd;

    private Integer roleId;

    private String gender;

    private String roleName;

    private String userEmail;

    private Integer userStatus;

    private Date lastLoginTime;

    private Date regTime;

    private String headImgUrl;

    private String location;

    private String introduce;



}
