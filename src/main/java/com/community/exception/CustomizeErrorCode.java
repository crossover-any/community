package com.community.exception;

/**
 * @Classname CustomizeErrorCode
 * @Description TODO
 * @Date 2019/10/22 21:16
 * @Created by Tengxq
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    /**
     * 问题没有找到
     */
    TARTGET_PARAM_NOT_FOUND(2002,"未选择任何问题或回复进行评论"),
    CONTENT_IS_NULL(2004,"评论内容不能为空"),
    SYS_ERROR(2222,"系统偷懒去了，请稍后再试"),
    NOT_LOGIN(2003,"请先登录后再进行操作"),
    QUESTION_NOT_FOUND(2001,"你要找的问题走丢了，换一个试试呢？");

    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code,String message){
        this.message = message;
        this.code = code;
    }
}
