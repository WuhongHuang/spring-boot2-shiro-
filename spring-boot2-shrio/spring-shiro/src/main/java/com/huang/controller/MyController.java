package com.huang.controller;

import com.huang.pojo.user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @authour huang
 */
@Controller
public class MyController {

    private static final transient Logger log = LoggerFactory.getLogger(MyController.class);


    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello, Shiro");
        return "index";
    }
    @RequestMapping("/user/add")
    public String add(){
        log.info("有add请求进来");
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //获取登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //验证令牌，验证登录
            subject.login(token);
            user currentUser = (user)subject.getPrincipal();
            model.addAttribute("user",currentUser);
            log.info(currentUser.toString());
            return "index";
        } catch (UnknownAccountException uae) {
            //定义了自定义关于，用户登录错误的情况,用户不存在情况。
            log.info("There is no user with username of " + token.getPrincipal());
            model.addAttribute("info","不存在此用户");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            model.addAttribute("info","密码错误");
            return "login";
        } catch (LockedAccountException lae) {
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
        }
        return null;
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "对不起，你不具备此权限";
    }


}
