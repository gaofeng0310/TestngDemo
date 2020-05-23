package com.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class Response {


    public static final String DEFAULT_CHARSET = "UTF-8";
    int status;
    String responseStr;

    public Response(int responseStatus, String responseStr){
        this.status = responseStatus;
        this.responseStr = responseStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseStr() {
        return responseStr;
    }



    /**
     * 将HttpResponse的entity转换为JSONObject
     * @author luosirui
     * @param response
     * @return
     * @throws IOException
     */
    public static JSONObject httpResponseEntityToJson(HttpResponse response) throws IOException {
        if(response.getStatusLine().getStatusCode() != 200) {
            System.out.println("请求状态码不是200 , code is "+response.getStatusLine().getStatusCode());
            System.out.println("reason is "+response.getStatusLine().getReasonPhrase());
        }
        String str = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        JSONObject responseJs=JSONObject.parseObject(str);
        return responseJs;
    }

}
