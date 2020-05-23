package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Tool {


    public String getProValue(String value){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/conf/para.properties");
        Properties properties = new Properties();
        try{
            properties.load(inputStream);
        } catch (IOException e1){
            e1.printStackTrace();
        }
        return (String) properties.get(value);
    }




}
