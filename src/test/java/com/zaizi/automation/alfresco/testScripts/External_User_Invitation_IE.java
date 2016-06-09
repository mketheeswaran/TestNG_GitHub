package com.zaizi.automation.alfresco.testScripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.Span;
import com.zaizi.automation.alfresco.core.elements.TakeScreenShot;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;
import com.zaizi.automation.alfresco.core.pages.CreateObjects;
import com.zaizi.automation.alfresco.core.pages.LoginPage;
import com.zaizi.automation.alfresco.core.pages.NavigateToPage;
import com.zaizi.automation.alfresco.core.pages.RemoveObjects;
import com.zaizi.automation.alfresco.core.pages.SearchObjects;
import com.zaizi.automation.alfresco.core.pages.SiteDashboardPage;
import com.zaizi.automation.extentReports.ExtentManagerIE;
import com.zaizi.automation.listeners.IERetryAnalyzer;


/**
*
* @author mrashad@zaizi.com
*/



public class External_User_Invitation_IE {

	
	
	

	/**
	 * 
	 * Defining log4j
	 */

	public static final Logger LOGGER = LogManager
			.getLogger(External_User_Invitation_IE.class.getName());


	
	/**
	 * 
	 * Defining Report
	 */
static ExtentReports extent;
    
	/**
	 * 
	 * Define WebDriver
	 */

	private static WebDriver driver;	
	
	
	
	/**
	 * 
	 * Define className
	 */

	public static String className = External_User_Invitation_IE.class
			.getSimpleName();

	
	
	/**
	 * 
	 * Declares ExtentReports Configurations here 
	 * SeleniumWebdriver Specification (Set capabilities of Platform,BrowserName,Version)
	 * RemoteWebdriver Specification(new RemoteWebDriver(new URL(NODEURL),capability)) 
	 * Grid Specification
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	@BeforeTest(alwaysRun=true)
	public static void beforeClass() throws IOException

	{
		
		extent = ExtentManagerIE.getReporter(TestCaseProperties.REPORT_TEST_PATH_IE+className+".html");
		System.out.println("Testcase started");
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public static void beforemethod() throws MalformedURLException{
				//Set the DriverType(BrowserName,Platform)
				driver = TestCaseProperties.driverType("IE", "WINDOWS");
				
				driver.manage().window().setSize(new Dimension(1920, 1920));
				
				//Get LoginScreen_URl from TestcaseProperties Values
				driver.get(TestCaseProperties.LOGIN_SCREEN_URL);	
	}	
	
	
	
	/**
	 * 
	 * @Test createSite
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */

