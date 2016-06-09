package com.zaizi.automation.alfresco.core.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.Link;
import com.zaizi.automation.alfresco.core.elements.Span;





/**
 * @author nbrahmananthan@zaizi.com
 * 
 */

public class NavigateToPage {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(NavigateToPage.class.getName());

	//public static  ExtentReports extent = ExtentReports.get(NavigateToPage.class);
	
	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public NavigateToPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Navigates to the Home Page
	 * 
	 * @throws InterruptedException
	 */

	public void goToHome() throws InterruptedException {
		Span home = new Span(driver, By.id("HEADER_HOME"));
		home.click();
		Element.waitForLoad(driver);

	}

	/**
	 * Navigates to People Page
	 * 
	 * @throws InterruptedException
	 */
	public void goToPeople() throws InterruptedException {
		Span people = new Span(driver, By.id("HEADER_PEOPLE"));
		people.click();
		Element.waitForLoad(driver);
	}

	/**
	 * Navigates to My Files Page
	 * 
	 * @throws InterruptedException
	 */
	public void goToMyFiles() throws InterruptedException {
		Span myFiles = new Span(driver, By.id("HEADER_MY_FILES"));
		myFiles.click();
		Element.waitForLoad(driver);
	}

	/**
	 * Navigates to Shared Files Page
	 * 
	 * @throws InterruptedException
	 */
	public void goToSharedFiles() throws InterruptedException {
		Span sharedFiles = new Span(driver, By.id("HEADER_SHARED_FILES"));
		sharedFiles.click();
		Element.waitForLoad(driver);
	}

	
	/**
	 * Navigates to Shared Files Page
	 * 
	 * @throws InterruptedException
	 */
	public void goToSites() throws InterruptedException {
		Span sites = new Span(driver, By.id("HEADER_SITES_MENU_text"));
		sites.click();
		Element.waitForLoad(driver);
	}
	
