package com.community.mapper;


import com.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}
