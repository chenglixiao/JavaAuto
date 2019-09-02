package com.web.auto.cases;

import com.web.auto.pojo.Register;
import com.web.auto.util.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @ Author：Pada_xiao
 * @ Date：20:34 2018/11/27
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class RegisterCase extends LaunchBrowser{
    public static List<Register> registerDatas;
    static{
       List<Register> list = ExcelUtil.load(PropertiesUtil.getValueByKey("register.path"), "register",Register.class);
       registerDatas.addAll(list);
    }
    @Test(dataProvider = "reverseDatas",priority = 0)
    public void  reverseRegister(String mobilePhone,String password,String passwordConfirm,String expectMsg ){
        BehaviorUtil.interview("register.page");
        BehaviorUtil.input("注册","用户名",mobilePhone);
        BehaviorUtil.input("注册","密码",password);
        BehaviorUtil.input("注册","确认密码",passwordConfirm);
        BehaviorUtil.input("注册","验证码",VerifyCodeUtil.getVerifyCode());
        BehaviorUtil.click("注册","注册");
        String actualexpectMsg = BehaviorUtil.getText("注册","提示信息");
        AssertUtil.assertEquals(actualexpectMsg,expectMsg);
    }
    @Test(dataProvider="positiveDatas",priority = 1)
    public void  positiveRegister(String mobilePhone,String password,String passwordConfirm,String expectMsg) {
        BehaviorUtil.interview("register.page");
        BehaviorUtil.input("注册","用户名",mobilePhone);
        BehaviorUtil.input("注册","密码",password);
        BehaviorUtil.input("注册","确认密码",passwordConfirm);
        BehaviorUtil.input("注册","验证码",VerifyCodeUtil.getVerifyCode());
        BehaviorUtil.click("注册","注册");
        AssertUtil.assertContains(driver.getTitle(),expectMsg);
    }

    @DataProvider
    public Object[][] positiveDatas(){
        String [] cellNames = {"MobilePhone","Password","PasswordConfirm","ExpectMsg"};
        Object[][] positiveDatas = GetDatasUtil.getDatasByFlag("1",cellNames,registerDatas);
        return positiveDatas ;
    }
    @DataProvider
    public Object[][] reverseDatas(){
        String [] cellNames = {"MobilePhone","Password","PasswordConfirm","ExpectMsg"};
        Object[][] reverseDatas = GetDatasUtil.getDatasByFlag("0",cellNames,registerDatas);
        return reverseDatas ;
    }
}
