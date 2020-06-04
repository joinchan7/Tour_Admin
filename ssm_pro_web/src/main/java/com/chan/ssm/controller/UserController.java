package com.chan.ssm.controller;

import com.chan.ssm.domain.Role;
import com.chan.ssm.domain.UserInfo;
import com.chan.ssm.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    // 来到添加页面
    @GetMapping("/user")
    public String toAddPage() {
        return "user-add";
    }

    // 用户添加
    @PostMapping("/user")
    public String addUser(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:/users";
    }

    // 删除指定角色
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    // 查询所有用户
    @GetMapping("/users")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer size, Model model) {
        List<UserInfo> userInfos = userService.findAll(page, size);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        model.addAttribute("pageInfo", pageInfo);
        return "user-list";
    }

    // 点击编辑,来到修改页面
    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable String id, Model model) {
        UserInfo userInfo = userService.findById(id);
        model.addAttribute("user", userInfo);
        return "user-show";
    }

    // 来到角色添加页面,查询用户和用户可添加的角色
    @GetMapping("/user/{id}/roles")
    public String toRoleAddPage(@PathVariable String id, Model model) {
        // 根据id查询用户
        UserInfo userInfo = userService.findById(id);
        // 根据id查询可添加的角色
        List<Role> otherRoles = userService.findOtherRoles(id);
        model.addAttribute("user", userInfo);
        model.addAttribute("roleList", otherRoles);
        return "user-role-add";
    }

    // 来到角色添加页面,查询用户和用户可添加的角色
    @PostMapping("/user/{id}/roles")
    public String addRoles(@PathVariable("id") String userId, @RequestParam("ids") String[] roleIds) {
        userService.addRoles(userId, roleIds);
        return "redirect:/users";
    }
}
