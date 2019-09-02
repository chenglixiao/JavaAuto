package com.web.auto.cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * @ Author：chenglixiao
 * @ Date：20:11 2018/11/22
 * @ Description：多个窗口切换定位元素示例
 * @ Modified By：
 * @Version:
 */
public class MultipleWindowDemo extends LaunchBrowser {
    @Test
    public void multipleWindowDemo() throws InterruptedException {
        driver.navigate().to("file:///D:/java/java_auto/Aa.html");
        logger.info("获取默认页面的handle");
        String defalutHandle = driver.getWindowHandle();
        logger.info("默认页面handle："+defalutHandle);
        logger.info("a.html输入框并输入内容");
        driver.findElement(By.id("aa")).sendKeys("A页面");
        Thread.sleep(1000);
        logger.info("点击链接打开B页面");
        driver.findElement(By.linkText("跳到b页面")).click();
        Thread.sleep(1000);
        logger.info("点击链接打开C页面");
        driver.findElement(By.partialLinkText("c页面")).click();
        logger.info("获取已打开链接的所有Handel");
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            logger.info("将driver作用域切换到对应handle："+handle+"页面");
            driver.switchTo().window(handle);
            logger.info("通过判断对应Handel页面的title后操作对应页面的元素");
            logger.info("当前页面的title："+driver.getTitle());
            if ("file:///D:/java/java_auto/Bb.html".equals(driver.getCurrentUrl())) {
                logger.info("当前页面的URL："+driver.getCurrentUrl());
                driver.findElement(By.id("bb")).sendKeys("B页面");
                driver.switchTo().defaultContent();
                try {
                    Thread.sleep(3000);
                    logger.info("关闭"+driver.getTitle()+"页面窗口");
                    driver.close();
                    logger.info("将driver切换到默认页面");
                    driver.switchTo().window(defalutHandle);
                    driver.findElement(By.id("aa")).sendKeys("a页面");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            else if("c页面".equals(driver.getTitle())){
                driver.findElement(By.id("cc")).sendKeys("C页面");
                try {
                    Thread.sleep(3000);
                    driver.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                logger.info("当前页面不做操作");
            }
        }
        Thread.sleep(3000);
    }
}
