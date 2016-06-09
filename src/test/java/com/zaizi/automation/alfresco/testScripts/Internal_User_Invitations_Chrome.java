package com.zaizi.automation.alfresco.testScripts;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
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
import com.zaizi.automation.alfresco.core.pages.AdminConsolePage;
import com.zaizi.automation.alfresco.core.pages.CreateObjects;
import com.zaizi.automation.alfresco.core.pages.LoginPage;
import com.zaizi.automation.alfresco.core.pages.NavigateToPage;
import com.zaizi.automation.alfresco.core.pages.RemoveObjects;
import com.zaizi.automation.alfresco.core.pages.SearchObjects;
import com.zaizi.automation.alfresco.core.pages.SiteDashboardPage;
import com.zaizi.automation.extentReports.ExtentManagerChrome;
import com.zaizi.automation.listeners.ChromeRetryAnalyzer;





/**
  *
  * @author mrashad@zaizi.com
  */

public class Internal_User_Invitations_Chrome  {

	
	/**
	 * 
	 * Defining log4j
	 */

	public static final Logger LOGGER = LogManager
			.getLogger(Internal_User_Invitations_Chrome.class.getName());

	
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

	public static String className = Internal_User_Invitations_Chrome.class
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
		
		extent = ExtentManagerChrome.getReporter(TestCaseProperties.REPORT_TEST_PATH_CHROME+className+".html");
		System.out.println("Testcase started");
		
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public static void beforemethod() throws MalformedURLException{
				//Set the DriverType(BrowserName,Platform)
				driver = TestCaseProperties.driverType("Chrome", "WINDOWS");
				
				driver.manage().window().setSize(new Dimension(1920, 1920));
				
				//Get LoginScreen_URl from TestcaseProperties Values
				driver.get(TestCaseProperties.LOGIN_SCREEN_URL);	
	}
	/**
	 * 
	 * @Test Create Internal User Invitations 	 
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */

	

	//@Parameters({"firstNameChrome", "lastNameChrome","emailChrome","fullNameChrome","screenShotNameChrome" })
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Create user in Chrome",priority = 1)
	public void createUser(String firstName,String lastName,String email,String userName,String password,String fullName,String screenShotName) throws InterruptedException, IOException

