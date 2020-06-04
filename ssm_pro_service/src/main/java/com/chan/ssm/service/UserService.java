package com.chan.ssm.service;

import com.chan.ssm.domain.Role;
import com.chan.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserInfo> findAll();

    List<UserInfo> findAll(Integer page, Integer size);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String id);

    void addRoles(String userId, String[] roleIds);

    void deleteById(String id);

}
