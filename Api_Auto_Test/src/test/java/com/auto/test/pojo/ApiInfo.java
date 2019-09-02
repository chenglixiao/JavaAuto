package com.auto.test.pojo;

/**
 * @ Author：Pada_xiao
 * @ Date：9:21 2019/1/17
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class ApiInfo {
    private String apiId;
    private String apiName;
    private String Type;
    private String url;
    public String getApiId() {
        return apiId;
    }
    public void setApiId(String apiId) {
        this.apiId = apiId;
    }
    public String getApiName() {
        return apiName;
    }
    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("apiId="+apiId+",apiName="+apiName+",Type="+Type+",url="+url);
    }
}
