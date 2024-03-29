package com.community.mapper;


import com.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2019/10/13 12:09
 * @Created by Tengxq
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED,BIO,AVATAR_URL) VALUES(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insertUser(User user);

    @Select("Select * from user where token = #{token}")
    User findByToken(String token);

    @Select("Select * from user where id = #{creator}")
    User findById(Integer creator);

    @Select("Select * from user where ACCOUNT_ID = #{accountId}")
    User findByAccountId(String accountId);

    @Update("Update user set name = #{name},token = #{token},gmt_modified = #{gmtModified},bio = #{bio},avatar_url = #{avatarUrl}")
    void updateUser(User user);

    @Select({
            "<script>",
            "select * from user where account_id in",
            "<foreach collection='commentators' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<User> list(@Param("commentators") Object[] commentators);
}
