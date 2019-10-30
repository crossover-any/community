package com.community.controller;

import com.community.dto.CommentDTO;
import com.community.dto.ResultDTO;
import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import com.community.model.Comment;
import com.community.model.User;
import com.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Tengxq
 * @Date 2019/10/28 14:54
 * @Describle
 * @Version 1.0
 */
@Controller
public class   CommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @PostMapping("/comment")
    public Object comment(@RequestBody CommentDTO commentDTO , HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (null == user){
            throw new CustomizeException(CustomizeErrorCode.NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setCommentator(Long.valueOf(user.getAccountId()));;
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(Long.valueOf(1));

        commentService.insert(comment);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("提交回复成功");
        resultDTO.setCode(200);
        return resultDTO;
    }
}
