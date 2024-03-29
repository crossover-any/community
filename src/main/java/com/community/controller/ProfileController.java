package com.community.controller;

import com.community.dto.PaginationDTO;
import com.community.dto.QuestionDTO;
import com.community.mapper.UserMapper;
import com.community.model.User;
import com.community.service.QuestionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Tengxq
 * @Date 2019/10/16 16:38
 * @Describle
 * @Version 1.0
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionServer questionServer;

    @GetMapping("/myQuestions")
    public String myQuestions(Model model, HttpServletRequest request,
                              @RequestParam(name = "page",defaultValue = "1")Integer page,
                              @RequestParam(name = "size",defaultValue = "2")Integer size){
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (null != cookies){
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    break;
                }
            }
        }
        if (null == user){
            return "redirect:/";
        }
        PaginationDTO paginationDTO =  questionServer.myQuestionsPage(page,size,user);
        model.addAttribute("sectionName","我的问题");
        model.addAttribute("section","myQuestions");
        model.addAttribute("page",paginationDTO);
        return "profile";
    }

    @GetMapping("/latestReply")
    public String latestReply( Model model, HttpServletRequest request){

        model.addAttribute("sectionName","最新回复");
        model.addAttribute("section","latestReply");
        return "profile";
    }
}
