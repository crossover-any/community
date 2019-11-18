package com.community.model;

import lombok.Data;
import lombok.ToString;

/**
 * @Classname Question
 * @Description TODO
 * @Date 2019/10/14 20:28
 * @Created by Tengxq
 */
@Data
@ToString
public class Question {
    private  Long id;
    private  String title;
    private  String description;
    private  String tag;
    private  Long gmtCreate;
    private  Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
