package com.chan.ssm.service.impl;

import com.chan.ssm.dao.OrdersDao;
import com.chan.ssm.domain.Orders;
import com.chan.ssm.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }

    @Override
    public List<Orders> findAll() {
        return ordersDao.findAll();
    }

    @Override
    public List<Orders> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }
}
