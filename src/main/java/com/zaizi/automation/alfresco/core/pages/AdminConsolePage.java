/**
 * This file is part of AlfrescoBasicFunctionalityTestingScripts.
 *
 * AlfrescoBasicFunctionalityTestingScripts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AlfrescoBasicFunctionalityTestingScripts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AlfrescoBasicFunctionalityTestingScripts.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zaizi.automation.alfresco.core.pages;

import java.awt.AWTException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Container;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.Link;
import com.zaizi.automation.alfresco.core.elements.Span;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;
import com.zaizi.automation.zaiziautomationapi.core.ZaiziPageObject;



/**
 * @author nbrahmananthan@zaizi.com
 * 
 */
public class AdminConsolePage implements ZaiziPageObject {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(AdminConsolePage.class.getName());
	
	/**
	 * Defining Report
	 */
	//public static  ExtentReports extent = ExtentReports.get(AdminConsolePage.class);

	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public AdminConsolePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

 /**
   *Add a user to a Specific Group.
   * 
   * @param groupName
               : Name of the group the user should be added to.
    @param userName
               : Username of the user.
    @param userFirstName
               : First name of the user.
    @return void
   * @throws InterruptedException
 * @throws IOException 
   */
  public void addUserToGroup(String userName, String userFirstName,
          String userLastName, String groupName) throws InterruptedException, IOException {
      NavigateToPage navigateTo = new NavigateToPage(driver);
      navigateTo.goToGroups();
      SearchObjects searchObjects = new SearchObjects(driver);
      searchObjects.searchGroup(groupName);
      Button browse = new Button(
              driver,
              By.id("page_x002e_ctool_x002e_admin-console_x0023_default-browse-button-button"));
      browse.click();
      Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);

      java.util.List<WebElement> myList = driver
              .findElements(By.xpath("//div//ul//li//div/a[contains(., '"
                      + groupName + "')]"));

      for (int i = 0; i < myList.size(); i++) {
          if (myList.get(i).getText().equals(groupName)) {
              WebElement checkBox = myList.get(i);
              checkBox.click();
              Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
          }
      }

