package com.web.auto.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

/**
 * @ Author：Pada_xiao
 * @ Date：16:21 2019/3/16
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class FileDownload extends LaunchBrowser{
    @Test
    public void download(){
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            //设置浏览器文件下载路径，0表示默认下载路径，2表示指定下载路径
            firefoxProfile.setPreference("browser.download.folderList",2);
            //是否显示开始
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
            //指定文件下载路径
            firefoxProfile.setPreference("browser.download.dir","d:\\java");
            //指定文件下载页面的Content-Type,常用对照表：http://tool.oschina.net/commons，可以在浏览器输入about:config 进行设置
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","appli cation/octet-stream");
            WebDriver driver = new FirefoxDriver((Capabilities) firefoxProfile);
            driver.get("https://pypi.Python.org/pypi/selenium");
            driver.findElement(By.partialLinkText("selenium-2")).click();
    }
}
