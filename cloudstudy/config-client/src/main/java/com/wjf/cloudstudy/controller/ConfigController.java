package com.wjf.cloudstudy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cfg")
@RestController
public class ConfigController {

    @Value("${foo}")
    String foo;

    @Value("${bar}")
    String bar;

    @RequestMapping(value = "/foo")
    public String foo() {
        return foo + "---" + bar;
    }
}
