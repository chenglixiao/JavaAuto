package com.web.auto.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ Author：chenglixiao
 * @ Date：21:38 2018/11/21
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class ImplicitlyWaitDemo extends LaunchBrowser {
    @Test
    public  void  implicitlyWaitDemo() throws InterruptedException {
        driver.get("http://www.our51.com/cas");
        driver.findElement(By.id("username")).sendKeys("test0508");
        driver.findElement(By.id("password")).sendKeys("t123456789");
        driver.findElement(By.id("btn-submit")).click();
        driver.findElement(By.partialLinkText("营销应用")).click();
        logger.info("设置隐式等待时间3s");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("div[style='background-image: url(\"/static/images/marketingApplication/saleimg.png\");']")).click();
        driver.findElement(By.xpath("//*[contains(text(),'新建商品')]")).click();
        logger.info("将driver转换为js驱动");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("document.querySelector(\"input[placeholder='选择日期和时间']\").value='2018-11-24 00:00 - 2018-11-25 00:00'");
        Thread.sleep(3000);
    }
}
