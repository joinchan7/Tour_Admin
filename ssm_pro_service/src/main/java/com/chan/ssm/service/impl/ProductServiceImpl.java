package com.chan.ssm.service.impl;

import com.chan.ssm.dao.ProductDao;
import com.chan.ssm.domain.Product;
import com.chan.ssm.service.ProductService;
import com.chan.ssm.utils.IdUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteById(String id) {
        productDao.deleteById(id);
    }

    @Override
    public void save(Product product) {
        product.setId(IdUtils.randomId());
        productDao.save(product);
    }

    @Override
    public List<Product> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }
}
