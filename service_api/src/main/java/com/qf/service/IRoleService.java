package com.qf.service;

import com.qf.entity.Role;

import java.util.List;

public interface IRoleService {
    List<Role> roleList();

    boolean addRole(Role role);

    List<Role> queryAllRoleWithUid(Integer uid);

    boolean deleteRoleByid(Integer id);

    boolean updatePowerByRid(Integer rid,Integer[] pids);
}
