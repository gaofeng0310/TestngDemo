package com.dataprovider;


import org.testng.annotations.DataProvider;

public class UserData {


    @DataProvider(name = "test1")
    public Object[][] dataProvider001(){
        Object[][] params=new Object[][]{
                {"小明",12},
                {"小红",13},
                {"小白",14}
        };
        return params;
    }

    @DataProvider(name = "userInsert001")
    public Object[][] userInsert001(){
        Object[][] params=new Object[][]{
                {"userInsert001","添加用户",
                        "{\"username\":\"小明\",\"password\":\"123456\",\"age\":12,\"sex\":\"男\"}"},
        };
        return  params;
    }

    @DataProvider(name = "getUserByName")
    public Object[][] getUserByName(){
        Object[][] params=new Object[][]{
                {"getUserByName_001","查询用户存在","苏美丽",
                        "{\"uid\":26,\"password\":\"888888\",\"sex\":\"女\",\"iddelete\":0,\"age\":18,\"username\":\"苏美丽\"}"},
        };
        return params;
    }

    @DataProvider(name = "userInsert")
    public Object[][] userInsert(){
        Object[][] params=new Object[][]{
                //因添加接口做了重名校验，所以username这样传参
                {"userInsert_001","查询用户存在","{\"password\":\"888888\",\"sex\":\"女\",\"age\":18,\"username\":"+System.currentTimeMillis()+"}",
                        "{\"password\":\"888888\",\"sex\":\"女\",\"age\":18}"}
        };
        return params;
    }



}
