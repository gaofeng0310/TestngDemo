package com.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpHeleper {


    private CloseableHttpClient httpClient= HttpClients.createDefault();
    private static final Logger logger = Logger.getLogger(HttpHeleper.class);

    /**
     *
     * @param user_host  系统的地址，例：www.baidu.com
     * @param action     接口地址，例：/user/add
     * @param params     需要传的参数，可为空
     * @param headers    请求头信息，可为空
     * @return
     */
    public HttpResponse get(String user_host, String action, JSONObject params, Map<String, String> headers) throws IOException {
        HttpGet httpGet = new HttpGet(CommonString.getCompleteURL(user_host,action,params));

        if (headers != null) {//如果 headers 不等于null
            Set<String> keys = headers.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                httpGet.addHeader(key, headers.get(key));//循环遍历取值headers的值，放在请求头中
            }
        }

        HttpResponse httpResponse= httpClient.execute(httpGet);
        return httpResponse;
    }

    /**
     *
     * @param user_host
     * @param action
     * @param params
     * @param headers
     * @param completeUrl  如果params是需要拼接在链接后面，completeUrl：true，反之，则 completeUrl：false
     * @return
     * @throws IOException
     */
    public  HttpResponse post(String user_host, String action, JSONObject params, Map<String, String> headers, boolean completeUrl,String content_type) throws IOException {
        if(completeUrl == false) {  //请求参数不跟在链接后面
            //下面这行重点，定义httpPost请求，把：域名+接口+参数，放进来
            HttpPost httpPost = new HttpPost(CommonString.getCompleteURL(user_host, action, null));//拼接：域名+接口

            if (headers != null) {//如果有头信息，循环遍历头信息，把头信息放在请求里
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();
                    httpPost.addHeader(key, headers.get(key));
                }
            }

            logger.info("params:" + params);
            if (params != null) {//参数不为空，则把参数放在请求里

                StringEntity entity = new StringEntity(params.toString(), "UTF-8");//这行重点，把参数放进StringEntity，
                httpPost.setHeader("content-type",content_type);//设置请求类型
                httpPost.setEntity(entity);//这行重点，把entity放进httpPost，这样传递参数
            }
            HttpResponse response = httpClient.execute(httpPost);//这一行重点，执行请求

            return response;
        }
        else {//completeUrl=true ,则需要吧参数拼在链接后面
            HttpPost httpPost = new HttpPost(CommonString.getCompleteURL(user_host, action, params));//拼接：域名+接口+参数

            if (headers != null) {//如果有头信息，循环遍历头信息，把头信息放在请求里
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();
                    httpPost.addHeader(key, headers.get(key));
                }
            }

            logger.info("params:" + params);
            HttpResponse response = httpClient.execute(httpPost);//这一行重点，执行请求
            return response;
        }
    }


}
