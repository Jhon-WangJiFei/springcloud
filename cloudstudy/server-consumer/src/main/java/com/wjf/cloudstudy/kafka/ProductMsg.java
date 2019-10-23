package com.wjf.cloudstudy.kafka;

import com.google.common.base.MoreObjects;
import lombok.Data;

@Data
public class ProductMsg {
    /**消息类型：更新商品，值：{@Value}*/
    public static final String MSG_UPDATE = "update";
    /**消息类型：删除商品，值：{@Value}*/
    public static final String MSG_DELETE = "delete";

    private String action;
    private String itemCode;

    ProductMsg(){
    }

    public ProductMsg(String action, String itemCode) {
        this.action = action;
        this.itemCode = itemCode;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("action", this.getAction())
                .add("itemCode", this.getItemCode()).toString();
    }
}
