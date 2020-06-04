package com.chan.ssm.service;

import com.chan.ssm.domain.Product;

import java.util.List;

public interface ProductService {
    // 查询商品信息
    List<Product> findAll();

    List<Product> findAll(int page, int size);

    // 保存产品
    void save(Product product);

    // 查询指定商品
    Product findById(String id);

    void deleteById(String id);

    void update(Product product);

}
