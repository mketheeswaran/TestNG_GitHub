/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.zaizi.automation.alfresco.core.elements.CheckBox;
import com.zaizi.automation.alfresco.core.elements.Division;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.Link;
import com.zaizi.automation.alfresco.core.elements.Span;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;


/**
 *
 * @author kdesilva
 */
public class Dashboard {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager.getLogger(LoginPage.class
			.getName());
	
	//public static  ExtentReports extent = ExtentReports.get(LoginPage.class);

	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public Dashboard(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
    
	/**
	 * Logout From System
	 * 	  
	 */
	
    public void logout() {
       
        Button btnUser = new Button(driver, By.xpath("//div//span[@id='HEADER_USER_MENU_POPUP_text']"));
        btnUser.click();

        Link logoutLink = new Link(driver, By.xpath("//td[@id='HEADER_USER_MENU_LOGOUT_text']"));
        logoutLink.click();
        
    }
    
    /**
	 * Logout From System
	 * 	 
	 * @return Returns to LoginPage
	 * @throws InterruptedException
	 * @throws IOException 
	 */
    
    public LoginPage logout1() {
       
        Button btnUser = new Button(driver, By.xpath("//div//span[@id='HEADER_USER_MENU_POPUP_text']"));
        btnUser.click();

        Link logoutLink = new Link(driver, By.xpath("//td[@id='HEADER_USER_MENU_LOGOUT_text']"));
        logoutLink.click();

        return new LoginPage(driver);
    }
    
    /**
	 * Navigate To ChangePassword Page	 
	 */
    
    public void changePassword() {
        
        Button btnUser = new Button(driver, By.xpath("//div//span[@id='HEADER_USER_MENU_POPUP_text']"));
        btnUser.click();

        Link changePasswordLink = new Link(driver, By.xpath("//td[@id='HEADER_USER_MENU_CHANGE_PASSWORD_text']"));
        changePasswordLink.click();
        
    }
    
    
    public void profile(String className,String screenShot) throws IOException {
        //click "My Profile"
        Button btnUser = new Button(driver, By.xpath("//div//span[@id='HEADER_USER_MENU_POPUP_text']"));
        btnUser.click();

        LOGGER.info("Go to \"My profile\"");
        //extent.log(LogStatus.INFO, "Go to \"My profile\"");
       // Element.takescreenshot(driver, className, screenShot+"Profilepic");
        Link profileLink = new Link(driver, By.xpath("//td[@id='HEADER_USER_MENU_PROFILE_text']"));
        profileLink.click();

        
    }
  
  
    /**
     * Navigate to the Manage User Page
     * @return
     * @throws InterruptedException
     */
    public ManageUserPage gotoManageUserPage() throws InterruptedException 
    {
        Button defaultAppMore = new Button(driver, By.xpath("//span[@id='HEADER_ADMIN_CONSOLE_text']//a"));     
        defaultAppMore.click();
        Thread.sleep(500);
        Link users = new Link(driver, By.xpath("//div[@id='page_x002e_tools_x002e_admin-console_x0023_default-body']//ul[3]//li[2]//span//a"));
        users.click();
        
        return new ManageUserPage(driver);
    }
    
    /**
     * Navigates to the User Profile Page
     * @return
     */
    public UserProfilePage gotoTestUserProfilePage(){
        driver.navigate().to(TestCaseProperties.USER_PROFILE_PAGE_URL);
        return new UserProfilePage(driver);
    }
    
    /**
     * Navigates to the Edit User Page
     * @return
     */
    public EditUserProfilePage gotoEditTestUserProfilePage(){
        driver.navigate().to(TestCaseProperties.EDIT_USER_PROFILE_PAGE_URL);
        return new EditUserProfilePage(driver);
    }
    
    /**
     * Navigate to the Change Password Page
     * @return
     */
    public ChangePasswordPage gotoChangePasswordPage(){
        driver.navigate().to(TestCaseProperties.CHANGE_PASSWORD_PAGE_URL);
        return new ChangePasswordPage(driver);
    }
    
    public AdmintoolPage gotoAdmintoolPage() 
    {
    	driver.navigate().to(TestCaseProperties.ADMINTOOL_PAGE_URL);
        return new AdmintoolPage(driver);
		    	
    }
    
    /**
   	 * Navigate To AdminConsole Page	 
   	 */
    
    public AdminConsolePage gotoAdminConsole() {
        String adminConsoleID = "HEADER_ADMIN_CONSOLE_text";
        Span adminConsole = new Span(driver, By.id(adminConsoleID));
        adminConsole.click();
        Element.waitForLoad(driver);
        return new AdminConsolePage(driver);
        
    }
    
    /**
     * @param dashletTitle
     * @throws InterruptedException
     */
    public void customizeDashboard(String dashletTitle) throws InterruptedException
    {
        Link customize = new Link(driver, By.xpath("//img[@alt='Customize Dashboard']"));
        customize.click();
        Thread.sleep(2000);
        Button addDashlets = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-addDashlets-button-button']"));
        addDashlets.click();
        Thread.sleep(2000);
        Actions performer = new Actions(driver);
        WebElement rssFeed = driver.findElement(By.xpath("//div[@title='" + dashletTitle + "']"));
        WebElement list = driver.findElement(By.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-column-ul-1']"));
        Action dragAndDrop = performer.clickAndHold(rssFeed).moveToElement(list).release(list).build();
        Thread.sleep(5000);
        dragAndDrop.perform();
        Thread.sleep(5000);
        Button okButton = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-save-button-button']"));
        okButton.click();
        Thread.sleep(5000);
  /*      Division rssFeedBlog = new Division(
                driver,
                By.xpath("//div[@id='page_x002e_component-1-3_x002e_user_x007e_admin_x007e_dashboard_x0023_default-title']"));
        if (rssFeedBlog.getWebElement().isDisplayed())
        {
        	LOGGER.info( "Dashlet added");			
			extent.log(LogStatus.PASS, "<font color=green>"
					+ "Dashlet is added" + "<font>");
        }
        else
        {
        	LOGGER.info(" Dashlet is not added");
			extent.log(LogStatus.FAIL, "<font color=green>"
					+ "Dashlet is not added" + "<font>");
        }*/
    }
    /**
     * @param siteNameText
     * @throws InterruptedException
     */
    public void createPublicSite(String siteNameText) throws InterruptedException
    {
        Button sitesButton = new Button(driver, By.xpath("//span[@id='HEADER_SITES_MENU_text']"));
        sitesButton.click();
        Link createSiteLink = new Link(driver, By.xpath("//td[@id='HEADER_SITES_MENU_CREATE_SITE_text']"));
        createSiteLink.click();
        Thread.sleep(2000);
        TextField siteName = new TextField(driver, By.xpath("//input[@id='alfresco-createSite-instance-title']"));
        siteName.enterText(siteNameText);
        Thread.sleep(2000);
        CheckBox publicOption = new CheckBox(driver, By.xpath("//input[@id='alfresco-createSite-instance-isPublic']"));
        publicOption.click();
        Thread.sleep(2000);
        Button okButton = new Button(driver, By.xpath("//button[@id='alfresco-createSite-instance-ok-button-button']"));
        okButton.click();
        Thread.sleep(2000);
    }
    
    /**
     * @param dashletTitle
     * @throws InterruptedException
     */
    public void customizeSiteDashboard(String dashletTitle) throws InterruptedException
    {
        Thread.sleep(5000);
        Link customize = new Link(driver, By.xpath("//img[@alt='Site configuration options']"));
        customize.click();
        Thread.sleep(2000);
        Link customizeOption = new Link(driver, By.xpath("//a[contains(., 'Customize Dashboard')]"));
        customizeOption.click();
        Thread.sleep(1000);
        Button addDashlets = new Button(
                driver,
                By.xpath("//span/span/button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-addDashlets-button-button']"));
        addDashlets.click();
        Thread.sleep(2000);
        Actions performer = new Actions(driver);
        WebElement wiki = driver.findElement(By.xpath("//div[@title='" + dashletTitle + "']"));
        WebElement list = driver.findElement(By.xpath("//div/ul[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-column-ul-1']"));
        Action dragAndDrop = performer.clickAndHold(wiki).moveToElement(list).release(list).build();
        Thread.sleep(5000);
        dragAndDrop.perform();
        Thread.sleep(5000);
        Button okButton = new Button(
                driver,
                By.xpath("//span/span/button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-save-button-button']"));
        okButton.click();
        Thread.sleep(5000);
        Division wikiDashlet = new Division(driver, By.xpath("//div/div/div[@class='title' and contains(., 'Wiki')]"));
        if (wikiDashlet.getWebElement().isDisplayed())
        {
        	LOGGER.info("Pass");			
			
        }
        else
        {
        	LOGGER.info("Fail");
			
        }
    }
    
    /**
     * @param dashletTitle
     * @throws InterruptedException
     */
    public void customizeSiteDashboard1(String dashletTitle) throws InterruptedException
    {
        Thread.sleep(5000);
        Link customize = new Link(driver, By.xpath("//img[@alt='Site configuration options']"));
        customize.click();
        Thread.sleep(2000);
        Link customizeOption = new Link(driver, By.xpath("//a[contains(., 'Customize Dashboard')]"));
        customizeOption.click();
        Thread.sleep(1000);
        Button addDashlets = new Button(
                driver,
                By.xpath("//span/span/button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-addDashlets-button-button']"));
        addDashlets.click();
        Thread.sleep(2000);
        Actions performer = new Actions(driver);
        WebElement wiki = driver.findElement(By.xpath("//div[@title='" + dashletTitle + "']"));
        WebElement list = driver.findElement(By.xpath("//div/ul[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-column-ul-1']"));
        Action dragAndDrop = performer.clickAndHold(wiki).moveToElement(list).release(list).build();
        Thread.sleep(5000);
        dragAndDrop.perform();
        Thread.sleep(5000);
        Button okButton = new Button(
                driver,
                By.xpath("//span/span/button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-save-button-button']"));
        okButton.click();
        Thread.sleep(5000);
      
    }
    
    /**
     * @param siteName
     * @throws InterruptedException
     */
    public void searchSite(String siteName) throws InterruptedException
    {
        Button sitesButton = new Button(driver, By.xpath("//span[@id='HEADER_SITES_MENU_text']"));
        sitesButton.click();
        Thread.sleep(2000);
        Link searchForSites = new Link(driver, By.xpath("//td[@id='HEADER_SITES_MENU_SITE_FINDER_text']"));
        searchForSites.click();
        Thread.sleep(2000);
        TextField searchText = new TextField(driver,
                By.xpath("//input[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-term']"));
        searchText.enterText(siteName);
        Thread.sleep(2000);
        Button searchButton = new Button(driver,
                By.xpath("//button[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-button-button']"));
        searchButton.click();
        Thread.sleep(2000);
    }
    

    /**
     * @throws InterruptedException
     */
    public void deleteSite() throws InterruptedException
    {
        Thread.sleep(2000);
        Button deleteButton = new Button(driver, By.xpath("//button[contains(., 'Delete')]"));
        deleteButton.click();
        Thread.sleep(1000);
        Button confirmDeletion = new Button(driver, By.xpath("//button[contains(., 'Delete')]"));
        confirmDeletion.click();
        Thread.sleep(1000);
        Button yesButton = new Button(driver, By.xpath("//button[contains(., 'Yes')]"));
        yesButton.click();
    }
    
    
}
