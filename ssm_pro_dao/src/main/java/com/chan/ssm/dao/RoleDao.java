package com.chan.ssm.dao;

import com.chan.ssm.domain.Permission;
import com.chan.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    @Select("select * from role")
    List<Role> findAll();

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(id = "roleMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "permissions", column = "id", many = @Many(select = "com.chan.ssm.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId);

    @Select("select * from role where id=#{id}")
    @ResultMap("roleMap")
    Role findById(String id);

    @Insert("insert into role values(#{id},#{roleName},#{roleDesc})")
    void save(Role role);

    @Delete("delete from users_role where roleId=#{id}")
    void deleteFromUser_RoleById(String id);

    @Delete("delete from role_permission where roleId=#{id}")
    void deleteFromRole_PermissionById(String id);

    @Delete("delete from role where id=#{id}")
    void deleteById(String id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermissions(String id);

    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissions(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
