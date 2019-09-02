package com.web.auto.util;

import com.web.auto.cases.LaunchBrowser;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @ Author：Pada_xiao
 * @ Date：9:35 2019/3/9
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class AssertUtil {
    private static final Logger LOGGER = Logger.getLogger(Assert.class);
    public static void assertEquals(String actualMsg,String expectMsg) {
        LOGGER.info("断言actualMsg【"+actualMsg+"】,expectMsg【"+expectMsg+"】");
            Assert.assertEquals(actualMsg, expectMsg);
            LOGGER.info("断言成功");

    }
    public static void assertContains(String actualMsg,String expectMsg) {
            Assert.assertTrue(actualMsg.contains(expectMsg));
            LOGGER.info("断言成功");
    }
    public static void assertElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(LaunchBrowser.driver, 8);
        boolean elementToBeClickable = true;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch(Exception e){
            elementToBeClickable = false;
        }
        Assert.assertTrue(elementToBeClickable);
    }

    public static void main(String[] args) {
        String a = "A";
        String b = "A";
       try {

           Assert.assertTrue(a.contains(b));
           LOGGER.info("DUANYAN ");

       }catch(Throwable e) {
           System.out.println("断言失败");
           e.printStackTrace();
       }
    }
}
