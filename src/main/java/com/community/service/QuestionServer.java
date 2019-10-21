package com.community.service;

import com.community.dto.PaginationDTO;
import com.community.dto.QuestionDTO;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Question;
import com.community.model.User;
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
        List<Question> questionList = questionMapper.list((page - 1) * size, size);
        List<QuestionDTO> list = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(userMapper.findByAccountId(question.getCreator().toString()));
            list.add(questionDTO);
        }
        return list;

    }

    public PaginationDTO myQuestionsPage(Integer page, Integer size, User user) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.listCountByUserId(user.getId());
        paginationDTO.setPagination(page,size,totalCount);
        List<Question> questionList = questionMapper.listByUserId((paginationDTO.getPage()-1)*size, size, user.getId());
        List<QuestionDTO> list = new ArrayList<>();
        for (Question question:questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        paginationDTO.setQuestions(list);
        return paginationDTO;
    }

    public void updateOrInsert(Question question){
        if (null == question.getId()){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.insertQuestion(question);
        }else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateQuestion(question);
        }
    }

    public Question findQuestionById(Integer id) {
        Question question = questionMapper.findQuestionById(id);
        return question;
    }
}
