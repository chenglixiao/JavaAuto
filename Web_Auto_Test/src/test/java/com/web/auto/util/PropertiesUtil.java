package com.web.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ Author：Pada_xiao
 * @ Date：22:41 2018/11/27
 * @ Description：解析properties文件，获取文件路径
 * @ Modified By：
 * @Version:
 */
public class PropertiesUtil {
    static Properties properties = new Properties();
    static {
        InputStream iStream=null;
        try {
            iStream = new FileInputStream(new File("src/test/resources/config.properties"));
            properties.load(iStream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if (iStream!=null){
                try {
                    iStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * @Author：Pada_xiao
     * @Description：根据特定的key获取对应的value
     * @Date：23:01 2018/11/27
     * @Param：[sql,values]
     * @return：java.lang.String
     **/
    public static String getValueByKey(String key) {
        String value = null;
        try {
            value = properties.getProperty(key);
        }catch (Exception E){
            System.out.println("文件找不到");
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(getValueByKey("login.url"));
    }
}