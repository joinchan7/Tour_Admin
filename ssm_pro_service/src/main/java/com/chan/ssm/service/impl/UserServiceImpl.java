package com.chan.ssm.service.impl;

import com.chan.ssm.dao.UserDao;
import com.chan.ssm.domain.Role;
import com.chan.ssm.domain.UserInfo;
import com.chan.ssm.service.UserService;
import com.chan.ssm.utils.IdUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserInfo userInfo) {
        // 生成id
        userInfo.setId(IdUtils.randomId());
        // 密码加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public List<Role> findOtherRoles(String id) {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteFromUser_RoleById(id);
        userDao.deleteById(id);
    }

    @Override
    public void addRoles(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoles(userId, roleId);
        }
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<UserInfo> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUserName(username);
        System.out.println(userInfo);
        // 自己的userInfo封装成userDetails
        // return new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority(userInfo.getRoles()));
        return new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 1,
                true, true, true, getAuthority(userInfo.getRoles()));
    }

    public static List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return authorities;
    }
}
