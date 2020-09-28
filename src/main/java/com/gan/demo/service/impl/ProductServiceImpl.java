package com.gan.demo.service.impl;

import com.gan.demo.mapper.ProductMapper;
import com.gan.demo.pojo.Product;
import com.gan.demo.service.ProductService;
import com.gan.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired(required = false)
    private ProductMapper productMapper;

    @Autowired
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Product> searchProduct() {
        List list = new ArrayList<>();
        if (redisUtil.hasKey("productList")) {
            log.info("从redis获取数据");
            list = redisList.get("productList", 0, -1);
        } else {
            list = productMapper.searchProduct();
            log.info("从数据库取得数据");
            log.info("存放的redis");
            redisList.set("productList", list);
        }
        return list;
    }
}
