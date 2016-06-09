package com.zaizi.automation.alfresco.core.elements;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
*
* @author mketheeswaran
*/

public class Element {

	
	/**
	 * Defining driver
	 */
	private WebDriver driver;
	
	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager.getLogger(Element.class
			.getName());
	
		
	/**
	 * Defining element
	 */
	private WebElement element;

	/**
	 * @param driver
	 * @param elementIdentifier
	 *            it could be xpath, id, class etc. example :
	 *            by.xpath("//input[@id='btn1']")
	 */
	
	public Element(WebDriver driver, By elementIdentifier) {
		Element.waitUntilElementPresent(driver, elementIdentifier);
		
		Element.waitUntilElementClickable(driver,elementIdentifier);
		this.driver = driver;
		
		element = this.driver.findElement(elementIdentifier);
	}

	/**
	 * click method
	 */
	public void click() {
		element.click();
	}

	/**
	 * @param attribute
	 * @return
	 */
	public String getElementAttribute(String attribute) {
		return element.getAttribute(attribute);
	}

	/**
	 * @return
	 */
	public WebElement getWebElement() {
		return this.element;
	}

	/**
	 * @return
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param driver
	 * @param xPath
	 * @throws InterruptedException
	 */
	public static Boolean isElementPresent(WebDriver driver, By xPath)
			throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			driver.findElement(xPath);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public static Boolean isElementPresentTestNG(WebDriver driver, By xPath)
			throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			driver.findElement(xPath);
			return true;
		} catch (Exception e) {
			return true;
		}finally {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
		}
	}
	
	/**
	 * Check if the text is present in the List
	 * 
	 * @param findElements
	 * @param name
	 * @throws InterruptedException
	 */

	public static Boolean isTextPresentInList(
		java.util.List<WebElement> findElements, String name) {

            
		java.util.List<WebElement> myList = findElements;

		for (WebElement webele : myList) {
			if (webele.getText().equals(name)) {
				
			LOGGER.info("User IS Display in \"Search Users\"");
			
				return true;				
			
				
			}
		}
		LOGGER.info("User IS NOT Display in \"Search Users\"");
		
		return false;		
	}	
	
	/**
	 * Check if the text is present in the List
	 * 
	 * @param findElements
	 * @param name
	 * @throws InterruptedException
	 */

	public static Boolean isTextPresentInListForGroup(
		java.util.List<WebElement> findElements, String name) {

		java.util.List<WebElement> myList = findElements;

		for (WebElement webele : myList) {
			if (webele.getText().equals(name)) {
				
			LOGGER.info("Group IS Display in \"Search\"");
			
				return true;			
				
			}
		}
		LOGGER.info("Group IS NOT Display in \"Search\"");
		
		return false;		
	}	
	
	/**
	 * Check if the text is present in the Site List
	 * 
	 * @param findElements
	 * @param name
	 * @throws InterruptedException
	 */

	public static Boolean isTextPresentInListForSite(
		java.util.List<WebElement> findElements, String name) {

		java.util.List<WebElement> myList = findElements;

		for (WebElement webele : myList) {
			if (webele.getText().equals(name)) {		
			
				return true;		
				
			}
		}	
		
		return false;		
	}	
	
	public String getText()
	{
		return getWebElement().getText();
	}

	/**
	 * Check if the text is present in Element
	 * 
	 * @param elements
	 * @param name
	 * @throws InterruptedException
	 */

	public static Boolean isTextPresentInElement(Element element, String name)
			throws InterruptedException {

		if (element.getWebElement().getText().equals(name)) {
			return true;

		} else {
			return false;

		}

	}

	/**
	 * Wait till the page loads completely
	 * 
	 * @param driver
	 */
	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(pageLoadCondition);
		
	}

	/**
	 * Wait Until Element is present by locator
	 */
	public static void waitUntilElementPresent(WebDriver driver, By locator)
	{

	WebElement myDynamicElement = (new WebDriverWait(driver,20)).until(ExpectedConditions.presenceOfElementLocated(locator));
	
	}

	
	/**
	 * Wait Until Element is Clickable by locator
	 * @throws InterruptedException
	 */
	public static void waitUntilElementClickable(WebDriver driver, By locator) 
	{
		
	WebElement myDynamicElement = (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(locator));

	}
	
	
	
	
	
}
