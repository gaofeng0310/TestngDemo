package com.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

public class CommonString {

    public  static  String getCompleteURL(String host,String action, JSONObject param) {

        StringBuilder sb = new StringBuilder("http://");
        sb.append(host);//拼接 host，例：http://www.baidu.com
        sb.append(action);//拼接 接口，例：http://www.baidu.com/user/add
        if (param == null) {//如果请求参数为空，则不拼接参数
            return sb.toString();//返回 已拼接的sb(http://www.baidu.com/user/add)
        }

        sb.append("?");// 如果param 不为空，则挂？拼接参数

        Set<String> siterator = param.keySet();
        int length = siterator.size();
        int  i=0;
        for(String s : siterator) {
            String value = param.get(s).toString();
            sb.append(s + "=").append(value);
            if(i!=length-1)
            {
                sb.append("&");
            }
            i++;
        }

        return sb.toString();

    }


}
