package com.chan.ssm.service.impl;

import com.chan.ssm.dao.PermissionDao;
import com.chan.ssm.domain.Permission;
import com.chan.ssm.service.PermissionService;
import com.chan.ssm.utils.IdUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permission.setId(IdUtils.randomId());
        permissionDao.save(permission);
    }

    @Override
    public void deleteById(String id) {
        permissionDao.deleteFromRole_Permission(id);
        permissionDao.deleteById(id);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }
}
