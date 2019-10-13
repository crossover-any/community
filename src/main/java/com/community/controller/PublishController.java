package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Classname PublishController
 * @Description TODO
 * @Date 2019/10/13 19:18
 * @Created by Tengxq
 */
@Controller
public class PublishController {

    @GetMapping("/publish")
    public String toPublishView(){
        return "publish";
    }
}
