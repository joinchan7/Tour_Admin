package com.chan.ssm.service;

import com.chan.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {
    // 查询订单信息
    List<Orders> findAll(int page, int size);

    List<Orders> findAll();

    Orders findById(String ordersId);
}