	{

		
		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Create User in Chrome "+userName);

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("Create new User","Create new user : \" "+userName+" \"");
		
		LOGGER.info("Test case createUser started executing");
		test.log(LogStatus.INFO,
				"Test case createUser started executing");		

		LOGGER.info("Accessing the Login Page");
        test.log(LogStatus.INFO, "Accessing the Login Page");        
               	 		         
        LOGGER.info("Login as \"Admin\"");
        test.log(LogStatus.INFO, "Login as \"Admin\""); 
        extent.flush();
        
   	    LoginPage loginPage=new LoginPage(driver);   	            
   	    loginPage.loginAsAdmin();
   	         
   	    LOGGER.info("Create new user : \" "+userName+" \"");
        test.log(LogStatus.INFO, "Create new user : \" "+userName+" \""); 
        
   	    LOGGER.info("Accessing the AdminTool Page");
        test.log(LogStatus.INFO, "Accessing the AdminTool Page");         
        test.log(LogStatus.INFO, "Navigate \"Users\" in AdminTools");
        test.log(LogStatus.INFO, "Click \"New User\"");
        test.log(LogStatus.INFO, "Fill the userfields");
        test.log(LogStatus.INFO, "Click \"Create User\"");
        extent.flush();
        
        Element.waitForLoad(driver);
		AdminConsolePage createUser=new AdminConsolePage(driver);		
		createUser.createUser(firstName, lastName, email,userName,password,password);			
		Element.waitForLoad(driver);
		Thread.sleep(5000);
		 //If Any Message prompt as "Failure",User is alredy exist
        if(Element.isElementPresent(driver, By.xpath("//div[@id='prompt_h'][text()='Failure']"))) 
        {   
        	Thread.sleep(5000);
            Span notification = new Span(driver, By.xpath("//div[@id='prompt']/descendant::div[@class='bd']"));
            notification.getText();
            
            LOGGER.info("Message display as : "+notification.getText());
            test.log(LogStatus.INFO, "<font color=blue>Message display as : "+notification.getText()+"<font>");
            
            TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"1");
     	   	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"1"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();   
            
            Thread.sleep(3000);
            Button okaybtn=new Button(driver,By.xpath("//Button[text()='OK']"));
            okaybtn.click();
             
            
           
        }
       //ELSE USER CREATED SUCESSFULLY
        else 
        {   
        	
        	LOGGER.info("User is Successfully Created");
        	test.log(LogStatus.INFO, "<font color=green>User is Successfully Created<font>");
        	
        	TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"2");
     	   	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"2"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();          
        	
        } 
        
        Thread.sleep(3000);
        LoginPage loginPage1=new LoginPage(driver);  
		loginPage1.logout();	
		
		Element.waitForLoad(driver);    
		Thread.sleep(3000);
        LOGGER.info("CHECK WHETHER USER IS CREATED OR NOT");
		test.log(LogStatus.INFO, "CHECK WHETHER USER IS CREATED OR NOT");  
		
       
		LOGGER.info("Accessing the Login Page");
        test.log(LogStatus.INFO, "Accessing the Login Page");

        LOGGER.info("Login as created user"+userName);
        test.log(LogStatus.INFO, "Login as created user"+userName);
        
        LoginPage loginPage2 = new LoginPage(driver);
        loginPage2.loginAsUser(userName, password);
           
        Thread.sleep(5000);
        LOGGER.info("Verify the HEADER_USER_MENU_NAME");
 		test.log(LogStatus.INFO, "Verify the HEADER_USER_MENU_NAME");
        
 		if(!(Element.isElementPresent(driver,By.xpath("//div[@class='error']"))))
        {
        	Thread.sleep(5000);
        	
 		if (Element.isElementPresent(driver,By.xpath("//Span[@id='HEADER_USER_MENU_POPUP_text'][text()='"+fullName+"']"))) {
 			
 			Thread.sleep(2000);
 			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"User is Sucessfully Created");
 			test.log(LogStatus.PASS, "<font color=green>User is Sucessfully Created<font>");
 			
 			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"3");
     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"3"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();    	

 		} else {
 			LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"User is NOT Sucessfully Created");
 			test.log(LogStatus.FAIL, "<font color=RED>User is NOT Sucessfully Created<font>");
 			
 			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"3");
     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"3"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();   
           
 		}
		
      }
 		else
 		{
        
        	
        	LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"User is NOT Sucessfully Created");
  			test.log(LogStatus.FAIL, "<font color=RED>User is NOT Sucessfully Created<font>");
  			
  			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"4");
     	   	test.log(LogStatus.FAIL, "User is NOT created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"4"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();        
 		}
 		
 		Thread.sleep(3000);
        LoginPage loginPage3 = new LoginPage(driver);
        loginPage3.logout();
        Element.waitForLoad(driver);
        
        LOGGER.info("Test case createUser executed");
		test.log(LogStatus.INFO, "Test case createUser executed");
		extent.flush();
        extent.endTest(test);       
		
		

	}
	
	/**
	 * 
	 * @Test createSite
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */

	//@Parameters({"siteNameChrome", "siteIdChrome", "siteCreatorNameChrome","expectedResultChrome","isPrivateChrome", "screenShotNameChrome" })
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "createSite in Chrome",priority = 2)
    public void createSite(String siteName,String siteId,String siteCreatorName,String expectedResult,
			Boolean isPrivate,String screenShotName) throws InterruptedException, IOException
    {
    {
		 
		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Create Site "+siteName);

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("b_createSite","Create site called \" "+siteName +" \",Is it private Site "+isPrivate);

		
		LOGGER.info("Test case b_createSite started executing");
		test.log(LogStatus.INFO,
				"Test case b_createSite started executing");		

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
             createObjects.createPrivateSite(siteName, siteId,expectedResult);
			
                        WebDriver augmentedDriver1 = new Augmenter().augment(driver);
		        File screenshot1 = ((TakesScreenshot)augmentedDriver1).getScreenshotAs(OutputType.FILE);
		    	FileUtils.copyFile(screenshot1, new File(TestCaseProperties.REPORT_TEST_PATH_CHROME+className+"/"+screenShotName+"171"+".jpg"));	
		    	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"171"+".jpg"));  		
		        System.out.println("Screenshot Taken Successfully!!!!"); 
                        
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
                                
				TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"172");
	     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"172"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();  
				
			}
			else if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName))
			{	
				LOGGER.info(siteName+ " SITE IS VISIBLE TO "+siteCreatorName);
				test.log(LogStatus.INFO,"<font color=blue>"+siteName+ " SITE IS VISIBLE TO "+siteCreatorName+"<font>");
                                
				TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"172");
	     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"173"+".png"));
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
                    
                    TakeScreenShot ts=new TakeScreenShot();
             	   	ts.takeScreenShot(driver,className, screenShotName+"174");
             	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"174"+".png"));
             	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                    extent.flush();   
                    
                }
                else 
                {
                    Thread.sleep(5000);
                    if (Element.isTextPresentInList(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName)) {
                        
                        LOGGER.info(siteName+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                        test.log(LogStatus.FAIL,siteName+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                        
                        TakeScreenShot ts=new TakeScreenShot();
                 	   	ts.takeScreenShot(driver,className, screenShotName+"175");
                 	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"175"+".png"));
                 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                        extent.flush();  
                        
                        
                    } else {
                        LOGGER.info(siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER");
                        test.log(LogStatus.PASS,siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
                        TakeScreenShot ts=new TakeScreenShot();
                 	   	ts.takeScreenShot(driver,className, screenShotName+"176");
                 	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"176"+".png"));
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
     	   	ts.takeScreenShot(driver,className, screenShotName+"177");
     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"177"+".png"));
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
	     	   	ts.takeScreenShot(driver,className, screenShotName+"178");
	     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"178"+".png"));
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
	     	   	ts.takeScreenShot(driver,className, screenShotName+"179");
	     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"179"+".png"));
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
     	   	ts.takeScreenShot(driver,className, screenShotName+"180");
     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"180"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();  
			
		}
		else 
		{
			if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName)) {
				
                            LOGGER.info("Site is Display in \"Site Search\"");
                            test.log(LogStatus.PASS, "<font color=green>Site is Display in \"Site Search\"<font>");
                            
                        	TakeScreenShot ts=new TakeScreenShot();
                     	   	ts.takeScreenShot(driver,className, screenShotName+"181");
                     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"181"+".png"));
                     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                            extent.flush();  
                            
                        
			} else {
				
                            LOGGER.info("Site IS NOT Display in \"Site Search\"");
                            test.log(LogStatus.FAIL, "<font color=red>Site is NOT Display in \"Site Search\"<font>");
                            
                        	TakeScreenShot ts=new TakeScreenShot();
                     	   	ts.takeScreenShot(driver,className, screenShotName+"182");
                     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"182"+".png"));
                     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                            extent.flush();  
				
				
			}
		}
		}	
		loginPage.logout();
		LOGGER.info("Test case b_createSite executed");
		test.log(LogStatus.INFO, "Test case b_createSite executed");
                
                 extent.endTest(test);        
                extent.flush();
                
    }
		
    }
	
	/**
	 * 
	 * @Test sendSite Invitation to internalUser	 
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */
	
	
	//@Parameters({"firstNameChrome","userNameChrome","siteNameChrome", "siteIdChrome","roleNameChrome","expectedResultChrome" , "screenShotNameChrome" })
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "sendSiteInvitationtointernalUser in Chrome",priority = 3)
    public void sendSiteInvitationtointernalUser(String firstName,String userName, String siteName,String siteId,
			String roleName,String expectedResult,String screenShotName) throws InterruptedException, IOException
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

		LOGGER.info("Test case c_sendSiteInvitation started executing");
		test.log(LogStatus.INFO,
				"Test case c_sendSiteInvitation started executing");

		//Check whether Invite Option is Available For InternalUsers
		
		//InviteButton IS AVAILABLE FOR internalUsers
		SiteDashboardPage siteDashboardPage = new SiteDashboardPage(driver);
		if (siteDashboardPage.checkInternalUserInvite(siteName, className,
				screenShotName + "psiteName1")) {
			LOGGER.info("\"Search for People \" OPTION IS AVAILABLE for INTERNAL USERS ");
			test.log(LogStatus.INFO,
					"\"Search for People \" OPTION IS AVAILABLE for INTERNAL USERS ");

			LOGGER.info("SEND THE SITE INVITATION");
			test.log(LogStatus.INFO,"SEND THE SITE INVITATION");
			
			 test.log(LogStatus.INFO,"Enter "+userName+" in \"search For people Field\" ");
			 test.log(LogStatus.INFO,"Click \"Search\" Button ");
			 test.log(LogStatus.INFO,"Click \"Add>>\" Button "+userName);
			 test.log(LogStatus.INFO,"Select \""+roleName+"\" next to "+userName);
			 test.log(LogStatus.INFO,"Click \"Invite\" Button ");
			 
			 Thread.sleep(3000);
			 
			siteDashboardPage.searchPopleForInvite(userName, roleName,
					className, screenShotName + "pnew1");
			
			

			String str1 = siteDashboardPage.successNotification(className,
					screenShotName + "pe1");
			
			//ExpectedResult=[1 INVITES SENT OUT, 0 FAILURES]
			//ExpectedResult==CurrentResult[PASS]
			if (str1.toUpperCase().equals(expectedResult)) {
				LOGGER.info("Expected Results : " + expectedResult);
				test.log(LogStatus.INFO, "Expected Results : "
						+ expectedResult);

				LOGGER.info("Current Test Results : " + str1);
				test.log(LogStatus.PASS, "Current Test Results : "
						+ "<font color=green>" + str1 + "<font>");
				
				TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"183");
	     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"183"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();  
			//	Element.takescreenshot(driver, className, screenShotName
				//		+ "spvalid1");
			} 
			//ExpectedResult!=CurrentResult[FAIL]
			else {
				LOGGER.info("Expected Results : " + expectedResult);
				test.log(LogStatus.INFO, "Expected Results : "
						+ expectedResult);

				LOGGER.info("Current Test Results : " + str1);
				test.log(LogStatus.PASS, "Current Test Results : "
						+ "<font color=red>" + str1 + "<font>");
				
				TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"184");
	     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"184"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();  
				//Element.takescreenshot(driver, className, screenShotName
					//	+ "spvalid2");
			}
		} 
		//InviteButton IS NOT AVAILABLE FOR internalUsers
		else {
			LOGGER.info("\"Search for People \" OPTION IS NOT AVAILABLE for INTERNAL USERS ");
			test.log(LogStatus.INFO,
					"\"Search for People \" OPTION IS NOT AVAILABLE for INTERNAL USERS ");
			
			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"185");
     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"185"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();  
		}

		loginPage.logout();
		LOGGER.info("Test case c_sendSiteInvitation executed");
		test.log(LogStatus.INFO,"Test case c_sendSiteInvitation executed");

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
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "DeleteSite in Chrome",priority = 4)
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
 	   	ts.takeScreenShot(driver,className, screenShotName+"186");
 	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"186"+".png"));
 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
        extent.flush();  
		//Element.takescreenshot(driver, className, screenShotName+"deleteConfirmation");
		
		//CheckBox checkbox=new CheckBox(driver, By.xpath("//div[@class='name'][text()='"+item+"']/ancestor::td[1]/preceding-sibling::td[2]//input"));
		//checkbox.click();

		LOGGER.info("Check the patucular siteUrl");
		test.log(LogStatus.INFO, "Check the patucular siteUrl");
		
		if(Element.isElementPresent(driver, By.xpath("//tbody[@class='yui-dt-message']//tr//td//div[text()='No items exist']")))
		{
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+" SITE IS DELETED SUCCESSFULLY");
			
			
     	   	ts.takeScreenShot(driver,className, screenShotName+"187");
     	   	test.log(LogStatus.PASS, "Site is Deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"187"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
		}
		else if(Element.isElementPresent(driver,By.xpath("//div[text()='"+siteId+"']")))
		{
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+" SITE IS DELETED SUCCESSFULLY");
			
			
     	   	ts.takeScreenShot(driver,className, screenShotName+"A18");
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
     	   	ts2.takeScreenShot(driver,className, screenShotName+"188");
     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"188"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();  
		}
		else
		{
			LOGGER.info(siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+"SITE IS DELETED SUCCESSFULLY");
			TakeScreenShot ts3=new TakeScreenShot();
     	   	ts3.takeScreenShot(driver,className, screenShotName+"189");
     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"189"+".png"));
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
   
	
	
	/**
     * deleteUser test case
     * 
     * @throws InterruptedException
	 * @throws IOException 
     */
	
	
	//@Parameters({"firstNameChrome", "userNameChrome", "lastNameChrome","emailChrome","fullNameChrome","screenShotNameChrome" })
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Create user in Chrome",priority = 5)
	public void deleteUser(String firstName,String userName, String lastName,String email,String fullName,String screenShotName) throws InterruptedException, IOException


	{
		
		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Delete User called \" "+userName+ " \"once loginTest Over");

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("f_deleteUser","Delete User called \" "+userName+" \" once loginTest Over");

		

		LOGGER.info("Test case f_deleteUser started executing");
		test.log(LogStatus.INFO,
				"Test case f_deleteUser started executing");		

		LOGGER.info("Accessing the Login Page Again");
        test.log(LogStatus.INFO, "Accessing the Login Page Again");
        
		LOGGER.info("Login as admin");
		test.log(LogStatus.INFO, "Login as admin");

		Thread.sleep(3000);
		LoginPage loginPage1 = new LoginPage(driver);
		loginPage1.loginAsAdmin();
		
		LOGGER.info("CHECK WHETHER USER EXIST TO DELETE");
		test.log(LogStatus.INFO, "CHECK WHETHER USER EXIST TO DELETE");
		
		test.log(LogStatus.INFO, "Accessing HomePage");
		test.log(LogStatus.INFO, "Accessing Admintool page \"Users\"");
		test.log(LogStatus.INFO, "Accessing UserProfilePage");
		test.log(LogStatus.INFO, "Search User");
		test.log(LogStatus.INFO, "Enter the username "+userName);	
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		
                Element.waitForLoad(driver);
		AdminConsolePage adminConsolePage1 = new AdminConsolePage(driver);		
		if (adminConsolePage1.checkUserPresence(userName)) {
			
			test.log(LogStatus.INFO, "<font color=green>User "+firstName+" is exist <font>");
			WebDriver augmentedDriver1 = new Augmenter().augment(driver);
	        File screenshot1 = ((TakesScreenshot)augmentedDriver1).getScreenshotAs(OutputType.FILE);
	    	FileUtils.copyFile(screenshot1, new File(TestCaseProperties.REPORT_TEST_PATH_CHROME+className+"/"+screenShotName+"192"+".jpg"));	
	    	test.log(LogStatus.INFO, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"192"+".jpg"));  		
	        System.out.println("Screenshot Taken Successfully!!!!");  
	            
			test.log(LogStatus.INFO, "Accessing UserProfilePage Again");
			test.log(LogStatus.INFO, "Enter the username "+userName+" to search");
			test.log(LogStatus.INFO, "Click \"search\" button");
			test.log(LogStatus.INFO, "Click user to go inside the userprofile");		
			test.log(LogStatus.INFO, "Click \"Delete User\" Button");
			test.log(LogStatus.INFO, "Click \"Delete Confirmation\" Button");
			
                        Element.waitForLoad(driver);
			AdminConsolePage deleteUser=new AdminConsolePage(driver);                
			deleteUser.deleteUser(firstName,lastName,userName,className, screenShotName);
			
		}
		else
		{
			test.log(LogStatus.INFO, "User : " + firstName
					+ " Not Available in the System to Delete");
		}
		
		
        LOGGER.info("CHECK WHETHER USER IS DELETED OR NOT");
		test.log(LogStatus.INFO, "CHECK WHETHER USER IS DELETED OR NOT");
           
		test.log(LogStatus.INFO, "Accessing HomePage");
		test.log(LogStatus.INFO, "Accessing Admintool page \"Users\"");
		test.log(LogStatus.INFO, "Accessing UserProfilePage");
		test.log(LogStatus.INFO, "Search User");
		test.log(LogStatus.INFO, "Enter the username "+userName);	
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		
                Element.waitForLoad(driver);
		AdminConsolePage adminConsolePage2 = new AdminConsolePage(driver);
		if(adminConsolePage2.checkUserPresence(userName))
			{
				LOGGER.info("User "+firstName+" IS NOT DELETED");
		    	test.log(LogStatus.FAIL, "<font color=red>User "+firstName+" IS NOT DELETED<font>");
		    	
		    	TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"190");
	     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"190"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();  
		    	
			}
			
			else
			{
				LOGGER.info("User "+firstName+" IS DELETED SUCCESSFULLY DELETED");
		        test.log(LogStatus.PASS, "<font color=green>User "+firstName+" IS SUCCESSFULLY DELETED<font>");
		        
		        TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"191");
	     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"191"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();  
		       
			}
		loginPage1.logout();
		Element.waitForLoad(driver);
		LOGGER.info("Test case f_deleteUser executed");
		test.log(LogStatus.INFO, "Test case f_deleteUser executed");
                extent.endTest(test);        
                extent.flush();
		
		
   
		
		
    }
	
	//String firstName,String userName, String lastName,String email,String fullName,String screenShotName
	
	@DataProvider(name = "getData")
	public Object[][] provideData(Method method) {
	Object[][] result = null;

	if (method.getName().equals("createUser")) {
			result = new Object[][] {
						{ "testChrome1", "testChrome1" , "testChrom1e@gmail.com","testChrome1" ,"User@123" , "testChrome1 testChrome1", "chromeLoginTest1"} 
									};
	}else if (method.getName().equals("createSite")) {
			result = new Object[][] { 
						{ "ChromeTest", "ChromeTest", "testChrome1", "COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED",true, "ChromeLoginTest2"}
									};
	}else if (method.getName().equals("sendSiteInvitationtointernalUser")) {
			result = new Object[][] { 
						{ "testChrome1", "testChrome1", "ChromeTest", "ChromeTest", "Collaborator", "COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED",  "ChromeLoginTest3"}
									};
	}else if (method.getName().equals("DeleteSite")) {
			result = new Object[][] { 
						{ "ChromeTest", "ChromeTest", "ChromeLoginTest4"}
									};
	}else if (method.getName().equals("deleteUser")) {
			result = new Object[][] { 
						{ "testChrome1","testChrome1","testChrome1","testChrome1@gmail.com","testChrome1 testChrome1","ChromeLoginTest5"}
									};
			}
						return result;
		}
	
	
   
	@AfterMethod
	public void close() throws MalformedURLException{
		
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
	


