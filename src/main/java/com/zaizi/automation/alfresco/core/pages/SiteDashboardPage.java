package com.zaizi.automation.alfresco.core.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.ExplicitWait;
import com.zaizi.automation.alfresco.core.elements.Link;
import com.zaizi.automation.alfresco.core.elements.Span;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;





/**
 * @author nbrahmananthan@zaizi.com
 */

public class SiteDashboardPage {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(SiteDashboardPage.class.getName());
	
	//public static  ExtentReports extent = ExtentReports.get(SiteDashboardPage.class);

	/**
	 * Defining WebDriver
	 */
	private static WebDriver driver;

	/**
	 * @param driver
	 */
	public SiteDashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Add a user to a Specific Site
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @param userName
	 *            : User name of the user
	 * @param userFirstName
	 *            : First name of the user
	 * @param userRole
	 *            : User Role of the user
	 * @return void
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void addUserToSite(String siteName, String userName,
			String userFirstName, String userRole,String className,String screenShot) throws InterruptedException, IOException {

		SearchObjects searchObjects = new SearchObjects(driver);
		searchObjects.searchSite(siteName);
		// LoginPage loginPage = new LoginPage(driver);

		java.util.List<WebElement> mySiteList = driver.findElements(By
				.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName
						+ "')]"));

		for (int i = 0; i < mySiteList.size(); i++) {

			if (mySiteList.get(i).getText().equals(siteName)) {
				WebElement checkBox = mySiteList.get(i);
				i = mySiteList.size();
				checkBox.click();

			} else {
				LOGGER.error("Site " + siteName + " Not found");
			}
		}
		Element.waitForLoad(driver);
		Link inviteLink = new Link(
				driver,
				By.xpath("//div//div//div//div[@id='HEADER_SITE_INVITE' and @widgetid='HEADER_SITE_INVITE' and @title='Invite users']"));

		inviteLink.click();
		Element.waitForLoad(driver);
		TextField userSearch = new TextField(
				driver,
				By.id("template_x002e_people-finder_x002e_invite_x0023_default-search-text"));
		userSearch.enterText(userName);
		Button search = new Button(
				driver,
				By.id("template_x002e_people-finder_x002e_invite_x0023_default-search-button-button"));
		search.click();
		Thread.sleep(4000);

		java.util.List<WebElement> myUsers = driver
				.findElements(By
						.xpath("//div[@id='template_x002e_people-finder_x002e_invite_x0023_default-results']//table//tbody//tr//td//div//span"));

		for (int i = 0; i < myUsers.size(); i++) {

			if (myUsers.get(i).getText().equals("(" + userName + ")")) {
				WebElement addButton = myUsers.get(i + 2);
				i = myUsers.size();
				addButton.click();
				Thread.sleep(1000);
			}

		}

