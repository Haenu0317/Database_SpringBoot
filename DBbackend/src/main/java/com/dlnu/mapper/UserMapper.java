package com.dlnu.mapper;

import com.dlnu.entity.auth.AccountUser;
import com.dlnu.entity.user.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from db_account where username = #{text} or email= #{text}")
    Account findAccountByUsernameOrEmail(String text);

    @Select("select * from db_account where username = #{username} or email= #{username}")
    AccountUser findAccountUserByUsernameOrEmail(String username);
    @Insert("insert into db_account(email,username,password) values(#{email},#{username},#{password})")
    int createAccount(String username, String password, String email);
}
