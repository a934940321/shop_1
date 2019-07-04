package com.qf.service;

import com.qf.entity.Power;

import java.util.List;

public interface IPowerService {
    List<Power> powerList();

    boolean addPower(Power power);

    List<Power> powerListByRid(Integer rid);
}
