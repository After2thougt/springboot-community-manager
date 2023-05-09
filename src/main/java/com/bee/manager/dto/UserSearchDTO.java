package com.bee.manager.dto;


import lombok.Data;

/**
 * 用户搜索DTO
 *
 */
@Data
public class UserSearchDTO {
    private String sysUserName;

    private String commonUser;

    private String userEmail;

    private String roleId;

    private String startTime;

    private String endTime;

}
