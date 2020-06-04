package com.chan.ssm.controller;

import com.chan.ssm.domain.Permission;
import com.chan.ssm.domain.Role;
import com.chan.ssm.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 来到添加页面
    @GetMapping("/role")
    public String toAddPage() {
        return "role-add";
    }

    // 角色添加
    @PostMapping("/role")
    public String addRole(Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    // 角色详情
    @GetMapping("/role/{id}")
    public String details(@PathVariable String id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "role-show";
    }

    // 删除指定角色
    @DeleteMapping("/role/{id}")
    public String deleteRole(@PathVariable String id) {
        roleService.deleteById(id);
        return "redirect:/roles";
    }

    // 查询所有角色
    @GetMapping("/roles")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer size, Model model) {
        List<Role> roles = roleService.findAll(page, size);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        model.addAttribute("pageInfo", pageInfo);
        return "role-list";
    }

    // 来到权限添加页面,查询角色和角色可添加的权限
    @GetMapping("/role/{id}/permissions")
    public String toPermissionAddPage(@PathVariable String id, Model model) {
        // 根据id查询用户
        Role role = roleService.findById(id);
        // 根据id查询可添加的角色
        List<Permission> otherPermissions = roleService.findOtherPermissions(id);
        model.addAttribute("role", role);
        model.addAttribute("permissionList", otherPermissions);
        return "role-permission-add";
    }

    // 来到角色添加页面,查询用户和用户可添加的角色
    @PostMapping("/role/{id}/permissions")
    public String addPermissions(@PathVariable("id") String roleId, @RequestParam("ids") String[] permissionIds) {
        roleService.addPermissions(roleId, permissionIds);
        return "redirect:/roles";
    }

}