	/**
	 * Navigates to Repository Page
	 * 
	 * @throws InterruptedException
	 */
	public void goToRepository() throws InterruptedException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToHome();
		Element.waitForLoad(driver);
		Span repository = new Span(driver, By.id("HEADER_REPOSITORY"));
		repository.click();
		Element.waitForLoad(driver);
	}

	/**
	 * Navigates to Admin Tools Page
	 * 
	 * @throws InterruptedException
	 */
	public void goToAdminTools() throws InterruptedException {	
        
		Span adminTools = new Span(driver,By.xpath("//a[text()='Admin Tools']"));
		adminTools.click();		
		Element.waitForLoad(driver);
	}

	/**
	 * Navigates to Site Finder Page
	 * 
	 * @throws InterruptedException
	 */
	public void goToSiteSearch() throws InterruptedException {
		Element.waitForLoad(driver);
		Thread.sleep(2000);                
                
		Button sitesButton = new Button(driver, By.id("HEADER_SITES_MENU_text"));
		sitesButton.click();
		Thread.sleep(2000);               
                
		Link searchForSites = new Link(driver,
				By.id("HEADER_SITES_MENU_SITE_FINDER_text"));
		searchForSites.click();
		Element.waitForLoad(driver);
		
	}

	/**
	 * Navigates to a specific Site
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void goToSite(String siteName,String className,String screenShot) throws InterruptedException, IOException {
		SearchObjects searchObjects = new SearchObjects(driver);
		searchObjects.searchSite(siteName);
		Element.waitForLoad(driver);
		Thread.sleep(4000);
		java.util.List<WebElement> mySiteList = driver.findElements(By
				.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName
						+ "')]"));

		Thread.sleep(4000);
		for (int i = 0; i < mySiteList.size(); i++) {

			if (mySiteList.get(i).getText().equals(siteName)) {
				WebElement checkBox = mySiteList.get(i);
				i = mySiteList.size();
				checkBox.click();
				//extent.log(LogStatus.PASS, "Site " + siteName + " was found");
				LOGGER.info("Site " + siteName + " Not found");

			} else {
				LOGGER.error("Site " + siteName + " Not found");
				//extent.log(LogStatus.FAIL, "Site " + siteName + " Not found");
			}
		}
		Element.waitForLoad(driver);
	}
	
	/**
	 * Navigates to a specific Site
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void goToSiteForEditSiteName(String siteName) throws InterruptedException, IOException {
		SearchObjects searchObjects = new SearchObjects(driver);
		searchObjects.searchSite(siteName);
		Element.waitForLoad(driver);
		Thread.sleep(4000);
		Thread.sleep(3000);

		
		java.util.List<WebElement> mySiteList = driver.findElements(By
				.xpath("//tbody//tr//td//div//h3//a[text()='" + siteName+ "']"));

		Thread.sleep(4000);
		for (int i = 0; i < mySiteList.size(); i++) {

			if (mySiteList.get(i).getText().equals(siteName)) {
				WebElement checkBox = mySiteList.get(i);
				i = mySiteList.size();
				checkBox.click();
				
				LOGGER.info("Site " + siteName + " Not found");

			} else {
				LOGGER.error("Site " + siteName + " Not found");
				
			}
		}
		Element.waitForLoad(driver);
	
	}

	/**
	 * Navigates to Admin console users tab
	 * 
	 * @throws InterruptedException
	 */

	public void goToUsers() throws InterruptedException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToAdminTools();		
		
		Link userLink = new Link(driver,By.xpath("//a[@title='User Management']"));
		userLink.click();
		
		
		Element.waitForLoad(driver);
	}

	/**
	 * Navigates to Admin console Groups tab
	 * 
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void goToGroups() throws InterruptedException, IOException {
		goToAdminTools();
		
		Link groupLink = new Link(driver,
				By.xpath("//ul//li//span//a[@title='Group Management']"));
		groupLink.click();		
		Element.waitForLoad(driver);
		
	}

	/**
	 * Go to User Profile
	 * 
	 * @param firstname
	 *            : First name of the user
	 * @param lastName
	 *            : Last name of the user
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void goToUserProfile(String firstName, String lastName,String userName)
			throws InterruptedException, IOException {

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToUsers();

		SearchObjects searchObjects = new SearchObjects(driver);
		searchObjects.searchUser(userName);
		Element.waitForLoad(driver);

		if (lastName.isEmpty()) {
			java.util.List<WebElement> myUserList = driver.findElements(By
					.xpath("//tbody//tr//td//div//a[contains(., '" + firstName+ "')]"));

			for (int i = 0; i < myUserList.size(); i++) {

				if (myUserList.get(i).getText()
						.equals(firstName)) {

					WebElement checkBox = myUserList.get(i);
					i = myUserList.size();
					checkBox.click();
					

				} else {
					LOGGER.error("User " + firstName + " Not found1");
				}
			}
		}else if (!(lastName.isEmpty())) {
			java.util.List<WebElement> myUserList = driver.findElements(By
					.xpath("//tbody//tr//td//div//a[contains(., '" + firstName+ " "+lastName+ "')]"));

			for (int i = 0; i < myUserList.size(); i++) {

				if (myUserList.get(i).getText()
						.equals(firstName+" "+lastName)) {

					WebElement checkBox = myUserList.get(i);
					i = myUserList.size();
					checkBox.click();
					
                                       
				} else {
					
					LOGGER.error("User " + firstName + " Not found2");
				}
			}
		} 
		
		else {
			java.util.List<WebElement> myUserList = driver.findElements(By
					.xpath("//tbody//tr//td//div//a[contains(., '" + firstName
							+ " " + lastName + "')]"));

			for (int i = 0; i < myUserList.size(); i++) {

				if (myUserList.get(i).getText()
						.equals(firstName + " " + lastName)) {

					WebElement checkBox = myUserList.get(i);
					i = myUserList.size();
					checkBox.click();
					
                                         
				} else {
					LOGGER.error("User " + firstName + " Not found3");
				}
			}
		}
		Element.waitForLoad(driver);
	}

	
	/**
	 * Go to User Profile
	 * 
	 * @param firstname
	 *            : First name of the user
	 * @param lastName
	 *            : Last name of the user
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void goToUserProfile1(String firstName, String lastName)
			throws InterruptedException, IOException {

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToUsers();
		
		Thread.sleep(5000);
		SearchObjects searchObjects = new SearchObjects(driver);
		searchObjects.searchUser(firstName);
		Element.waitForLoad(driver);

		if (lastName.isEmpty()) {
			java.util.List<WebElement> myUserList = driver.findElements(By
					.xpath("//tbody//tr//td//div//a[contains(., '" + firstName
							+ "')]"));

			for (int i = 0; i < myUserList.size(); i++) {

				if (myUserList.get(i).getText()
						.equals(firstName + "" + lastName)) {

					WebElement checkBox = myUserList.get(i);
					i = myUserList.size();
					checkBox.click();

				} else {
					LOGGER.error("User " + firstName + " Not found");
				}
			}
		} else {
			java.util.List<WebElement> myUserList = driver.findElements(By
					.xpath("//tbody//tr//td//div//a[contains(., '" + firstName
							+ " " + lastName + "')]"));

			for (int i = 0; i < myUserList.size(); i++) {

				if (myUserList.get(i).getText()
						.equals(firstName + " " + lastName)) {

					WebElement checkBox = myUserList.get(i);
					i = myUserList.size();
					checkBox.click();

				} else {
					LOGGER.error("User " + firstName + " Not found");
				}
			}
		}
		Element.waitForLoad(driver);
	}
	/**
	 * Navigates to User My Profile
	 * 
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void goToMyProfile() throws InterruptedException, IOException {
		Button userButton = new Button(driver,
				By.id("HEADER_USER_MENU_POPUP_text"));
		userButton.click();
		
               // Element.takescreenshot(driver, className, Screenshotname+"gotomyprofile1");
		LOGGER.info("Accessing \"My Profile Page\"");
		//extent.log(LogStatus.INFO, "Accessing \"My Profile Page\"");	
               
		Thread.sleep(2000);		
		Link myProfile = new Link(driver, By.id("HEADER_USER_MENU_PROFILE"));
		myProfile.click();              
                
		Element.waitForLoad(driver);
	}

	/**
	 * Navigates to a User Trash Can
	 * 
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void goToUserTrashCan() throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);             
                              
               
		navigateTo.goToMyProfile();
                
            //    Element.takescreenshot(driver, className, Screenshotname+"accessProfile1");
                
		LOGGER.error("Accessing trashcan Page");
		
		Link trashCan = new Link(driver,
				By.xpath("//div//a[@href='user-trashcan']"));
		trashCan.click();
		//Element.takescreenshot(driver, className, Screenshotname);
		Element.waitForLoad(driver);

	}

	/**
	 * Navigate to a specific group
	 * 
	 * @param groupName
	 *            : Name of the group
	 * @throws InterruptedException
	 * @throws IOException 
	 * */

	public void goToGroup(String groupName) throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToGroups();
		SearchObjects searchGroup = new SearchObjects(driver);
		searchGroup.searchGroup(groupName);
		Button browse = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-browse-button-button"));
		browse.click();
		Thread.sleep(2000);

		java.util.List<WebElement> myList = driver
				.findElements(By.xpath("//div//ul//li//div/a[contains(., '"
						+ groupName + "')]"));

		for (int i = 0; i < myList.size(); i++) {
			if (myList.get(i).getText().equals(groupName)) {
				WebElement checkBox = myList.get(i);
				checkBox.click();
				Thread.sleep(2000);
			}
		}
		Element.waitForLoad(driver);
	}

	/**
	 * Navigates to a User Change Password
	 * 
	 * @throws InterruptedException
	 */

	public void goToChangePassword() throws InterruptedException {
		Button userButton = new Button(driver,
				By.id("HEADER_USER_MENU_POPUP_text"));
		userButton.click();
		Thread.sleep(1000);
		Link changePassword = new Link(driver,
				By.id("HEADER_USER_MENU_CHANGE_PASSWORD"));
		changePassword.click();
		Element.waitForLoad(driver);

	}

	/**
	 * Navigates to My Tasks page.
	 * 
	 * @throws InterruptedException
	 */

	public void goToMyTasksPage() throws InterruptedException {
		Element.waitForLoad(driver);
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		Button tasks = new Button(driver, By.id("HEADER_TASKS"));
		tasks.click();
		Thread.sleep(500);
		Button myTasks = new Button(driver, By.id("HEADER_MY_TASKS"));
		myTasks.click();
		Element.waitForLoad(driver);

	}
	
	/**
	 * Navigate to Records Management Site
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void goToRMSite(String className,String screenShot) throws InterruptedException, IOException
	{
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToSite("Records Management",className ,screenShot);
		Element.waitForLoad(driver);
	}
	
	/**
	 * Navigate to Document Library
	 * @throws InterruptedException
	 */
	public void goToDocLib() throws InterruptedException
	{
		Button documentLibraryButon = new Button(driver,
				By.id("HEADER_SITE_DOCUMENTLIBRARY"));
		documentLibraryButon.click();
		Element.waitForLoad(driver);
	}
	
	/**
	 * Navigate to Report in Site
	 * @throws InterruptedException
	 */
	public void goToReport() throws InterruptedException
	{
		Button reportButton = new Button(driver,
				By.id("HEADER_SITE_SITE-REPORTS_text"));
		reportButton.click();
		Element.waitForLoad(driver);
	}
	
	public void goToReportOption(String reportOption) throws InterruptedException
	{
		Button reportOptionButton = new Button(driver,
				By.xpath("//div//div//ul//li//span[@class='"+ reportOption + "']//a"));
		reportOptionButton.click();
		Element.waitForLoad(driver);
	}

	
	
	

}