		Button selectRole = new Button(driver,
				By.xpath("//span/button[contains(., 'Select Role')]"));
		selectRole.click();
		Thread.sleep(2000);
		Link role = new Link(driver,
				By.xpath("//div//ul//li//a[@href=contains(., '" + userRole
						+ "')]"));
		role.click();
		Thread.sleep(2000);
		Button invite = new Button(
				driver,
				By.id("template_x002e_invitationlist_x002e_invite_x0023_default-invite-button-button"));
		invite.click();

	}
	/**
	 * Add a group to a Specific Site
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @param groupName
	 *            : Name of the group
	 * @param groupIdentifier
	 *            : the group identifier
	 * @param groupRole
	 *            : User Role of the user
	 * @return void
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void addGroupToSite(String siteName, String groupName,
			String groupIdentifier, String groupRole,String className,String screenShot) throws InterruptedException, IOException {

		SearchObjects searchObjects = new SearchObjects(driver);
		searchObjects.searchSite(siteName);
		// LoginPage loginPage = new LoginPage(driver);

		java.util.List<WebElement> mySiteList = driver.findElements(By
				.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName
						+ "')]"));

		for (int i = 0; i < mySiteList.size(); i++) {

			if (mySiteList.get(i).getText().equals(siteName)) {
				WebElement checkBox = mySiteList.get(i);
				i = mySiteList.size();
				checkBox.click();

			} else {
				LOGGER.error("Site " + siteName + " Not found");
			}
		}
		Element.waitForLoad(driver);
		Link inviteLink = new Link(
				driver,
				By.xpath("//div//div//div//div[@id='HEADER_SITE_INVITE' and @widgetid='HEADER_SITE_INVITE' and @title='Invite users']"));

		inviteLink.click();
		Element.waitForLoad(driver);
		Link groupSelect = new Link(
				driver,
				By.xpath("//div//div//div//div//div//a[@href='site-groups']"));

		groupSelect.click();
		Element.waitForLoad(driver);
		Button addGroupButton = new Button(driver, By.id("template_x002e_site-groups_x002e_site-groups_x0023_default-addGroups-button"));
		addGroupButton.click();

		Element.waitForLoad(driver);
		TextField userSearch = new TextField(
				driver,
				By.id("template_x002e_group-finder_x002e_add-groups_x0023_default-search-text"));
		userSearch.enterText(groupName);
		Button search = new Button(
				driver,
				By.id("template_x002e_group-finder_x002e_add-groups_x0023_default-group-search-button-button"));
		search.click();
		Thread.sleep(4000);
		Button addTheGroup = new Button(driver, By
						.xpath("//div[@id='template_x002e_group-finder_x002e_add-groups_x0023_default-results']//table//tbody//tr//td//div//h3[text()='"+groupName+"' and //div[@id='template_x002e_group-finder_x002e_add-groups_x0023_default-results']//table//tbody//tr//td//div//div[contains(.,'GROUP_"+groupIdentifier+"')]]//ancestor::td[1]//following-sibling::td[1]//button"));
		addTheGroup.click();
		Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
		Button selectRole = new Button(driver,
				By.xpath("//span/button[contains(., 'Select Role')]"));
		selectRole.click();
		Thread.sleep(2000);
		Link role = new Link(driver,
				By.xpath("//div//ul//li//a[@href=contains(., '" + groupRole
						+ "')]"));
		role.click();
		Thread.sleep(2000);
		Button invite = new Button(
				driver,
				By.id("template_x002e_groupslist_x002e_add-groups_x0023_default-add-button-button"));
		invite.click();

	}

	/**
	 * Check if the user is present in the site
	 * 
	 * @param userName
	 *            : Username of the user
	 * @param siteName
	 *            : Name of the site
	 * @return Returns true if the user is present in the site
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public Boolean checkUserInSite(String userFirstName, String siteName,String className,String screenShot)
			throws InterruptedException, IOException {
		// SearchObjects searchObjects = new SearchObjects(driver);
		// searchObjects.searchSite(siteName);
		// LoginPage loginPage = new LoginPage(driver);
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToSite(siteName,className,screenShot);

		// java.util.List<WebElement> mySiteList = driver
		// .findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '"
		// + siteName + "')]"));
		//
		// for (int i = 0; i < mySiteList.size(); i++) {
		//
		// if (mySiteList.get(i).getText().equals(siteName)) {
		// WebElement checkBox = mySiteList.get(i);
		// i = mySiteList.size();
		// checkBox.click();
		//
		// }
		// else
		// {
		// LOGGER.error("Site " + siteName + " Not found");
		// }
		// }
		Element.waitForLoad(driver);
		Button siteMembers = new Button(driver,
				By.id("HEADER_SITE_MEMBERS_text"));
		siteMembers.click();
		TextField searchBar = new TextField(
				driver,
				By.xpath("//div//input[@id='template_x002e_site-members_x002e_site-members_x0023_default-term' and @class='search-term']"));
		searchBar.clearText();
		searchBar.enterText(userFirstName);
		Thread.sleep(2000);

		if (Element.isTextPresentInList(driver.findElements(By
				.xpath("//tbody//tr//td//div//h3//a[contains(., '"
						+ userFirstName + "')]")), userFirstName)) {
			//Element.takescreenshot(driver, className, screenShot+"uf1");
			return true;
		} else {
			//Element.takescreenshot(driver, className, screenShot+"uf2");
			return false;
		}

	}

	/**
	 * Check if Public Site Present in the system
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @return Returns true if the public site is present in the system
	 * 
	 * @param className
	 *            : ClassName to Save ExtentReport
	 * @param screenShot
	 *            : Save Image as screenShot   
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public Boolean checkPublicSitePresence(String siteName)throws InterruptedException, IOException {
		Thread.sleep(6000);

                LOGGER.info("CHECK WHETHER "+siteName+" IS DISPLAY IN SITES LIST");
                //extent.log(LogStatus.INFO,"CHECK WHETHER "+siteName+" IS DISPLAY IN SITES LIST");
                
                LOGGER.info("Click \"Sites\"");
		//extent.log(LogStatus.INFO, "Click \"Sites\"");
                
                LOGGER.info("Click \"Site Finder\"");
		//extent.log(LogStatus.INFO, "Click \"Site Finder\"");
                
		SearchObjects searchSite = new SearchObjects(driver);
		searchSite.searchSite(siteName);
		if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
		{
			LOGGER.info("Message display as \"No sites found\"");
			//extent.log(LogStatus.INFO, "Message display as \"No sites found\"");
			return false;
		}
		else 
		{
			if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName)) {
				
				return true;
				
			} else {
				
				return false;
				
			}
		}
		//Element.waitForLoad(driver);		
		// Link siteName = new Link(driver, By.xpath("//a[contains(., '" +
		// TestCaseProperties.TEST_SITE_NAME + "')]"));			

	}

	/**
	 * Check if Moderate Site Present in the system
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @return Returns true if the Site is present in the system
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public Boolean checkModerateSitePresence(String siteName,String className,String screenShot)
			throws InterruptedException, IOException {
		Thread.sleep(2000);

		SearchObjects searchSite = new SearchObjects(driver);
		searchSite.searchSite(siteName);

		Element.waitForLoad(driver);
		// Link siteName = new Link(driver, By.xpath("//a[contains(., '" +
		// TestCaseProperties.TEST_SITE_NAME + "')]"));

		if (Element.isTextPresentInList(driver.findElements(By
				.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName
						+ "')]")), siteName)
				&& Element.isElementPresent(driver,
						By.xpath("//td//div//span[contains(.,'Moderated')]"))) {

			return true;
		} else {

			return false;
		}

	}
	
	 public void siteNotification(String siteName,String className,String screenShot) throws InterruptedException, IOException 
	    { 
	        
	        LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"Site "+siteName +" CREATED ");
			//extent.log(LogStatus.PASS, "Site "+siteName +" CREATED ");		    
	      //  Element.takescreenshot(driver, className, screenShot+"createObjectNotifi");
	       
	        
	    }
	 
	 public String errorNotification(String siteName) throws InterruptedException, IOException 
	    {    	
	        new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//div[@id='prompt']//div[@class='bd']"),2);
	        Span notification = new Span(driver, By.xpath("//div[@id='prompt']//div[@class='bd']"));	        
	        String notificationMessage=notification.getText();
	        
	        LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"Site "+siteName +" IS ALREADY EXIST ");		    
		    
	       // Element.takescreenshot(driver, className, screenShot+"createObjectno");
	       /* Button okayButton=new Button(driver,By.xpath("//button[text()='OK']"));
	        okayButton.click(); */       
