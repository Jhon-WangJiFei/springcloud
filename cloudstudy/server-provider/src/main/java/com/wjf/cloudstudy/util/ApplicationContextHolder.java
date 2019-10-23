package com.wjf.cloudstudy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * ApplicationContext辅助类
 *
 * @since 1.0.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
    private static ApplicationContext applicationContext = null;

    @Override
    public void destroy() throws Exception {
        ApplicationContextHolder.clearHolder();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("注入ApplicationContext到SpringContextHolder: {}", applicationContext);

        if (ApplicationContextHolder.applicationContext != null) {
            logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为: {}", ApplicationContextHolder.applicationContext);
        }

        ApplicationContextHolder.applicationContext = applicationContext;
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        logger.debug("清除SpringContextHolder中的ApplicationContext: {}", applicationContext);
        applicationContext = null;
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Assert.notNull(applicationContext, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }
}