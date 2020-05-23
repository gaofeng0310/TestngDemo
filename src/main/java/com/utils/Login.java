package com.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.applet.Main;

import java.io.IOException;

public class Login {



    public String userlogin() throws IOException {
        String token = null;
        HttpGet httpGet =new HttpGet("http://47.99.64.37:8081/login?username=gaog5&password=123456");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity());
        JSONObject resJs=JSONObject.parseObject(result);
        if (resJs.getString("status").equals("200")){
             token=resJs.getJSONObject("data").getString("token");
            System.out.println("登陆成功，token："+token);
        }else {
            System.out.println("登陆失败！！！");
        }
        return token;
    }

    public static void main(String[] args) throws IOException {
        Login login=new Login();
        login.userlogin();
    }

}
