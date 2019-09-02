package com.app.auto.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @ Author：Pada_xiao
 * @ Date：17:38 2019/3/29
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class LocateElement extends LaunchApp{
    @Test
    public void locateElement(){
        try {
            androidDriver.findElementById("com.lemon.lemonban:id/navigation_my").click();
            Thread.sleep(3000);
            androidDriver.findElementById("com.lemon.lemonban:id/fragment_my_lemon_avatar_layout").click();
            Thread.sleep(3000);
            androidDriver.findElementById("com.lemon.lemonban:id/et_mobile").sendKeys("15283156102");
            androidDriver.findElementById("com.lemon.lemonban:id/et_password").sendKeys("156102");
            androidDriver.findElementById("com.lemon.lemonban:id/btn_login").click();Thread.sleep(5000);
        /*androidDriver.findElementByAndroidUIAutomator("new UiSelector().text(\"我的柠檬\")").click();
        List<WebElement> webElementList = androidDriver.findElementsByClassName("android.widget.FrameLayout");
        System.out.println(webElementList.size());
        androidDriver.findElement(By.xpath("//TextView[@text='软件测试']"));
        Thread.sleep(5000);
        WebElement fathorElement = androidDriver.findElementByClassName("android.widget.FrameLayout");
        fathorElement.findElement(By.linkText("高级")).click();
            Thread.sleep(5000);
        androidDriver.findElementsByAndroidUIAutomator("new UiSelector().class(\" android.support.v7.widget.RecyclerView\")").get(1).click();
            Thread.sleep(5000);
        androidDriver.findElementByAndroidUIAutomator("UiSelector().contentdesc(\"Navigate up\")").click();*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
