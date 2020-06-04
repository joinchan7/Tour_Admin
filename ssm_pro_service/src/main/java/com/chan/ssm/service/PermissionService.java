package com.chan.ssm.service;

import com.chan.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    List<Permission> findAll(Integer page, Integer size);

    void save(Permission permission);

    Permission findById(String id);

    void deleteById(String id);

}
