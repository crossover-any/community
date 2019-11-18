package com.community.service;

import com.community.dto.CommentDTO;
import com.community.enums.CommentTypeEnum;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.mapper.CommentMapper;
import com.community.mapper.QuestionMapper;
import com.community.mapper.UserMapper;
import com.community.model.Comment;
import com.community.model.Question;
import com.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;
    public void insert(Comment comment) {
        if (comment.getParentId() == null){
            throw  new CustomizeException(CustomizeErrorCode.TARTGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null){
            throw new CustomizeException(CustomizeErrorCode.TARTGET_PARAM_NOT_FOUND);
        }
        if ("".equals(comment.getContent()) || null == comment.getContent()){
            throw  new CustomizeException(CustomizeErrorCode.CONTENT_IS_NULL);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType()) ){
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
        }else{
            Question question = questionMapper.findQuestionById(comment.getParentId());
            if (question == null){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
        commentMapper.insert(comment);
    }

    public List<CommentDTO> listByQuestionId(long qustionId) {
        List<Comment> list = commentMapper.listByQuestionId(qustionId,CommentTypeEnum.QUESTION.getType());
        if (list.size()<1){
            return new ArrayList<CommentDTO>();
        }
        Set<Long> commentators =  list.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<User> userList = userMapper.list(commentators.toArray());
        Map<String,User> userMap = userList.stream().collect(Collectors.toMap(o -> o.getAccountId(),o->o));
        List<CommentDTO> relist = list.stream().map(comment ->{
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator().toString()));
            return  commentDTO;
        }).collect(Collectors.toList());
        return  relist;
    }
}
