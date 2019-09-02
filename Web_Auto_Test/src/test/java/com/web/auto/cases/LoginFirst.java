package com.web.auto.cases;

import com.web.auto.util.BehaviorUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * @ Author：Pada_xiao
 * @ Date：19:23 2019/3/12
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class LoginFirst extends LaunchBrowser {
    @BeforeClass
    @Parameters(value={"username","passwd"})
    public static void Login(String username,String passwd){
        BehaviorUtil.interview("login.url");
        BehaviorUtil.input("登录","用户名",username);
        BehaviorUtil.input("登录","密码",passwd);
        BehaviorUtil.click("登录","登录");
    }
}
