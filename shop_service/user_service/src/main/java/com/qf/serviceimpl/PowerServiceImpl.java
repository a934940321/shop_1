package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.PowerMapper;
import com.qf.entity.Power;
import com.qf.service.IPowerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PowerServiceImpl implements IPowerService {

    @Autowired
    private PowerMapper powerMapper;

    @Override
    public List<Power> powerList() {
        List<Power> powers = powerMapper.powerList();
        return powers;
    }

    @Override
    public boolean addPower(Power power) {
        int insert = powerMapper.insert(power);
        if (insert > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Power> powerListByRid(Integer rid) {
        List<Power> powers = powerMapper.powerListByRid(rid);
        return powers;
    }


}
