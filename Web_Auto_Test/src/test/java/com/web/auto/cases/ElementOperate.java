package com.web.auto.cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * @ Author：chenglixiao
 * @ Date：14:56 2018/11/17
 * @ Description：元素的基本操作
 * @ Modified By：
 * @Version:
 */
public class ElementOperate extends LaunchBrowser {
    @Test
    public void elementOperate() {
        logger.info("访问网址www.our51.com");
        driver.get("http://www.our51.com/cas");
        try {
            logger.info("等待1s");
            Thread.sleep(1000);
            logger.info("定位用户名输入框获取标签名");
            logger.info(driver.findElement(By.id("username")).getTagName());
            logger.info("定位密码输入框并获取name属性值");
            logger.info(driver.findElement(By.id("password")).getAttribute("name"));
            logger.info("定位忘记密码并获取文本值");
            logger.info(driver.findElement(By.xpath("//a[@style='color: rgb(153, 153, 153); font-size: 14px;']")).getText());
            logger.info("访问模态框示例页面");
            driver.get("file:///D:/java/java_auto/alert_confirm.html");
            logger.info("alert模态框点击示例");
            driver.findElement(By.id("login")).click();
            driver.switchTo().alert().accept();
            logger.info("confirm模态框点击示例");
            driver.findElement(By.id("username")).sendKeys("aaaaa");
            Thread.sleep(1000);
            driver.findElement(By.id("btn-reset")).click();
            driver.switchTo().alert().accept();
            driver.get("http://www.our51.com/cas");
            logger.info("定位用户名输入框并输入用户名");
            driver.findElement(By.id("username")).sendKeys("test0508");
            logger.info("定位密码输入框并输入密码");
            driver.findElement(By.name("password")).sendKeys("t123456789");
            logger.info("定位登录按钮并点击登录");
            driver.findElement(By.className("btn-submit")).click();
            driver.findElement(By.xpath("//a[contains(text(),'数据中心')]")).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