      Span addUser = new Span(driver,
              By.xpath("//div//span//span[@class='groups-adduser-button']"));
      addUser.click();
      Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
      TextField peopleFinder = new TextField(
              driver,
              By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-peoplefinder-search-text"));
      peopleFinder.enterText(userName);
      Button searchButton = new Button(
              driver,
              By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-peoplefinder-search-button-button"));
      searchButton.click();
      Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
      java.util.List<WebElement> myUsers = driver.findElements(By
              .xpath("//tbody//tr//td//div//span"));

      for (int i = 0; i < myUsers.size(); i++) {

          if (myUsers.get(i).getText().equals("(" + userName + ")")) {

              WebElement checkBox = myUsers.get(i + 3);
              checkBox.click();
              Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
              break;
          }
      }AdminConsolePage adminConsolePage = new AdminConsolePage(driver);
      if (adminConsolePage.checkUserInGroup(userName, userFirstName,
              userLastName, groupName)) {
          LOGGER.info("" + userFirstName + " Added to " + groupName
                  + " Group Sucessfully ");
      } else {
          LOGGER.error("" + userFirstName
                  + " Could Not be Added to the Group " + groupName
                  + " Sucessfully");
      }

  }
  
  
/**
 *Add a Group to a Specific Group.
 * 
 * @param groupName
             : Name of the root group the nested group should be added to.
  
  @return void
 * @throws InterruptedException
 * @throws IOException 
 */
  
  
  public void addGroupToGroup(String groupName, String groupName1) throws InterruptedException, IOException {
      NavigateToPage navigateTo = new NavigateToPage(driver);
      navigateTo.goToGroups();
      SearchObjects searchObjects = new SearchObjects(driver);
      searchObjects.searchGroup(groupName);
      System.out.println("GroupBrowse");
      Button browse = new Button(driver,By.id("page_x002e_ctool_x002e_admin-console_x0023_default-browse-button-button"));
      browse.click();
      Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
      System.out.println("GroupBrowse1");
      java.util.List<WebElement> myList = driver
              .findElements(By.xpath("//div//ul//li//div/a[contains(., '"
                      + groupName + "')]"));
      System.out.println("GroupBrowse2");
      for (int i = 0; i < myList.size(); i++) {
    	  System.out.println("GroupBrowse1 i : " + i + ", groupName : " + groupName + ", myList.get(i) : " + myList.get(i));
    	  if (myList.get(i).getText().equals(groupName)) {
    		  System.out.println("GroupBrowse1 .. i : " + i);
              WebElement checkBox = myList.get(i);
              System.out.println("GroupBrowse1 checkBox : " + checkBox);
              checkBox.click();
              System.out.println("GroupBrowse1 ..aaa i : " + i);
              Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
          }
      }
      System.out.println("GroupBrowse3");
      Span addGroup = new Span(driver,
              By.xpath("//div//span//span[@class='groups-addgroup-button']"));
      addGroup.click();
      Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
      TextField groupFinder = new TextField(
              driver,
              By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-groupfinder-search-text"));
      groupFinder.enterText(groupName1);
      Button searchButton = new Button(
              driver,
              By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-groupfinder-group-search-button-button"));
      searchButton.click();
      Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
      Button button =new Button(driver, By.xpath("//tbody//tr//td//div//h3[text()='"+ groupName1 +"']//ancestor::div[1]//ancestor::td[1]//following-sibling::td[1]//button"));
           button.click(); 

      AdminConsolePage adminConsolePage = new AdminConsolePage(driver);
      if (adminConsolePage.checkGroupInGroup(groupName, groupName1)) {
          LOGGER.info("" + groupName1 + " Added to " + groupName
                  + " Group Sucessfully ");
      } else {
          LOGGER.error("" + groupName1
                  + " Could Not be Added to the Group " + groupName
                  + " Sucessfully");
      }

  }
  
  
/**
 *Check if the user is present in the system
 * 
 * @param userFirstName
             : First Name of the user.
  @return Returns true if the user is present in the system
 * @throws InterruptedException
 * @throws IOException 
 */

public Boolean checkUserPresence(String userName)
        throws InterruptedException, IOException {
    NavigateToPage navigateTo = new NavigateToPage(driver);
    
    LOGGER.info("Accessing HomePage");
        
    navigateTo.goToHome();
    
    LOGGER.info("Accessing Admintool page \"Users\"");
    
    navigateTo.goToUsers();
    Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
    
    SearchObjects searchUser=new SearchObjects(driver); 
    LOGGER.info("Search User");
   
    
    searchUser.searchUser(userName);   
    LOGGER.info("Clicked \"Search\" Button");
    
    Element.waitForLoad(driver);
    return Element.isTextPresentInList(
            driver.findElements(By.xpath("//tbody//td[3]//div[text()='"+userName+"']")),userName);
    

}

/**
 *Check if the user is present in the system
 * 
 * @param userFirstName
             : First Name of the user.
  @return Returns true if the user is present in the system
 * @throws InterruptedException
 * @throws IOException 
 */

public Boolean checkTestUserPresence()
        throws InterruptedException, IOException {
    NavigateToPage navigateTo = new NavigateToPage(driver);
    
    LOGGER.info("Accessing HomePage");
        
    navigateTo.goToHome();
    
    LOGGER.info("Accessing Admintool page \"Users\"");
    
    navigateTo.goToUsers();
    Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
    
    SearchObjects searchUser=new SearchObjects(driver); 
    LOGGER.info("Search User");
   
    
    searchUser.searchUser(TestCaseProperties.TEST_USER_NAME);   
    LOGGER.info("Clicked \"Search\" Button");
    
    Element.waitForLoad(driver);
    return Element.isTextPresentInList(
            driver.findElements(By.xpath("//tbody//td[3]//div[text()='"+TestCaseProperties.TEST_USER_NAME+"']")),TestCaseProperties.TEST_USER_NAME);
    

}


/*
Check if the user is present in the group.
* 
* @param userName
           : User name of the user
@param userFirstName
           : First name of the user
@param groupName
           : Name of the group
@return Returns true if the user is present in the group
* @throws InterruptedException
*/

public Boolean checkUserInGroup(String userName, String userFirstName,
      String userLastName, String groupName) throws InterruptedException, IOException {

  NavigateToPage navigateTo = new NavigateToPage(driver);
  navigateTo.goToGroup(groupName);

  String name;

  if (userLastName.isEmpty()) {
      name = userFirstName;
  } else {
      name = userFirstName + " " + userLastName;
  }

  java.util.List<WebElement> userList = driver.findElements(By
          .xpath("//li//div//a//span[contains(., '" + userName + "')]"));

  for (int i = 0; i < userList.size(); i++) {

      if (userList.get(i).getText().equals(name + " (" + userName + ")")) {
          LOGGER.info("User Present in the Group");
          return true;
      }

  }

  return false;

}


/*
Check if the group is present in the group.
* 
@param groupName
           : Name of the group
@return Returns true if the user is present in the group
* @throws InterruptedException
*/


public Boolean checkGroupInGroup(String groupName1, String groupName) throws InterruptedException, IOException {

	  NavigateToPage navigateTo = new NavigateToPage(driver);
	  navigateTo.goToGroup(groupName);

	  String name;

	  if (groupName1.isEmpty()) {
	      name = groupName1;
	  } else {
	      name = groupName1 + " " + groupName1;
	  }

	  java.util.List<WebElement> groupList = driver.findElements(By
	          .xpath("//li//div//a//span[contains(., '" + groupName1 + "')]"));

	  for (int i = 0; i < groupList.size(); i++) {

	      if (groupList.get(i).getText().equals(name + " (" + groupName1 + ")")) {
	          LOGGER.info("Group Present in the Group");
	          return true;
	      }

	  }

	  return false;

	}


/**
 * Check User Profile As Admin
 * 
 * @param userFirstName
 *            : First name of the user
 * @param userLastName
 *            : Last name of the user
 * @param userProfile
 *            : Attributes of the fields which needs to be updated in a Map
 *            format. the parameters are identified by pre-set key values.
 *            Key Values are : userFirstName, userCompanyEmail, userEmail,
 *            userCompanyAddress, userUsage, userTelephone,
 *            userAccountStatus, userCompanyName, userCompanyFax,
 *            userJobTitle, userGoogleUserName, userSummary,
 *            userCompanyTelephone, userGroups, userLocation, userQuota,
 *            userMobile, userIM, userName=, userLastName=, userSkype.
 * @return Returns true if the user details are matching.
 * @throws InterruptedException
 * @throws IOException 
 */

public boolean checkUserProfileAdmin(String userFirstName,
		String userLastName, Map<String, Serializable> userProfile)
		throws InterruptedException, IOException {
	NavigateToPage navigateTo = new NavigateToPage(driver);
	navigateTo.goToUserProfile1(userFirstName, userLastName);
	Element.waitForLoad(driver);

	Thread.sleep(5000);
	
	TextField email = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-email"));
	String userEmail = email.getWebElement().getText();
	//String userEmail = email.getElementAttribute("value");

	Thread.sleep(1000);
	/*TextField telephone = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-telephone"));
	//String userTelephone = telephone.getWebElement().getText();
	String userTelephone = telephone.getElementAttribute("value");

	Thread.sleep(1000);
	TextField mobile = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-mobile"));
	//String userMobile = mobile.getWebElement().getText();
	String userMobile = mobile.getElementAttribute("value");

	Thread.sleep(1000);
	TextField skype = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-skype"));
	//String userSkype = skype.getWebElement().getText();
	String userSkype = skype.getElementAttribute("value");

	Thread.sleep(1000);
	TextField im = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-instantmsg"));
	//String userIM = im.getWebElement().getText();
	String userIM = im.getElementAttribute("value");
	Thread.sleep(1000);
	TextField googleUserName = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-googleusername"));
	//String userGoogleUserName = googleUserName.getWebElement().getText();
	String userGoogleUserName = googleUserName.getElementAttribute("value");
	
	Thread.sleep(1000);
	TextField companyName = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companyname"));
	//String userCompanyName = companyName.getWebElement().getText();
	String userCompanyName = companyName.getElementAttribute("value");

	Thread.sleep(1000);
	TextField companyAddress = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companyaddress"));
	//String userCompanyAddress = companyAddress.getWebElement().getText();
	String userCompanyAddress = companyAddress.getElementAttribute("value");

	Thread.sleep(1000);
	TextField companyTelephone = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companytelephone"));
	//String userCompanyTelephone = companyTelephone.getWebElement().getText();
	String userCompanyTelephone = companyTelephone.getElementAttribute("value");

	Thread.sleep(1000);
	TextField companyFax = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companyfax"));
	//String userCompanyFax = companyFax.getWebElement().getText();
	String userCompanyFax = companyFax.getElementAttribute("value");

	Thread.sleep(1000);
	TextField companyEmail = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companyemail"));
	//String userCompanyEmail = companyEmail.getWebElement().getText();
	String userCompanyEmail = companyEmail.getElementAttribute("value");*/

	Thread.sleep(1000);
	TextField username = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-username"));
	String userName = username.getWebElement().getText();
	//String userName = username.getElementAttribute("value");

	/*Thread.sleep(1000);
	TextField groups = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-groups"));
	//String userGroups = groups.getWebElement().getText();
	String userGroups = groups.getElementAttribute("value");*/

	Thread.sleep(1000);
	TextField accountStatus = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-enabled"));
	String userAccountStatus = accountStatus.getWebElement().getText();
	//String userAccountStatus = accountStatus.getElementAttribute("value");

	/*Thread.sleep(1000);
	TextField quota = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-quota"));
	//String userQuota = quota.getWebElement().getText();
	String userQuota = quota.getElementAttribute("value");*/

	Thread.sleep(1000);
	TextField usage = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-usage"));
	//String userUsage = usage.getWebElement().getText();
	String userUsage = usage.getElementAttribute("value");

	/*Thread.sleep(1000);
	TextField jobTitle = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-jobtitle"));
	//String userJobTitle = jobTitle.getWebElement().getText();
	String userJobTitle = jobTitle.getElementAttribute("value");

	Thread.sleep(1000);
	TextField location = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-location"));
	//String userLocation = location.getWebElement().getText();
	String userLocation = location.getElementAttribute("value");

	Thread.sleep(1000);
	TextField summary = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-bio"));
	//String userSummary = summary.getWebElement().getText();
	String userSummary = summary.getElementAttribute("value");*/

	String userfirstname = "";
	String userlastname = "";
	TextField name = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-name"));

	StringTokenizer st2 = new StringTokenizer(name.getWebElement()
			.getText(), " ");
	if (st2.hasMoreTokens()) {
		userfirstname = st2.nextToken();
		userlastname = st2.nextToken();

	}
	Element.waitForLoad(driver);

	/*StringTokenizer st3 = new StringTokenizer(companyAddress
			.getWebElement().getText(), "\n");
	userCompanyAddress = "";

	int j = st3.countTokens();

	for (int i = 0; i < j; i++) {

		userCompanyAddress = userCompanyAddress + "" + st3.nextToken();

	}*/

	Map<String, Serializable> adminUserProfile = new HashMap<String, Serializable>();
	adminUserProfile.put("userFirstName", userfirstname);
	adminUserProfile.put("userLastName", userlastname);
	adminUserProfile.put("userEmail", userEmail);
/*	adminUserProfile.put("userCompanyAddress", userCompanyAddress);
	adminUserProfile.put("userTelephone", userTelephone);
	adminUserProfile.put("userMobile", userMobile);
	adminUserProfile.put("userSkype", userSkype);
	adminUserProfile.put("userIM", userIM);
	adminUserProfile.put("userGoogleUserName", userGoogleUserName);
	adminUserProfile.put("userCompanyName", userCompanyName);
	adminUserProfile.put("userCompanyTelephone", userCompanyTelephone);
	adminUserProfile.put("userCompanyFax", userCompanyFax);
	adminUserProfile.put("userCompanyEmail", userCompanyEmail);*/
	adminUserProfile.put("userName", userName);
	//adminUserProfile.put("userGroups", userGroups);
	adminUserProfile.put("userAccountStatus", userAccountStatus);
	//adminUserProfile.put("userQuota", userQuota);
	adminUserProfile.put("userUsage", userUsage);
	/*adminUserProfile.put("userJobTitle", userJobTitle);
	adminUserProfile.put("userLocation", userLocation);
	adminUserProfile.put("userSummary", userSummary);*/

	// LOGGER.info(userProfile);
	// LOGGER.info(adminUserProfile);
	Element.waitForLoad(driver);

	Boolean checkUserProfile = false;

	if ((String) userProfile.get("userFirstName") != null) {

		if (userProfile.get("userFirstName").equals(
				adminUserProfile.get("userFirstName"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("First Name Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userLastName") != null) {
		if (userProfile.get("userLastName").equals(
				adminUserProfile.get("userLastName"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Last Name Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userEmail") != null) {
		if (userProfile.get("userEmail").equals(
				adminUserProfile.get("userEmail"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Email Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userTelephone") != null) {
		if (userProfile.get("userTelephone").equals(
				adminUserProfile.get("userTelephone"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Telephone Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userMobile") != null) {
		if (userProfile.get("userMobile").equals(
				adminUserProfile.get("userMobile"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Mobile Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userSkype") != null) {
		if (userProfile.get("userSkype").equals(
				adminUserProfile.get("userSkype"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Skype Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userIM") != null) {
		if (userProfile.get("userIM")
				.equals(adminUserProfile.get("userIM"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User IM Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userGoogleUserName") != null) {
		if (userProfile.get("userGoogleUserName").equals(
				adminUserProfile.get("userGoogleUserName"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Google Username Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyName") != null) {
		if (userProfile.get("userCompanyName").equals(
				adminUserProfile.get("userCompanyName"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Name Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyAddress") != null) {
		if (userProfile.get("userCompanyAddress").equals(
				adminUserProfile.get("userCompanyAddress"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Address Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyTelephone") != null) {
		if (userProfile.get("userCompanyTelephone").equals(
				adminUserProfile.get("userCompanyTelephone"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Telephone Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyFax") != null) {
		if (userProfile.get("userCompanyFax").equals(
				adminUserProfile.get("userCompanyFax"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Fax Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyEmail") != null) {
		if (userProfile.get("userCompanyEmail").equals(
				adminUserProfile.get("userCompanyEmail"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Email Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userGroups") != null) {
		if (userProfile.get("userGroups").equals(
				adminUserProfile.get("userGroups"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Groups Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userAccountStatus") != null) {
		if (userProfile.get("userAccountStatus").equals(
				adminUserProfile.get("userAccountStatus"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Account Status Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userQuota") != null) {
		if (userProfile.get("userQuota").equals(
				adminUserProfile.get("userQuota"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Quota Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userUsage") != null) {
		if (userProfile.get("userUsage").equals(
				adminUserProfile.get("userUsage"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Usage Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userJobTitle") != null) {
		if (userProfile.get("userJobTitle").equals(
				adminUserProfile.get("userJobTitle"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Job Title Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userLocation") != null) {
		if (userProfile.get("userLocation").equals(
				adminUserProfile.get("userLocation"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Location Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userSummary") != null) {
		if (userProfile.get("userSummary").equals(
				adminUserProfile.get("userSummary"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Summary Field Does Not Match");
		}

	}
	Element.waitForLoad(driver);
	return checkUserProfile;

}


public boolean checkUserProfileAdmin2(String userFirstName,
		String userLastName, Map<String, Serializable> userProfile)
		throws InterruptedException, IOException {
	NavigateToPage navigateTo = new NavigateToPage(driver);
	navigateTo.goToUserProfile1(userFirstName, userLastName);
	Element.waitForLoad(driver);

	Thread.sleep(5000);
	
	TextField email = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-email"));
	String userEmail = email.getWebElement().getText();
	//String userEmail = email.getElementAttribute("value");

	Thread.sleep(1000);
	TextField telephone = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-telephone"));
	String userTelephone = telephone.getWebElement().getText();
	//String userTelephone = telephone.getElementAttribute("value");

	Thread.sleep(1000);
	TextField mobile = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-mobile"));
	String userMobile = mobile.getWebElement().getText();
	//String userMobile = mobile.getElementAttribute("value");

	Thread.sleep(1000);
	TextField skype = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-skype"));
	String userSkype = skype.getWebElement().getText();
	//String userSkype = skype.getElementAttribute("value");

	Thread.sleep(1000);
	TextField im = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-instantmsg"));
	String userIM = im.getWebElement().getText();
	//String userIM = im.getElementAttribute("value");
	Thread.sleep(1000);
	TextField googleUserName = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-googleusername"));
	String userGoogleUserName = googleUserName.getWebElement().getText();
	//String userGoogleUserName = googleUserName.getElementAttribute("value");
	
	Thread.sleep(1000);
	TextField companyName = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companyname"));
	String userCompanyName = companyName.getWebElement().getText();
	//String userCompanyName = companyName.getElementAttribute("value");

	Thread.sleep(1000);
	TextField companyAddress = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companyaddress"));
	String userCompanyAddress = companyAddress.getWebElement().getText();
	//String userCompanyAddress = companyAddress.getElementAttribute("value");

	Thread.sleep(1000);
	TextField companyTelephone = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companytelephone"));
	String userCompanyTelephone = companyTelephone.getWebElement().getText();
	//String userCompanyTelephone = companyTelephone.getElementAttribute("value");

	Thread.sleep(1000);
	TextField companyFax = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companyfax"));
	String userCompanyFax = companyFax.getWebElement().getText();
	//String userCompanyFax = companyFax.getElementAttribute("value");

	Thread.sleep(1000);
	TextField companyEmail = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-companyemail"));
	String userCompanyEmail = companyEmail.getWebElement().getText();
	//String userCompanyEmail = companyEmail.getElementAttribute("value");

	Thread.sleep(1000);
	TextField username = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-username"));
	String userName = username.getWebElement().getText();
	//String userName = username.getElementAttribute("value");

	/*Thread.sleep(1000);
	TextField groups = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-groups"));
	String userGroups = groups.getWebElement().getText();
	//String userGroups = groups.getElementAttribute("value");
*/
	Thread.sleep(1000);
	TextField accountStatus = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-enabled"));
	String userAccountStatus = accountStatus.getWebElement().getText();
	//String userAccountStatus = accountStatus.getElementAttribute("value");

	/*Thread.sleep(1000);
	TextField quota = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-quota"));
	String userQuota = quota.getWebElement().getText();
	//String userQuota = quota.getElementAttribute("value");
*/
	Thread.sleep(1000);
	TextField usage = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-usage"));
	//String userUsage = usage.getWebElement().getText();
	String userUsage = usage.getElementAttribute("value");

	Thread.sleep(1000);
	TextField jobTitle = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-jobtitle"));
	String userJobTitle = jobTitle.getWebElement().getText();
	//String userJobTitle = jobTitle.getElementAttribute("value");

	Thread.sleep(1000);
	TextField location = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-location"));
	String userLocation = location.getWebElement().getText();
	//String userLocation = location.getElementAttribute("value");

	Thread.sleep(1000);
	TextField summary = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-bio"));
	String userSummary = summary.getWebElement().getText();
	//String userSummary = summary.getElementAttribute("value");

	String userfirstname = "";
	String userlastname = "";
	TextField name = new TextField(
			driver,
			By.id("page_x002e_ctool_x002e_admin-console_x0023_default-view-name"));

	StringTokenizer st2 = new StringTokenizer(name.getWebElement()
			.getText(), " ");
	if (st2.hasMoreTokens()) {
		userfirstname = st2.nextToken();
		userlastname = st2.nextToken();

	}
	Element.waitForLoad(driver);

	StringTokenizer st3 = new StringTokenizer(companyAddress
			.getWebElement().getText(), "\n");
	userCompanyAddress = "";

	int j = st3.countTokens();

	for (int i = 0; i < j; i++) {

		userCompanyAddress = userCompanyAddress + "" + st3.nextToken();

	}

	Map<String, Serializable> adminUserProfile = new HashMap<String, Serializable>();
	adminUserProfile.put("userFirstName", userfirstname);
	adminUserProfile.put("userLastName", userlastname);
	adminUserProfile.put("userEmail", userEmail);
	adminUserProfile.put("userCompanyAddress", userCompanyAddress);
	adminUserProfile.put("userTelephone", userTelephone);
	adminUserProfile.put("userMobile", userMobile);
	adminUserProfile.put("userSkype", userSkype);
	adminUserProfile.put("userIM", userIM);
	adminUserProfile.put("userGoogleUserName", userGoogleUserName);
	adminUserProfile.put("userCompanyName", userCompanyName);
	adminUserProfile.put("userCompanyTelephone", userCompanyTelephone);
	adminUserProfile.put("userCompanyFax", userCompanyFax);
	adminUserProfile.put("userCompanyEmail", userCompanyEmail);
	adminUserProfile.put("userName", userName);
	//adminUserProfile.put("userGroups", userGroups);
	adminUserProfile.put("userAccountStatus", userAccountStatus);
	//adminUserProfile.put("userQuota", userQuota);
	adminUserProfile.put("userUsage", userUsage);
	adminUserProfile.put("userJobTitle", userJobTitle);
	adminUserProfile.put("userLocation", userLocation);
	adminUserProfile.put("userSummary", userSummary);

	// LOGGER.info(userProfile);
	// LOGGER.info(adminUserProfile);
	Element.waitForLoad(driver);

	Boolean checkUserProfile = false;

	if ((String) userProfile.get("userFirstName") != null) {

		if (userProfile.get("userFirstName").equals(
				adminUserProfile.get("userFirstName"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("First Name Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userLastName") != null) {
		if (userProfile.get("userLastName").equals(
				adminUserProfile.get("userLastName"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Last Name Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userEmail") != null) {
		if (userProfile.get("userEmail").equals(
				adminUserProfile.get("userEmail"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Email Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userTelephone") != null) {
		if (userProfile.get("userTelephone").equals(
				adminUserProfile.get("userTelephone"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Telephone Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userMobile") != null) {
		if (userProfile.get("userMobile").equals(
				adminUserProfile.get("userMobile"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Mobile Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userSkype") != null) {
		if (userProfile.get("userSkype").equals(
				adminUserProfile.get("userSkype"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Skype Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userIM") != null) {
		if (userProfile.get("userIM")
				.equals(adminUserProfile.get("userIM"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User IM Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userGoogleUserName") != null) {
		if (userProfile.get("userGoogleUserName").equals(
				adminUserProfile.get("userGoogleUserName"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Google Username Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyName") != null) {
		if (userProfile.get("userCompanyName").equals(
				adminUserProfile.get("userCompanyName"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Name Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyAddress") != null) {
		if (userProfile.get("userCompanyAddress").equals(
				adminUserProfile.get("userCompanyAddress"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Address Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyTelephone") != null) {
		if (userProfile.get("userCompanyTelephone").equals(
				adminUserProfile.get("userCompanyTelephone"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Telephone Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyFax") != null) {
		if (userProfile.get("userCompanyFax").equals(
				adminUserProfile.get("userCompanyFax"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Fax Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userCompanyEmail") != null) {
		if (userProfile.get("userCompanyEmail").equals(
				adminUserProfile.get("userCompanyEmail"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Company Email Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userGroups") != null) {
		if (userProfile.get("userGroups").equals(
				adminUserProfile.get("userGroups"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Groups Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userAccountStatus") != null) {
		if (userProfile.get("userAccountStatus").equals(
				adminUserProfile.get("userAccountStatus"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Account Status Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userQuota") != null) {
		if (userProfile.get("userQuota").equals(
				adminUserProfile.get("userQuota"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Quota Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userUsage") != null) {
		if (userProfile.get("userUsage").equals(
				adminUserProfile.get("userUsage"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("User Usage Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userJobTitle") != null) {
		if (userProfile.get("userJobTitle").equals(
				adminUserProfile.get("userJobTitle"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Job Title Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userLocation") != null) {
		if (userProfile.get("userLocation").equals(
				adminUserProfile.get("userLocation"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Location Field Does Not Match");
		}

	}

	if ((String) userProfile.get("userSummary") != null) {
		if (userProfile.get("userSummary").equals(
				adminUserProfile.get("userSummary"))) {
			checkUserProfile = true;
		} else {
			checkUserProfile = false;
			LOGGER.error("Summary Field Does Not Match");
		}

	}
	Element.waitForLoad(driver);
	return checkUserProfile;

}

	/**
	 * Create a New User
	 * 
	 * @param firstName
	 *            : First Name of the user
	 * @param userName
	 *            : Username of the user
	 * @param password
	 *            : Password of the user
	 * @param emailVal
	 *            : Email Address of the user
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void createNewUser(String firstName,String lastName,String fullName, String emailVal,String userName,String password,String verifyPassword) throws InterruptedException, IOException {
		
		if (Element.isElementPresent(
				driver,
				By.xpath("//Span[@id='HEADER_USER_MENU_POPUP_text'][text()='"+fullName+"']")))
		{

			LOGGER.info(TestCaseProperties.TEST_USER_NAME + " IS EXSIT");
			//extent.log(LogStatus.PASS, "<font color=green>" + userName
			//		+ " IS EXSIT" + "<font>");

			//Element.takescreenshot(driver, className, screenShotName+"successlogin");
			
			Dashboard logout=new Dashboard(driver);
			logout.logout();

		}

		else		
			
		//LOGIN as SEARCHING USER[LOGIN FAIL]	
		//login AS ADMIN
	    //Create particular searching user
		//Verify whether that user Create or NOT
		{

			LOGGER.info(TestCaseProperties.TEST_USER_NAME + " IS NOT EXSIT");			
			//extent.log(LogStatus.INFO, "<font color=blue>" + userName
				//	+ " IS NOT EXSIT" + "<font>");
			
			//Element.takescreenshot(driver, className, screenShotName+"faillogin");
			
			LOGGER.info("Create User called \" "+TestCaseProperties.TEST_USER_NAME+" \"");
	       // extent.log(LogStatus.INFO, "Create User "+userName);			

			AdminConsolePage createUser = new AdminConsolePage(driver);
			createUser.createUser(firstName,lastName,emailVal,userName,password,verifyPassword);

		}

	}
	
	public void createUser(String firstName,String lastName,String email,String userName,String password,String verifyPassword) throws InterruptedException, IOException 

	 {

		 	 Element.waitForLoad(driver);
	 		 
	         AdmintoolPage admintoolPage = new AdmintoolPage(driver);
	         
	         LOGGER.info("Click  \"AdminTools\" ");
	         Dashboard dashboard=new Dashboard(driver);
	         dashboard.gotoAdminConsole();
	         
	         Element.waitForLoad(driver);
	         
	         LOGGER.info("Navigate \"Users\" IN AdminTools");
	         admintoolPage.user();
	         Element.waitForLoad(driver);
	         
	         LOGGER.info("Click \"New User\"");
	         admintoolPage.createNewUser();
	         
	         LOGGER.info("Fill the userfields");
	         admintoolPage.fillUserFields(firstName,lastName,email,userName,password,verifyPassword);
	         
    

	 }

	/**
	 * Create a New Group
	 * 
	 * @param groupName
	 *            : Name of the group
	 * @param groupId
	 *            : Group ID
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void createGroup(String groupName, String groupId)
			throws InterruptedException, IOException {

		NavigateToPage navigateTo = new NavigateToPage(driver);
		
		
		navigateTo.goToGroups();
		
		LOGGER.info("Click \"Browse\" Button");
		
		Button browse = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-browse-button-button"));
		browse.click();
		Thread.sleep(2000);
		
		LOGGER.info("Click \"+\" ,to create Group");
		
		//Element.takescreenshot(driver, className, screenShotName+"pulse");
		Span group = new Span(driver,
				By.xpath("//span[@class='groups-newgroup-button']"));
		group.click();
		Thread.sleep(2000);
		
		
		LOGGER.info("Enter the groupID as "+groupId);
		
		TextField identifier = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-create-shortname"));
		identifier.enterText(groupId);
		Thread.sleep(2000);
		
		LOGGER.info("Enter the group name as "+groupName);
		
		TextField displayName = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-create-displayname"));
		displayName.enterText(groupName);
		Thread.sleep(2000);
		
		LOGGER.info("Click \"Create\" Button");
		
		
		Button create = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-creategroup-ok-button-button"));
		
		//Element.takescreenshot(driver, className, screenShotName+"groupid");
		create.click();
		Element.waitForLoad(driver);
		Thread.sleep(2000);
		
	}
	
	
	/**
	 * Modify User Details as Admin
	 * 
	 * @param userFirstName
	 *            : First name of the user
	 * @param userLastName
	 *            : Last name of the user
	 * @param newUserFirstName
	 *            : New First name of the user
	 * @param newUserLastName
	 *            : New last name of the user
	 * @param newUserEmail
	 *            : New Email Address of the user
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void editUserDetails(String userFirstName, String userLastName,
			String newUserFirstName, String newUserLastName,String userName, String newUserEmail)
			throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToUserProfile(userFirstName, userLastName,userName);
		Element.waitForLoad(driver);
		Button editUserButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-edituser-button"));
		editUserButton.click();
		Thread.sleep(2000);
		TextField userFirstNameTextField = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-firstname"));
		TextField userLastNameTextField = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-lastname"));
		TextField userEmailTextField = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-email"));
		userFirstNameTextField.clearText();
		Thread.sleep(500);
		userFirstNameTextField.enterText(newUserFirstName);
		userLastNameTextField.clearText();
		Thread.sleep(500);
		userLastNameTextField.enterText(newUserLastName);
		userEmailTextField.clearText();
		Thread.sleep(500);
		userEmailTextField.enterText(newUserEmail);
		Thread.sleep(500);
		Button saveButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-updateuser-save-button-button"));
		saveButton.click();
		Thread.sleep(2000);
		Element.waitForLoad(driver);
	}

	
	/**
	 * Modify User Details as Admin
	 * 
	 * @param userFirstName
	 *            : First name of the user
	 * @param userLastName
	 *            : Last name of the user
	 * @param newUserFirstName
	 *            : New First name of the user
	 * @param newUserLastName
	 *            : New last name of the user
	 * @param newUserEmail
	 *            : New Email Address of the user
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void editUserDetails1(String userFirstName, String userLastName,
			String newUserFirstName, String newUserLastName, String newUserEmail)
			throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToUserProfile1(userFirstName, userLastName);
		Element.waitForLoad(driver);
		Button editUserButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-edituser-button"));
		editUserButton.click();
		Thread.sleep(5000);
		TextField userFirstNameTextField = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-firstname"));
		TextField userLastNameTextField = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-lastname"));
		TextField userEmailTextField = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-email"));
		userFirstNameTextField.clearText();
		Thread.sleep(3000);
		userFirstNameTextField.enterText(newUserFirstName);
		userLastNameTextField.clearText();
		Thread.sleep(3000);
		userLastNameTextField.enterText(newUserLastName);
		userEmailTextField.clearText();
		Thread.sleep(3000);
		userEmailTextField.enterText(newUserEmail);
		Thread.sleep(3000);
		Button saveButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-updateuser-save-button-button"));
		saveButton.click();
		Thread.sleep(5000);
		Element.waitForLoad(driver);
	}
	
	/**
	 * Change User Password as Administrator
	 * 
	 * @param userFirstName
	 *            : First name of the user
	 * @param userLastName
	 *            : Last name of the user
	 * @param userPassword
	 *            : New Password of the user
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void changeUserPassAsAdmin(String userFirstName,
			String userLastName, String userPassword,String userName)
			throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToUserProfile(userFirstName, userLastName,userName);
		Element.waitForLoad(driver);
		Button editUserButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-edituser-button"));
		editUserButton.click();
		Thread.sleep(2000);
		TextField passwordTextField = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-password"));
		TextField verifyPasswordTextField = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-update-verifypassword"));
		passwordTextField.clearText();
		Thread.sleep(500);
		passwordTextField.enterText(userPassword);
		verifyPasswordTextField.clearText();
		Thread.sleep(500);
		verifyPasswordTextField.enterText(userPassword);
		Thread.sleep(500);
		Button saveButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-updateuser-save-button-button"));
		saveButton.click();
		Thread.sleep(2000);
		Element.waitForLoad(driver);
	}

	/**
	 * Delete User from the System
	 * 
	 * @param firstName
	 *            : First name of the user
	 * @param lastName
	 *            : Last name of the user
	 * @param userName
	 *            : Username of the user
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	
	public void deleteUser(String firstName, String lastName, String userName,String className,String screenShotName)
			throws InterruptedException, IOException {

		AdminConsolePage adminConsolePage = new AdminConsolePage(driver);		
		if (adminConsolePage.checkUserPresence(userName)) {
			
			NavigateToPage navigateTo = new NavigateToPage(driver);	
			
			LOGGER.info("Accessing UserProfilePage Again");
	        

			navigateTo.goToUserProfile(firstName,lastName,userName);
			Element.waitForLoad(driver);
			
                        
            LOGGER.info("Click \"Delete User\" Button");
           
			Button deleteButton = new Button(
					driver,
					By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-deleteuser-button-button']"));
                      //  Element.takescreenshot(driver, className, screenShotName+"deleteButton");
			deleteButton.click();                      
			
			Element.waitForLoad(driver);
			Thread.sleep(2000);
			
            LOGGER.info("Click \"Delete Confirmation\" Button");
          
			Button deleteConfirmation = new Button(
					driver,
					By.xpath("//div[@class='ft']//button[text()='Delete']"));
                        //Element.takescreenshot(driver, className, screenShotName+"deleteConfirm");
			deleteConfirmation.click();			
			
			Element.waitForLoad(driver);
			Thread.sleep(4000);
			
        
			
		} else {
			LOGGER.info("User : " + firstName
					+ " Not Available in the System to Delete");
			
		}
	}

	public void deleteTestUser(String firstName, String lastName,String userName)
			throws InterruptedException, IOException {

		AdminConsolePage adminConsolePage = new AdminConsolePage(driver);		
		if (adminConsolePage.checkUserPresence(userName)) {
			
			NavigateToPage navigateTo = new NavigateToPage(driver);	
			
			LOGGER.info("Accessing UserProfilePage Again");
	        

			navigateTo.goToUserProfile(firstName,lastName,userName);
			Element.waitForLoad(driver);
			
                        
            LOGGER.info("Click \"Delete User\" Button");
           
			Button deleteButton = new Button(
					driver,
					By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-deleteuser-button-button']"));
                      //  Element.takescreenshot(driver, className, screenShotName+"deleteButton");
			deleteButton.click();                      
			
			Element.waitForLoad(driver);
			Thread.sleep(2000);
			
            LOGGER.info("Click \"Delete Confirmation\" Button");
          
			Button deleteConfirmation = new Button(
					driver,
					By.xpath("//div[@class='ft']//button[text()='Delete']"));
                        //Element.takescreenshot(driver, className, screenShotName+"deleteConfirm");
			deleteConfirmation.click();			
			
			Element.waitForLoad(driver);
			Thread.sleep(4000);
			
        
			
		} else {
			LOGGER.info("User : " + firstName
					+ " Not Available in the System to Delete");
			
		}
	}
	/*
    Remove User From Group
   * 
   * @param userName
               : Username of the user
    @param userFirstName
               : First name of the user
    @param groupName
               : Name of the group
    @throws InterruptedException
   */

  public void removeUserFromGroup(String userName, String userFirstName,
          String userLastName, String groupName) throws InterruptedException, IOException {
      NavigateToPage navigateTo = new NavigateToPage(driver);
      navigateTo.goToGroup(groupName);

      AdminConsolePage adminConsolePage = new AdminConsolePage(driver);
      if (adminConsolePage.checkUserInGroup(userName, userFirstName,
              userLastName, groupName)) {

          java.util.List<WebElement> userList = driver
                  .findElements(By
                          .xpath("//li//div//a[@class='yui-columnbrowser-item groups-item-user']"));

          for (int i = 0; i < userList.size(); i++) {

              if (userList.get(i).getText()
                      .equals(userFirstName + " (" + userName + ")")) {

                  int j = i;
                  userList.get(i).click();
                  Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);

                  java.util.List<WebElement> removeUserButton = driver
                          .findElements(By
                                  .xpath("//div//ul//li//div//a//span//span[@class='users-remove-button']"));

                  Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_1000);
                  removeUserButton.get(j).click();
                  Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
                  Button yesButton = new Button(
                          driver,
                          By.xpath("//div//span//span//span//button[contains(.,'Yes')]"));
                  yesButton.click();
                  Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
                  break;
              }

          }
      } else {
          LOGGER.error("User " + userFirstName
                  + " Not Present in group to Delete");
      }

      Element.waitForLoad(driver);

  }
	/**
	 * Search for Presence of Group.
	 * 
	 * @param groupName
	 *            : Name of the group
	 * @return Returns true if the group is present in the system
	 * @throws InterruptedException
	 * @throws IOException 
	 * 
	 */

	public Boolean checkGroupPresence(String groupName)
			throws InterruptedException, IOException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		
		LOGGER.info("Click \"Groups\"");
		//extent.log(LogStatus.INFO, "Click \"Groups\"");
		navigateToPage.goToGroups();
		Element.waitForLoad(driver);
		//Element.takescreenshot(driver, className, screenShotName+"gotoGroup122");
		
		LOGGER.info("Enter GroupName to search");
		//extent.log(LogStatus.INFO, "Enter GroupName to search");
		
		TextField groupSearchBar = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-text"));
		groupSearchBar.clearText();
		groupSearchBar.enterText(groupName);
		 
		//Element.takescreenshot(driver, className,screenShotName+"checkgrp12");
		
		LOGGER.info("Click \"Search\" Button");
		//extent.log(LogStatus.INFO, "Click \"Search\" Button");
		
		Button searchButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button"));
		searchButton.click();
		Element.waitForLoad(driver);
		Thread.sleep(1000);
		 
		
		if (Element.isTextPresentInListForGroup(
				driver.findElements(By
						.xpath("//tbody//tr//td//div[contains(., '" + groupName
								+ "')]")), groupName)) {
			
			LOGGER.info("Group "+groupName+" IS SUCCESSFULLY CREATED");
			//extent.log(LogStatus.PASS, "Group "+groupName+" IS SUCCESSFULLY CREATED");
			//Element.takescreenshot(driver, className, screenShotName+"grppresensuc44");
			return true;
		}

		else {
			
			LOGGER.info("Group "+groupName+" IS NOT SUCCESSFULLY CREATED");
			//extent.log(LogStatus.FAIL, "Group "+groupName+" IS NOT SUCCESSFULLY CREATED");
			//Element.takescreenshot(driver, className, screenShotName+"grppresennotsuc44");
			return false;
		}

	}
	public Boolean checkGroupPresenceAtSearching(String groupName)
			throws InterruptedException, IOException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		
		LOGGER.info("Click \"Groups\"");
		//extent.log(LogStatus.INFO, "Click \"Groups\"");
		navigateToPage.goToGroups();
		Element.waitForLoad(driver);
		//Element.takescreenshot(driver, className, screenShotName+"gotoGroup122");
		
		LOGGER.info("Enter GroupName to search");
		//extent.log(LogStatus.INFO, "Enter GroupName to search");
		
		TextField groupSearchBar = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-text"));
		groupSearchBar.clearText();
		groupSearchBar.enterText(groupName);
		 
		//Element.takescreenshot(driver, className,screenShotName+"checkgrp12");
		
		LOGGER.info("Click \"Search\" Button");
		//extent.log(LogStatus.INFO, "Click \"Search\" Button");
		
		Button searchButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button"));
		searchButton.click();
		Element.waitForLoad(driver);
		Thread.sleep(1000);
		 
		
		if (Element.isTextPresentInListForGroup(
				driver.findElements(By
						.xpath("//tbody//tr//td//div[contains(., '" + groupName
								+ "')]")), groupName)) {
			
			return true;
		}

		else {
			
			return false;
		}

	}
        
	/**
	 * Search for Presence of Group.
	 * 
	 * @param groupName
	 *            : Name of the group
	 * @return Returns true if the group is present in the system
	 * @throws InterruptedException
	 * @throws IOException 
	 * 
	 */

	public Boolean checkGroupPresenceAfterRemoval(String groupName)
			throws InterruptedException, IOException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToGroups();
		Element.waitForLoad(driver);
		
		LOGGER.info("Enter GroupName to search");
		//extent.log(LogStatus.INFO, "Enter GroupName to search");
		
		TextField groupSearchBar = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-text"));
		groupSearchBar.clearText();
		groupSearchBar.enterText(groupName);
		 
		//Element.takescreenshot(driver, className,screenShotName+"checkgrsp");
		
		LOGGER.info("Click \"Search\" Button");
		//extent.log(LogStatus.INFO, "Click \"Search\" Button");
		
		Button searchButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button"));
		searchButton.click();
		Element.waitForLoad(driver);
		Thread.sleep(3000);
		 
		
		if (Element.isTextPresentInListForGroup(
				driver.findElements(By
						.xpath("//tbody//tr//td//div[contains(., '" + groupName
								+ "')]")), groupName)) {
			
			LOGGER.info("Group "+groupName+" IS NOT SUCCESSFULLY Removed");
			//extent.log(LogStatus.FAIL, "Group "+groupName+" IS NOT SUCCESSFULLY Removed");			
		//	Element.takescreenshot(driver, className, screenShotName+"grppressensuc");
			return true;
		}

		else {
			
			LOGGER.info("Group "+groupName+" IS SUCCESSFULLY Removed");
			//extent.log(LogStatus.PASS, "Group "+groupName+" IS SUCCESSFULLY Removed");
			//Element.takescreenshot(driver, className, screenShotName+"grppresensnotsuc");
			return false;
		}

	}
	/**
	 * Search for Presence of Group.
	 * 
	 * @param groupName
	 *            : Name of the group
	 * @return Returns true if the group is present in the system
	 * @throws InterruptedException
	 * @throws IOException 
	 * 
	 */

	public Boolean checkGroupPresenceAfterEdit(String newGroupName,String groupName)
			throws InterruptedException, IOException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToGroups();
		Element.waitForLoad(driver);
		
		LOGGER.info("Enter GroupName to search");
		//extent.log(LogStatus.INFO, "Enter GroupName to search");
		
		TextField groupSearchBar = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-text"));
		groupSearchBar.clearText();
		groupSearchBar.enterText(newGroupName);
		 
		//Element.takescreenshot(driver, className,screenShotName+"checkgrp");
		
		LOGGER.info("Click \"Search\" Button");
		//extent.log(LogStatus.INFO, "Click \"Search\" Button");
		
		Button searchButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button"));
		searchButton.click();
		Element.waitForLoad(driver);
		Thread.sleep(1000);
		 
		
		if (Element.isTextPresentInListForGroup(
				driver.findElements(By
						.xpath("//tbody//tr//td//div[contains(., '" + newGroupName
								+ "')]")), newGroupName)) {		
			
			
			LOGGER.info("Group "+groupName+" IS SUCCESSFULLY EDITED as Group "+newGroupName);
			//extent.log(LogStatus.PASS, "Group "+groupName+"  IS SUCCESSFULLY EDITED as Group "+newGroupName);			
			//Element.takescreenshot(driver, className, screenShotName+"grppresensuc1");
			return true;
		}

		else {
			LOGGER.info("Group "+groupName+" IS NOT SUCCESSFULLY EDITED as Group "+newGroupName);
			//extent.log(LogStatus.FAIL, "Group "+groupName+" IS NOT SUCCESSFULLY EDITED as Group "+newGroupName);
			//Element.takescreenshot(driver, className, screenShotName+"grppresennotfail2");
			
			
			return false;
		}

	}
	/**
	 * Edit to a specific group
	 * 
	 * @param groupName
	 *            : Name of the group
	 * @throws InterruptedException
	 * @throws IOException 
	 * */

	public void editGroup(String groupName,String newGroupName) throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
                
                LOGGER.info("Click \"Groups\"");
		
		navigateTo.goToGroups();
                
                
                AdminConsolePage adminConsolePage = new AdminConsolePage(driver);
                if (adminConsolePage.checkGroupPresenceAtSearching(groupName)) {

			//SearchObjects searchGroup = new SearchObjects(driver);
			//searchGroup.searchGroup(groupName);

                LOGGER.info("Edit  GroupName from \""+groupName+"\" to \""+newGroupName+"\"");
		
		//SearchObjects searchGroup = new SearchObjects(driver);
		//searchGroup.searchGroup(groupName);
		
		Container editButton=new Container(driver, By.xpath("//div[text()='"+groupName+"']/ancestor::td/following-sibling::td//div//a[@class='update']"));
		editButton.click();

		TextField groupNameField=new TextField(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-displayname']"));
		groupNameField.clearText();
		Thread.sleep(500);
		groupNameField.enterText(newGroupName);
		
		Button save=new Button(driver, By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-updategroup-save-button-button']"));
		save.click();
                }
                else{
                    
			LOGGER.error("Group " + groupName + " Not Present to Delete"); 
                }
		
		Element.waitForLoad(driver);
	}

	/**
	 * Delete Group
	 * 
	 * @param groupName
	 *            : Name of the group which needs to be deleted
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void removeGroup(String groupName) throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		

		LOGGER.info("Click \"Groups\"");
			
		navigateTo.goToGroups();

		LOGGER.info("Accessing AdminToolPage");
              
		AdminConsolePage adminConsolePage = new AdminConsolePage(driver);
		if (adminConsolePage.checkGroupPresenceAtSearching(groupName)) {

			//SearchObjects searchGroup = new SearchObjects(driver);
			//searchGroup.searchGroup(groupName);

                LOGGER.info("Remove  Group\""+groupName+"\"");
		
			java.util.List<WebElement> groupList = driver
					.findElements(By
							.xpath("//div[contains(@class,'results')]//table//tbody//tr//td[contains(@headers,'displayName')]"));

			for (int i = 0; i < groupList.size(); i++) {

				if (groupList.get(i).getText().equals(groupName)) {

					int j = i;
					groupList.get(i).click();
					Thread.sleep(2000);

					java.util.List<WebElement> removeGroupButton = driver
							.findElements(By
									.xpath("//div[contains(@class,'results')]//table//tbody//tr//td//div//a[@class='delete']"));

					// java.util.List<WebElement> removeUserButton =
					// driver.findElements(By
					// .xpath("//div//ul//li//div//a[@class='yui-columnbrowser-item groups-item-user']//span[@class='yui-columnbrowser-item-buttons']"));
					// LOGGER.info("found remove button");

					// div//ul//li//div//a[@class='yui-columnbrowser-item
					// groups-item-user']//span[@class='yui-columnbrowser-item-buttons']
					// removeUserButton.get(j).
					Thread.sleep(1000);
					removeGroupButton.get(j).click();
					Thread.sleep(2000);
                                        
                                        LOGGER.info("Click \"Delete\" Button");
		                        
					Button deleteButton = new Button(
							driver,
							By.xpath("//div//span//span//button[contains(.,'Delete')]"));
					deleteButton.click();
					Thread.sleep(2000);
					i = groupList.size();
				}

			}
		} else {
                        
			LOGGER.error("Group " + groupName + " Not Present to Delete");
		}

		Element.waitForLoad(driver);

	}

	/**
	 * Change System Theme
	 * 
	 * @param themeName
	 *            : Name of the theme
	 * @throws InterruptedException
	 * @throws AWTException
	 */

	public void changeTheme(String themeName) throws InterruptedException,
			AWTException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToHome();
		Element.waitForLoad(driver);
		navigateToPage.goToAdminTools();
		Element.waitForLoad(driver);

		Actions action = new Actions(driver);

		Button themeDropDown = new Button(driver,
				By.xpath("//div//select[@id='console-options-theme-menu']"));
		themeDropDown.click();
		Thread.sleep(500);

		WebElement themeSelect = driver
				.findElement(By
						.xpath("//div//select[@id='console-options-theme-menu']//option[contains(text(),'"
								+ themeName + "')]"));

		action.click(themeSelect).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		Element.waitForLoad(driver);
		Thread.sleep(1000);
		Button applyButton = new Button(
				driver,
				By.xpath("//div[@class='apply']//span//span//button[contains(text(),'Apply')]"));
		applyButton.click();
		Thread.sleep(1000);
		Element.waitForLoad(driver);
		Thread.sleep(500);
		navigateToPage.goToHome();
		Element.waitForLoad(driver);
	}

	/**
	 * Check System Theme
	 * 
	 * @param themeName
	 *            : Name of the theme
	 * @return Returns true if the theme is present
	 * @throws InterruptedException
	 */

	public boolean checkTheme(String themeName) throws InterruptedException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToHome();
		Element.waitForLoad(driver);
		navigateToPage.goToAdminTools();
		Element.waitForLoad(driver);

		if (Element
				.isElementPresent(
						driver,
						By.xpath("//div//select[@id='console-options-theme-menu']//option[contains(text(),'"
								+ themeName + "')][@selected='selected']"))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Check If user can upload new theme logo
	 * 
	 * @return Returns true if the upload new logo option is available
	 * @throws InterruptedException
	 */
	public boolean checkUploadNewLogo() throws InterruptedException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToHome();
		Element.waitForLoad(driver);
		navigateToPage.goToAdminTools();
		Element.waitForLoad(driver);

		if (Element
				.isElementPresent(
						driver,
						By.xpath("//form//div//div//span//span//button[contains(@id,'upload-button')][text()='Upload']"))) {
			return true;
		} else {
			return false;
		}
	}       
    
    
    public void prepareElements() {

    }

    

    public AdminConsolePage nodeBrowser() throws InterruptedException {

    	Element.waitForLoad(driver);

    	String nodeBrowser = "//span//a[@href='node-browser']";

    	Link nodeBrowserLink = new Link(driver, By.xpath(nodeBrowser));

    	nodeBrowserLink.click();

    	Element.waitForLoad(driver);

        return new AdminConsolePage(driver);

    }

    

    public boolean emailExistance(String emailAddress) throws InterruptedException {

    	Element.waitForLoad(driver);

    	String queryFieldXpath = "//textarea[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-text']";

    	TextField queryField = new TextField(driver, By.xpath(queryFieldXpath));
    	
    	queryField.clearText();

    	queryField.enterText("@cm\\:email:\""+emailAddress+"\"");
    	
    	Thread.sleep(3000);

    	String selectorXpath = "//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-lang-menu-button-button']";

    	Button selector = new Button(driver, By.xpath(selectorXpath));

    	selector.click();

    	Thread.sleep(3000);

    	String queryTypeXpath = "//div//div//ul//li//a[text()='lucene']";

    	Link queryType = new Link(driver, By.xpath(queryTypeXpath));

    	queryType.click();
    	
    	Thread.sleep(3000);

    	//String contentSelectorXpath = "//button[@id='page_x002e_ctool_x002e_admin-console"+queryFieldXpath+"_x0023_default-store-menu-button-button']";
    	String contentSelectorXpath = "//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-store-menu-button-button']";
    	//page_x002e_ctool_x002e_admin-console_x0023_default-store-menu-button-button

    	Button contentSelector = new Button(driver, By.xpath(contentSelectorXpath));

    	contentSelector.click();

    	Thread.sleep(3000);

    	String contentStoreTypeXpath = "//div//ul//li//a[text()='workspace://SpacesStore']";

    	Link contentStoreType = new Link(driver, By.xpath(contentStoreTypeXpath));

    	contentStoreType.click();

    	Thread.sleep(3000);

    	String searchXpath = "//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button']";

    	Button search = new Button(driver, By.xpath(searchXpath));

    	search.click();
    	
    	Thread.sleep(3000);

    	Element.waitForLoad(driver);

    	String resultXpath = "//tbody[@class='yui-dt-message']//tr//td//div[text()='No items found']";

    	if (Element.isElementPresent(driver, By.xpath(resultXpath))) {

    		

    		return true;

    		

    	}

    	else {

    		return false;

    	}

    	

    }  



}
