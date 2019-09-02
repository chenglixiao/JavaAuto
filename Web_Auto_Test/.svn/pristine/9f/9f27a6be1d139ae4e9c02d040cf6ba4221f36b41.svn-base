package com.web.auto.util;

import com.web.auto.cases.LaunchBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;

/**
 * @ Author：Pada_xiao
 * @ Date：11:22 2019/3/10
 * @ Description：截图，并将文件图片保存到特定目录
 * @ Modified By：
 * @Version:
 */
public class ScreenshotUtil {
    /**
     * @Author：Pada_xiao
     * @Description：截图，并将文件图片保存到特定目录
     * @Date：13:08 2019/3/11
     * @Param：[filePath]
     * @return：java.io.File
     **/
    public static File svaeScreenshot(String filePath) {
        File screenshot = null;
        if(LaunchBrowser.driver instanceof FirefoxDriver){
            FirefoxDriver firefoxDriver = (FirefoxDriver) LaunchBrowser.driver;
            screenshot = firefoxDriver.getScreenshotAs(OutputType.FILE);
        }else if(LaunchBrowser.driver instanceof InternetExplorerDriver){
            InternetExplorerDriver internetExplorerDriver = (InternetExplorerDriver) LaunchBrowser.driver;
            screenshot = internetExplorerDriver.getScreenshotAs(OutputType.FILE);
        }if (LaunchBrowser.driver instanceof ChromeDriver){
            ChromeDriver chromeDriver = (ChromeDriver) LaunchBrowser.driver;
            screenshot = chromeDriver.getScreenshotAs(OutputType.FILE);
        }
        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(screenshot,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile;
    }
}
