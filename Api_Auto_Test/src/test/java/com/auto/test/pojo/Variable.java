package com.auto.test.pojo;

import com.auto.test.util.ExcelUtil;
import com.auto.test.util.PropertiesUtil;

import java.util.List;

/**
 * @ Author：Pada_xiao
 * @ Date：14:34 2019/1/26
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class Variable {
    private String name;
    private String staticValue;
    private String dynamicValue;
    private String remarks;

    public Variable() {
    }

    public Variable(String name, String staticValue, String dynamicValue, String remarks) {
        this.name = name;
        this.staticValue = staticValue;
        this.dynamicValue = dynamicValue;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaticValue() {
        return staticValue;
    }

    public void setStaticValue(String staticValue) {
        this.staticValue = staticValue;
    }

    public String getDynamicValue() {
        return dynamicValue;
    }

    public void setDynamicValue(String dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return ("name【"+name+"】，staticValue【"+staticValue+"】，dynamicValue【"+dynamicValue+"】");
    }

}
