package com.community.controller;

import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Question;
import com.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname PublishController
 * @Description TODO
 * @Date 2019/10/13 19:18
 * @Created by Tengxq
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String toPublishView(){
        return "publish";
    }

    @PostMapping("/publish")
    public String addQuestion(@RequestParam(value = "title",required = false)String title,
                              @RequestParam(value = "tag",required = false)String tag,
                              @RequestParam(value = "description",required = false)String description,
                              HttpServletRequest request,
                              Model model){
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (null != cookies ){
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    user = userMapper.findByToken(cookie.getValue());
                }
            }
        }
        if (null == user){
            return "redirect:/";
        }
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (StringUtils.isEmpty(title)){
            model.addAttribute("error","error:标题不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(description)){
            model.addAttribute("error","error:问题描述不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)){
            model.addAttribute("error","error:标签不能为空");
            return "publish";
        }
        Question question = new Question();
        question.setTag(tag);
        question.setTitle(title);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insertQuestion(question);
        return "redirect:/";
    }
}
