package com.community.exception;

/**
 * @Classname CustomizeException
 * @Description TODO
 * @Date 2019/10/22 21:11
 * @Created by Tengxq
 */
public class CustomizeException extends  RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode customizeErrorCode){
        this.message = customizeErrorCode.getMessage();
        this.code = customizeErrorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode(){
        return code;
    }
}
