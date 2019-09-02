package com.web.auto.util;


import com.web.auto.cases.LaunchBrowser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**断言类库：支持多种场景的断言处理
 * @author Administrator
 *
 */
public class Assertion {
	/**判断当前页面url是否包含指定的内容
	 * @param urlcontains 要匹配的内容
	 */
	public static void assertUrlContains(String urlcontains) {
		WebDriverWait wait = new WebDriverWait(LaunchBrowser.driver,8);
		boolean isDirectedToLogin = true;
		try {
			wait.until(ExpectedConditions.urlContains(urlcontains));
		} catch (Exception e) {
			isDirectedToLogin = false;
		}
		Assert.assertTrue(isDirectedToLogin);
	}
	
	/**断言文本值出现在页面的指定元素
	 * @param element
	 * @param text
	 */
	public static void assertTextPresentInElement(WebElement element, String text){
		WebDriverWait wait = new WebDriverWait(LaunchBrowser.driver, 8);
		boolean textToBePresentInElement = true;
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		} catch (Exception e) {
			textToBePresentInElement = false;
		}
		Assert.assertTrue(textToBePresentInElement);
	}
	
	/**断言页面某个元素是可以点击的
	 * @param element
	 */
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
	
}
