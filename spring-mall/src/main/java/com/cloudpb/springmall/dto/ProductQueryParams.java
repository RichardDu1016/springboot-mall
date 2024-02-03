package com.cloudpb.springmall.dto;

import com.cloudpb.springmall.constant.ProductCategory;

public class ProductQueryParams {

    // 用來裝前端所傳來的篩選條件
    // 使用一個DTO類別來裝，之後若要新增比較方便
    private ProductCategory category;
    private String search;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
