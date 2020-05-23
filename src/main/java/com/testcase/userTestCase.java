package com.testcase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.UserApi;
import com.dataprovider.UserData;
import com.utils.Login;
import com.utils.Verify;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.utils.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(locations = {"classpath:applicationContext-mybatis-userDao.xml", "classpath:applicationContext-mybatis-aaaDao.xml"})
public class userTestCase  extends AbstractTestNGSpringContextTests {

    UserApi userApi = new UserApi();
    Map<String, String> header = new HashMap<String, String>();//装用户后台头信息的


    @Autowired
    Verify verify;

    @BeforeTest
    public  void login() throws IOException {
        Login login=new Login();
        String token = login.userlogin();
        header.put("token",token);
    }



    /**
     * 根据username查询用户信息
     * @param caseID
     * @param caseName
     * @param username
     * @param expData
     * @throws IOException
     */
    @Test(dataProvider = "getUserByName",dataProviderClass = UserData.class)
    public void getUserByName (String caseID,String caseName,String username,String expData) throws IOException {
        JSONObject param = new JSONObject();
        param.put("username", username);
        JSONObject json = Response.httpResponseEntityToJson(userApi.getUserByName(param,header));
        System.out.println(json);
        JSONObject dataJS=json.getJSONObject("data");

        //校验数据
        JSONObject expDataJS= JSON.parseObject(expData);
        if (json.getString("status").equals("200")){
            verify.verifySelectByUserName(username,expDataJS);
        }
    }


    /**
     * 添加用户
     * @param caseID
     * @param caseName
     * @param params
     * @param expData
     * @throws IOException
     */
    @Test(dataProvider = "userInsert",dataProviderClass = UserData.class)
    public void userInsert(String caseID,String caseName,String params,String expData) throws IOException {
        JSONObject paramJS=JSON.parseObject(params);

        JSONObject responseJS=Response.httpResponseEntityToJson(userApi.userInsert(paramJS,header,false));
        if(responseJS.getString("status").equals("200")){
            System.out.println("添加用户接口调用成功");
        }
        System.out.println(responseJS);

    }

    /**
     * 查询用户列表
     * @throws IOException
     */
    @Test
    public void userList() throws IOException {
        JSONObject params=new JSONObject();
        JSONObject responseJS = Response.httpResponseEntityToJson(userApi.userList(params,header));
        if (responseJS.getString("status").equals("200")){
            System.out.println("查询用户列表接口调用成功，返回内容："+responseJS);
        }
    }


    /**
     * 这个是最原始的调法，post请求
     * @throws IOException
     */
    @Test
    public void userInsert001() throws IOException {
        //定义json,把参数放进json里
        JSONObject param= new JSONObject();
        param.put("username",""+System.currentTimeMillis());
        param.put("password","123456");
        param.put("sex","男");
        param.put("age","12");
        //定义post请求，把请求连接放进 HTTPPost里
        HttpPost httpPost = new HttpPost("http://47.99.64.37:8081/insert");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("content-type","application/json");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response=httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());

        System.out.println(result);
    }


    /**
     * 这个是最原始的调法，get请求，无参数
     * @throws IOException
     */
    @Test
    public void userList001() throws IOException {
        String result;
        HttpGet get = new HttpGet("http://47.99.64.37:8081/list");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(get);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }

    /**
     * 这个是最原始的调法，get 请求，带参数
     * 根据username查询用户信息
     * @throws IOException
     */
    @Test
    public void getUserByName001() throws IOException {
        HttpGet httpGet =new HttpGet("http://47.99.64.37:8081/getUserByName?username=姓名");
        CloseableHttpClient httpClient = HttpClients.createDefault();

        JSONObject params= new JSONObject();
        params.put("username","姓名");
        StringEntity entity= new StringEntity(params.toString(),"utf-8");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity());
        JSONObject json=JSONObject.parseObject(result);
        System.out.println(json);
    }

}
