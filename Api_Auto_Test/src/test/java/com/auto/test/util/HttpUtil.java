package com.auto.test.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.http.Header;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ Author：Pada_xiao
 * @ Date：15:07 2019/1/15
 * @ Description：http请求工具类，支持get、post请求
 * 支持数据提交格式：
 *  <pre>
 *   --(json格式)：application/json
 *   --(表单格式)：application/x-www-form-urlencoded
 *  </pre>
 * @ Modified By：
 * @Version:
 */
public class HttpUtil {
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json;charset=utf-8";
    private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";
    private static final Logger LOGGER = Logger.getLogger(HttpUtil.class);
    private static Map<String,String> cookies = new HashedMap<String, String>();
    public static Map<String,String> access_token = new HashMap<String, String>();
    /**
     * create by:Pada_xiao
     * description:分发请求，根据传入的请求类型调用对应的请求方法
     * create time:17:52 2019/1/15
     * @params[contenttype, url, params]
     * @returnjava.lang.String
     */
    public static  String doServer(String requestType,String url,Map<String,String>params){
        replaceToken(params);
        String result="";
        if (requestType.equals("get")){
           // LOGGER.info("调用"+requestType+"请求");
            result = doGet(url,params);
        }
      else if (requestType.equals("post")){
         // LOGGER.info("调用"+requestType+"请求");
          result = doPost(url,params);
        }
        return  result;
    }
    /**
     * create by:Pada_xiao
     * description:通过get请求方式调用接口
     * create time:18:00 2019/1/15
     * @params[url, params]
     * @returnjava.lang.String
     */
    private static String doGet(String url,Map<String,String>params){
        String param="";
        if (!params.isEmpty()){
            List<BasicNameValuePair> pairParams = prepareNameValuePair(params);
            param = URLEncodedUtils.format(pairParams, Charsets.UTF_8);}
             url +="?"+ param;
            // LOGGER.info("接口请求地址"+url);
             HttpGet httpGet = new HttpGet(url);
             if (PropertiesUtil.getProperty("Content-Type").equalsIgnoreCase(CONTENT_TYPE_APPLICATION_JSON)){
            httpGet.addHeader("Content-Type",CONTENT_TYPE_APPLICATION_JSON);
        }
        else if(PropertiesUtil.getProperty("Content-Type").equalsIgnoreCase(CONTENT_TYPE_FORM)){
            httpGet.addHeader("Content-Type",CONTENT_TYPE_FORM);
        }
        addCookieToHeader(httpGet);
        HttpClient httpClient = HttpClients.createDefault();
        String result="";
        try {
          HttpResponse httpResponse = httpClient.execute(httpGet);
          getAndStoreCookie(httpResponse);
          int code = httpResponse.getStatusLine().getStatusCode();
            String response =EntityUtils.toString(httpResponse.getEntity(),Charsets.UTF_8);
            getAndStoreToken(response);
            result = "code【"+code+"】,msg【"+response+"】";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }
    /**
     * create by:Pada_xiao
     * description:通过post请求方式调用接口
     * create time:18:00 2019/1/15
     * @params[url, params]
     * @returnjava.lang.String
     */
    private static String doPost(String url,Map<String,String>params){
        HttpPost httpPost = new HttpPost(url);
        //LOGGER.info("接口请求地址"+url);
       if(params.size()>0) {
           if (PropertiesUtil.getProperty("Content-Type").equalsIgnoreCase(CONTENT_TYPE_APPLICATION_JSON)) {
               httpPost.addHeader("Content-Type", CONTENT_TYPE_APPLICATION_JSON);
               String jsonStr = JSONObject.toJSONString(params);
               try {
                   httpPost.setEntity(new StringEntity(jsonStr));
               } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
               }
           } else if (PropertiesUtil.getProperty("Content-Type").equalsIgnoreCase(CONTENT_TYPE_FORM)) {
               httpPost.addHeader("Content-Type", CONTENT_TYPE_FORM);
               List<BasicNameValuePair> pairParams = prepareNameValuePair(params);
               try {
                   httpPost.setEntity(new UrlEncodedFormEntity(pairParams));
               } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
               }
           }
       }
            addCookieToHeader(httpPost);
            HttpClient httpClient = HttpClients.createDefault();
            String result = "";
            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                getAndStoreCookie(httpResponse);
                int code = httpResponse.getStatusLine().getStatusCode();
                String response =EntityUtils.toString(httpResponse.getEntity(),Charsets.UTF_8);
                getAndStoreToken(response);
                result = "code【"+code+"】,msg【"+response+"】";
            } catch (IOException e) {
                e.printStackTrace();
            }
        return result;
    }
    /**
     * create by:Pada_xiao
     * description:接口响应信息中取出cookie,并保存到map
     * create time:18:00 2019/1/15
     * @params[httpResponse]
     * @returnvoid
     */
    private static void getAndStoreCookie(HttpResponse httpResponse){
        Header setCookie = httpResponse.getFirstHeader("Set-Cookie");
        if (setCookie!=null){
            String setCookieValue = setCookie.getValue();
            if(setCookieValue!=null&&setCookieValue.trim().length()>0){
                String[] setCookies =setCookieValue .split(";");
                for (String cookie : setCookies) {
                    if (cookie.contains("JSESSIONID")){
                        cookies.put("JSESSIONID",cookie);
                    }
                }
            }
        }
    }
    /**
     * create by:Pada_xiao
     * description:将cookie，添加到请求头
     * create time:18:00 2019/1/15
     * @params[httpResponse]
     * @returnvoid
     */
    private static void addCookieToHeader(HttpRequest httpRequest){
        if(!cookies.isEmpty()){
            String cookie = cookies.get("JSESSIONID");
            httpRequest.addHeader("cookie",cookie);
        }
    }
    /**
     * create by:Pada_xiao
     * description:将接口返回结果中的token键值对取出并存取map集合,返回值为json字符串
     * create time:12:56 2019/1/21
     * @params [string]
     * @return void
     */
    private static  void getAndStoreToken(String string){
       if (string.contains("access_token")) {
           JSONObject jsonObject = JSONObject.parseObject(string);
           string = jsonObject.getString("access_token");
           access_token.put("access_token",string);
       }
    }
    /**
     * create by:Pada_xiao
     * description:参数中的token变量替换成Map中的token value
     * create time:14:26 2019/1/21
     * @params[]
     * @return void
     */
    public static void replaceToken(Map<String,String> params){
                String token = access_token.get("access_token");
                params.put("access_token", token);
    }
    /**
     * create by:Pada_xiao
     * description:准备键值对参数集合
     * create time:17:56 2019/1/15
     * @params[params]
     *      * @returnjava.util.List<org.apache.http.message.BasicNameValuePair>
     */
    private static List<BasicNameValuePair> prepareNameValuePair(Map<String, String>params){
        List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
       if (!params.isEmpty()){Set<String> paramNames= params.keySet();
        for (String paramName : paramNames) {
            String paramValue = params.get(paramName);
            basicNameValuePair.add(new BasicNameValuePair(paramName,paramValue));
        }
       }
        return  basicNameValuePair;
    }
}
