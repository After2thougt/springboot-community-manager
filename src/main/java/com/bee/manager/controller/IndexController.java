package com.bee.manager.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: LoginController
 * @Description:
 *
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("login")
    public String tologin(){
        logger.info("to login page");
        return "login";
    }

    @RequestMapping("home")
    public String home(){
        logger.info("to home page");
        return "home";
    }

    @RequestMapping("logout")
    public String logout(){
        logger.info("exit system");
        Subject subject = SecurityUtils.getSubject();
        subject.logout(); // shiro底层删除session的会话信息
        return "redirect:login";
    }

}
