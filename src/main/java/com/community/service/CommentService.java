package com.community.service;

import com.community.mapper.CommentMapper;
import com.community.model.Comment;
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
    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
