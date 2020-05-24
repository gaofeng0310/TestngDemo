package com.dao.UserMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserMapper {


    @Select("<script>" +
            "SELECT * FROM t_user " +
            "<where> "+
            "<if test=\"uid != null and uid != ''\">  uid = #{uid} </if>"+
            "<if test=\"username != null and username !=''\">  username = #{username} </if> "+
            "</where>"+
            "</script>")
    List<Map<String,String>> selectUserByUserName(@Param("uid") String uid,@Param("username") String username);


    @Select({"<script>",
            "SELECT * FROM t_user",
            "WHERE 1=1",
            "<when test='uid!=null'>",
            "AND uid = #{uid}",
            "</when>",
            "<when test='username!=null'>",
            "AND username = #{username}",
            "</when>",
            "</script>"})
    List<Map<String,String>> selectUserByUserName00(@Param("uid") String uid,@Param("username") String username);


    @Select("<script>" +
            "SELECT * FROM t_user " +
            "<where>"+
            "<if test=\"uid != null\">  uid = #{uid} </if>"+
            "<if test=\"username != null\"> and username = #{username} </if>"+
            "</where>"+
            "</script>")
    List<Map<String,String>> selectUser(@Param("uid") String uid,@Param("username") String username);


}