	@Test(dataProvider="getData",retryAnalyzer=IERetryAnalyzer.class,testName = "Create user in IE",priority = 1)
	public void createSite(String siteName,String siteId,String siteCreatorName,String expectedResult,
			Boolean isPrivate,String screenShotName) throws InterruptedException, IOException
	{

		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Create Site "+siteName);

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("a_createSite","Create site called \" "+siteName +" \",Is it private Site "+isPrivate);	

		LOGGER.info("Test case a_createSite started executing");
		test.log(LogStatus.INFO,
				"Test case a_createSite started executing");		

		LOGGER.info("Accessing the Login Page");
        test.log(LogStatus.INFO, "Accessing the Login Page");
        
		LOGGER.info("Login as admin");
		test.log(LogStatus.INFO, "Login as admin");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsAdmin();
		
		CreateObjects createObjects = new CreateObjects(driver);
		
		if (isPrivate)
        {	
            LOGGER.info("CREATE PRIVATE SITE");
             test.log(LogStatus.INFO, "CREATE PRIVATE SITE");
                
             test.log(LogStatus.INFO,"Click \"Create Site\" ");
             test.log(LogStatus.INFO,"Enter "+siteName+" in \"site Name Field\" ");
	     test.log(LogStatus.INFO,"Enter "+siteId+" in \"site URL Field\" ");	
             test.log(LogStatus.INFO,"Check \"private\" Option");
             test.log(LogStatus.INFO,"Click \"OK\" Button "); 
             createObjects.createPrivateSite(siteName, siteId,expectedResult+ "private2");
			
             TakeScreenShot ts=new TakeScreenShot();
      	   	ts.takeScreenShotIE(driver,className, screenShotName+"195");
      	   	test.log(LogStatus.PASS, "Snapshot below:" +test.addScreenCapture("./"+className+"/"+screenShotName+"195"+".png"));
      	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
             extent.flush();    
                        
                        //If Site Created
		if(Element.isElementPresent(driver, By.xpath("//div[@class='bd']/span[@class='wait']")))
		{	
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"Site "+siteName +" CREATED ");
			test.log(LogStatus.INFO, "<font color=blue>Site "+siteName +" CREATED <font> ");	
		    	
		}		
		//If Site IS NOT CREATED
		//Expected Result1=[COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED]
		//ExpectedResult1==CurrentResult
		else 
		{
			SiteDashboardPage siteDash1=new SiteDashboardPage(driver);
			String siteErrorNotification=siteDash1.errorNotification(siteName);
	    	
    	    if(siteErrorNotification.toUpperCase().equals(expectedResult))
    		{    	    			    
	    	        LOGGER.info("Expected Results : " + expectedResult);
	        	test.log(LogStatus.INFO, "Expected Results : " + expectedResult);
	        	
	        	LOGGER.info("Current Test Results : "+siteErrorNotification);
	        	test.log(LogStatus.PASS, "Current Test Results : " +"<font color=green>" +siteErrorNotification+"<font>");	        	

                        Button okaybtn=new Button(driver,By.xpath("//button[text()='OK']"));
		        okaybtn.click(); 
    		}
    	    //ExpectedResult1!=CurrentResult[PROBLEM]
    	    else 
    	    {
    	    	LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"Private Site "+siteName +"IS NOT CREATED ");
                test.log(LogStatus.FAIL, "Private Site "+siteName +"IS NOT CREATED ");
			
	    	    LOGGER.info("Expected Results : " + expectedResult);
	            test.log(LogStatus.INFO, "Expected Results : " + expectedResult);
	        	
	        	LOGGER.info("Current Test Results : "+siteErrorNotification);
	        	test.log(LogStatus.FAIL, "Current Test Results : " +"<font color=red>" +siteErrorNotification+"<font>");        	
	        		
    	    }
		}
             
             Element.waitForLoad(driver);
             LOGGER.info("CHECK WHETHER PRIVATE SITE CREATED OR NOT");
             test.log(LogStatus.INFO, "CHECK WHETHER PRIVATE SITE CREATED OR NOT");
             
             LOGGER.info("Check whether \" " +siteName+ "\" SITE IS VISIBLE TO "+siteCreatorName);
             test.log(LogStatus.INFO, "Check whether \" " +siteName+ "\" SITE IS VISIBLE TO "+siteCreatorName);
	     Thread.sleep(5000);

             LOGGER.info("Search the sitename in Site finder");
             test.log(LogStatus.INFO, "Search the sitename in Site finder");
             
			SearchObjects searchSite = new SearchObjects(driver);			
			searchSite.searchSite(siteName);	
			
