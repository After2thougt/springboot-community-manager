package com.bee.manager.dao;


import com.bee.manager.dto.AdminUserDTO;
import com.bee.manager.dto.UserSearchDTO;
import com.bee.manager.pojo.BaseAdminUser;
import tk.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAdminUserMapper extends MyMapper<BaseAdminUser> {

    List<AdminUserDTO> getUserList(UserSearchDTO userSearchDTO);

    BaseAdminUser getUserByUserName(@Param("sysUserName")String sysUserName,@Param("id") Long id);

    BaseAdminUser getUserByUserEmail(@Param("userEmail")String userEmail,@Param("id") Long id);

    BaseAdminUser getUserByUserId(@Param("id") Long id);

    int updateUserStatus(@Param("id") Long id,@Param("status") Integer status);

    int updateUser(BaseAdminUser user);

    BaseAdminUser findByUserName(@Param("userName") String userName);

    int updatePwd(@Param("userName") String userName,@Param("password") String password);

    int updateTime(@Param("userName") String userName,@Param("lastLoginTime") String lastLoginTime);

}