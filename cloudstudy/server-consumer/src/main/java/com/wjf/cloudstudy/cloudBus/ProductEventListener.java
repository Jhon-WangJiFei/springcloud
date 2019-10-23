package com.wjf.cloudstudy.cloudBus;

import com.wjf.cloudstudy.entity.Product;
import com.wjf.cloudstudy.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener implements ApplicationListener<ProductEvent> {
    protected Logger log = LoggerFactory.getLogger(ProductEventListener.class);

    @Autowired
    private ProductService productService;
    @Override
    public void onApplicationEvent(ProductEvent event) {
        if (ProductEvent.MSG_UPDATE.equalsIgnoreCase(event.getAction())) {
            this.log.info("收到消息：商品变更：商品货号为：{}",event.getItemCode());
            //重新获取该商品信息
            Product product = this.productService.loadByItemCode(event.getItemCode());
            if (null != product) {
                this.log.info("重新获取到商品信息为：{}",product);
            } else {
                this.log.info("货号为：{} 的商品信息不存在",event.getItemCode());
            }
        } else if (ProductEvent.MSG_DELETE.equalsIgnoreCase(event.getAction())) {
            this.log.info("收到消息： 商品删除：商品货号为：{}", event.getItemCode());
        } else {
            this.log.info("收到消息：{}", event);
        }
    }
}
