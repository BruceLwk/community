package com.lwk.community.community.controller;

import com.lwk.community.community.mapper.UserMapper;
import com.lwk.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    private UserMapper userMapper;

    //测试hotfix
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("token")) {
                String value = cookie.getValue();
                User user = userMapper.findByToken(value);
                if (user!=null) {
                    request.getSession().setAttribute("user",user);
                }

                break;
            }
        }


        return "index";

    }

}
