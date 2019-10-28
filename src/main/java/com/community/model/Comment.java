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
    private long id;
    private long parentId;
    private int type;
    private int commentator;
    private long gmtCreate;
    private long gmtModified;
    private long likeCount;
    private String content;
}
