package com.chan.ssm.service.impl;

import com.chan.ssm.dao.RoleDao;
import com.chan.ssm.domain.Permission;
import com.chan.ssm.domain.Role;
import com.chan.ssm.service.RoleService;
import com.chan.ssm.utils.IdUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void save(Role role) {
        role.setId(IdUtils.randomId());
        roleDao.save(role);
    }

    @Override
    public List<Permission> findOtherPermissions(String id) {
        return roleDao.findOtherPermissions(id);
    }

    @Override
    public void addPermissions(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissions(roleId, permissionId);
        }
    }

    @Override
    public void deleteById(String id) {
        roleDao.deleteFromUser_RoleById(id);
        roleDao.deleteFromRole_PermissionById(id);
        roleDao.deleteById(id);
    }

    @Override
    public List<Role> findByUserId(String userId) {
        return roleDao.findByUserId(userId);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> findAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }
}
