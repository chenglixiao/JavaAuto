package com.web.auto.pojo;

import org.testng.annotations.Test;

/**
 * @ Author：Pada_xiao
 * @ Date：16:02 2019/4/9
 * @ Description：保存用例的基本信息
 * @ Modified By：
 * @Version:
 */
public class BaseData {
    protected String isNegative;
    protected String desc;
    protected String expectMsg;

    public String getIsNegative() {
        return isNegative;
    }

    public void setIsNegative(String isNegative) {
        this.isNegative = isNegative;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getExpectMsg() {
        return expectMsg;
    }

    public void setExpectMsg(String expectMsg) {
        this.expectMsg = expectMsg;
    }
}
