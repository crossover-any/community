package com.community.enums;

/**
 * @Classname CommentTypeEnum
 * @Description TODO
 * @Date 2019/10/28 21:31
 * @Created by Tengxq
 */
public enum CommentTypeEnum {
    /**
     * question
     */
    QUESTION(1),
    /**
     * comment
     */
    COMMENT(2);
    private int type;
    CommentTypeEnum(Integer type){
        this.type = type;
    }

    public Integer getType(){
        return type;
    }
}
