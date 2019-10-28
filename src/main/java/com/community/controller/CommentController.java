package com.community.controller;

import com.community.dto.CommentDTO;
import com.community.model.Comment;
import com.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Tengxq
 * @Date 2019/10/28 14:54
 * @Describle
 * @Version 1.0
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @PostMapping("/comment")
    public Object comment(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setCommentator(1);;
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(1);

        commentService.insert(comment);
        return null;
    }
}