/*
        	Button cancel=new Button(driver,By.xpath("//button[text()='Cancel']"));
        	cancel.click();*/
	        return notificationMessage;
	        
	        
	        
	    }

	/**
	 * Check if Private Site Present in the system
	 * 
	 * @param privateSite
	 *            : Name of the site
	 *            
	 * @param className
	 *            : ClassName to Save ExtentReport
	 * @param screenShot
	 *            : Save Image as screenShot  
	 * @return Returns true if the Site is present in the system
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	 public Boolean checkPrivateSitePresence(String privateSite,String userName,String className,String screenShot)throws InterruptedException, IOException {
			Thread.sleep(5000);

			SearchObjects searchSite = new SearchObjects(driver);
			LOGGER.info("Check whether \" " +privateSite+ "\" SITE IS VISIBLE TO "+userName);
			
			searchSite.searchSite(privateSite);	
			
			if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
			{
				LOGGER.info("Message display as \"No sites found\"");
				//extent.log(LogStatus.INFO, "Message display as \"No sites found\"");
				
				LOGGER.info(privateSite+ " SITE IS NOT VISIBLE TO "+userName);
				//extent.log(LogStatus.FAIL,privateSite+ " SITE IS NOT VISIBLE TO "+userName);
				
			}
			else if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + privateSite+ "')]")), privateSite))
			{	
				LOGGER.info(privateSite+ " SITE IS VISIBLE TO "+userName);
				//extent.log(LogStatus.PASS,privateSite+ " SITE IS VISIBLE TO "+userName);
							
			}		
				
			
		    if(Element.isElementPresent(driver,By.xpath("//div[@id='prompt']//div[@class='bd']")))
		            {
		                Button okaybtn=new Button(driver,By.xpath("//button[text()='OK']"));
		                okaybtn.click();
		                Thread.sleep(5000);
		                NavigateToPage navigateTo2 = new NavigateToPage(driver);
		                navigateTo2.goToHome();                        
		                Element.waitForLoad(driver);
		                
		                LoginPage loginPage2 = new LoginPage(driver);
		                loginPage2.logout();        
		                Element.waitForLoad(driver);
		                
		                Thread.sleep(5000);
		                LOGGER.info("Login as  \"privateuser\" ");
		                //extent.log(LogStatus.INFO,"Login as \"privateuser\" ");                
		                loginPage2.loginAsUser1("privateuser", "1qaz@WSX");
		                Element.waitForLoad(driver);
		                
		                LOGGER.info("Check whether \"" +privateSite+ "\" SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
		               // extent.log(LogStatus.INFO, "Check whether \" " +privateSite+ "\" SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
		                
		                SearchObjects searchSite1 = new SearchObjects(driver);
		                searchSite1.searchSite(privateSite);
		                Element.waitForLoad(driver);
		                
		                if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']")))
		                {
		                    LOGGER.info("Message display as \"No sites found\"");
		                   // extent.log(LogStatus.INFO, "Message display as \"No sites found\"");
		                    
		                    LOGGER.info(privateSite+ " SITE IS NOT VISIBLE TO UNJOIN USER");
		                    //extent.log(LogStatus.PASS,privateSite+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
		                    return false;
		                }
		                else
		                {
		                    if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + privateSite+ "')]")), privateSite)) {
		                        
		                        LOGGER.info(privateSite+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
		                       // extent.log(LogStatus.FAIL,privateSite+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
		                        return true;
		                        
		                    } else {
		                        LOGGER.info(privateSite+ " SITE IS NOT VISIBLE TO UNJOIN USER");
		                       // extent.log(LogStatus.PASS,privateSite+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
		                        return false;
		                        
		                    }
		                }            
		            }
		    else 
            {
                Thread.sleep(5000);
                NavigateToPage navigateTo1 = new NavigateToPage(driver);
                navigateTo1.goToHome();        
                Element.waitForLoad(driver);
                
                LoginPage loginPage1 = new LoginPage(driver);
                loginPage1.logout();        
                Element.waitForLoad(driver);
                
                LOGGER.info("Login as  \"privateuser\" ");
                //extent.log(LogStatus.INFO,"Login as \"privateuser\" ");
                
                loginPage1.loginAsUser1("privateuser", "1qaz@WSX");
                Element.waitForLoad(driver);
                Thread.sleep(5000);
                LOGGER.info("Check whether \"" +privateSite+ "\" SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
               // extent.log(LogStatus.INFO, "Check whether \" " +privateSite+ "\" SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                
                SearchObjects searchSite2 = new SearchObjects(driver);
                searchSite2.searchSite(privateSite);
                Element.waitForLoad(driver);
                
                if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
                {
                    Thread.sleep(5000);
                    LOGGER.info("Message display as \"No sites found\"");
                    //extent.log(LogStatus.INFO, "Message display as \"No sites found\"");
                    
                    LOGGER.info(privateSite+ " SITE IS NOT VISIBLE TO UNJOIN USER");
                    //extent.log(LogStatus.PASS,privateSite+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
                    return false;
                }
                else 
                {
                    Thread.sleep(5000);
                    if (Element.isTextPresentInList(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + privateSite+ "')]")), privateSite)) {
                        
                        LOGGER.info(privateSite+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                        //extent.log(LogStatus.FAIL,privateSite+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                        return true;
                        
                    } else {
                        LOGGER.info(privateSite+ " SITE IS NOT VISIBLE TO UNJOIN USER");
                        //extent.log(LogStatus.PASS,privateSite+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
                        return false;
                        
                    }
                }            
            }
		    
	 }

		public Boolean checkPrivateSiteVisible(String siteName,String className,String screenShot)	throws InterruptedException, IOException {
			Thread.sleep(2000);

			SearchObjects searchSite = new SearchObjects(driver);
			searchSite.searchSite(siteName);
			if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
			{
				LOGGER.info("Message display as \"No sites found\"");
				//extent.log(LogStatus.INFO, "Message display as \"No sites found\"");
				return false;
			}
			else 
			{
				if (Element.isTextPresentInList(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName)) {
					
					return true;
					
				} else {
					
					return false;
					
				}
			}
			//Element.waitForLoad(driver);		
			// Link siteName = new Link(driver, By.xpath("//a[contains(., '" +
			// TestCaseProperties.TEST_SITE_NAME + "')]"));
			

			

		}

	/**
	 * Edit Site Name
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @param newSiteName
	 *            : New name of the site
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void editSiteName(String siteName, String newSiteName)
			throws InterruptedException, IOException {

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToSiteForEditSiteName(siteName);
		//Element.waitForLoad(driver);

		LOGGER.info("Click \"Configuration button\" to edit Site");
		
		
		Button configurationButton = new Button(driver,
				By.id("HEADER_SITE_CONFIGURATION_DROPDOWN"));
		configurationButton.click();
		//Element.takescreenshot(driver, className, screenShot+"CONFIG1");
		
		Thread.sleep(500);		
		Link editSiteDetails = new Link(driver,
				By.id("HEADER_EDIT_SITE_DETAILS"));
		editSiteDetails.click();
		LOGGER.info("Clicked \"Edit Site Details\"");
		
		Thread.sleep(2000);
		
		LOGGER.info("Edit the siteName form \""+siteName +"\" to \""+newSiteName+" \"");
		
		TextField editName = new TextField(driver,
				By.id("alfresco-editSite-instance-title"));
		editName.clearText();
		editName.enterText(newSiteName);
		LOGGER.info("click \"OK\" button");
		
		Button okayButton = new Button(driver,
				By.id("alfresco-editSite-instance-ok-button-button"));
            //    Element.takescreenshot(driver, className, screenShot+"CONFIG2");
		okayButton.click();
		
		Element.waitForLoad(driver);

	}

	/**
	 * check site Availability
     * @throws InterruptedException
	 * @throws IOException 
     */
    public void checkSiteAvailability(String siteName,String newSiteName,String className,String screenShot) throws InterruptedException, IOException
    {
        Thread.sleep(2000);
        SearchObjects searchObjects = new SearchObjects(driver);
		searchObjects.searchSite(siteName);
		
		Element.waitForLoad(driver);
		Thread.sleep(3000);

		if(Element.isElementPresent(driver, By.xpath("//tbody//tr//td//div//h3//a[text()='" + siteName+ "']")))
		{
			LOGGER.info("Site " + siteName + " was found,Site name was NOT Changed Successfully");
			//extent.log(LogStatus.FAIL, "Site " + siteName + " was found,Site name was NOT Changed Successfully");
		}
		 else 
		 {
			LOGGER.error("Site " + siteName + " is NOT FOUND,Site name was Changed Successfully FROM \""+siteName+"\"TO \""+newSiteName+"\"");
			//extent.log(LogStatus.PASS, "Site " + siteName + " is NOT FOUND,Site name was Changed Successfully FROM \""+siteName+"\"TO \""+newSiteName+"\"");
		
		}
	
		Element.waitForLoad(driver);
    }
	/**
	 * Check Site Dashboard Logo
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
	 * @throws IOException 
	 */

	public boolean checkSiteDashLogo(String siteName, String logoSource,
			int logoHight, int logoWidth, int logoDimX, int logoDimY,String className,String screenShot)
			throws InterruptedException, IOException {
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToHome();
		Element.waitForLoad(driver);
		navigateToPage.goToSite(siteName,className,screenShot);
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
	 * Add A Dash let to Site DashBoard
	 * 
	 * @param siteName
	 *            : Name of the site which needs to be modified
	 * @param dashletName
	 *            : Name of the dashlet which needs to be added
	 * @param columnNo
	 *            : Column no which the dashlet needs to be added
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void addDashletSiteDash(String siteName, String dashletName,
			String columnNo,String className,String screenShot) throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		navigateTo.goToSite(siteName,className,screenShot);
		Element.waitForLoad(driver);

		Link customizeDrop = new Link(driver,
				By.id("HEADER_SITE_CONFIGURATION_DROPDOWN"));
		customizeDrop.click();
		Thread.sleep(500);
		Element.waitForLoad(driver);
		Link customize = new Link(driver,
				By.id("HEADER_CUSTOMIZE_SITE_DASHBOARD"));
		customize.click();
		Element.waitForLoad(driver);
		TestCaseProperties.dashletCount = 0;
		java.util.List<WebElement> dashletList = driver
				.findElements(By
						.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-column-ul-"
								+ columnNo + "']//li//span"));
		for (int k = 0; k < dashletList.size(); k++) {
			if (dashletList.get(k).getText().equals(dashletName)) {
				TestCaseProperties.dashletCount = TestCaseProperties.dashletCount + 1;
			}
		}

		Button addDashlets = new Button(
				driver,
				By.id("template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-addDashlets-button"));
		addDashlets.click();
		Thread.sleep(2000);
		Actions performer = new Actions(driver);

		java.util.List<WebElement> myList = driver.findElements(By
				.xpath("//ul//li[contains(.,'" + dashletName
						+ "')][contains(@class,'availableDashlet')]"));
		int j = 0;
		for (int i = 0; i < myList.size(); i++) {
			if (myList.get(i).getText().equals(dashletName)) {
				// WebElement selectDashlet = myList.get(i);
				j = i;
			}
		}
		// WebElement selectDashlet =
		// driver.findElement(By.xpath("//ul//li[contains(.,'"+ dashletName
		// +"')]"));

		WebElement selectDashlet = myList.get(j);
		WebElement list = driver
				.findElement(By
						.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-column-ul-"
								+ columnNo + "']"));
		Action dragAndDrop = performer.clickAndHold(selectDashlet)
				.moveToElement(list).release(list).build();
		// Action dragAndDrop = performer.dragAndDrop(selectDashlet,
		// list).build();
		Thread.sleep(5000);
		dragAndDrop.perform();
		Thread.sleep(5000);
		Button okButton = new Button(
				driver,
				By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-save-button-button']"));
		okButton.click();
		Thread.sleep(5000);
		// Division rssFeedBlog = new Division(
		// driver,
		// By.xpath("//div[@id='page_x002e_component-1-3_x002e_user_x007e_admin_x007e_dashboard_x0023_default-title']"));
		//
	}

	/**
	 * Check Presence of dashlet in Site dashboard
	 * 
	 * @param siteName
	 *            : Name of site
	 * @param dashletName
	 *            : Name of the Dashlet
	 * @param columnNo
	 *            : Column number the dashlet is present
	 * @return : Returns true if the dashlet is present
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public boolean checkSiteDashboardDashlet(String siteName,
			String dashletName, String columnNo,String className,String screenShot) throws InterruptedException, IOException {

		// div[contains(@class,'column3')][contains(@class,'yui-u')]//div//div//div//div[@class='title']

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		navigateTo.goToSite(siteName,className,screenShot);
		Element.waitForLoad(driver);
		int count = 0;
		// java.util.List<WebElement> myList =
		// driver.findElements(By.xpath("//div//div[@id='bd']//div//div//div[contains(.,'"+dashletName+"')]"));
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
	 * Check Site Dashboard Layout
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @param columnSize
	 *            : Number of columns present in the layout
	 * @return : Returns true if the layout is correct
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public boolean checkSiteDashboardLayout(String siteName, String columnSize,String className,String screenShot)
			throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		navigateTo.goToSite(siteName,className,screenShot);
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
	 * Change Site Dashboard Layout
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @param layoutNo
	 *            layoutNo 1 - One column, layoutNo 2 - Two columns: narrow
	 *            left, wide right, layoutNo 3 - Two columns: wide left, narrow
	 *            right, layoutNo 4 - Three columns: wide centre, layoutNo 5 -
	 *            Four columns
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void changeSiteDashboardLayout(String siteName, int layoutNo,String className,String screenShot)
			throws InterruptedException, IOException {
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		navigateTo.goToSite(siteName,className,screenShot);
		Element.waitForLoad(driver);
		Link customizeDrop = new Link(driver,
				By.id("HEADER_SITE_CONFIGURATION_DROPDOWN"));
		customizeDrop.click();
		Thread.sleep(500);
		Element.waitForLoad(driver);
		Link customize = new Link(driver,
				By.id("HEADER_CUSTOMIZE_SITE_DASHBOARD"));
		customize.click();
		Element.waitForLoad(driver);
		Button changeLayout = new Button(
				driver,
				By.id("template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-change-button-button"));
		changeLayout.click();
		Thread.sleep(2000);

		if (layoutNo == 1) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-layout-li-dashboard-1-column']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-select-button-dashboard-1-column-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}

		if (layoutNo == 2) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-layout-li-dashboard-2-columns-wide-right']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-select-button-dashboard-2-columns-wide-right-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}
		if (layoutNo == 3) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-layout-li-dashboard-2-columns-wide-left']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-select-button-dashboard-2-columns-wide-left-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}
		if (layoutNo == 4) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-layout-li-dashboard-3-columns']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-select-button-dashboard-3-columns-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}
		if (layoutNo == 5) {
			Button selectButton = new Button(
					driver,
					By.xpath("//ul//li[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-layout-li-dashboard-4-columns']//div//span//span//button[@id='template_x002e_customise-layout_x002e_customise-site-dashboard_x0023_default-select-button-dashboard-4-columns-button']"));
			selectButton.click();
			Thread.sleep(1000);
		}

		Button okButton = new Button(
				driver,
				By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-save-button-button']"));
		okButton.click();
		Thread.sleep(1000);
	}

	/**
	 * Remove a Dash let from Site dashboard
	 * 
	 * @param siteName
	 *            : Name of the Site
	 * @param dashletName
	 *            : Name of the dashlet which needs to be removed.
	 * @param columnNo
	 *            : Column Number the dashlet should be removed from
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void removeDashletSiteDash(String siteName, String dashletName,
			String columnNo,String className,String screenShot) throws InterruptedException, IOException {

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		navigateTo.goToSite(siteName,className,screenShot);
		Element.waitForLoad(driver);

		Link customizeDrop = new Link(driver,
				By.id("HEADER_SITE_CONFIGURATION_DROPDOWN"));
		customizeDrop.click();
		Thread.sleep(500);
		Element.waitForLoad(driver);
		Link customize = new Link(driver,
				By.id("HEADER_CUSTOMIZE_SITE_DASHBOARD"));
		customize.click();
		Element.waitForLoad(driver);

		Actions performer = new Actions(driver);

		java.util.List<WebElement> myList = driver
				.findElements(By
						.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-column-ul-"
								+ columnNo
								+ "']//li[contains(.,'"
								+ dashletName
								+ "')][contains(@class,'usedDashlet')]"));
		Thread.sleep(1000);
		int j = 0;
		int count1 = 0;
		for (int i = 0; i < myList.size(); i++) {
			if (myList.get(i).getText().equals(dashletName)) {
				// WebElement selectDashlet = myList.get(i);
				j = i;
				count1 = count1 + 1;
			}
		}
		// WebElement selectDashlet =
		// driver.findElement(By.xpath("//ul//li[contains(.,'"+ dashletName
		// +"')]"));

		WebElement selectDashlet = myList.get(j);
		WebElement trash = driver
				.findElement(By
						.id("template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-trashcan-img"));
		Thread.sleep(1000);
		// Action dragAndDrop = performer.clickAndHold(selectDashlet)
		// .moveToElement(trash).release(trash).build();
		// Action dragAndDrop = performer.dragAndDrop(selectDashlet,
		// trash).build();
		Action dragAndDrop = performer.dragAndDrop(selectDashlet, trash)
				.build();
		Thread.sleep(1000);
		dragAndDrop.perform();
		Thread.sleep(1000);

		java.util.List<WebElement> myupdateList = driver
				.findElements(By
						.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-column-ul-"
								+ columnNo
								+ "']//li[contains(.,'"
								+ dashletName
								+ "')][contains(@class,'usedDashlet')]"));
		Thread.sleep(1000);
		int l = 0;
		int count2 = 0;
		for (int n = 0; n < myupdateList.size(); n++) {
			if (myList.get(n).getText().equals(dashletName)) {
				// WebElement selectDashlet = myList.get(i);
				l = n;
				count2 = count2 + 1;
			}
		}

		if (count2 >= count1) {
			WebElement selectDashlet1 = myList.get(l);
			WebElement trash1 = driver
					.findElement(By
							.id("template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-trashcan-img"));
			Thread.sleep(1000);
			// Action dragAndDrop = performer.clickAndHold(selectDashlet)
			// .moveToElement(trash).release(trash).build();
			// Action dragAndDrop = performer.dragAndDrop(selectDashlet,
			// trash).build();
			Action dragAndDrop1 = performer.dragAndDrop(selectDashlet1, trash1)
					.build();
			Thread.sleep(1000);
			dragAndDrop1.perform();
			Thread.sleep(1000);
		}

		Button addDashlets = new Button(
				driver,
				By.id("template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-addDashlets-button"));
		addDashlets.click();
		Thread.sleep(1000);
		Button okButton = new Button(
				driver,
				By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-save-button-button']"));
		okButton.click();
		Thread.sleep(4000);
		Element.waitForLoad(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		// ul[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-column-ul-2']//li[contains(.,'My
		// Activities')]
	}
	
	/**
	 * Add Wiki Page to the site
	 * @param siteName = Name of the site
	 * @param pageName = Name of the page which needs to be added
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void addWikiPage(String siteName, String pageName,String className,String screenShot) throws InterruptedException, IOException
	{
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
		Element.waitForLoad(driver);
		navigateTo.goToSite(siteName,className,screenShot);
		Element.waitForLoad(driver);

		String newPageName;
		newPageName= pageName.toLowerCase();
		Link customizeDrop = new Link(driver,
				By.id("HEADER_SITE_CONFIGURATION_DROPDOWN"));
		customizeDrop.click();
		Thread.sleep(500);
		Element.waitForLoad(driver);
		Link customize = new Link(driver,
				By.id("HEADER_CUSTOMIZE_SITE"));
		customize.click();
		Element.waitForLoad(driver);
		
		
		
		Thread.sleep(1000);
		
		Actions performer = new Actions(driver);

//		Division page = new Division(driver, By.xpath("//div[contains(@class,'available-pages')]//ul//li[contains(@id,'"+wiki+"')]"));
 
		WebElement page = driver
				.findElement(By
						.xpath("//div[contains(@class,'available-pages')]//ul//li[contains(@id,'"+newPageName+"')]"));
		

		WebElement list = driver
				.findElement(By
						.xpath("//div[contains(@class,'current-pages')]//ul[@class='page-list']"));
		Action dragAndDrop = performer.clickAndHold(page)
				.moveToElement(list).release(list).build();
		// Action dragAndDrop = performer.dragAndDrop(selectDashlet,
		// list).build();
		Thread.sleep(5000);
		dragAndDrop.perform();
		Element.waitForLoad(driver);
		Thread.sleep(2000);
		Button okButton = new Button(
				driver,
				By.xpath("//span[contains(@id,'save-button')]//span//button[contains(@id,'save-button')]"));
		okButton.click();
		Element.waitForLoad(driver);
		Thread.sleep(1000);
		
		
	}
	
	/**
	 * Check Site Internal User Invite
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	
	public boolean checkInternalUserInvite(String siteName,String className,String screenShot) throws InterruptedException, IOException
	{
		Thread.sleep(4000);
		
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToSite(siteName,className,screenShot);
		
		Element.waitForLoad(driver);
		Link inviteLink = new Link(driver,By.xpath("//div//div//div//div[@id='HEADER_SITE_INVITE' and @widgetid='HEADER_SITE_INVITE' and @title='Invite users']"));
		inviteLink.click();
		Element.waitForLoad(driver);
		
		if(Element.isElementPresent(driver, By.xpath("//div[contains(@class,'search-text')]//following::div[@class='search-button']")))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void searchPopleForInvite(String userName,String roleName,String className,String screenShot) throws InterruptedException, IOException
	{
		
		LOGGER.info("Enter "+userName+" in \"search For people Field\" ");
        
        
		TextField searchNameField = new TextField(driver,By.xpath("//input[@id='template_x002e_people-finder_x002e_invite_x0023_default-search-text']"));
		searchNameField.enterText(userName);
		Thread.sleep(2000);
		
		LOGGER.info("Click \"Search\" Button ");
       
        
		Button btnSearch=new Button(driver,By.xpath("//button[@id='template_x002e_people-finder_x002e_invite_x0023_default-search-button-button']"));
		btnSearch.click();
		
		LOGGER.info("Click \"Add>>\" Button next to "+userName);
       
        
		//Button addPeople=new Button(driver,By.xpath("//h3[@class='itemname']/a[contains(.,'"+userName+"')]//ancestor::td[1]/following-sibling::td//div//button[text()='Add >>']"));
        Button addPeople=new Button(driver,By.xpath("//h3[@class='itemname']/a[@href='/share/page/user/"+userName+"/profile']//ancestor::td[1]/following-sibling::td//div//button[text()='Add >>']"));
       // Element.takescreenshot(driver, className, screenShot+"searchAdd");
		addPeople.click();
		
		LOGGER.info("Select \""+roleName+"\" next to "+userName);
       
		
        Button role=new Button(driver,By.xpath("//h3[@class='itemname'][contains(.,'"+userName+"')]//ancestor::td[1]/following-sibling::td//div//button[contains(text(),'Select Role')]"));
        role.click();
        
        Button selectRole=new Button(driver,By.xpath("//li/a[text()='"+roleName+"']"));
        selectRole.click();
        
        LOGGER.info("Click \"Invite\" Button ");
        
        
        Button inviteBtn=new Button(driver,By.xpath("//button[text()='Invite']"));
       // Element.takescreenshot(driver, className, screenShot+"invite");
        inviteBtn.click();
        Element.waitForLoad(driver);
		
	}
	
	 public String successNotification(String className,String screenShot) throws InterruptedException, IOException 
	    {    	
	    	new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//div[@class='bd']/span[@class='message']"),8);
	        Span notification = new Span(driver, By.xpath("//div[@class='bd']/span[@class='message']")); 
	     
	        return notification.getText();
	    }
	
	/**
	 * Check Site External User Invite
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public boolean checkExternalUserInvite(String siteName,String className,String screenShot) throws InterruptedException, IOException
	{
		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToSite(siteName,className,screenShot);
		
		Element.waitForLoad(driver);
		Link inviteLink = new Link(
				driver,
				By.xpath("//div//div//div//div[@id='HEADER_SITE_INVITE' and @widgetid='HEADER_SITE_INVITE' and @title='Invite users']"));

		inviteLink.click();
		Element.waitForLoad(driver);
		
		if(Element.isElementPresent(driver, By.xpath("//div[contains(@id,'addemail')][contains(@id,'invite')][contains(.,'Add External Users')]//div//div[@class='inviteusersbyemail']")))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
     * @param firstName
     * @param lastName
     * @param emailText
     * @throws InterruptedException
	 * @throws IOException 
     */
	/**
     * @param firstName
     * @param lastName
     * @param emailText
     * @throws InterruptedException
	 * @throws IOException 
     */
    public void inviteExternalUser(String siteName,String firstName, String lastName, String emailText,String roleName) throws InterruptedException, IOException
    {
    	
    	SearchObjects search = new SearchObjects(driver);
		
		//Search for the Site
                LOGGER.info("Click \"Search Finder\"");
		
                
                LOGGER.info("Search Site \""+siteName+"\"");
		
		search.searchSite(siteName);
				
		//Clicks on the Site Link
                LOGGER.info("Navigate to the " +siteName);
		
		Link siteLink=new Link(driver, By.xpath("//h3[@class='sitename']/a[text()='"+siteName+"']"));
		siteLink.click();
		
        Thread.sleep(4000);
        LOGGER.info("Click \"Invite Button\"");
		
        Link inviteLink = new Link(driver, By.xpath("//a[@href='invite' and @class='theme-color-1']"));
        inviteLink.click();
        Thread.sleep(2000);

        LOGGER.info("Enter the firstName");
		
        TextField fName = new TextField(driver,
                By.xpath("//input[@id='template_x002e_addemail_x002e_invite_x0023_default-firstname']"));
        fName.enterText(firstName);
        
        LOGGER.info("Enter the lastName");
		
        TextField lName = new TextField(driver,
                By.xpath("//input[@id='template_x002e_addemail_x002e_invite_x0023_default-lastname']"));
        lName.enterText(lastName);
        
        LOGGER.info("Enter the email");
		
        TextField email = new TextField(driver,
                By.xpath("//input[@id='template_x002e_addemail_x002e_invite_x0023_default-email']"));
        email.enterText(emailText);
        
        LOGGER.info("Click \"Add\" next to user");
		
        Button add = new Button(driver,
                By.xpath("//button[@id='template_x002e_addemail_x002e_invite_x0023_default-add-email-button-button']"));
       // Element.takescreenshot(driver, className, screenShotName+"extuser12");
        add.click();
        
        Thread.sleep(4000);
        LOGGER.info("Select the role");
		
        Button selectRole = new Button(driver, By.xpath("//span/button[contains(., 'Select Role')]"));
        selectRole.click();
        Thread.sleep(3000);
        
        Link collaborator = new Link(driver, By.xpath("//a[contains(., '"+roleName+"')]"));
        collaborator.click();
        Thread.sleep(2000);
        
        LOGGER.info("Click \"Invite\" Button");
		
        Button invite = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_invitationlist_x002e_invite_x0023_default-invite-button-button']"));
        invite.click();
    }
    
    /**
     * @param userName
     * @param passwordText
     * @throws InterruptedException
     */
    public void externalUserInvitationCheck(String userName, String passwordText) throws InterruptedException
    {
    	LOGGER.info("Go to Gmail");
		//extent.log(LogStatus.INFO, "Go to Gmail");
		
        driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/?ui%3D2&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1");
        Thread.sleep(4000);
        
        LOGGER.info("Enter email Address");
		//extent.log(LogStatus.INFO, "Enter email Address");
        TextField email = new TextField(driver, By.xpath("//input[@id='Email']"));
        email.enterText(userName);
        
        LOGGER.info("Enter password");
		//extent.log(LogStatus.INFO, "Enter password");
        TextField password = new TextField(driver, By.xpath("//input[@id='Passwd']"));
        password.enterText(passwordText);
        
        LOGGER.info("Click \"Sign In\"");
		//extent.log(LogStatus.INFO, "Click \"Sign In\"");
        TextField signIn = new TextField(driver, By.xpath("//input[@id='signIn']"));
        signIn.click();
        
        
        Thread.sleep(10000);
        Span link = new Span(
                driver,
                By.xpath("//span/b[contains(., 'Alfresco Share: You have been invited to join the Test Site site by admin')]"));
        if (link.getWebElement().isDisplayed())
        {
            LOGGER.info("The invitation has been received!");
           // extent.log(LogStatus.PASS, "The invitation has been received!");
        }
        else
        {
            LOGGER.info("The invitation was not found!");
           // extent.log(LogStatus.FAIL, "The invitation was not found!");
        }

    }
}
