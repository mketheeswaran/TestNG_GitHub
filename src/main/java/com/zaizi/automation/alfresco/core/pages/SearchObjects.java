package com.zaizi.automation.alfresco.core.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.TextField;






/**
 * @author nbrahmananthan@zaizi.com
 * 
 */

public class SearchObjects {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(SearchObjects.class.getName());
	
	//public static  ExtentReports extent = ExtentReports.get(SearchObjects.class);

	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public SearchObjects(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Search for Group. The user should be in the Admin Console groups page.
	 * 
	 * @param groupName
	 *            : Name of the group
	 * @return void
	 * @throws InterruptedException
	 */

	public void searchGroup(String groupName) throws InterruptedException {

		Element.waitForLoad(driver);
		TextField groupSearchBar = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-text"));
		groupSearchBar.clearText();
		groupSearchBar.enterText(groupName);
		Button searchButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button"));
		searchButton.click();
		Element.waitForLoad(driver);
		Thread.sleep(1000);

	}

	/**
	 * Search for a specific site.
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void searchSite(String siteName) throws InterruptedException, IOException {		
		Thread.sleep(2000);
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
                
                navigateTo.goToSiteSearch();		
        
		TextField searchText = new TextField(driver,By.xpath("//input[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-term']"));
		searchText.enterText(siteName);
		Thread.sleep(2000);
		
		Button searchButton = new Button(driver,By.id("template_x002e_site-finder_x002e_site-finder_x0023_default-button-button"));		
		searchButton.click();
		Thread.sleep(5000);
		
		//Element.takescreenshot(driver, className, screenShot+siteName);
		
		

	}

	/**
	 * Search User in users page
	 * 
	 * @param firstName
	 *            : First name of the user
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void searchUser(String userName) throws InterruptedException, IOException {
		
		Element.waitForLoad(driver);
		
		TextField searchUser = new TextField(driver,By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-text']"));
		searchUser.clearText();
		
		LOGGER.info("Enter the username "+userName+" to search");
		
		searchUser.enterText(userName);
		Thread.sleep(2000);
		
		LOGGER.info("Click \"search\" button");
		
		Button searchButton = new Button(driver,By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button']"));
		
		searchButton.click();
				
		Element.waitForLoad(driver);
		Thread.sleep(4000);
		
		//Element.takescreenshot(driver, className, screenShotName);
	}

}
