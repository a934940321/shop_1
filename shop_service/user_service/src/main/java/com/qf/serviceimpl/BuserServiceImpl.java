package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.BuserMapper;
import com.qf.dao.UserRoleMapper;
import com.qf.entity.BackUser;
import com.qf.entity.UserRoleTable;
import com.qf.service.IBuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuserServiceImpl implements IBuserService {

    @Autowired
    private BuserMapper buserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<BackUser> backUserList() {
        List<BackUser> backUsers = buserMapper.selectList(null);
        return backUsers;
    }

    @Override
    public boolean addBuser(BackUser backUser) {
        int insert = buserMapper.insert(backUser);
        if (insert > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        int i = buserMapper.deleteById(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateRole(Integer uid, Integer[] rid) {
        //根据用户id删除用户和角色的所有关系
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("uid",uid);
        userRoleMapper.delete(wrapper);

        //将当前的用户和角色关系进行保存
        for (Integer roleid : rid) {
            UserRoleTable userRoleTable = new UserRoleTable(roleid,uid);
            userRoleMapper.insert(userRoleTable);
        }
        return true;
    }

    @Override
    public BackUser checkBuser(String username) {
        BackUser backUser = buserMapper.checkBuser(username);
        return backUser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BackUser backUser = buserMapper.checkBuser(username);
        return (UserDetails) backUser;
    }
}
