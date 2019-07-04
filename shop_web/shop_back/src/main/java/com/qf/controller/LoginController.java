package com.qf.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.BackUser;
import com.qf.service.IBuserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("buser")
//添加了该注解的方法，添加的attribute都会放到session里面
//@SessionAttributes("login")
public class LoginController {

    @Reference
    private IBuserService buserService;

    @RequestMapping("toLogin")
    public String buserToLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session){
        BackUser backUser = buserService.checkBuser(username);
        System.err.println(backUser);
        if (backUser == null){
            model.addAttribute("msg","用户名错误");
            return "login";
        }
        if (!backUser.getPassword().equals(password)){
            model.addAttribute("msg","密码错误");
            return "login";
        }

        session.setAttribute("buser",backUser);
        return "index";
    }
}
