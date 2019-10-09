package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2019/10/9 20:33
 * @Created by Tengxq
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name",required = false,defaultValue = "world") String name, Model model){
        model.addAttribute("name",name);
        return "greeting";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
