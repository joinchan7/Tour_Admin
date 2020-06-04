package com.chan.ssm.dao;

import com.chan.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id=#{id}")
    Permission findById(String id);

    @Delete("delete from permission where id=#{id}")
    void deleteById(String id);

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_Permission(String id);
}
