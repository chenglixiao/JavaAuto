package com.web.auto.cases;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * @ Author：chenglixiao
 * @ Date：10:42 2018/11/15
 * @ Description：窗口的基本操作
 * @ Modified By：
 * @Version:
 */
public class OprationWindow extends LaunchBrowser {
    @Test
    public void operationWindow() {
        try {
            logger.info("休眠1s");
            Thread.sleep(1000);
        logger.info("获取window对象");
        WebDriver.Window window = driver.manage().window();
        logger.info("窗口最大化");
        window.maximize();
        logger.info("窗口大小:"+window.getSize());
        logger.info("设置窗口位置");
        window.setPosition(new Point(500,500));
        logger.info("休眠1s");
            Thread.sleep(1000);
            logger.info("设置窗口大小");
            window.setSize(new Dimension(900,900));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
