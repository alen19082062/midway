package com.gg.web.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 对 t_user 表的操作，以 sql 语句为主
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM t_user")
    @Results({
            @Result(property = "userId",  column = "id" ),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "phone", column = "mobile")
    })
    List<User> getAll();

    @Select("select * from t_user where user_name = #{username}")
    List<User> findByName(@Param("username") String name);

    @Select("select * from t_user ")
    List<User> findAll();

    @Insert("insert into t_user ( username ,phone ) values (#{username},#{phone})")
    int insert(@Param("username") String username, @Param("phone") String phone);

    @Delete("delete from t_user where userid = #{userid}")
    int delete(@Param("userid") Integer id);

}
