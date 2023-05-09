package com.bee.manager.service;

import com.bee.manager.dto.UserSearchDTO;
import com.bee.manager.pojo.BaseAdminUser;
import com.bee.manager.response.PageDataResult;

import java.util.Date;
import java.util.Map;


/**
 * @Title: AdminUserService
 *
 */
public interface AdminUserService {

    PageDataResult getUserList(UserSearchDTO userSearch, Integer pageNum, Integer pageSize);

    Map<String,Object> addUser(BaseAdminUser user);

    Map<String,Object> updateUser(BaseAdminUser user);

    BaseAdminUser getUserById(Long id);

    BaseAdminUser findByUserName(String userName);

    int updatePwd(String userName,String password);

    int updateTime(String userName);

    Map<String, Object> delUser(Long id,Integer status);

    Map<String, Object> recoverUser(Long id,Integer status);
}
