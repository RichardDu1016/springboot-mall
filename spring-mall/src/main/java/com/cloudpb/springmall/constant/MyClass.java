package com.cloudpb.springmall.constant;

public class MyClass {
    public static void main(String[] args) {
        ProductCategory category = ProductCategory.FOOD;
        String s = category.name();
        System.out.println(s);

        String s2 = "CAR";
        ProductCategory category1 = ProductCategory.valueOf(s2);
        System.out.println(category1);
    }
}
