package com.ecnu.back.dao;
import com.ecnu.back.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select count(*) from phishmaster_user.user where email = #{email}")
    boolean existsByUsername(String email);

    @Select("select count(*) from phishmaster_user.user where email = #{email} and password = #{password}")
    boolean checkPassword(String email, String password);

    @Insert("insert into phishmaster_user.user (username, email, password) values (#{username}, #{email}, #{password})")
    void register(User user);
}
