package com.chan.ssm.controller;

import com.chan.ssm.domain.Permission;
import com.chan.ssm.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // 来到添加页面
    @GetMapping("/permission")
    public String toAddPage() {
        return "permission-add";
    }

    // 权限添加
    // @PreAuthorize("authentication.principal.username=='tom'")
    @PostMapping("/permission")
    public String addPermission(Permission permission) {
        permissionService.save(permission);
        return "redirect:/permissions";
    }

    // 权限详情
    @GetMapping("/permission/{id}")
    public String details(@PathVariable String id, Model model) {
        Permission permission = permissionService.findById(id);
        model.addAttribute("permission", permission);
        return "permission-show";
    }

    // 删除指定权限
    @DeleteMapping("/permission/{id}")
    public String deletePermission(@PathVariable String id) {
        permissionService.deleteById(id);
        return "redirect:/permissions";
    }

    // 查询所有权限
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/permissions")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer size, Model model) {
        List<Permission> permissions = permissionService.findAll(page, size);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
        model.addAttribute("pageInfo", pageInfo);
        return "permission-list";
    }

}
