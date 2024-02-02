package com.cloudpb.springmall.service;

import com.cloudpb.springmall.constant.ProductCategory;
import com.cloudpb.springmall.dto.ProductRequest;
import com.cloudpb.springmall.model.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer productId);

    List<Product> getProducts(ProductCategory category, String search);


    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);


    void deleteProduct(Integer productId);

}
