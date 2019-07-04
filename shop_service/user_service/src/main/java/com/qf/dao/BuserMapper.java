package com.qf.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.BackUser;


public interface BuserMapper extends BaseMapper<BackUser> {

    BackUser checkBuser(String username);

}
