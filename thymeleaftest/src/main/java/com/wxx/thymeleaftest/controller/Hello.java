package com.wxx.thymeleaftest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wxx on 2017/11/12.
 */
@Controller
@RequestMapping
public class Hello {

    @RequestMapping("/sayHello")
    public String sayHello(ModelMap map) {
        map.addAttribute("hello", "hello thymeleaf");
        return "index";
    }
}
