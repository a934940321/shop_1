package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Power;
import com.qf.service.IPowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("power")
public class PowerController {

    @Reference
    private IPowerService powerService;

    @RequestMapping("toList")
    public String toList(Model model){
        List<Power> powers = powerService.powerList();
        model.addAttribute("powers",powers);
        return "/powerlist";
    }

    @RequestMapping("listajax")
    @ResponseBody
    public List<Power> listajax(){
        List<Power> powers = powerService.powerList();
        return powers;
    }

    //添加权限
    @RequestMapping("addPower")
    public String addPower(Power power){
        boolean b = powerService.addPower(power);
        return "redirect:/power/toList";
    }

    //根据角色id查询所有权限
    @RequestMapping("queryPowersByRid")
    @ResponseBody
    public List<Power> queryPowersByRid(Integer rid){
        List<Power> powers = powerService.powerListByRid(rid);
        return powers;
    }



}
