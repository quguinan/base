package com.cms.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Desc:properties文件获取工具类
 * Created by hafiz.zhang on 2016/9/15.
 */
public class PropertyUtilLisence {
    @Test
    public static String  readFile() {  
    	String lsreturn="";
        try {  
            InputStream in =  PropertyUtilLisence.class.getClassLoader().getResourceAsStream("license.lic");
            byte bs[] = new byte[in.available()];  
            in.read(bs);  
//            System.out.println(new String(bs));  
            in.close();  
            return new String(bs);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
        return  lsreturn;
    }   
}