package com.community.mapper;

import com.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author Tengxq
 * @Date 2019/10/28 14:53
 * @Describle
 * @Version 1.0
 */
@Mapper
public interface CommentMapper {

    @Insert("Insert into Comment (PARENT_ID,TYPE,COMMENTATOR,GMT_CREATE,GMT_MODIFIED,LIKE_COUNT,CONTENT) " +
            "VALUES(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    public void insert(Comment comment);

    @Select("select * from comment where id = #{id}")
    Comment selectByPrimaryKey(Long parentId);
}
