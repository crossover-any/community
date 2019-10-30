package com.community.model;

import lombok.Data;

/**
 * @Author Tengxq
 * @Date 2019/10/28 14:48
 * @Describle
 * @Version 1.0
 */
@Data
public class Comment {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
}
