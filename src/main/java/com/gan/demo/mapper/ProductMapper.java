package com.gan.demo.mapper;

import com.gan.demo.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    public List<Product> searchProduct();
}
