package com.chan.ssm.controller;

import com.chan.ssm.domain.Product;
import com.chan.ssm.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    // 点击添加,来到添加页面
    @GetMapping("/product")
    public String toAddPage() {
        return "product-add";
    }

    // 点击编辑,来到修改页面
    @GetMapping("/product/{id}")
    public String toEditPage(@PathVariable String id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    // 添加产品
    @PostMapping("/product")
    public String addProduct(Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    // 修改产品
    @PutMapping("/product")
    public String updateProduct(Product product) {
        System.out.println(product.getId());
        productService.update(product);
        return "redirect:/products";
    }

    // 删除指定产品
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    // 查询所有产品,jsr-250可以省略前缀
    // @RolesAllowed("ADMIN")
    @GetMapping("/products")
    public String findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer size, Model model) {
        List<Product> products = productService.findAll(page, size);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo", pageInfo);
        return "product-list";
    }

}
