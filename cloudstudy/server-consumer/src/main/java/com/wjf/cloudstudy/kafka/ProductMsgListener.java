package com.wjf.cloudstudy.kafka;

import com.wjf.cloudstudy.entity.Product;
import com.wjf.cloudstudy.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class ProductMsgListener {
    protected Logger log = LoggerFactory.getLogger(ProductMsgListener.class);


    @Autowired
    private ProductService productService;

    @StreamListener(Sink.INPUT)
    public void onProductMsg(ProductMsg productMsg) {
        if (ProductMsg.MSG_UPDATE.equalsIgnoreCase(productMsg.getAction())) {
            this.log.info("收到消息：商品变更：商品货号为：{}",productMsg.getItemCode());
            //重新获取该商品信息
            Product product = this.productService.loadByItemCode(productMsg.getItemCode());
            if (null != product) {
                this.log.info("重新获取到商品信息为：{}",product);
            } else {
                this.log.info("货号为：{} 的商品信息不存在",productMsg.getItemCode());
            }
        } else if (ProductMsg.MSG_DELETE.equalsIgnoreCase(productMsg.getAction())) {
            this.log.info("收到消息： 商品删除：商品货号为：{}", productMsg.getItemCode());
        } else {
            this.log.info("收到消息：{}", productMsg);
        }
    }

}
