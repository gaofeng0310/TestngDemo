package com.api;

import com.alibaba.fastjson.JSONObject;
import com.utils.CaseUrl;
import com.utils.DynamicParam;
import com.utils.HttpHeleper;
import com.utils.Tool;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;



public class UserApi extends AbstractTestNGSpringContextTests {

    @Autowired
    DynamicParam dynamicParam;
    Tool tool = new Tool();

    String USER_HOST="47.99.64.37:8081";
    HttpHeleper httpHeleper = new HttpHeleper();
    String  APPLICATION_JSON="application/json";
    String APPLICATION_X_FROM="application/x-www-form-urlencoded";



    /***
     * 根据username查询用户信息
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public HttpResponse getUserByName(JSONObject params, Map<String, String> headers) throws IOException {
        HttpResponse response = httpHeleper.get(USER_HOST, CaseUrl.GET_USER_BY_NAME, params, headers);
        return response;
    }

    /**
     * 添加用户
     * @param params   请求参数
     * @param headers  请求头信息
     * @param completeUrl  如果params是需要拼接在链接后面，completeUrl：true，反之，则 completeUrl：false
     * @return
     */
    public HttpResponse userInsert(JSONObject params,Map<String,String> headers,boolean completeUrl) throws IOException {
        HttpResponse response=httpHeleper.post(USER_HOST,CaseUrl.UESR_INSERT,params,headers,false,APPLICATION_JSON);
        return response;
    }

    /**
     * 查询用例列表
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public HttpResponse userList(JSONObject params,Map<String,String> headers) throws IOException {
        HttpResponse response = httpHeleper.get(USER_HOST,CaseUrl.UESR_LIST,params,headers);
        return response;
    }


    public HttpResponse userLogin(JSONObject params,Map<String,String> headers) throws IOException {
        HttpResponse response = httpHeleper.get(USER_HOST,CaseUrl.LOGIN,params,headers);
        return response;
    }




}
