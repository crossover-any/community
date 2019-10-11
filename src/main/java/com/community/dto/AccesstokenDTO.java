package com.community.dto;

import lombok.Data;

/**
 * @Classname AccesstokenDTO
 * @Description TODO
 * @Date 2019/10/11 20:37
 * @Created by Tengxq
 */
@Data
public class AccesstokenDTO {
    private String code;
    private String redirect_uri;
    private String state;
    private String client_id;
    private String client_secret;
}
