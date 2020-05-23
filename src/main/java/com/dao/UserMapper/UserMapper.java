package com.dao.UserMapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {

    @Select("SELECT * from t_user WHERE username=#{0}")
    List<Map<String,String>> selectUserByUserName(String username);

}
