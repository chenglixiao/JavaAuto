package com.web.auto.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 * @ Author：chenglixiao
 * @ Date：13:11 2018/11/25
 * @ Description：通过Actions类模拟鼠标键盘操作实现拖曳元素的目的
 * @ Modified By：
 * @Version:
 */
public class ActionsDemo extends LaunchBrowser {
    @Test
    public void actionsDemo() throws InterruptedException {
        driver.get("http://www.treejs.cn/v3/demo/cn/exedit/drag.html");
        Thread.sleep(3000);
       /* WebElement sourceSpan = driver.findElement(By.id("treeDemo_5_span"));
        WebElement targetSpan = driver.findElement(By.id("treeDemo_6_span"));*/
        WebElement sourceLi = driver.findElement(By.id("treeDemo_5"));
        WebElement targetLi = driver.findElement(By.xpath("//a[@id='treeDemo_6_a']"));
        logger.info("驱动实例当做参数传递给Actions类");
        Actions action = new Actions(driver);
       /* logger.info("通过dragAndDrop方法将元素拖曳到目标位置后释放");
        action.dragAndDrop(sourceSpan,targetLi).build().perform();
        Thread.sleep(3000);*/
        logger.info("将元素拖到目标元素同级位置");
        action.dragAndDrop(sourceLi,targetLi).build().perform();
        Thread.sleep(3000);
        /*logger.info("通过clickAndHold方法拖动元素");
        action.clickAndHold(sourceSpan).moveToElement(targetSpan).release().build().perform();
        Thread.sleep(10000);*/
    }
}
