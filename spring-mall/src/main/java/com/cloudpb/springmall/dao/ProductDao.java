package com.cloudpb.springmall.dao;

import com.cloudpb.springmall.dto.ProductRequest;
import com.cloudpb.springmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
