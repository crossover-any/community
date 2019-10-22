package com.community.exception;

/**
 * @Classname CustomizeErrorCode
 * @Description TODO
 * @Date 2019/10/22 21:16
 * @Created by Tengxq
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你要找的问题走丢了，换一个试试呢？");

    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }

    CustomizeErrorCode(String message){
        this.message = message;
    }
}
