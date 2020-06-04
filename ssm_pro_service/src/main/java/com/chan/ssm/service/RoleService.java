package com.chan.ssm.service;

import com.chan.ssm.domain.Permission;
import com.chan.ssm.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    List<Role> findAll(Integer page, Integer size);

    void save(Role role);

    List<Role> findByUserId(String userId);

    Role findById(String id);

    void deleteById(String id);

    List<Permission> findOtherPermissions(String id);

    void addPermissions(String roleId, String[] permissionIds);

}
