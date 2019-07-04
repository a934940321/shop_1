package com.qf.service;

import com.qf.entity.BackUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IBuserService extends UserDetailsService {
    List<BackUser> backUserList();

    boolean addBuser(BackUser backUser);

    boolean deleteUserById(Integer id);

    boolean updateRole(Integer uid,Integer[] rid);

    BackUser checkBuser(String username);
}
