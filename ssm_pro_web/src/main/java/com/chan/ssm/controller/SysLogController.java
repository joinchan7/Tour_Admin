package com.chan.ssm.controller;

import com.chan.ssm.domain.SysLog;
import com.chan.ssm.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;


    @GetMapping("/sysLogs")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Model model) {
        List<SysLog> sysLogs = sysLogService.findAll(page, size);
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogs);
        model.addAttribute("pageInfo", pageInfo);
        return "sysLog-list";
    }

}
