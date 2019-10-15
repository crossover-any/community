package com.community.dto;

import com.community.model.User;
import lombok.Data;

/**
 * @Classname QuestionDTO
 * @Description TODO
 * @Date 2019/10/15 21:32
 * @Created by Tengxq
 */
@Data
public class QuestionDTO {
    private  Integer id;
    private  String title;
    private  String description;
    private  String tag;
    private  Long gmtCreate;
    private  Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
