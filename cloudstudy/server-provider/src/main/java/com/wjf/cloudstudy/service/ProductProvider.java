package com.wjf.cloudstudy.service;

import com.wjf.cloudstudy.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductProvider {
    protected Logger log = LoggerFactory.getLogger(ProductProvider.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> listAll(){
        return this.buildProducts();
    }


    @RequestMapping(value = "upa/{itemCode}", method = RequestMethod.POST)
    public Product save(@PathVariable String itemCode){
        Product product = this.productService.findOne(itemCode);
        if (null != product) {
            this.productService.save(product);
        }
        return product;
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public Product detail(@PathVariable String itemCode){
        List<Product> products = this.buildProducts();
        for (Product product : products) {
            if (product.getItemCode().equalsIgnoreCase(itemCode))
                return product;
        }
        return null;
    }

    protected List<Product> buildProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("1001","足球服","耐克",1000));
        products.add(new Product("1002","篮球裤","耐克",2000));
        products.add(new Product("1003","篮球服","耐克",1500));
        products.add(new Product("1004","足球短裤","耐克",1500));
        products.add(new Product("1005","足球鞋","耐克",2000));
        products.add(new Product("1006","篮球鞋","耐克",2000));
        products.add(new Product("1007","棒球帽","耐克",1500));

        return products;
    }
}
