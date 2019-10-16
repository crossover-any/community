package com.community.controller;

import com.community.dto.PaginationDTO;
import com.community.dto.QuestionDTO;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.User;
import com.community.service.QuestionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2019/10/9 20:33
 * @Created by Tengxq
 */
@Controller
public class HelloController {

    @Autowired
    private QuestionServer questionServer;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name",required = false,defaultValue = "world") String name, Model model){
        model.addAttribute("name",name);
        return "greeting";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "2")Integer size){
        Cookie[] cookies = request.getCookies();
        if (null != cookies){
            for(Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questions = questionServer.list(page,size);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestions(questions);
        Integer totalCount = questionMapper.allCount();
        paginationDTO.setPagination(page,size,totalCount);
        model.addAttribute("page",paginationDTO);
        return "index";
    }
}
