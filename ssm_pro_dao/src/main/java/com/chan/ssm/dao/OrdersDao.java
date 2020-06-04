package com.chan.ssm.dao;

import com.chan.ssm.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {
    // 查询订单信息
    @Select("select * from orders")
    @Result(property = "product", column = "productId", one = @One(select = "com.chan.ssm.dao.ProductDao.findById"))
    List<Orders> findAll();

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(property = "product", column = "productId", one = @One(select = "com.chan.ssm.dao.ProductDao.findById")),
            @Result(property = "member", column = "memberId", one = @One(select = "com.chan.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers", column = "id", many = @Many(select = "com.chan.ssm.dao.TravellerDao.findByOrdersId")),
    })
    Orders findById(String id);
}
