package com.wjf.cloudstudy.service;

import com.netflix.appinfo.EurekaInstanceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloProvider {
    protected Logger log = LoggerFactory.getLogger(HelloProvider.class);

    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;
    @Value("${server.port}")
    private Integer ServerPort = 0;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        this.log.info("/hello instanceId:{},host:{}",eurekaInstanceConfig.getInstanceId(),eurekaInstanceConfig.getHostName(false));
        return "This is Spring Family From WJF Server Port" + String.valueOf(ServerPort);
    }
}
