package com.web.auto.cases;

import com.web.auto.util.BehaviorUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @ Author：Pada_xiao
 * @ Date：18:06 2019/4/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class LoginCase extends LaunchBrowser{
    @Test(dataProvider = "" ,priority=0)
    public void reverseLogin(String username,String passwd){
        BehaviorUtil.input("登录","用户名",username);
        BehaviorUtil.input("登录","密码",username);
        BehaviorUtil.click("登录","登录");
    }
    @Test(dataProvider = "",priority = 1)
    public void positiveLogin(String username,String passwd){
        BehaviorUtil.input("登录","用户名",username);
        BehaviorUtil.input("登录","密码",username);
        BehaviorUtil.click("登录","登录");
    }

}
