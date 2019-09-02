package com.web.auto.cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * @ Author：chenglixiao
 * @ Date：10:15 2018/11/15
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class LocationElement extends LaunchBrowser {
    @Test
    public void locationElement() {
        logger.info("访问网址www.our51.com");
        driver.get("http://www.our51.com/cas");
        logger.info("当前页面title"+ driver.getTitle());
        try {
            logger.info("等待1s");
            Thread.sleep(1000);
            logger.info("定位用户名输入框并输入用户名");
            driver.findElement(By.id("username")).sendKeys("test0508");
            logger.info("定位密码输入框并输入密码");
            driver.findElement(By.name("password")).sendKeys("t12345678");
            logger.info("定位登录按钮并点击登录");
            driver.findElement(By.className("btn-submit")).click();
            logger.info("清除用户名输入框");
            driver.findElements(By.id("username")).get(0).clear();
            Thread.sleep(10000);
            logger.info("通过tagName方式定位用户名输入框并输入用户名");
            driver.findElements(By.tagName("input")).get(0).sendKeys("test0508");
            driver.navigate().to("http://www.baidu.com");
            Thread.sleep(2000);
            logger.info("通过linkText方式定位地图元素并点击");
            driver.findElement(By.linkText("地图")).click();
            logger.info("返回百度");
            driver.navigate().back();
            logger.info("访问登录");
            driver.navigate().to("http://www.our51.com/cas");
            Thread.sleep(3000);
            logger.info("通过partialLinkText方式定位注册链接元素并点击");
            driver.findElement(By.partialLinkText("没有账号")).click();
            logger.info("返回登录");
            driver.navigate().back();
            logger.info("通过tagName方式定位用户名输入框并输入用户名");
            driver.findElements(By.tagName("input")).get(0).sendKeys("test0508");
            logger.info("通过tagName方式定位密码输入框并输入密码");
            driver.findElements(By.tagName("input")).get(1).sendKeys("t123456789");
            driver.findElement(By.id("username")).clear();
            Thread.sleep(2000);
            logger.info("xpath id属性定位用户名输入框");
            driver.findElement(By.xpath("//input[@id='username']")).sendKeys("何师烧烤");
            logger.info("xpath name属性定位用户名输入框");
            driver.findElement(By.xpath("//input[@name='username']")).sendKeys("何师烧烤");
            Thread.sleep(2000);
            logger.info("属性与层级结合定位密码输入框");
            driver.findElement(By.xpath("//*[@id='passInput']/div/input[@id='password']")).sendKeys("t123456789");
            logger.info("逻辑运算符定位密码输入框");
            driver.findElement(By.xpath("//*[@id='password'and @name='password']")).sendKeys("3333");
            Thread.sleep(2000);
            driver.findElement(By.id("username")).clear();
            Thread.sleep(5000);
            logger.info("css选择器定位用户名输入框");
            driver.findElement(By.cssSelector("div>input#username")).sendKeys("test0508");
            logger.info("css选择器定位密码输入框");
            driver.findElements(By.cssSelector("div>input.required.ivu-input")).get(1).sendKeys("t123456789");
            Thread.sleep(4000);
            driver.findElement(By.cssSelector("input[value='登录']")).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
