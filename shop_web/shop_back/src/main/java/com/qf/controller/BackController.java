package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.BackUser;
import com.qf.entity.Role;
import com.qf.service.IBuserService;
import com.qf.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("buser")
public class BackController {

    @Reference
    private IBuserService buserService;
    @Reference
    private IRoleService roleService;

    @RequestMapping("toList")
    public String tolist(Model model){
        List<BackUser> backUsers = buserService.backUserList();
        model.addAttribute("backUsers",backUsers);
        return "buserlist";
    }

    //添加后台用户
    @RequestMapping("addBuser")
    public String addBuser(BackUser backUser){
        boolean b = buserService.addBuser(backUser);
        return "redirect:/buser/tolist";
    }

    //删除后台用户
    @RequestMapping("deleteUserById/{id}")
    public String deleteUserById(@PathVariable Integer id){
        boolean b = buserService.deleteUserById(id);
        return "redirect:/buser/tolist";
    }

    //修改用户角色信息
    @RequestMapping("updateRole")
    public String updateRole(Integer uid,Integer[] rid){
        boolean b = buserService.updateRole(uid, rid);
        return "redirect:/buser/toList";
    }





}
