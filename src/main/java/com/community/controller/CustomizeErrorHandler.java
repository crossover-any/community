package com.community.controller;

import com.alibaba.fastjson.JSON;
import com.community.dto.ResultDTO;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Classname CustomizeErrorController
 * @Description TODO
 * @Date 2019/10/22 20:32
 * @Created by Tengxq
 */
@ControllerAdvice
public class CustomizeErrorHandler {

    @ExceptionHandler(Exception.class)
     ModelAndView handle(HttpServletRequest request, Throwable ex, Model model,
                         HttpServletResponse response){
        String contentType = request.getContentType();
        ResultDTO resultDTO;
        if ("application/json".equals(contentType)){
            if (ex instanceof CustomizeException){
                resultDTO = ResultDTO.erroOf((CustomizeException) ex);
            }else {
                resultDTO = ResultDTO.erroOf(CustomizeErrorCode.SYS_ERROR);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(200);
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
            }catch (Exception e){

            }finally {
                writer.close();
            }
            return null;

        }else {
            HttpStatus status = getStatus(request);
            model.addAttribute("mes",ex.getMessage());
            return new ModelAndView("error");
        }
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
