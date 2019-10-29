package com.kk.springsecuritymybatis.mapper;

import com.kk.springsecuritymybatis.pojo.Role;
import com.kk.springsecuritymybatis.pojo.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where login_name=#{loginName}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "login_name", property = "loginName"),
            @Result(column = "password", property = "password"),
            @Result(column = "username", property = "username"),
            @Result(column = "id", property = "roles",
                    many = @Many(select = "findRoleByUser", fetchType = FetchType.EAGER))
    })
    User findByLoginName(String loginName);

    @Select("select id,authority from role r,user_role ur where r.id=ur.role_id and user_id=#{id}")
    List<Role> findRoleByUser(Long id);
}
