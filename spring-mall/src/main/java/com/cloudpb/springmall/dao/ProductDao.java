package com.cloudpb.springmall.dao;

import com.cloudpb.springmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
