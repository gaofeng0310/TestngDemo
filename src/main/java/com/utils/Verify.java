package com.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.AaaMapper.AaaMapper;
import com.dao.UserMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;


@ContextConfiguration(locations = {"classpath:applicationContext-mybatis-userDao.xml", "classpath:applicationContext-mybatis-aaaDao.xml"})
public class Verify extends AbstractTestNGSpringContextTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AaaMapper aaaMapper;

    @Test
    public  void verifySelectByUserName(String username,JSONObject expCheckDataJs){

        List<Map<String,String>> list=userMapper.selectUserByUserName(username);
        Assert.assertEquals(list.get(0).get("username"),expCheckDataJs.getString("username"));

        System.out.println("t_user 表校验通过");
    }


    @Test
    public  void bbbb(){

        System.out.println("进行 quartz 库查询数据");
        System.out.println(aaaMapper.selectUserByUserName("开心"));
    }



}
