package com.qf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.qf.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> queryAllRoleWithUid(Integer uid);
}
