package com.utils;

import lombok.Getter;

@Getter
public class CaseUrl {

    //登陆接口
    public static String LOGIN="/login";
    //查询用户列表
    public static String UESR_LIST="/list";
    //修改用户
    public static String UESR_UPDATE="/update";
    //添加用户
    public static String UESR_INSERT="/insert";
    //根据用户姓名查询用户信息
    public static  String GET_USER_BY_NAME="/getUserByName";
    //删除用户
    public static  String USER_DELETE="/delete";


}
