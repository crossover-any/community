package com.community.mapper;

import com.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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

    @Select("Select * from question")
    List<Question> list();
}
