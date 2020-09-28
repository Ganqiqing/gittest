package com.gan.demo.pojo;

public class Product {
    private Integer productid;
    private String productcategories;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductcategories() {
        return productcategories;
    }

    public void setProductcategories(String productcategories) {
        this.productcategories = productcategories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productid=" + productid +
                ", productcategories='" + productcategories + '\'' +
                '}';
    }
}
