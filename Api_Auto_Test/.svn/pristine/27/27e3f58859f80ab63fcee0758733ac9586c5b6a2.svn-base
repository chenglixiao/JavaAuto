package com.auto.test.util;


import java.io.*;
import java.util.Properties;

/**
 * @ Author：Pada_xiao
 * @ Date：15:10 2019/1/15
 * @ Description：解析properties文件
 * @ Modified By：
 * @Version:
 */
public class PropertiesUtil {
   static Properties properties = new Properties();
    static {
        InputStream inputStream = null;
        try {
             inputStream = new FileInputStream(new File("src/test/resources/CaseInfo.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * create by:Pada_xiao
     * description:从已加载的文件中根据key获取value
     * create time:17:40 2019/1/16
     * @params[key]
     * @returnjava.lang.String
     */
    public static String getProperty(String key){
        String value = properties.getProperty(key);
        return value;
    }
    /**
     * create by:Pada_xiao
     * description:读取指定文件信息
     * create time:17:50 2019/1/16
     * @params[filePath, key]
     * @returnjava.lang.String
     */
    public static  String getProperty(String filePath,String key){
        Properties properties = new Properties();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(new File(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return properties.getProperty(key);
    }
}

