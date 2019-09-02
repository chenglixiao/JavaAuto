package com.web.auto.cases;

import com.web.auto.util.UILibrary;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @ Author：chenglixiao
 * @ Date：21:53 2018/11/22
 * @ Description：下拉框、单选框选择示例
 * @ Modified By：
 * @Version:
 */
public class SelectDemo extends LaunchBrowser {
    @Test
    public void selectDemo() throws InterruptedException {
        driver.get("file:///D:/java/java_auto/Ddemo.html");
        Select select = new Select(driver.findElement(By.tagName("select")));
        logger.info("是否多选：" + select.isMultiple());
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            String text = option.getText();
            if ("篮球".equals(text)) {
                logger.info("当前选项："+text);
                select.selectByVisibleText(text);
                Thread.sleep(2000);
                logger.info("篮球是否被选中：" + option.isSelected());
            }
        }
        logger.info("通过输入空格的方式选择男性");
        driver.findElement(By.id("man")).sendKeys(Keys.SPACE);
        Thread.sleep(2000);
        logger.info("通过点击的方式选择女性");
        driver.findElement(By.id("female")).click();
        logger.info("判断男性是否被选中："+driver.findElement(By.id("man")).isSelected());
        Thread.sleep(2000);
        logger.info("取消所有选项");
    }
    /**根据文本值选中下拉框
     * @param
     * @param
     * @param
     */
    public void selectByOptionText(String pageKeyword, String uiElementKeyword, String optionText) {
        if(!StringUtils.isEmpty(optionText)){
            //logger.info("选中【"+pageKeyword+"】下拉框【"+uiElementKeyword+"】");
            WebElement element = UILibrary.getElementByKeyWord(pageKeyword, uiElementKeyword);
            Select select = new Select(element);
            select.selectByVisibleText(optionText);
        }

    }

    /**根据option的value值选中下拉框的某一个选项
     * @param pageKeyword
     * @param uiElementKeyword
     * @param optionValue
     */
    public void selectByOptionValue(String pageKeyword, String uiElementKeyword, String optionValue) {
        if(!StringUtils.isEmpty(optionValue)){
            //logger.info("选中【"+pageKeyword+"】下拉框【"+uiElementKeyword+"】");
            WebElement element =UILibrary.getElementByKeyWord(pageKeyword, uiElementKeyword);
            Select select = new Select(element);
            select.selectByValue(optionValue);
        }
    }
}
