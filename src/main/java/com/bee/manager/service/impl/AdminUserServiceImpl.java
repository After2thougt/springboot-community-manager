package com.bee.manager.service.impl;

import com.bee.manager.common.utils.DateUtils;
import com.bee.manager.common.utils.DigestUtils;
import com.bee.manager.common.utils.PatternUtil;
import com.bee.manager.dao.BaseAdminUserMapper;
import com.bee.manager.dto.AdminUserDTO;
import com.bee.manager.dto.UserSearchDTO;
import com.bee.manager.pojo.BaseAdminUser;
import com.bee.manager.response.PageDataResult;
import com.bee.manager.service.AdminUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseAdminUserMapper baseAdminUserMapper;

    /**
     * 获取用户列表
     *
     * @param userSearch
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageDataResult getUserList(UserSearchDTO userSearch, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<AdminUserDTO> baseAdminUsers = baseAdminUserMapper.getUserList(userSearch);

        PageHelper.startPage(pageNum, pageSize);

        if(baseAdminUsers.size() != 0){
            PageInfo<AdminUserDTO> pageInfo = new PageInfo<>(baseAdminUsers);
            pageDataResult.setList(baseAdminUsers);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }


    @Override
    public Map<String,Object> addUser(BaseAdminUser user) {
        Map<String,Object> data = new HashMap();
        try {
            BaseAdminUser old = baseAdminUserMapper.getUserByUserName(user.getSysUserName(),null);
            if(old != null){
                data.put("code",0);
                data.put("msg","用户名已存在");
                logger.error("用户[新增]，结果=用户名已存在");
                return data;
            }
            if(baseAdminUserMapper.getUserByUserEmail(user.getUserEmail(), null) != null){
                data.put("code",0);
                data.put("msg","邮箱已存在");
                logger.error("邮箱[新增]，结果=邮箱已存在");
                return data;
            }

            if(!PatternUtil.isEmail(user.getUserEmail())){
                data.put("code",0);
                data.put("msg","邮箱格式不正确");
                logger.error("邮箱[新增]，结果=邮箱格式不正确");
                return data;
            }

            String username = user.getSysUserName();
            if(user.getSysUserPwd() == null){
                String password = DigestUtils.Md5(username,"admin");
                user.setSysUserPwd(password);
            }else{
                String password = DigestUtils.Md5(username,user.getSysUserPwd());
                user.setSysUserPwd(password);
            }
            user.setRegTime(new Date());

            user.setUserStatus(0);
            int result = baseAdminUserMapper.insert(user);
            if(result == 0){
                data.put("code",0);
                data.put("msg","新增失败");
                logger.error("用户[新增]，结果=新增失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功");
            logger.info("用户[新增]，结果=新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户[新增]异常", e);
            return data;
        }
        return data;
    }


    @Override
    public Map <String, Object> updateUser(BaseAdminUser user) {
        Map<String,Object> data = new HashMap();
        Long id = user.getId();
        BaseAdminUser old = baseAdminUserMapper.getUserByUserName(user.getSysUserName(), id);
        if(old != null){
            data.put("code",0);
            data.put("msg","用户名已存在");
            logger.error("用户[更新]，结果=用户名已存在");
            return data;
        }
        if(baseAdminUserMapper.getUserByUserEmail(user.getUserEmail(), id) != null){
            data.put("code",0);
            data.put("msg","邮箱已存在");
            logger.error("邮箱[新增]，结果=邮箱已存在");
            return data;
        }

        String username = user.getSysUserName();
        if(user.getSysUserPwd() != null){
            String password = DigestUtils.Md5(username,user.getSysUserPwd());
            user.setSysUserPwd(password);
        }

        if(!PatternUtil.isEmail(user.getUserEmail())){
            data.put("code",0);
            data.put("msg","邮箱格式不正确");
            logger.error("邮箱[新增]，结果=邮箱格式不正确");
            return data;
        }

        int result = baseAdminUserMapper.updateUser(user);
        if(result == 0){
            data.put("code",0);
            data.put("msg","更新失败");
            logger.error("用户[更新]，结果=更新失败");
            return data;
        }
        data.put("code",1);
        data.put("msg","更新成功");
        logger.info("用户[更新]，结果=更新成功");
        return data;
    }

    @Override
    public BaseAdminUser getUserById(Long id) {
        return baseAdminUserMapper.selectByPrimaryKey(id);
    }


    @Override
    public Map <String, Object> delUser(Long id,Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除用户
            int result = baseAdminUserMapper.updateUserStatus(id,status);
            if(result == 0){
                data.put("code",0);
                data.put("msg","操作失败");
                logger.error("操作失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","操作成功");
            logger.info("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("禁用用户异常！", e);
        }
        return data;
    }

    @Override
    public Map <String, Object> recoverUser(Long id, Integer status) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = baseAdminUserMapper.updateUserStatus(id,status);
            if(result == 0){
                data.put("code",0);
                data.put("msg","操作失败");
            }
            data.put("code",1);
            data.put("msg","操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("操作异常", e);
        }
        return data;
    }

    @Override
    public BaseAdminUser findByUserName(String userName) {
        return baseAdminUserMapper.findByUserName(userName);
    }


    @Override
    public int updatePwd(String userName, String password) {
        password = DigestUtils.Md5(userName,password);
        return baseAdminUserMapper.updatePwd(userName,password);
    }

    @Override
    public int updateTime(String userName) {

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowDate = dateFormat.format(date);

        return baseAdminUserMapper.updateTime(userName, nowDate);
    }
}
