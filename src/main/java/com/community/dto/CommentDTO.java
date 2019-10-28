package com.community.dto;

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
}
