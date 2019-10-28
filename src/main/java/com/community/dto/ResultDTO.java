package com.community.dto;

import com.community.exception.CustomizeErrorCode;
import com.community.exception.CustomizeException;
import lombok.Data;

/**
 * @Classname ResultDTO
 * @Description TODO
 * @Date 2019/10/28 22:00
 * @Created by Tengxq
 */
@Data
public class ResultDTO {
    private String message;
    private Integer code;

    public static ResultDTO erroOf(String message, Integer code) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;

    }

    public static ResultDTO erroOf(CustomizeException e) {

        return erroOf(e.getMessage(), e.getCode());
    }

    public static ResultDTO erroOf(CustomizeErrorCode sysError) {
        return erroOf(sysError.getMessage(),sysError.getCode());
    }
}
