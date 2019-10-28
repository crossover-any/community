package com.community.service;

import com.community.enums.CommentTypeEnum;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.mapper.CommentMapper;
import com.community.mapper.QuestionMapper;
import com.community.model.Comment;
import com.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Tengxq
 * @Date 2019/10/28 14:59
 * @Describle
 * @Version 1.0
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;
    public void insert(Comment comment) {
        if (comment.getParentId() == null){
            throw  new CustomizeException(CustomizeErrorCode.TARTGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null){
            throw new CustomizeException(CustomizeErrorCode.TARTGET_PARAM_NOT_FOUND);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType()) ){
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
        }else{
            Question question = questionMapper.findQuestionById(comment.getParentId());
        }
        commentMapper.insert(comment);
    }
}
