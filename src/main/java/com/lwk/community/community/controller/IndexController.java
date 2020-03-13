package com.lwk.community.community.controller;

import com.lwk.community.community.mapper.QuestionMapper;
import com.lwk.community.community.mapper.UserMapper;
import com.lwk.community.community.model.Question;
import com.lwk.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    //测试hotfix
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {


            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("token")) {
                    String value = cookie.getValue();
                    User user = userMapper.findByToken(value);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }

                    break;
                }
            }


        }

        List<Question> questionList = questionMapper.list();
        model.addAttribute("question",questionList);

        return "index";

    }

}
