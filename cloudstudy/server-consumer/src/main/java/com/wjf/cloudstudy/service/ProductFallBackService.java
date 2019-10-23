package com.wjf.cloudstudy.service;

import com.wjf.cloudstudy.entity.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductFallBackService implements ProductService {
    @Override
    public List<Product> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Product loadByItemCode(String itemCode) {
        return new Product("error","未知","fallback",0);
    }
}
