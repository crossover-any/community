package com.community.mapper;


import com.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2019/10/13 12:09
 * @Created by Tengxq
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER (ACCOUNT_ID,NAME,TOKEN,GMT_CREATE,GMT_MODIFIED,BIO) VALUES(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio})")
    void insertUser(User user);

    @Select("Select * from user where token = #{token}")
    User findByToken(String token);
}
