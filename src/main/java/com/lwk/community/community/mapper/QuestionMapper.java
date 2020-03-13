package com.lwk.community.community.mapper;

import com.lwk.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {


    @Insert("insert into question(title,creator,description,tag,gmt_create,gmt_modified) values (#{title},#{creator},#{description},#{tag},#{gmt_create},#{gmt_modified})")
    void create(Question question);


    List<Question> list();
}
