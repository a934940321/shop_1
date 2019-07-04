package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.RoleMapper;
import com.qf.dao.RolePowerMapper;
import com.qf.dao.UserRoleMapper;
import com.qf.entity.Role;
import com.qf.entity.RolePowerTable;
import com.qf.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService  {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePowerMapper rolePowerMapper;

    @Override
    public List<Role> roleList() {
        List<Role> roles = roleMapper.selectList(null);
        return roles;
    }

    @Override
    public boolean addRole(Role role) {
        int insert = roleMapper.insert(role);
        if (insert > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Role> queryAllRoleWithUid(Integer uid) {
        List<Role> roles = roleMapper.queryAllRoleWithUid(uid);
        return roles;
    }

    @Override
    public boolean deleteRoleByid(Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("rid",id);
        userRoleMapper.delete(wrapper);
        int i = roleMapper.deleteById(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    //修改角色权限
    @Override
    @Transactional
    public boolean updatePowerByRid(Integer rid, Integer[] pids) {
        //先通过角色id删除该角色的所有权限
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("rid",rid);
        rolePowerMapper.delete(wrapper);

        if (pids[0] != 0){
            //再给角色重新建立角色
            for (Integer pid : pids) {
                RolePowerTable rolePowerTable = new RolePowerTable(pid,rid);
                rolePowerMapper.insert(rolePowerTable);
            }
        }

        return true;
    }
}
