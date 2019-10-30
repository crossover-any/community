package com.community.dto;

import com.community.model.User;
import lombok.Data;

/**
 * @Author Tengxq
 * @Date 2019/10/28 14:52
 * @Describle
 * @Version 1.0
 */
@Data
public class CommentDTO {
    private String content;
    private Integer type;
    private Long parentId;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private User user;
}
