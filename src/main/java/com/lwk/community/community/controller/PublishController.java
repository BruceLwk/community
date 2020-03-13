package com.lwk.community.community.controller;

import com.lwk.community.community.mapper.QuestionMapper;
import com.lwk.community.community.mapper.UserMapper;
import com.lwk.community.community.model.Question;
import com.lwk.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {

        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ) {


        model.addAttribute("title",title);
        model.addAttribute("tag",tag);
        model.addAttribute("description",description);

        if (title==null||title.trim().equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if (description==null||description.trim().equals("")){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }

        if (tag==null||tag.trim().equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }



        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {

                }
                break;
            }

        }

        if (user == null) {

            model.addAttribute("error", "用户未登录");

            return "publish";
        }

        Question question = new Question();
        question.setDescription(description);
        question.setTag(tag);
        question.setTitle(title);
        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionMapper.create(question);


        return "redirect:/";
    }


}
