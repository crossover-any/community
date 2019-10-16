package com.community.service;

import com.community.dto.QuestionDTO;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Question;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname QuestionServer
 * @Description TODO
 * @Date 2019/10/15 21:33
 * @Created by Tengxq
 */
@Service
public class QuestionServer {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public List<QuestionDTO> list(Integer page, Integer size) {
        List<Question> questionList = questionMapper.list((page-1)*size,size);
        List<QuestionDTO> list = new ArrayList<>();
        for(Question question :questionList){
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(userMapper.findById(question.getCreator()));
            list.add(questionDTO);
        }
        return list;

    }
}
