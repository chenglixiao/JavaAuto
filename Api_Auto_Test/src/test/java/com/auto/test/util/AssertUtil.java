package com.auto.test.util;

import org.testng.Assert;

public class AssertUtil {

    /**
     * create by:Pada_xiao
     * description:
     * create time:15:19 2019/1/21
     * @params[actualResponseData, expectedResponseData]
     * @returnjava.lang.String
     */
    public static String assertEquals(String actualResponseData, String expectedResponseData) {
        String result = "通过";
        try {
            Assert.assertEquals(actualResponseData, expectedResponseData);
        } catch (Throwable e) {
            result = actualResponseData;
        }
        return result;
    }
}
