package com.bee.manager.shiro;

import com.bee.manager.pojo.BaseAdminUser;
import com.bee.manager.service.AdminPermissionService;
import com.bee.manager.service.AdminRoleService;
import com.bee.manager.service.AdminUserService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Shiro
 */
public class MyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private AdminUserService userService;

    @Autowired
    private AdminRoleService roleService;

    @Autowired
    private AdminPermissionService permissionService;


    /**
     * 用户授权，在访问授权资源时调用，返回当前用户具有的角色和权限
     * @param arg0
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        //授权
        // 添加权限 和 角色信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        return authorizationInfo;
    }


    /**
     * 用户登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken用于存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("用户登录认证：验证当前Subject时获取到token为：" + ReflectionToStringBuilder
                .toString(token, ToStringStyle.MULTI_LINE_STYLE));

        String username = token.getUsername();
        // 调用数据层
        BaseAdminUser sysUser = userService.findByUserName(username);
        logger.debug("用户登录认证！用户信息user：" + sysUser);
        if (sysUser == null) {
            // 用户不存在
            return null;
        }
        // 返回密码
        return new SimpleAuthenticationInfo(sysUser, sysUser.getSysUserPwd(), ByteSource.Util.bytes(username), getName());

    }

}
