package com.community.model;

import lombok.Data;
import lombok.ToString;

/**
 * @Classname User
 * @Description TODO
 * @Date 2019/10/13 12:17
 * @Created by Tengxq
 */

@Data
@ToString
public class User {
    private Integer id;
    private String name;
    private String token;
    private String accountId;
    private Long gmtCreate;
    private Long gmtModified;
}
