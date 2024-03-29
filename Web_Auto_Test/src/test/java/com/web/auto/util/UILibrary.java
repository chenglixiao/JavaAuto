package com.web.auto.util;

import com.web.auto.cases.LaunchBrowser;
import com.web.auto.pojo.Page;
import com.web.auto.pojo.UIElement;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @ Author：chenglixiao
 * @ Date：14:15 2018/12/3
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class UILibrary {
    private final  static Logger LOGGER = Logger.getLogger(UILibrary.class);
    private static List<Page> pagesList ;
    static {
        pagesList = parsePageXml(PropertiesUtil.getValueByKey("pages"));
    }
    /**
     * create by:Pada_xiao
     * description:解析xml文件封装成page类型的对象，存入list集合
     * create time:16:14 2018/12/3
     * @params null
     * @returnjava.util.List<com.web.auto.test.pojo.Page>
     */
    public static List<Page> parsePageXml(String xmlPath){
        //创建list集合保存pages对象
        List<Page> pagesList = new ArrayList<Page>();
        //创建解析器
        SAXReader saxReader = new SAXReader();
        try {
            //将xml文件解析为document对象
            Document document = saxReader.read(new FileInputStream(xmlPath));
            //获取文件根元素
            Element rootElement = document.getRootElement();
            //获取根元素下的所有子元素，list集合保存
            List<Element> pages = rootElement.elements();
            //遍历子元素
            for (Element page : pages) {
                Class<Page> pageClazz = Page.class;
                //创建page对象，封装解析出来的元素信息
                Page pg = pageClazz.newInstance();
                //依次获取所有子元素的所有属性，并存入集合
                List<Attribute> pageAttributes = page.attributes();
                //遍历每个属性，并为page对象赋值
                for (Attribute pageAttribute : pageAttributes) {
                    //获取方法的名字
                    String methodName = "set"+firstWordUpper(pageAttribute.getName());
                    //获取对象的方法
                    Method method = pageClazz.getMethod(methodName,String.class);
                    //利用反射为对象赋值
                    method.invoke(pg,pageAttribute.getValue());
                }
                //封装UIElement类型的对象
                List<UIElement> uiElementList = new ArrayList<UIElement>();
                //获取每个子元素下面的所有子元素
                List<Element> uiElements =  page.elements();
                for (Element uiElement : uiElements) {
                    Class<UIElement> uiElementClazz = UIElement.class;
                    //创建UIElement类型的对象封装uiElement元素的属性
                    UIElement uiElement1 = uiElementClazz.newInstance();
                    //获取元素的所有所有属性值
                    List<Attribute> UIElementAttributes = uiElement.attributes();
                    //遍历属性集合
                    for (Attribute  UIElementAttribute : UIElementAttributes) {
                        //获取方法的名字
                        String methodName = "set"+firstWordUpper(UIElementAttribute.getName());
                        //获取方法
                        Method method = uiElementClazz.getMethod(methodName,String.class);
                        //为对象赋值
                        method.invoke(uiElement1,UIElementAttribute.getValue());
                    }
                    uiElementList.add(uiElement1);
                   // LOGGER.info(uiElementList);
                    pg.setUiElement(uiElementList);
                }
                //将page对象存取集合
                pagesList.add(pg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pagesList;
    }
    public static WebElement getElementByKeyWord( String pageKeyword, String uiElementKeyWord ){
        WebElement webElement = null;
      /* List<Page> pages = pagesList.stream().filter(page ->page.getKeyword().equals(pageKeyword)).collect(Collectors.toList());
       if(!pages.isEmpty()){
           for (Page page : pages) {
               List<UIElement> uiElementList = page.getUiElement();
               List<UIElement> uiElements = uiElementList.stream().filter(uiElement -> uiElement.getKeyword().equals(uiElementKeyWord)).collect(Collectors.toList());
               if(!uiElements.isEmpty()){
                   for (UIElement uiElement : uiElements) {
                       String id = uiElement.getBy();
                       String value = uiElement.getValue();
                       webElement = getElementByby(id,value);
                   }
               }else {
                   LOGGER.info("未找到元素关键字【"+uiElementKeyWord+"】");
               }
           }
       }else {
           LOGGER.info("未找到页面关键字【"+pageKeyword+"】");
       }*/
        for (Page page : pagesList) {
            if(pageKeyword.equals(page.getKeyword())){
                List<UIElement> uiElementList = page.getUiElement();
                for (UIElement uiElement : uiElementList) {
                       if (uiElementKeyWord.equals(uiElement.getKeyword())){
                           String id = uiElement.getBy();
                           String value = uiElement.getValue();
                           webElement = getElementByby(id,value);
                           break;
                       }
                }
            }
            break;
        }
        return  webElement;
    }

    /**
     * create by:Pada_xiao
     * description:根据传入的by值，定位元素
     * create time:17:24 2018/12/3
     * @params[by,value]
     * @returnorg.openqa.selenium.WebElement
     */
    private  static WebElement getElementByby(String by, String value){
       Class<By> classzz = By.class;
        By locator = null;
        String methodName = by;
        try {
            Method method = classzz.getMethod(methodName,String.class);
            locator = (By) method.invoke(null,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*if ("id".equals(by)){
            locator = By.id(value);
        }
        else if ("name".equals(by)){
            locator = By.name(value);
        } else if ("className".equals(by)){
            locator = By.className(value);
        } else if ("tagName".equals(by)){
            locator = By.tagName(value);
        } else if ("linkText".equals(by)){
            locator =  By.linkText(value);
        }else if ("partialLinkText".equals(by)){
            locator = By.partialLinkText(value);
        } else if ("xpath".equals(by)){
            locator = By.xpath(value);
        }else if ("cssSelector".equals(by)){
            locator = By.cssSelector(value);
        }else {
            System.out.println("暂不支持此定位方式");
        }*/
        return getWebElementUtilPresenceOfElementLocation(locator);
    }
    /**
     * create by: Pada_xiao
     * description:为元素设置显示等待，默认等待5s
     * create time: 14:13 2018/12/3
     * [by, timeOutInSeconds]
     * @return org.openqa.selenium.WebElement
     */
    public static WebElement getWebElementUtilPresenceOfElementLocation(By by, long...timeOutInSeconds){
        WebDriverWait wait = null;
        WebElement webElement = null;
        if (timeOutInSeconds.length==0){
            wait = new WebDriverWait(LaunchBrowser.driver,5,500);
        }
        else {
            for (int i=0;i<timeOutInSeconds.length;i++){
                long timeOutInSecond = timeOutInSeconds[0];
                wait = new WebDriverWait(LaunchBrowser.driver,timeOutInSecond,500);
            }
        }
        try {
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception E){
          LOGGER.info("元素定位异常");
          E.printStackTrace();
        }
        return  webElement;
    }
    /**
     * create by: Pada_xiao
     * description:将字符串的第一个单词大写
     * create time: 15:22 2018/12/3
     * [attribute]
     * @return void
     */
    private static String firstWordUpper(String string) {
        return string.substring(0,1).toUpperCase()+string.substring(1);
    }

    public static void main(String[] args) {
        //System.out.println(pagesList);
    }
}

