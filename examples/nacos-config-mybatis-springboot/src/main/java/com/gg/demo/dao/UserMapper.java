package com.gg.demo.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from t_user where username = #{username}")
    List<User> findByName(@Param("username") String name);

    @Select("select * from t_user ")
    List<User> findAll();

    @Insert("insert into t_user ( username ,phone ) values (#{username},#{phone})")
    int insert(@Param("username") String username, @Param("phone") String phone);

    @Delete("delete from t_user where userid = #{userid}")
    int delete(@Param("userid") Integer id);

}
