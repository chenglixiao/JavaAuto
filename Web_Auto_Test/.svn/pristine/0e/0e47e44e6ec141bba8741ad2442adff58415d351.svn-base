package com.web.auto.util;

import com.web.auto.cases.LaunchBrowser;

/**
 * @ Author:Pada_xiao
 * @ Date：19:49 2018/11/28
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class VerifyCodeUtil {
        public static final String COOKIE_NAME = "verifycode";
    /**
     * @Author：Pada_xiao
     * @Description：从浏览器cookie中获取验证码
     * @Date：19:52 2018/11/28
     * @Param：[]
     * @return：java.lang.String
     **/
        public static String getVerifyCode(){
            return LaunchBrowser.driver.manage().getCookieNamed(COOKIE_NAME).getValue();
        }
}
