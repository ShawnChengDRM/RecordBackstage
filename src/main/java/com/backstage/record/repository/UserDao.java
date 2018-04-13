package com.backstage.record.repository;

import com.backstage.record.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Select("SELECT * FROM users")
    @Results(id = "users", value = {
            @Result(column = "id", property = "id", javaType = Long.class),
            @Result(column="username", property="username", javaType = String.class),
            @Result(column="password", property="password", javaType = String.class)
    })
    List<User> getAll();

    @Insert("insert into users(username, password) values(#{username},#{password})")
    int add(User user);

    @Update("update users set username=#{username},password=#{password} where id = #{id}")
    int update(User user);

    //@Delete()

    @Select("select * from users where id = #{id}")
    @ResultMap("users")
    User queryById(@Param("id") Long id);




}
