package com.web.auto.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * @ Author：chenglixiao
 * @ Date：17:04 2018/11/25
 * @ Description：元素显示等待示例
 * @ Modified By：
 * @Version:
 */
public class WebDriverWaitDemo extends LaunchBrowser {
    @Test
    public void webDriverWaitDemo() {
        driver.get("http://www.our51.com/cas");
        driver.findElement(By.id("username")).sendKeys("test0508");
        driver.findElement(By.id("password")).sendKeys("t123456789");
        driver.findElement(By.id("btn-submit")).click();
        driver.findElement(By.partialLinkText("营销应用")).click();
        long timeOutInSeconds[] = new long[]{10L};
           getElementUtilPresenceOfElementLocation(By.cssSelector("div[style='background-image: url(\"/static/images/marketingApplication/saleimg.pn\");']"), timeOutInSeconds).click();
    }
    /**
     * @Author：Pada_xiao
     * @Description：使用显示等待获取特定的元素
     * @Date：19:47-2018/11/27
     * @Param：[by,timeOutInSeconds],timeOutInSeconds为可变参数，不传则默认为20
     * @return：org.openqa.selenium.WebElement
     **/
    public WebElement getElementUtilPresenceOfElementLocation(By by, long...timeOutInSeconds) {
        WebDriverWait wait = null;
        WebElement webElement =null;
        if (timeOutInSeconds.length==0) {
            logger.info("设置默认等待时间20s");
            wait = new WebDriverWait(driver, 20);
        } else {
               logger.info("设置等待时间为："+timeOutInSeconds[0]+"s");
                wait = new WebDriverWait(driver, timeOutInSeconds[0]);
        }
        try {
            logger.info("获取定位的元素");
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("元素定位超時");
        }
        return webElement;
    }
}
