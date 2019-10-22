package com.community.controller;

import com.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname CustomizeErrorController
 * @Description TODO
 * @Date 2019/10/22 20:32
 * @Created by Tengxq
 */
@ControllerAdvice
public class CustomizeErrorHandler {

    @ExceptionHandler(Exception.class)
     ModelAndView handle(HttpServletRequest request, Throwable ex, Model model){
        HttpStatus status = getStatus(request);
        model.addAttribute("mes",ex.getMessage());
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null)
        return HttpStatus.INTERNAL_SERVER_ERROR;
        return HttpStatus.valueOf(statusCode);
    }
}
