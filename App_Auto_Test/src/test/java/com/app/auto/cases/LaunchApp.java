package com.app.auto.cases;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ Author：Pada_xiao
 * @ Date：17:10 2018/12/23
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class LaunchApp {
    private final static Logger LOGGER = Logger.getLogger(LaunchApp.class);
    public static AndroidDriver<WebElement> androidDriver =null;
    @Test
    public static void launchApp() {
        //1、连接设备
        //（1）创建配置对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //（2）添加deviceName
        desiredCapabilities.setCapability("deviceName","192.168.1.2:5556");
        //（3）添加platformName
        desiredCapabilities.setCapability("platformName","Android");
        //（4）添加appPackge
        desiredCapabilities.setCapability("appPackage","com.lemon.lemonban");
        //（5）添加appActivity
        desiredCapabilities.setCapability("appActivity",".activity.WelcomeActivity");
        //desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        //（6）确定是否清除应用数据,在当前session前重置app状态
        desiredCapabilities.setCapability("noReset","false");
        //2、打开测试app程序，在初始化android驱动的时候，需要在构造方法里面传入两个参数
        //（1）与appium通讯的地址
        //（2）初始化的配置对象
        try {
            androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //设置页面加载时间10s
        androidDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //设置显示等待时间5s,找不到元素抛出no such Element异常
        androidDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }
    @AfterSuite
    public void tearDown(){
        LOGGER.info("关闭App");
        androidDriver.quit();
    }

}
