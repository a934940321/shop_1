package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Role;
import com.qf.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("brole")
public class RoleController {
    @Reference
    private IRoleService roleService;

    @RequestMapping("toRoleManagement")
    public String toRoleManagement(Model model){
        List<Role> roles = roleService.roleList();
        model.addAttribute("roles",roles);
        return "/RoleManagement";
    }

    //添加角色
    @RequestMapping("addRole")
    private String addRole(Role role){
        boolean b = roleService.addRole(role);
        return "redirect:/brole/toRoleManagement";
    }

    //查询所有角色的同时通过用户id查询他的角色
    @RequestMapping("roleListAjax")
    @ResponseBody
    public List<Role> roleListAjax(Integer uid){

        List<Role> roles = roleService.queryAllRoleWithUid(uid);
        System.out.println(roles);
        return roles;
    }

    @RequestMapping("deleteRoleByid/{id}")
    public String deleteRoleByid(@PathVariable Integer id){
        boolean b = roleService.deleteRoleByid(id);
        return "redirect:/brole/toRoleManagement";
    }

    //根据角色id修改权限
    @RequestMapping("updatePowerByRid")
    @ResponseBody
    public String updatePowerByRid(Integer rid, @RequestParam("pids[]") Integer[] pids){
        roleService.updatePowerByRid(rid,pids);
        return "succ";
    }
}
