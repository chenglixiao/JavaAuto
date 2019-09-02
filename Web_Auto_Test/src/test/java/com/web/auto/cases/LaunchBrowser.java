package com.web.auto.cases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @ Author：Pada_xiao
 * @ Date：20:29 2018/11/27
 * @ Description：根据parameters创建不同的浏览器驱动，并启动浏览器
 * @ Modified By：
 * @Version:
 */
public class LaunchBrowser {
    public static WebDriver driver = null;
    public Logger logger = Logger.getLogger(LaunchBrowser.class);
    @Parameters(value = {"browserType"})
    @BeforeTest
    public void launchBrowser(String browserType) {
        if ("ie".equalsIgnoreCase(browserType)) {
            //设置系统变量，指定驱动文件路径
            System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
            //忽略浏览器保护模式
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //创建IE浏览器驱动实例
            driver = new InternetExplorerDriver(desiredCapabilities);
        } else if ("firefox".equalsIgnoreCase(browserType)) {
            //设置系统变量，指定驱动文件路径
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            //创建Firefox浏览器驱动实例
            driver = new FirefoxDriver();
        } else if ("chrome".equalsIgnoreCase(browserType)) {
            //设置系统变量，指定驱动文件路径
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            //创建Chrome浏览器驱动实例
            driver = new ChromeDriver();
        } else {
            logger.info("暂不支持，请检查配置");
        }
        //设置页面加载时间10s
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //设置隐式等待时间5s，找不到元素抛出没有此元素异常
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void tearDown() {
        logger.info("关闭浏览器");
        driver.quit();
    }
}

