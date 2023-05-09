package com.bee.manager.service;

import com.bee.manager.pojo.BaseAdminRole;
import com.bee.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

/**
 * @Title: AdminRoleService
 *
 */
public interface AdminRoleService {

    PageDataResult getRoleList(Integer pageNum, Integer pageSize);

    List<BaseAdminRole> getRoles();

    BaseAdminRole findRoleById(Integer id);

    Map<String,Object> updateRole(BaseAdminRole role);

    Map<String,Object> delRole(Integer id,Integer status);

    Map<String,Object> recoverRole(Integer id,Integer status);

    Map<String,Object> addRole(BaseAdminRole role);

}
