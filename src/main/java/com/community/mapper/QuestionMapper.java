package com.community.mapper;

import com.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname QuestionMapper
 * @Description TODO
 * @Date 2019/10/14 22:25
 * @Created by Tengxq
 */
@Mapper
public interface QuestionMapper {

    @Insert("Insert into question (TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATOR,TAG)" +
            " VALUES(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void insertQuestion(Question question);

    @Select("Select * from question Limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset")Integer page,@Param(value = "size") Integer size);

    @Select("Select count(1) from question")
    Integer allCount();

    @Select("Select * from question where creator = #{id} Limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "offset")Integer page,@Param(value = "size") Integer size,@Param(value = "id") Integer id);

    @Select("Select count(1) from question where creator = #{id}")
    Integer listCountByUserId(Integer id);

    @Select("Select * from question where id = #{id}")
    Question findQuestionById(Integer id);
}
