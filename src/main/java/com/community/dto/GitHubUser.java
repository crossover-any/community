package com.community.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Classname GitHubUser
 * @Description TODO
 * @Date 2019/10/11 21:18
 * @Created by Tengxq
 */
@Data
@ToString
public class GitHubUser {
    private String name;
    private String bio;
    private Long id;
    private String avatar_url;
}
