package com.web.auto.cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * @ Author：chenglixiao
 * @ Date：19:20 2018/11/22
 * @ Description：定位Iframe内的元素
 * @ Modified By：
 * @Version:
 */
public class IframeDemo extends LaunchBrowser {
    @Test
    public  void iframeDemo() throws InterruptedException {
        logger.info("访问iframe示例页面");
        driver.get("file:///D:/java/java_auto/a.html");
        logger.info("定位到a页面输入框并输入内容");
        driver.findElement(By.id("aa")).sendKeys("a页面");
        logger.info("根据name属性值将driver作用域切换到b iframe");
        driver.switchTo().frame("bframe");
        driver.findElement(By.id("bb")).sendKeys("b页面");
        logger.info("根据iframe索引将driver作用域切换到c iframe");
        driver.switchTo().frame(0);
        driver.findElement(By.id("cc")).sendKeys("c页面");
        Thread.sleep(3000);
        logger.info("通过parentFrame()将父将driver作用域切换到上一级iframe");
        driver.switchTo().parentFrame();
        driver.findElement(By.id("bb")).sendKeys("b页面");
        logger.info("通过defaultContent()将driver作用域切换到默认的页面");
        driver.switchTo().defaultContent();
        driver.findElement(By.id("aa")).sendKeys("a页面");
        logger.info("iframe元素将driver作用域切换到下一级指定的页面");
        driver.switchTo().frame(driver.findElement(By.id("bframe")));
        driver.findElement(By.id("bb")).sendKeys("b页面");
        Thread.sleep(3000);
    }
}