			if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
			{
				LOGGER.info("Message display as \"No sites found\"");
				test.log(LogStatus.INFO, "Message display as \"No sites found\"");
				
				LOGGER.info(siteName+ " SITE IS NOT VISIBLE TO "+siteCreatorName);
				test.log(LogStatus.FAIL,"<font color=blue>"+siteName+ " SITE IS NOT VISIBLE TO "+siteCreatorName+"<font>");
                                
				TakeScreenShot ts1=new TakeScreenShot();
	     	   	ts1.takeScreenShotIE(driver,className, screenShotName+"196");
	     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"196"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();    
				
			}
			else if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName))
			{	
				LOGGER.info(siteName+ " SITE IS VISIBLE TO "+siteCreatorName);
				test.log(LogStatus.INFO,"<font color=blue>"+siteName+ " SITE IS VISIBLE TO "+siteCreatorName+"<font>");
                                
				TakeScreenShot ts3=new TakeScreenShot();
	     	   	ts3.takeScreenShotIE(driver,className, screenShotName+"197");
	     	   	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"197"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();    
							
			}				
			
		   
		   
                Thread.sleep(5000);                        
                Element.waitForLoad(driver);
                
                LoginPage loginPage1 = new LoginPage(driver);
                loginPage1.logout();        
                Element.waitForLoad(driver);
                
                LOGGER.info("Login as  \"privateuser\" ");
                test.log(LogStatus.INFO,"Login as \"privateuser\" ");
                
                loginPage1.loginAsUser1("privateuser", "1qaz@WSX");
                Element.waitForLoad(driver);
                Thread.sleep(5000);
                LOGGER.info("Check whether \"" +siteName+ "\" SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                test.log(LogStatus.INFO, "Check whether \" " +siteName+ "\" SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                
                SearchObjects searchSite2 = new SearchObjects(driver);
                searchSite2.searchSite(siteName);
                Element.waitForLoad(driver);       
                
                
                if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
                {
                    Thread.sleep(5000);
                    LOGGER.info("Message display as \"No sites found\"");
                    test.log(LogStatus.INFO, "Message display as \"No sites found\"");
                    
                    LOGGER.info(siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER");
                    test.log(LogStatus.PASS,siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
                    
                    TakeScreenShot ts4=new TakeScreenShot();
             	   	ts4.takeScreenShotIE(driver,className, screenShotName+"198");
             	   	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"198"+".png"));
             	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                    extent.flush();    
                    
                }
                else 
                {
                    Thread.sleep(5000);
                    if (Element.isTextPresentInList(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName)) {
                        
                        LOGGER.info(siteName+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                        test.log(LogStatus.FAIL,siteName+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                        
                        TakeScreenShot ts5=new TakeScreenShot();
                 	   	ts5.takeScreenShotIE(driver,className, screenShotName+"199");
                 	   	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"199"+".png"));
                 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                        extent.flush();    
                        
                        
                    } else {
                        LOGGER.info(siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER");
                        test.log(LogStatus.PASS,siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
                        
                        
                        TakeScreenShot ts6=new TakeScreenShot();
                 	   	ts6.takeScreenShotIE(driver,className, screenShotName+"200");
                 	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"200"+".png"));
                 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                        extent.flush();    	
                        
                        
                    }
                }            
            
        }
		else
		{	LOGGER.info("CREATE PUBLIC SITE");
        test.log(LogStatus.INFO, "CREATE PUBLIC SITE");
             
                        test.log(LogStatus.INFO,"Click \"Create Site\" ");
             test.log(LogStatus.INFO,"Enter "+siteName+" in \"site Name Field\" ");
	     test.log(LogStatus.INFO,"Enter "+siteId+" in \"site URL Field\" ");	
             test.log(LogStatus.INFO,"Check \"private\" Option");
             test.log(LogStatus.INFO,"Click \"OK\" Button ");
             
			createObjects.createPublicSite(siteName, siteId, expectedResult);
                        
                                                
                        //If Site Created
		if(Element.isElementPresent(driver, By.xpath("//div[@class='bd']/span[@class='wait']")))
		{	
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"Site "+siteName +" CREATED ");
			test.log(LogStatus.INFO, "<font color=blue>Site "+siteName +" CREATED <font> ");
                        
			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShotIE(driver,className, screenShotName+"201");
     	   	test.log(LogStatus.INFO, "User is created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"201"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();    	
		    	
		}		
		//If Site IS NOT CREATED
		//Expected Result1=[COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED]
		//ExpectedResult1==CurrentResult
		else 
		{
			SiteDashboardPage siteDash1=new SiteDashboardPage(driver);
			String siteErrorNotification=siteDash1.errorNotification(siteName);
	    	
    	    if(siteErrorNotification.toUpperCase().equals(expectedResult))
    		{    	    			    
	    	        LOGGER.info("Expected Results : " + expectedResult);
	        	test.log(LogStatus.INFO, "Expected Results : " + expectedResult);
	        	
	        	LOGGER.info("Current Test Results : "+siteErrorNotification);
	        	test.log(LogStatus.PASS, "Current Test Results : " +"<font color=green>" +siteErrorNotification+"<font>");	        	

	        	TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShotIE(driver,className, screenShotName+"202");
	     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"202"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();    	
                        
                        Button okaybtn=new Button(driver,By.xpath("//button[text()='OK']"));
		        okaybtn.click(); 
    		}
    	    //ExpectedResult1!=CurrentResult[PROBLEM]
    	    else 
    	    {
    	    	LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"Private Site "+siteName +"IS NOT CREATED ");
                test.log(LogStatus.FAIL, "Private Site "+siteName +"IS NOT CREATED ");
			
	    	    LOGGER.info("Expected Results : " + expectedResult);
	            test.log(LogStatus.INFO, "Expected Results : " + expectedResult);
	        	
	        	LOGGER.info("Current Test Results : "+siteErrorNotification);
	        	test.log(LogStatus.FAIL, "Current Test Results : " +"<font color=red>" +siteErrorNotification+"<font>");        	
	        	
	        	TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShotIE(driver,className, screenShotName+"203");
	     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"203"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush(); 
    	    }
		}
                LOGGER.info("CHECK WHETHER "+siteName+" IS CREATED OR NOT");
                test.log(LogStatus.INFO,"CHECK WHETHER "+siteName+" IS CREATED OR NOT");
                
		LOGGER.info("CHECK WHETHER "+siteName+" IS DISPLAY IN SITES LIST");
                test.log(LogStatus.INFO,"CHECK WHETHER "+siteName+" IS DISPLAY IN SITES LIST");
                
                LOGGER.info("Click \"Sites\"");
		test.log(LogStatus.INFO, "Click \"Sites\"");
                
                LOGGER.info("Click \"Site Finder\"");
		test.log(LogStatus.INFO, "Click \"Site Finder\"");
                
                LOGGER.info("Search sitename");
		test.log(LogStatus.INFO, "Search sitename");
                
		SearchObjects searchSite = new SearchObjects(driver);
		searchSite.searchSite(siteName);
                
		if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
		{
			LOGGER.info("Message display as \"No sites found\"");
			test.log(LogStatus.INFO, "Message display as \"No sites found\"");
                        
			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShotIE(driver,className, screenShotName+"204");
     	   	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"204"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
			
		}
		else 
		{
			if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName)) {
				
                            LOGGER.info("Site is Display in \"Site Search\"");
                            test.log(LogStatus.PASS, "<font color=green>Site is Display in \"Site Search\"<font>");
                            
                            TakeScreenShot ts=new TakeScreenShot();
                     	   	ts.takeScreenShotIE(driver,className, screenShotName+"205");
                     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"205"+".png"));
                     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                            extent.flush(); 
                            
                        
			} else {
				
                            LOGGER.info("Site IS NOT Display in \"Site Search\"");
                            test.log(LogStatus.FAIL, "<font color=red>Site is NOT Display in \"Site Search\"<font>");
                            
                            TakeScreenShot ts=new TakeScreenShot();
                     	   	ts.takeScreenShotIE(driver,className, screenShotName+"206");
                     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"206"+".png"));
                     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                            extent.flush(); 
				
				
			}
		}
		}	
		loginPage.logout();
		LOGGER.info("Test case a_createSite executed");
		test.log(LogStatus.INFO, "Test case a_createSite executed");
                
                 extent.endTest(test);        
                extent.flush();

	}
	
	
	
	
	
	/**
	 * 
	 * @Test send SiteInvitation to ExternalUser	 
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */
	
	
	@Test(dataProvider="getData",retryAnalyzer=IERetryAnalyzer.class,testName = "sendSiteInvitationToExternallUser in IE",priority = 2)
    public void sendSiteInvitationToExternallUser(String firstName, String lastName, String userName, String password, String siteName,String siteId,
			String roleName,String email, String expectedResult,String screenShotName) throws InterruptedException, IOException
    {
		
		
		
		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING,
				"Check whether Site Invitation sent BY " + firstName);
		
		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("Send SiteInvitation",
				"Check whether Site Invitation sent BY " + firstName);

		
		LoginPage loginPage = new LoginPage(driver);

		//Login as "userName" 
		LOGGER.info("Accessing the LoginPage");
		test.log(LogStatus.INFO,"Accessing the LoginPage");
		
		LOGGER.info("Login As Admin");
		test.log(LogStatus.INFO,"Login As Admin");
		loginPage.loginAsAdmin();
		Element.waitForLoad(driver);

		LOGGER.info("Test case b_sendExternalUserInvitation started executing");
		test.log(LogStatus.INFO,
				"Test case b_sendExternalUserInvitation started executing");
		

		LOGGER.info("SEND EXTERNAL SITE INVITATION");
		test.log(LogStatus.INFO,
				"SEND EXTERNAL SITE INVITATION");
		
		test.log(LogStatus.INFO, "Click \"Search Finder\"");
		test.log(LogStatus.INFO, "Search Site \""+siteName+"\"");
		test.log(LogStatus.INFO, "Navigate to the " +siteName);
		test.log(LogStatus.INFO, "Click \"Invite Button\"");
		test.log(LogStatus.INFO, "Enter the firstName");
		test.log(LogStatus.INFO, "Enter the lastName");
		test.log(LogStatus.INFO, "Enter the email");
		test.log(LogStatus.INFO, "Click \"Add\" next to user");
		test.log(LogStatus.INFO, "Select the role");
		test.log(LogStatus.INFO, "Click \"Invite\" Button");
		
		SiteDashboardPage inviteExternalUser=new SiteDashboardPage(driver);
		inviteExternalUser.inviteExternalUser(siteName, firstName, lastName, email,roleName);
		loginPage.logout();
		
		LOGGER.info("CHECK WHETHER INVITATION IS SEND OR NOT");
		test.log(LogStatus.INFO,
				"CHECK WHETHER INVITATION IS SEND OR NOT");
		
		LOGGER.info("Go to Gmail");
		test.log(LogStatus.INFO, "Go to Gmail");
		
        driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/?ui%3D2&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1");
        Thread.sleep(4000);
        
        LOGGER.info("Enter email Address");
		test.log(LogStatus.INFO, "Enter email Address");
        TextField emailField = new TextField(driver, By.xpath("//input[@id='Email']"));
        emailField.clearText();
        emailField.enterText(email);
        
        
        LOGGER.info("Enter email Address");
		test.log(LogStatus.INFO, "Enter email Address");
        TextField next = new TextField(driver, By.xpath("//input[@id='next']"));
        next.click();
        Thread.sleep(2000);
        
        LOGGER.info("Enter password");
		test.log(LogStatus.INFO, "Enter password");
        TextField passwordField = new TextField(driver, By.xpath("//input[@id='Passwd']"));
        passwordField.clearText();
        passwordField.enterText(password);
        
        LOGGER.info("Click \"Sign In\"");
		test.log(LogStatus.INFO, "Click \"Sign In\"");
        TextField signIn = new TextField(driver, By.xpath("//input[@id='signIn']"));
        signIn.click();
        
        
        Thread.sleep(10000);
        Span link = new Span(
                driver,
                By.xpath("//span/b[contains(., 'Alfresco Share: You have been invited to join the Test Site site by admin')]"));
        if (link.getWebElement().isDisplayed())
        {
            LOGGER.info("The invitation has been received!");
            test.log(LogStatus.PASS, "The invitation has been received!");
            TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShotIE(driver,className, screenShotName+"207");
     	   	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"207"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
        }
        else
        {
            LOGGER.info("The invitation was not found!");
            test.log(LogStatus.FAIL, "The invitation was not found!");
            TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShotIE(driver,className, screenShotName+"208");
     	   	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"208"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
	        
        }
		
		LOGGER.info("Test case b_sendExternalUserInvitation executed");
		test.log(LogStatus.INFO,"Test case b_sendExternalUserInvitation executed");
                 extent.endTest(test);        
                extent.flush();
		
		
		
		
		
    }
	
	
	
	
	/**
	 * 
	 * @Test DeleteSite
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */	 
	
	//@Parameters({"siteNameChrome", "siteIdChrome","screenShotNameChrome" })
	@Test(dataProvider="getData",retryAnalyzer=IERetryAnalyzer.class,testName = "DeleteSite in IE",priority = 3)
    public void DeleteSite(String siteName,String siteId,
			String screenShotName) throws InterruptedException, IOException
    {
	
		
		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Delete site called \" "+siteName +" \" ");

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("e_deleteSite","Delete site called \" "+siteName +" \" ");

		LOGGER.info("Test case e_deleteSite started executing");
		test.log(LogStatus.INFO,
				"Test case e_deleteSite started executing");		

		LOGGER.info("Accessing the Login Page");
                test.log(LogStatus.INFO, "Accessing the Login Page");
        
		LOGGER.info("Login as admin");
		test.log(LogStatus.INFO, "Login as admin");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsAdmin();
		
                LOGGER.info("Check the site,if site exist Delet the user");
		test.log(LogStatus.INFO, "Check the site,if site exist Delete the user");
                test.log(LogStatus.INFO, "Search the site");
                test.log(LogStatus.INFO, "Click \"Delete\" Button,next to site\""+siteName+"\"");
                test.log(LogStatus.INFO, "Click \"Delete\" Button,for delete confirmation");
		test.log(LogStatus.INFO, "Login as Admin to delete Site in TrashCan");
                test.log(LogStatus.INFO, "Click \"My Profile\"");
                test.log(LogStatus.INFO, "Place siteUrl");
                test.log(LogStatus.INFO, "Click \"Search\" Button");
                test.log(LogStatus.INFO, "Check the patucular siteUrl");
                test.log(LogStatus.INFO, "Click \"Selected Item\" Button");
                test.log(LogStatus.INFO, "Click \"Delete\" Button");
                test.log(LogStatus.INFO, "Click \"OK\" Button");
                test.log(LogStatus.INFO, "Click \"OK confirmation\" Button");
                RemoveObjects deleteSite=new RemoveObjects(driver);
		deleteSite.deleteSite(siteName, siteId);		
                
                LOGGER.info("CHECK WHETHER SITE\" "+siteName+" \"IS DELETED OR NOT");
		test.log(LogStatus.INFO,"CHECK WHETHER SITE\" "+siteName+" \"IS DELETED OR NOT");
                
                NavigateToPage navigateTo = new NavigateToPage(driver);		
		navigateTo.goToHome();
                
                LOGGER.error("Click \"My Profile\" Again");
		test.log(LogStatus.INFO, "Click \"My Profile\" Again");
                
                test.log(LogStatus.INFO, "Accessing trashcan Page");
		navigateTo.goToUserTrashCan();	
		
		LOGGER.info("Place siteUrl");
		test.log(LogStatus.INFO, "Place siteUrl");
		TextField textField = new TextField(
				driver,
				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-search-text"));
		textField.clearText();
		textField.enterText(siteId);
		
		LOGGER.info("Click \"Search\" Button");
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		Button searchButton = new Button(driver,By.xpath("//button[text()='Search']"));
		searchButton.click();
		Thread.sleep(2000);
                
		TakeScreenShot ts=new TakeScreenShot();
 	   	ts.takeScreenShotIE(driver,className, screenShotName+"210");
 	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"210"+".png"));
 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
        extent.flush();  
		//Element.takeScreenShotIE(driver, className, screenShotName+"deleteConfirmation");
		
		//CheckBox checkbox=new CheckBox(driver, By.xpath("//div[@class='name'][text()='"+item+"']/ancestor::td[1]/preceding-sibling::td[2]//input"));
		//checkbox.click();

		LOGGER.info("Check the patucular siteUrl");
		test.log(LogStatus.INFO, "Check the patucular siteUrl");
		
		if(Element.isElementPresent(driver, By.xpath("//tbody[@class='yui-dt-message']//tr//td//div[text()='No items exist']")))
		{
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+" SITE IS DELETED SUCCESSFULLY");
			
			
     	   	ts.takeScreenShotIE(driver,className, screenShotName+"211");
     	   	test.log(LogStatus.PASS, "Site is Deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"211"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
		}
		else if(Element.isElementPresent(driver,By.xpath("//div[text()='"+siteId+"']")))
		{
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+" SITE IS DELETED SUCCESSFULLY");
			
			
     	   	ts.takeScreenShotIE(driver,className, screenShotName+"A18");
     	   	test.log(LogStatus.PASS, "Site is Deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"A18"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
		}		
		else{
			Element.waitUntilElementPresent(driver, By.xpath("//div//div//table//tbody//tr[contains(.,'" + siteId
					+ "')]//td//div"));
	
	java.util.List<WebElement> myList = driver.findElements(By
			.xpath("//div//div//table//tbody//tr[contains(.,'" + siteId
					+ "')]//td//div"));

	for (int i = 0; i < myList.size(); i++) {

		if (myList.get(i).getText().equals(siteId)) 
		{	
			Thread.sleep(2000);
			LOGGER.info(siteName+ "SITE IS NOT DELETED");
			test.log(LogStatus.FAIL, siteName+" SITE IS NOT DELETED");
			TakeScreenShot ts2=new TakeScreenShot();
     	   	ts2.takeScreenShotIE(driver,className, screenShotName+"212");
     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"212"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();  
		}
		else
		{
			LOGGER.info(siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+"SITE IS DELETED SUCCESSFULLY");
			TakeScreenShot ts3=new TakeScreenShot();
     	   	ts3.takeScreenShotIE(driver,className, screenShotName+"213");
     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"213"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();  
		}
		}
		
		}
		
		
		loginPage.logout();
		
		LOGGER.info("Test case e_deleteSite executed");
		test.log(LogStatus.INFO, "Test case e_deleteSite executed");
                
                extent.endTest(test);        
                extent.flush();
		
    }
	
	
	
	//String firstName, String lastName, String userName, String password, String siteName,String siteId,String roleName,String email, String expectedResult,String screenShotName
	
	@DataProvider(name = "getData")
	public Object[][] provideData(Method method) {
	Object[][] result = null;

	 if (method.getName().equals("createSite")) {
			result = new Object[][] { 
						{ "IETest", "IETest", "testIE", "COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED",true, "IELoginTest8"}
									};
	}else if (method.getName().equals("sendSiteInvitationToExternallUser")) {
			result = new Object[][] { 
						{ "testIE", "testIE", "testIE", "testngzaizi123",  "IETest", "IETest", "Collaborator", "testngzaizi@gmail.com", "COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED",  "IELoginTest9"}
									};
	}else if (method.getName().equals("DeleteSite")) {
			result = new Object[][] { 
						{ "IETest", "IETest", "IELoginTest10"}
									};
									}		
						return result;
		}
	
	
   
	@AfterMethod
	public void clod() throws MalformedURLException{
		
		driver.quit(); 
		
	}

	@AfterTest(alwaysRun=true)
	public void extent() {
		
		LOGGER.info("Test case closed");
		extent.close();	
		driver.quit();
		//driver.close();

	} 
	
	
	
	
	
}
