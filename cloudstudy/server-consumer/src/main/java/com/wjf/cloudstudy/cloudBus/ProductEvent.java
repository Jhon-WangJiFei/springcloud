package com.wjf.cloudstudy.cloudBus;

import com.google.common.base.MoreObjects;
import lombok.Data;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

@Data
public class ProductEvent extends RemoteApplicationEvent {

    /**消息类型：更新商品，值：{@Value}*/
    public static final String MSG_UPDATE = "update";
    /**消息类型：删除商品，值：{@Value}*/
    public static final String MSG_DELETE = "delete";

    private String action;
    private String itemCode;

    public ProductEvent(){
    }

    public ProductEvent(Object source, String originService, String destinationService, String action, String itemCode) {
        super(source,originService,destinationService);
        this.action = action;
        this.itemCode = itemCode;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("action", this.getAction())
                .add("itemCode", this.getItemCode()).toString();
    }
}
