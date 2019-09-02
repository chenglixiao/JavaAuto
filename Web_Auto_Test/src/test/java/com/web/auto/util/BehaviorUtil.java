package com.web.auto.util;

import com.web.auto.cases.LaunchBrowser;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @ Author：Pada_xiao
 * @ Date：21:41 2019/3/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class BehaviorUtil {
    private final static Logger LOGGER = Logger.getLogger(BehaviorUtil.class);
    public static void interview(String url) {
        LOGGER.info("访问页面" + url );
        try {
            LaunchBrowser.driver.get(PropertiesUtil.getValueByKey(url));
        } catch (Exception e) {
            LOGGER.info("url不能为空");
            e.printStackTrace();
        }
    }

    public static void input(String pageKeyword, String uiElementKeyword, String string) {
        LOGGER.info("【" + pageKeyword + "】页面输入【" + uiElementKeyword + "：" + string+"】");
        try {
            UILibrary.getElementByKeyWord(pageKeyword, uiElementKeyword).sendKeys(string);
        } catch (Exception e) {
            LOGGER.info("元素不能为空");
            e.printStackTrace();
        }
    }

    public static void click(String pageKeyword, String uiElementKeyword) {
        LOGGER.info("【" + pageKeyword + "】页面点击【" + uiElementKeyword + "】按钮");
        try {
            UILibrary.getElementByKeyWord(pageKeyword, uiElementKeyword).click();
        } catch (Exception e) {
            LOGGER.info("元素不能为空");
            e.printStackTrace();
        }
    }
    public static String getText(String pageKeyword, String uiElementKeyword) {
        LOGGER.info("【" + pageKeyword + "】页面获取【" + uiElementKeyword + "】");
        try {
            return UILibrary.getElementByKeyWord(pageKeyword, uiElementKeyword).getText();
        } catch (Exception e) {
            LOGGER.info("元素不能为空");
            e.printStackTrace();
        }
        return null;
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
    public void switchToIframe(String pageKeyword, String uiElementKeyword) {
        //logger.info("切换到【"+pageKeyword+"】【"+uiElementKeyword+"】iframe");
        WebElement element = UILibrary.getElementByKeyWord(pageKeyword, uiElementKeyword);
        LaunchBrowser.driver.switchTo().frame(element);
    }

}
