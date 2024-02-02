package com.cloudpb.springmall.service;

import com.cloudpb.springmall.dto.ProductRequest;
import com.cloudpb.springmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);


    void deleteProduct(Integer productId);
}
