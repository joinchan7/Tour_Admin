package com.chan.ssm.dao;

import com.chan.ssm.domain.Role;
import com.chan.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Result(property = "roles", column = "id", many = @Many(select = "com.chan.ssm.dao.RoleDao.findByUserId"))
    UserInfo findByUserName(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roles", column = "id", many = @Many(select = "com.chan.ssm.dao.RoleDao.findByUserId"))
    })
    UserInfo findById(String id);


    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    List<Role> findOtherRoles(String id);


    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoles(@Param("userId") String userId, @Param("roleId") String roleId);


    @Delete("delete from users_role where userId=#{id}")
    void deleteFromUser_RoleById(String id);

    @Delete("delete from users where id=#{id}")
    void deleteById(String id);
}
