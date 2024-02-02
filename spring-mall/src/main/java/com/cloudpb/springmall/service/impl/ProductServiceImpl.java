package com.cloudpb.springmall.service.impl;

import com.cloudpb.springmall.dao.ProductDao;
import com.cloudpb.springmall.model.Product;
import com.cloudpb.springmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
