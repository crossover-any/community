package com.community.controller;

import com.community.dto.CommentDTO;
import com.community.dto.QuestionDTO;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Question;
import com.community.model.User;
import com.community.service.CommentService;
import com.community.service.QuestionServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Classname QuestionController
 * @Description TODO
 * @Date 2019/10/19 21:25
 * @Created by Tengxq
 */
@Controller
public class QuestionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionServer questionServer;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/question/{id}")
    public String toQuestionView(@PathVariable("id")Integer id, Model model){
        questionServer.updateViewCount(id);
        Question question = questionMapper.findQuestionById(Long.valueOf(id));
        if (question == null){
            throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.findByAccountId(question.getCreator().toString());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        List<CommentDTO> comments = commentService.listByQuestionId(Long.parseLong(String.valueOf(id)));
        model.addAttribute("comments",comments);
        questionDTO.setCommentCount(comments.size());
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
