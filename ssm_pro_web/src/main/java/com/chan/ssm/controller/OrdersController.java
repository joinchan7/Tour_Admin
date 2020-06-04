package com.chan.ssm.controller;

import com.chan.ssm.domain.Orders;
import com.chan.ssm.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    // 点击编辑,来到修改页面
    @GetMapping("/order/{id}")
    public String details(@PathVariable(name = "id") String ordersId, Model model) {
        Orders orders = ordersService.findById(ordersId);
        model.addAttribute("orders", orders);
        return "orders-show";
    }

    // 查询所有订单,spring-security不能省略前缀
    // @Secured("ROLE_ADMIN")
    @GetMapping("/orders")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer size, Model model) {
        List<Orders> orders = ordersService.findAll(page, size);
        // pageInfo 就是分页 bean
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        model.addAttribute("pageInfo", pageInfo);
        return "orders-list";
    }
}
