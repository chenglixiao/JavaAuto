package com.web.auto.cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * @ Author：chenglixiao
 * @ Date：10:46 2018/11/15
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class OperationalNavigationBar extends LaunchBrowser {
    @Test
    public void operationalNavigationBar() throws InterruptedException {
        driver.manage().window().maximize();
        logger.info("访问www.our51.com");
        driver.navigate().to("http://www.our51.com/cas");
        logger.info("定位用户名输入框并输入用户名");
        driver.findElement(By.id("username")).sendKeys("test0508");
        logger.info("定位密码输入框并输入密码");
        driver.findElement(By.id("password")).sendKeys("t123456789");
        Thread.sleep(2000);
        logger.info("刷新网页");
        driver.navigate().refresh();
        Thread.sleep(2000);

        logger.info("访问百度");
        driver.navigate().to("http://www.baidu.com");
        try {
            Thread.sleep(2000);
            logger.info("后退至登录页面");
            driver.navigate().back();
            Thread.sleep(2000);
            logger.info("请进至百度页面");
            driver.navigate().forward();
            logger.info("后退至登录页面");
            driver.navigate().back();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
