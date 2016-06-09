package com.zaizi.automation.alfresco.core.pages;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Division;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.Link;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;


/**
 * @author nbrahmananthan@zaizi.com
 */

public class UserDashboardPage {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(UserDashboardPage.class.getName());
	
	//public static  ExtentReports extent = ExtentReports.get(UserDashboardPage.class);

	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public UserDashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Add A Dash let to user DashBoard
	 * 
	 * @param dashletName
	 *            : Name of the dashlet which needs to be added
	 * @param columnNo
	 *            : Column no which the dashlet needs to be added
	 * @throws InterruptedException
	 */
	public void addDashletUserDash(String dashletName, String columnNo)
			throws InterruptedException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();

		Link customize = new Link(driver,
				By.id("HEADER_CUSTOMIZE_USER_DASHBOARD"));
		customize.click();
		Element.waitForLoad(driver);
		TestCaseProperties.dashletCount = 0;
		java.util.List<WebElement> dashletList = driver
				.findElements(By
						.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-column-ul-"
								+ columnNo + "']//li//span"));
		for (int k = 0; k < dashletList.size(); k++) {
			if (dashletList.get(k).getText().equals(dashletName)) {
				TestCaseProperties.dashletCount = TestCaseProperties.dashletCount + 1;
			}
		}

		Button addDashlets = new Button(
				driver,
				By.id("template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-addDashlets-button"));
		addDashlets.click();
		Thread.sleep(2000);
		Actions performer = new Actions(driver);

		java.util.List<WebElement> myList = driver.findElements(By
				.xpath("//ul//li[contains(.,'" + dashletName
						+ "')][contains(@class,'availableDashlet')]"));
		int j = 0;
		for (int i = 0; i < myList.size(); i++) {
			if (myList.get(i).getText().equals(dashletName)) {
				
				j = i;
			}
		}
		
		WebElement selectDashlet = myList.get(j);
		WebElement list = driver
				.findElement(By
						.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-column-ul-"
								+ columnNo + "']"));
		Action dragAndDrop = performer.clickAndHold(selectDashlet)
				.moveToElement(list).release(list).build();
		
		Thread.sleep(5000);
		dragAndDrop.perform();
		Thread.sleep(5000);
		Button okButton = new Button(
				driver,
				By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-save-button-button']"));
		okButton.click();
		Thread.sleep(5000);
	}

	/**
	 * Check Presence of User Button
	 * 
	 * @param userFirstName
	 *            : First Name of the user
	 * @return : Returns true if the user button is present
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public Boolean checkUserButton(String userFirstName, String userLastName)throws InterruptedException, IOException {
		Element element = new Element(driver,By.id("HEADER_USER_MENU_POPUP_text"));		
		String name;
		
		if(userLastName.isEmpty())
		{
			name = userFirstName;
		}
		else
		{
		name = userFirstName + " " + userLastName;
		}
		
		if (Element.isTextPresentInElement(element, name)) {
			
			return true;

		} else {			
			return false;

		}
	}
	
	/**
	 * Check Presence of User Button
	 * 
	 * @param userFirstName
	 *            : First Name of the user
	 * @return : Returns true if the user button is present
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public Boolean checkUserPresence(String userFirstName, String userLastName,String className,String screenShotname)throws InterruptedException, IOException {
		Element element = new Element(driver,By.id("HEADER_USER_MENU_POPUP_text"));		
		String name;
		
		if(userLastName.isEmpty())
		{
			name = userFirstName;
		}
		else
		{
		name = userFirstName + " " + userLastName;
		}
		
		if (Element.isTextPresentInElement(element, name)) {
			LOGGER.info("User firstName is successfully edited");
			//extent.log(LogStatus.PASS, "User firstName is successfully edited");
			//Element.takescreenshot(driver, className, screenShotname+"editSucess");
			return true;

		} else {
			LOGGER.info("User firstName IS NOT successfully edited");
			//extent.log(LogStatus.FAIL, "User firstName IS NOT successfully edited");
			//Element.takescreenshot(driver, className, screenShotname+"editFail");
			return false;

		}
	}
        
	
	/**
	 * Check Presence After creation of USER
	 * 
	 * @param userFirstName
	 *            : First Name of the user
	 * @param userLastName
	 * 			  : Last Name of the user
	 * @param className
	 * 			  : className to save screenShots
	 * @param screenShotName
	 * 			  : screenShots name
	 * @return : Returns true if the user button is present
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	
        public Boolean checkUserPresenceAfterCreation(String userFirstName, String userLastName,String className,String screenShotname)throws InterruptedException, IOException {
		
        LOGGER.info("Verify the HEADER_USER_MENU_NAME");
		//extent.log(LogStatus.INFO, "Verify the HEADER_USER_MENU_NAME");
                
                Element element = new Element(driver,By.id("HEADER_USER_MENU_POPUP_text"));		
		String name;
		
		if(userLastName.isEmpty())
		{
			name = userFirstName;
		}
		else
		{
		name = userFirstName + " " + userLastName;
		}
		
		if (Element.isTextPresentInElement(element, name)) {
			LOGGER.info("User is Sucessfully Created");
			//extent.log(LogStatus.PASS, "User is Sucessfully Created");
			//Element.takescreenshot(driver, className, screenShotname+"createSucess");
			return true;

		} else {
			LOGGER.info("User is NOT Sucessfully Created");
			//extent.log(LogStatus.FAIL, "User is NOT Sucessfully Created");
			//Element.takescreenshot(driver, className, screenShotname+"createFail");
			return false;

		}
	}

	/**
	 * Check User Status
	 * 
	 * @param status
	 *            : Status which needs to be checked
	 * @return : Returns true if the user status matches the status given
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public boolean checkUserStatus(String status) throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		navigateTo.goToMyProfile();
		Element.waitForLoad(driver);
		if (Element.isElementPresent(
				driver,
				By.xpath("//div//div[@class='user-status' and contains(.,'"
						+ status + "')]"))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Check Default User Profile Picture
	 * 
	 * @return : Returns true if the profile pic is default
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public boolean checkDefaultProfPic() throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		navigateTo.goToMyProfile();
		Element.waitForLoad(driver);
		if (Element
				.isElementPresent(
						driver,
						By.xpath("//div[@id='template_x002e_user-profile_x002e_user-profile_x0023_default-readview']//div//div//div//img[@src='/share/res/components/images/no-user-photo-64.png']"))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Check Presence of dashlet in User dashboard
	 * 
	 * @param dashletName
	 *            : Name of the Dashlet
	 * @param columnNo
	 *            : Column number the dashlet is present
	 * @return : Returns true if the dashlet is present
	 * @throws InterruptedException
	 */

	public boolean checkDashboardDashlet(String dashletName, String columnNo)
			throws InterruptedException {	

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		int count = 0;
	
		java.util.List<WebElement> myList = driver
				.findElements(By
						.xpath("//div[contains(@class,'column"
								+ columnNo
								+ "')][contains(@class,'yui-u')]//div//div//div//div[@class='title']"));
		for (int k = 0; k < myList.size(); k++) {
			if (myList.get(k).getText().equals(dashletName)) {
				count = count + 1;
			}
		}

		if (count > TestCaseProperties.dashletCount) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Check User Dashboard Layout
	 * 
	 * @param columnSize
	 *            : Number of columns present in the layout
	 * @return : Returns true if the layout is correct
	 * @throws InterruptedException
	 */
	public boolean checkUserDashboardLayout(String columnSize)
			throws InterruptedException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);

		WebElement column = driver.findElement(By
				.xpath("//body//div//div//div[@id='bd']//div"));

		if (column.getAttribute("class").contains(
				"columnSize" + columnSize + "")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Edit User Profile
	 * 
	 * @param userProfile
	 *            : Attributes of the fields which needs to be updated in a Map
	 *            format. the parameters are identified by pre-set key values.
	 *            Key Values are : userFirstName, userCompanyEmail, userEmail,
	 *            userCompanyAddress, userUsage, userTelephone,
	 *            userAccountStatus, userCompanyName, userCompanyFax,
	 *            userJobTitle, userGoogleUserName, userSummary,
	 *            userCompanyTelephone, userGroups, userLocation, userQuota,
	 *            userMobile, userIM, userName, userLastName, userSkype.
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void editUserProfile(Map<String, Serializable> userProfile)
			throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToMyProfile();
		Element.waitForLoad(driver);
		Button editProfileButton = new Button(
				driver,
				By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-button-edit-button"));
		editProfileButton.click();
		Thread.sleep(2000);


		if ((String) userProfile.get("userFirstName") != null) {
			TextField userFirstName = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-firstName"));
			userFirstName.clearText();
			Thread.sleep(500);
			userFirstName.enterText((String) userProfile.get("userFirstName"));
		}

		if ((String) userProfile.get("userLastName") != null) {
			TextField userLastName = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-lastName"));
			userLastName.clearText();
			Thread.sleep(500);
			userLastName.enterText((String) userProfile.get("userLastName"));
		}

		if ((String) userProfile.get("userJobTitle") != null) {
			TextField userJobTitle = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-jobtitle"));
			userJobTitle.clearText();
			Thread.sleep(500);
			userJobTitle.enterText((String) userProfile.get("userJobTitle"));
		}

		if ((String) userProfile.get("userLocation") != null) {
			TextField userLocation = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-location"));
			userLocation.clearText();
			Thread.sleep(500);
			userLocation.enterText((String) userProfile.get("userLocation"));
		}

		if ((String) userProfile.get("userSummary") != null) {
			TextField userSummary = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-bio"));
			userSummary.clearText();
			Thread.sleep(500);
			userSummary.enterText((String) userProfile.get("userSummary"));
		}

		if ((String) userProfile.get("userTelephone") != null) {
			TextField userTelephone = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-telephone"));
			userTelephone.clearText();
			Thread.sleep(500);
			userTelephone.enterText((String) userProfile.get("userTelephone"));
		}

		if ((String) userProfile.get("userMobile") != null) {
			TextField userMobile = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-mobile"));
			userMobile.clearText();
			Thread.sleep(500);
			userMobile.enterText((String) userProfile.get("userMobile"));
		}

		if ((String) userProfile.get("userEmail") != null) {
			TextField userEmail = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-email"));
			userEmail.clearText();
			Thread.sleep(500);
			userEmail.enterText((String) userProfile.get("userEmail"));
		}

		if ((String) userProfile.get("userSkype") != null) {
			TextField userSkype = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-skype"));
			userSkype.clearText();
			Thread.sleep(500);
			userSkype.enterText((String) userProfile.get("userSkype"));
		}

		if ((String) userProfile.get("userIM") != null) {
			TextField userIM = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-instantmsg"));
			userIM.clearText();
			Thread.sleep(500);
			userIM.enterText((String) userProfile.get("userIM"));
		}

		if ((String) userProfile.get("userGoogleUserName") != null) {
			TextField userGoogleUserName = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-googleusername"));
			userGoogleUserName.clearText();
			Thread.sleep(500);
			userGoogleUserName.enterText((String) userProfile
					.get("userGoogleUserName"));
		}

		if ((String) userProfile.get("userCompanyName") != null) {
			TextField userCompanyName = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-organization"));
			userCompanyName.clearText();
			Thread.sleep(500);
			userCompanyName.enterText((String) userProfile
					.get("userCompanyName"));
		}

		if ((String) userProfile.get("userCompanyAddress1") != null) {
			TextField userCompanyAddress1 = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-companyaddress1"));
			userCompanyAddress1.clearText();
			Thread.sleep(500);
			userCompanyAddress1.enterText((String) userProfile
					.get("userCompanyAddress1"));
		}

		if ((String) userProfile.get("userCompanyAddress2") != null) {
			TextField userCompanyAddress2 = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-companyaddress2"));
			userCompanyAddress2.clearText();
			Thread.sleep(500);
			userCompanyAddress2.enterText((String) userProfile
					.get("userCompanyAddress2"));
		}

		if ((String) userProfile.get("userCompanyAddress3") != null) {
			TextField userCompanyAddress3 = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-companyaddress3"));
			userCompanyAddress3.clearText();
			Thread.sleep(500);
			userCompanyAddress3.enterText((String) userProfile
					.get("userCompanyAddress3"));
		}

		if ((String) userProfile.get("userCompanyPostalCode") != null) {
			TextField userCompanyPostalCode = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-companypostcode"));
			userCompanyPostalCode.clearText();
			Thread.sleep(500);
			userCompanyPostalCode.enterText((String) userProfile
					.get("userCompanyPostalCode"));
		}

		if ((String) userProfile.get("userCompanyTelephone") != null) {
			TextField userCompanyTelephone = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-companytelephone"));
			userCompanyTelephone.clearText();
			Thread.sleep(500);
			userCompanyTelephone.enterText((String) userProfile
					.get("userCompanyTelephone"));
		}

		if ((String) userProfile.get("userCompanyFax") != null) {
			TextField userCompanyFax = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-companyfax"));
			userCompanyFax.clearText();
			Thread.sleep(500);
			userCompanyFax
					.enterText((String) userProfile.get("userCompanyFax"));
		}

		if ((String) userProfile.get("userCompanyEmail") != null) {
			TextField userCompanyEmail = new TextField(
					driver,
					By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-input-companyemail"));
			userCompanyEmail.clearText();
			Thread.sleep(500);
			userCompanyEmail.enterText((String) userProfile
					.get("userCompanyEmail"));
		}

		Button saveChangesButton = new Button(
				driver,
				By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-button-save-button"));
		saveChangesButton.click();
		Thread.sleep(2000);

	}

	/**
	 * Update User Status
	 * 
	 * @param status
	 *            : Status you need to update
	 * @throws InterruptedException
	 */

	public void updateUserStatus(String status) throws InterruptedException {
		// tbody//tr//td//div//div[@class='user-status' and
		// contains(.,'Testing')]

		Button userButton = new Button(driver,
				By.id("HEADER_USER_MENU_POPUP_text"));
		userButton.click();
		Thread.sleep(1000);

		Button userStatus = new Button(driver,
				By.id("HEADER_USER_MENU_SET_STATUS"));
		userStatus.click();
		Thread.sleep(1000);
		Element.waitForLoad(driver);
		TextField statusField = new TextField(driver,
				By.id("HEADER_USER_STATUS_STATUS_TEXTAREA"));
		statusField.clearText();
		statusField.enterText(status);
		Thread.sleep(1000);
		Button setStatus = new Button(
				driver,
				By.xpath("//div//span//span[@class='dijitReset dijitInline dijitButtonNode' and contains(.,'Post Status')]"));
		setStatus.click();
		Thread.sleep(1000);
		Element.waitForLoad(driver);

	}

	
	
	public void editUserFirstName(String userFirstName)
			throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
                
                LOGGER.info("Click \"My Profile\"");
		
                
		navigateTo.goToMyProfile();
                
            //    Element.takescreenshot(driver, className, screenshotName+"Userprofile");
		Element.waitForLoad(driver);	
		
                
                LOGGER.info("Click \"Edit Profile\"");
		
		Button editProfileButton = new Button(
				driver,
				By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-button-edit-button"));
		editProfileButton.click();
		Thread.sleep(2000);
		
		LOGGER.info("Change the User FirstName");
		
		//Element.takescreenshot(driver, className, screenshotName+"editUserName");		
		TextField firstName=new TextField(driver, By.xpath("//input[@id='template_x002e_user-profile_x002e_user-profile_x0023_default-input-firstName']"));
		if(firstName.getText().isEmpty())
		{
			LOGGER.info("FirstName is Empty,No need to Edit");
			
		}
		else
		{
			LOGGER.info("Edit the firstName as "+userFirstName);
			
			firstName.clearText();
			Thread.sleep(500);
			firstName.enterText(userFirstName);	
			//Element.takescreenshot(driver, className, screenshotName);
		}
		
		LOGGER.info("Click \"Save\" to save the changes");
		
		
		Button saveChangesButton = new Button(
				driver,
				By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-button-save-button"));
		saveChangesButton.click();
		Thread.sleep(2000);
                Element.waitForLoad(driver);
		
	}
	/**
	 * Change User Dashboard Layout
	 * 
	 * @param layoutNo
	 *            layoutNo 1 - One column, layoutNo 2 - Two columns: narrow
	 *            left, wide right, layoutNo 3 - Two columns: wide left, narrow
	 *            right, layoutNo 4 - Three columns: wide centre, layoutNo 5 -
	 *            Four columns
	 * @throws InterruptedException
	 */

	public void changeUserDashboardLayout(int layoutNo)
			throws InterruptedException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();

		Link customize = new Link(driver,
				By.id("HEADER_CUSTOMIZE_USER_DASHBOARD"));
		customize.click();
		Element.waitForLoad(driver);
		Button changeLayout = new Button(
				driver,
				By.id("template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-change-button-button"));
		changeLayout.click();
		Thread.sleep(2000);

		if (layoutNo == 1) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-layout-li-dashboard-1-column']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-select-button-dashboard-1-column-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}

		if (layoutNo == 2) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-layout-li-dashboard-2-columns-wide-right']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-select-button-dashboard-2-columns-wide-right-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}
		if (layoutNo == 3) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-layout-li-dashboard-2-columns-wide-left']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-select-button-dashboard-2-columns-wide-left-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}
		if (layoutNo == 4) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-layout-li-dashboard-3-columns']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-select-button-dashboard-3-columns-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}
		if (layoutNo == 5) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-layout-li-dashboard-4-columns']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-user-dashboard_x0023_default-select-button-dashboard-4-columns-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}

		Button okButton = new Button(
				driver,
				By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-save-button-button']"));
		okButton.click();
		Thread.sleep(1000);
	}

	/**
	 * Remove a Dash let from user dashboard
	 * 
	 * @param dashletName
	 *            : Name of the dashlet which needs to be removed.
	 * @param columnNo
	 *            : Column Number the dashlet should be removed from
	 * @throws InterruptedException
	 */

	public void removeDashletUserDash(String dashletName, String columnNo)
			throws InterruptedException {

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();

		Link customize = new Link(driver,
				By.id("HEADER_CUSTOMIZE_USER_DASHBOARD"));
		customize.click();
		Element.waitForLoad(driver);

		Actions performer = new Actions(driver);

		java.util.List<WebElement> myList = driver
				.findElements(By
						.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-column-ul-"
								+ columnNo
								+ "']//li[contains(.,'"
								+ dashletName
								+ "')][contains(@class,'usedDashlet')]"));
		Thread.sleep(1000);
		int j = 0;
		int count1 = 0;
		for (int i = 0; i < myList.size(); i++) {
			if (myList.get(i).getText().equals(dashletName)) {				
				j = i;
				count1 = count1 + 1;
			}
		}
		
		WebElement selectDashlet = myList.get(j);
		WebElement trash = driver
				.findElement(By
						.id("template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-trashcan-img"));
		Thread.sleep(1000);
		
		Action dragAndDrop = performer.dragAndDrop(selectDashlet, trash)
				.build();
		Thread.sleep(1000);
		dragAndDrop.perform();
		Thread.sleep(1000);

		java.util.List<WebElement> myupdateList = driver
				.findElements(By
						.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-column-ul-"
								+ columnNo
								+ "']//li[contains(.,'"
								+ dashletName
								+ "')][contains(@class,'usedDashlet')]"));
		Thread.sleep(1000);
		int l = 0;
		int count2 = 0;
		for (int n = 0; n < myupdateList.size(); n++) {
			if (myList.get(n).getText().equals(dashletName)) {
				
				l = n;
				count2 = count2 + 1;
			}
		}

		if (count2 >= count1) {
			WebElement selectDashlet1 = myList.get(l);
			WebElement trash1 = driver
					.findElement(By
							.id("template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-trashcan-img"));
			Thread.sleep(1000);
		
			Action dragAndDrop1 = performer.dragAndDrop(selectDashlet1, trash1)
					.build();
			Thread.sleep(1000);
			dragAndDrop1.perform();
			Thread.sleep(1000);
		}

		Button addDashlets = new Button(
				driver,
				By.id("template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-addDashlets-button"));
		addDashlets.click();
		Thread.sleep(1000);
		Button okButton = new Button(
				driver,
				By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-save-button-button']"));
		okButton.click();
		Thread.sleep(4000);
		Element.waitForLoad(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		
	}

	/**
	 * Accept Site Invitation
	 * 
	 * @param siteName
	 *            : Name of the site which the invitation should be Accepted.
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void acceptSiteInvitation(String siteName,String className,String screenShot)
			throws InterruptedException, IOException {
		Division taskMenu = new Division(driver, By.id("HEADER_TASKS"));
		taskMenu.click();
		Thread.sleep(2000);
		LOGGER.info("Click \"My task\"");
    	
	//	Element.takescreenshot(driver, className, screenShot+"mytask");
		Division myTasks = new Division(driver, By.id("HEADER_MY_TASKS"));
		myTasks.click();
		Element.waitForLoad(driver);

		Element.waitUntilElementClickable(
				driver,
				By.xpath("//tbody//tr//td//div//h3//a[contains(.,'Invitation to join "
								+ siteName + " site')]"));
		
		java.util.List<WebElement> myTasksList = driver
				.findElements(By
						.xpath("//tbody//tr//td//div//h3//a[contains(.,'Invitation to join "
								+ siteName + " site')]"));

		for (int i = 0; i < myTasksList.size(); i++) {

			if (myTasksList.get(i).getText()
					.equals("Invitation to join " + siteName + " site")) {

				WebElement checkBox = myTasksList.get(i);
				i = myTasksList.size();
				checkBox.click();
				Thread.sleep(1000);

				Element.waitForLoad(driver);
				LOGGER.info("Click \"Accept\"");
	        	
	        //	Element.takescreenshot(driver, className, screenShot+"AcceptInvitation");
				Element.waitUntilElementClickable(driver,By.xpath("//div//div//div//form//div//div//div//div//span//button[contains(.,'Accept')]"));
				Button acceptButton = new Button(driver,By.xpath("//div//div//div//form//div//div//div//div//span//button[contains(.,'Accept')]"));
				acceptButton.click();

			} else {
				LOGGER.error("Invitation to join site " + siteName+ " Not found");
				
			}
		}

	}

	/**
	 * Reject Site Invitation
	 * 
	 * @param siteName
	 *            : Name of the site which the invitation should be rejected.
	 * @throws InterruptedException
	 */

	public void rejectSiteInvitation(String siteName)
			throws InterruptedException {
		Division taskMenu = new Division(driver, By.id("HEADER_TASKS"));
		taskMenu.click();
		Thread.sleep(2000);
		Division myTasks = new Division(driver, By.id("HEADER_MY_TASKS"));
		myTasks.click();
		Element.waitForLoad(driver);

		java.util.List<WebElement> myTasksList = driver
				.findElements(By
						.xpath("//tbody//tr//td//div//h3//a[contains(.,'Invitation to join "
								+ siteName + " site')]"));

		for (int i = 0; i < myTasksList.size(); i++) {

			if (myTasksList.get(i).getText()
					.equals("Invitation to join " + siteName + " site")) {

				WebElement checkBox = myTasksList.get(i);
				i = myTasksList.size();
				checkBox.click();

			} else {
				LOGGER.error("Invitation to join site " + siteName
						+ " Not found");
			}
		}
		Element.waitForLoad(driver);
		Button rejectButton = new Button(
				driver,
				By.xpath("//div//div//div//form//div//div//div//div//span//button[contains(.,'Reject')]"));
		rejectButton.click();

	}

	/**
	 * Upload Profile Picture For an User.
	 * 
	 * @param fileName
	 *            : Name of the file which needs to be uploaded
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void uploadProfilePic(String fileName) throws InterruptedException,
			AWTException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToMyProfile();
		Element.waitForLoad(driver);
		Button editProfileButton = new Button(
				driver,
				By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-button-edit-button"));
		editProfileButton.click();
		Thread.sleep(2000);

		Button uploadButton = new Button(
				driver,
				By.id("template_x002e_user-profile_x002e_user-profile_x0023_default-button-upload"));
		uploadButton.click();
		Thread.sleep(500);		
		File file = null;

		try {
			file = new File(UserDashboardPage.class.getClassLoader()
					.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		Assert.assertTrue(file.exists());

		WebElement browseButton = driver.findElement(By
				.xpath("//span//input[@class='dnd-file-selection-button']"));
		browseButton.sendKeys(file.getAbsolutePath());

	}

	/**
	 * Check UserDashboard Logo
	 * 
	 * @param logoSource
	 *            : Source path of the logo
	 * @param logoHight
	 *            : Hight of the logo in pixels
	 * @param logoWidth
	 *            : Width of the logo in pixels
	 * @param logoDimX
	 *            : X axis Location of the logo placement in pixels
	 * @param logoDimY
	 *            : Y axix Location of the logo placement in pixels
	 * @return Returns true if the logo is present and located in the correct
	 *         location
	 * @throws InterruptedException
	 */

	public boolean checkUserDashLogo(String logoSource, int logoHight,
			int logoWidth, int logoDimX, int logoDimY)
			throws InterruptedException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToHome();
		Element.waitForLoad(driver);

		WebElement logo = driver.findElement(By.xpath("//div[@class='logo']"));

		int location_x = logoDimX;
		int location_y = logoDimY;
		Boolean location;
		if (logo.getLocation().getX() == location_x
				&& logo.getLocation().getY() == location_y) {
			location = true;
		} else {
			LOGGER.error("Location of the logo is incorrect");
			location = false;
		}

		int size_x = logoWidth;
		int size_y = logoHight;

		Boolean size;
		if (logo.getSize().getHeight() == size_y
				&& logo.getSize().getWidth() == size_x) {
			size = true;
		} else {
			LOGGER.error("Dimension of the logo is incorrect");
			size = false;
		}

		boolean logoSrc;
		if (Element.isElementPresent(driver, By
				.xpath("//div[@class='logo']//img[@src='" + logoSource + "']"))) {

			logoSrc = true;
		} else {
			LOGGER.error("Source of the logo is wrong");
			logoSrc = false;
		}

		if (location && size && logoSrc) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Accept Site Invitation
	 * 
	 * @param siteName
	 *            : Name of the site which the invitation should be Accepted.
	 * @param userName
	 *            : userName of User
	 * @param password
	 *            : password of User  
	 * @param className
	 *            : ClassName to Save ExtentReport
	 * @param screenShot
	 *            : Save Image as screenShot  
	 * @throws InterruptedException
	 * @throws IOException ,ParseException
	 */
	
	public void c_acceptSiteInvitationTest(String userName,String password,String siteName,String userFirstName,String searchName,String className,String screenShotName) throws InterruptedException, ParseException, IOException {
	
		UserDashboardPage userdashboardpage = new UserDashboardPage(driver);
		userdashboardpage.acceptSiteInvitation(siteName,className,screenShotName);
		Element.waitForLoad(driver);
		
		LOGGER.info(searchName+ " accept "+siteName+" Invitation Successfully");
		//extent.log(LogStatus.INFO, searchName+ " accept "+siteName+" Invitation Successfully");
		Element.waitForLoad(driver);
		Thread.sleep(3000);
		LoginPage loginPage = new LoginPage(driver);		
		loginPage.logout();
		
		LoginPage loginPage1 = new LoginPage(driver);
		LOGGER.info("Login as "+userName);
        //extent.log(LogStatus.INFO, "Login as "+userName);
        
		loginPage1.loginAsUser(userName, password);
		Element.waitForLoad(driver);
		
		LOGGER.info("Check whether accepted user display in "+siteName);
       //extent.log(LogStatus.INFO, "Check whether accepted user display in "+siteName);
		SiteDashboardPage sitedashboardpage = new SiteDashboardPage(driver);
		if(sitedashboardpage.checkUserInSite(userFirstName, siteName,className,screenShotName))
		{
			LOGGER.info(searchName+ " is available on "+siteName);
			//extent.log(LogStatus.INFO, searchName+ " is available on "+siteName);
		}
		else
		{
		LOGGER.info(searchName+ " is NOT available on "+siteName);
		//extent.log(LogStatus.INFO, searchName+ " is NOT available on "+siteName);
		}
		
		Element.waitForLoad(driver);
		
	}
	
	

}
