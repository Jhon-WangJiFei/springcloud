package com.wjf.cloudstudy.service;

import com.wjf.cloudstudy.cloudBus.ProductEvent;
import com.wjf.cloudstudy.entity.Product;
import com.wjf.cloudstudy.kafka.ProductMsg;
import com.wjf.cloudstudy.util.ApplicationContextHolder;
import com.wjf.cloudstudy.util.RemoteApplicationEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    protected Logger log = LoggerFactory.getLogger(ProductService.class);

    //private Source source;
    private List<Product> productList;

    @Autowired
    public ProductService() {
        //this.source = source;
        this.productList = this.buildProducts();
    }

    /**
     * 获取商品列表
     * @return
     */
    public List<Product> listAll() {
        return this.productList;
    }

    public Product findOne(String itemCode){
        List<Product> products = this.buildProducts();
        for (Product product : products) {
            if (product.getItemCode().equalsIgnoreCase(itemCode))
                return product;
        }
        return null;
    }

    /**
     *  更新商品
     * */
    public Product save(Product product) {
        for (Product sourceProduct : this.productList){
            if (sourceProduct.getItemCode().equalsIgnoreCase(product.getItemCode())) {
                sourceProduct.setName(sourceProduct.getName() + "_NEW");
                sourceProduct.setPrice(sourceProduct.getPrice() + 500);
                product = sourceProduct;
                break;
            }
        }
        //发送消息到消息中间件
        //this.sendMsg(ProductMsg.MSG_UPDATE, product.getItemCode());
        //发布事件到消息总线
        this.fireEvent(ProductEvent.MSG_UPDATE,product);
        return product;
    }

    /**
     *  发送消息
     * */
    protected void sendMsg(String msgAction, String itemCode) {
        ProductMsg productMsg = new ProductMsg(msgAction,itemCode);
        this.log.info("商品更新：发送商品消息 ：{}",productMsg);

        //调用消息中间件
        //this.source.output().send(MessageBuilder.withPayload(productMsg).build());
    }

    /**
     *  发布事件到消息中间件
     * */
    protected void fireEvent(String eventAction, Product product) {
        //初始化事件
        ProductEvent productEvent = new ProductEvent(product,
                ApplicationContextHolder.getApplicationContext().getId(),"*:**",eventAction
                ,product.getItemCode());

        //发布事件
        RemoteApplicationEventPublisher.publishEvent(productEvent);
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
