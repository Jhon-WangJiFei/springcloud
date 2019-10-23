package com.wjf.cloudstudy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.ApplicationContext;

public class RemoteApplicationEventPublisher {
    protected static Logger logger = LoggerFactory.getLogger(RemoteApplicationEventPublisher.class);

    /**
     * 发布一个事件
     * @param event
     */
    public static void publishEvent(RemoteApplicationEvent event){
        ApplicationContext context = ApplicationContextHolder.getApplicationContext();
        if(null != context) {
            context.publishEvent(event);
            logger.info("已发布事件:{}", event);
        }else{
            logger.warn("无法获取到当前Spring上下文信息，不能够发布事件");
        }
    }
}
