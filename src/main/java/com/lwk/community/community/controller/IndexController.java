package com.lwk.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //测试hotfix
    @GetMapping("/")
    public String index() {

        return "index";

    }

}
