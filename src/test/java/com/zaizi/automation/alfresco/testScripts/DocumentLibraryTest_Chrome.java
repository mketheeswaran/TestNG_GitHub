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
import com.zaizi.automation.alfresco.core.elements.Link;
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

public class DocumentLibraryTest_Chrome {

	/**
	 * 
	 * Defining log4j
	 */

	public static final Logger LOGGER = LogManager
			.getLogger(DocumentLibraryTest_Chrome.class.getName());
	
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

	public static String className = DocumentLibraryTest_Chrome.class
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
		LOGGER.info("Testcases Started");
		

	}
	
	@BeforeMethod(alwaysRun=true)
	public static void beforemethod() throws MalformedURLException{
				//Set the DriverType(BrowserName,Platform)
				driver = TestCaseProperties.driverType("Chrome", "WINDOWS");
				
				driver.manage().window().setSize(new Dimension(1920, 1920));
				
				//Get LoginScreen_URl from TestcaseProperties Values
				driver.get(TestCaseProperties.LOGIN_SCREEN_URL);	
	}
	
	/*@Parameters({"firstNameChrome", "lastNameChrome","emailChrome","userNameChrome","PasswordChrome","fullNameChrome","screenShotNameChrome" })*/
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
        	
            Span notification = new Span(driver, By.xpath("//div[@id='prompt']/descendant::div[@class='bd']"));
            notification.getText();
            
            LOGGER.info("Message display as : "+notification.getText());
            test.log(LogStatus.INFO, "<font color=blue>Message display as : "+notification.getText()+"<font>");
            
            TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"1");
     	   	test.log(LogStatus.INFO, "User is alredy created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"1"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();             
            
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
     	   	test.log(LogStatus.INFO, "User is created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"2"+".png"));
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
     	   	test.log(LogStatus.PASS, "User is created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"3"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();    	

 		} else {
 			LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"User is NOT Sucessfully Created");
 			test.log(LogStatus.FAIL, "<font color=RED>User is NOT Sucessfully Created<font>");
 			
 			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"3");
     	   	test.log(LogStatus.FAIL, "User is created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"3"+".png"));
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
	 * @Test createUser	 
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */

	/*@Parameters({"siteChrome", "isPrivateChrome","siteIdChrome","expectedResultChrome","siteCreatorNameChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Create site in Chrome",priority = 2)
	public void createSite(String siteName,Boolean isPrivate,String siteId,String expectedResult,String siteCreatorName,String screenShotName) throws InterruptedException, IOException

	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Create Site "+siteName);

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		 ExtentTest test = extent.startTest("Create Site","Create site called \" "+siteName +" \",Is it private Site "+isPrivate);        

		LOGGER.info("Test case Create Site started executing");
		test.log(LogStatus.INFO,
				"Test case Create Site started executing");		

		LOGGER.info("Accessing the Login Page");
        test.log(LogStatus.INFO, "Accessing the Login Page");
        
		LOGGER.info("Login as admin");
		test.log(LogStatus.INFO, "Login as admin");
		extent.flush(); 
		 
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
             extent.flush();        
             createObjects.createPrivateSite(siteName, siteId,expectedResult);
			
            TakeScreenShot ts=new TakeScreenShot();
       	   	ts.takeScreenShot(driver,className, screenShotName+"5");
       	   	test.log(LogStatus.INFO, "Site created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"5"+".png"));
       	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
            Thread.sleep(5000);
            
        //If Site Created
		if(Element.isElementPresent(driver, By.xpath("//div[@class='bd']/span[@class='wait']")))
		{	
			LOGGER.info("Site "+siteName +" CREATED ");
			test.log(LogStatus.INFO, "<font color=blue>Site "+siteName +" CREATED <font> ");	
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
    	    	LOGGER.info("Private Site "+siteName +"IS already CREATED ");
                test.log(LogStatus.INFO, "Private Site "+siteName +"IS already CREATED ");
                
	    	    LOGGER.info("Expected Results : " + expectedResult);
	        	test.log(LogStatus.INFO, "Expected Results : " + expectedResult);
	        	
	        	LOGGER.info("Current Test Results : "+siteErrorNotification);
	        	test.log(LogStatus.INFO, "Current Test Results : " +"<font color=blue>" +siteErrorNotification+"<font>");	        	

                Button okaybtn=new Button(driver,By.xpath("//button[text()='OK']"));
		        okaybtn.click(); 
		        
		        Thread.sleep(2000);
		        
		        Button okayButton=new Button(driver,By.xpath("//Button[text()='Cancel']"));
		        okayButton.click(); 	
		        extent.flush(); 
    		}
    	    //ExpectedResult1!=CurrentResult[PROBLEM]
    	    else 
    	    {
    	    	LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"Private Site "+siteName +"IS NOT CREATED ");
                test.log(LogStatus.FAIL, "Private Site "+siteName +"IS NOT CREATED ");
			
	    	    LOGGER.info("Expected Results : " + expectedResult);
	            test.log(LogStatus.FAIL, "Expected Results : " + expectedResult);
	        	
	        	LOGGER.info("Current Test Results : "+siteErrorNotification);
	        	test.log(LogStatus.FAIL, "Current Test Results : " +"<font color=red>" +siteErrorNotification+"<font>");        	
	        		
	        	 extent.flush(); 
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
             
             extent.flush(); 
             
			SearchObjects searchSite = new SearchObjects(driver);			
			searchSite.searchSite(siteName);	
			Thread.sleep(5000);
			
			if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
			{
				LOGGER.info("Message display as \"No sites found\"");
				test.log(LogStatus.INFO, "Message display as \"No sites found\"");
				
				LOGGER.info(siteName+ " SITE IS NOT VISIBLE TO "+siteCreatorName);
				test.log(LogStatus.INFO,"<font color=blue>"+siteName+ " SITE IS NOT VISIBLE TO "+siteCreatorName+"<font>");
                                
				   
		     	   	ts.takeScreenShot(driver,className, screenShotName+"6");
		     	   	test.log(LogStatus.INFO, "Site is not visible to site creator : " +test.addScreenCapture("./"+className+"/"+screenShotName+"6"+".png"));
		     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
		            extent.flush();   
				
			}
			else if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName))
			{	
				LOGGER.info(siteName+ " SITE IS VISIBLE TO "+siteCreatorName);
				test.log(LogStatus.INFO,"<font color=blue>"+siteName+ " SITE IS VISIBLE TO "+siteCreatorName+"<font>");
                                
				ts.takeScreenShot(driver,className, screenShotName+"7");
	     	   	test.log(LogStatus.INFO, "Site is visible to site creator : " +test.addScreenCapture("./"+className+"/"+screenShotName+"7"+".png"));
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
                extent.flush(); 
                
                loginPage1.loginAsUser("privateuser", "1qaz@WSX");            
                Element.waitForLoad(driver);
                Thread.sleep(5000);
                
                LOGGER.info("Check whether \"" +siteName+ "\" SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                test.log(LogStatus.INFO, "Check whether \" " +siteName+ "\" SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                extent.flush(); 
                
                SearchObjects searchSite2 = new SearchObjects(driver);
                searchSite2.searchSite(siteName);
                Element.waitForLoad(driver);       
                
                
                if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
                {
                    Thread.sleep(5000);
                    LOGGER.info("Message display as \"No sites found\"");
                    test.log(LogStatus.INFO, "Message display as \"No sites found\"");
                    
                    LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER");
                    test.log(LogStatus.PASS,siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
                    
                    ts.takeScreenShot(driver,className, screenShotName+"8");
    	     	   	test.log(LogStatus.PASS, "User is alredy created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"8"+".png"));
    	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
    	            extent.flush();  
                    
                }
                else 
                {
                    Thread.sleep(5000);
                    if (Element.isTextPresentInList(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName)) {
                        
                        LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,siteName+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                        test.log(LogStatus.FAIL,siteName+ " SITE IS VISIBLE TO UNJOIN USER \"privateuser\"");
                        
                        ts.takeScreenShot(driver,className, screenShotName+"9");
        	     	   	test.log(LogStatus.FAIL, "Site is not visible to site creator : " +test.addScreenCapture("./"+className+"/"+screenShotName+"9"+".png"));
        	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
        	            extent.flush(); 
                        
                        
                    } else {
                    	
                        LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER");
                        test.log(LogStatus.PASS,siteName+ " SITE IS NOT VISIBLE TO UNJOIN USER \"privateuser\"");
                        
                        ts.takeScreenShot(driver,className, screenShotName+"9");
        	     	   	test.log(LogStatus.PASS, "Site is visible to site creator : " +test.addScreenCapture("./"+className+"/"+screenShotName+"9"+".png"));
        	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
        	            extent.flush();
                        
                        
                    }
                }            
            
        }
		else
		{	
			LOGGER.info("CREATE PUBLIC SITE");
            test.log(LogStatus.INFO, "CREATE PUBLIC SITE");
            
			test.log(LogStatus.INFO, "Click \"Create Site\" ");
			test.log(LogStatus.INFO, "Enter " + siteName
					+ " in \"site Name Field\" ");
			test.log(LogStatus.INFO, "Enter " + siteId
					+ " in \"site URL Field\" ");
			test.log(LogStatus.INFO, "Check \"private\" Option");
			test.log(LogStatus.INFO, "Click \"OK\" Button ");
			extent.flush();
			
			createObjects.createPublicSite(siteName, siteId, expectedResult);
			Thread.sleep(5000);         
                                                
                        //If Site Created
		if(Element.isElementPresent(driver, By.xpath("//div[@class='bd']/span[@class='wait']")))
		{	
			LOGGER.info("Site "+siteName +" CREATED ");
			test.log(LogStatus.INFO, "<font color=blue>Site "+siteName +" CREATED <font> ");
                        
			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"10");
     	   	test.log(LogStatus.INFO, "Public site created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"10"+".png"));
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
	        	test.log(LogStatus.INFO, "Current Test Results : " +"<font color=green>" +siteErrorNotification+"<font>");	        	

	        	TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"11");
	     	   	test.log(LogStatus.INFO, "Public Site already created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"11"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush(); 
                        
                Button okaybtn=new Button(driver,By.xpath("//button[text()='OK']"));
		        okaybtn.click(); 
		        
		        Thread.sleep(2000);
		        
		        Button okayButton=new Button(driver,By.xpath("//button[text()='Cancel']"));
		        okayButton.click(); 
    		}
    	    //ExpectedResult1!=CurrentResult[PROBLEM]
    	    else 
    	    {
    	    	LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"Private Site "+siteName +"IS NOT CREATED ");
                test.log(LogStatus.FAIL, "Private Site "+siteName +"IS NOT CREATED ");
			
	    	    LOGGER.info("Expected Results : " + expectedResult);
	            test.log(LogStatus.INFO, "Expected Results : " + expectedResult);
	        	
	        	LOGGER.info("Current Test Results : "+siteErrorNotification);
	        	test.log(LogStatus.INFO, "Current Test Results : " +"<font color=red>" +siteErrorNotification+"<font>");        	
	        	
	        	TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"12");
	     	   	test.log(LogStatus.INFO, "Public Site not created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"12"+".png"));
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
                
                extent.flush();
                
                SearchObjects searchSite = new SearchObjects(driver);
                searchSite.searchSite(siteName);
                Thread.sleep(5000);
                
		if(Element.isElementPresent(driver,By.xpath("//Span[text()='No sites found']"))) 
		{
			LOGGER.info("Message display as \"No sites found\"");
			test.log(LogStatus.INFO, "Message display as \"No sites found\"");
                        
			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"13");
     	   	test.log(LogStatus.INFO, "Site is not visible in search : " +test.addScreenCapture("./"+className+"/"+screenShotName+"13"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
			
		}
		else 
		{
			if (Element.isTextPresentInListForSite(driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '" + siteName+ "')]")), siteName)) {
				
               LOGGER.info("Site is Display in \"Site Search\"");
               test.log(LogStatus.PASS, "<font color=green>Site is Display in \"Site Search\"<font>");
                            
               TakeScreenShot ts=new TakeScreenShot();
        	   	ts.takeScreenShot(driver,className, screenShotName+"14");
        	   	test.log(LogStatus.PASS, "Site is visible in search : " +test.addScreenCapture("./"+className+"/"+screenShotName+"14"+".png"));
        	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
               extent.flush(); 
                            
                        
			} else {
				
                            LOGGER.info("Site IS NOT Display in \"Site Search\"");
                            test.log(LogStatus.FAIL, "<font color=red>Site is NOT Display in \"Site Search\"<font>");
                            
                            TakeScreenShot ts=new TakeScreenShot();
                    	   	ts.takeScreenShot(driver,className, screenShotName+"14");
                    	   	test.log(LogStatus.FAIL, "Site is not visible in search : " +test.addScreenCapture("./"+className+"/"+screenShotName+"14"+".png"));
                    	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                            extent.flush(); 
				
				
			}
		}
		}	
		Thread.sleep(3000);
		LoginPage loginpage1=new LoginPage(driver);
		loginpage1.logout();
		LOGGER.info("Test case create Site executed");
		test.log(LogStatus.INFO, "Test case create Site executed");                      
        extent.flush();
        extent.endTest(test);   

	
	}
	
	/*@Parameters({"siteChrome", "documentNameChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Upload Document in Chrome",priority = 3)
	public void uploadDocument(String siteName,String documentName,String screenShotName) throws InterruptedException, IOException

	{

		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Upload Document in Chrome ");

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("Upload Document","Upload Document : "+"\""+documentName+"\"");		

		LOGGER.info("Test case Upload Document started executing");
		test.log(LogStatus.INFO,
				"Test case Upload Document started executing");		

		LOGGER.info("Accessing the Login Page");
        test.log(LogStatus.INFO, "Accessing the Login Page");        
               	 		         
        LOGGER.info("Login as \"Admin\"");
        test.log(LogStatus.INFO, "Login as \"Admin\""); 
        extent.flush();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
		Thread.sleep(3000);
		
		//Search for the Site
        LOGGER.info("Click \"Search Finder\"");
		test.log(LogStatus.INFO, "Click \"Search Finder\"");
                
        LOGGER.info("Search Site \""+siteName+"\"");
		test.log(LogStatus.INFO, "Search Site \""+siteName+"\"");
		extent.flush();
		
		SearchObjects search = new SearchObjects(driver);
		search.searchSite(siteName);
		Thread.sleep(5000);
		
		//Clicks on the Site Link
        LOGGER.info("Navigate to the " +siteName);
		test.log(LogStatus.INFO, "Navigate to the " +siteName);
		extent.flush();
		
		Link siteLink=new Link(driver, By.xpath("//h3[@class='sitename']/a[text()='"+siteName+"']"));
		siteLink.click();            
		Thread.sleep(3000); 
                
		
        //UploadDocument
        LOGGER.info("Upload the Document : " +documentName);
		test.log(LogStatus.INFO, "Upload the Document :  " +documentName);
		extent.flush();
		
		test.log(LogStatus.INFO, "Navigate To DocumentLibrary");
		test.log(LogStatus.INFO, "Click \"Upload Document\"");
		test.log(LogStatus.INFO, "Select the file \""+documentName+"\"");
		extent.flush();
                
                CreateObjects upload=new CreateObjects(driver);
                upload.uploadDocument(documentName, className, screenShotName);
                
                TakeScreenShot ts=new TakeScreenShot();
        	   	ts.takeScreenShot(driver,className, screenShotName+"15");
        	   	test.log(LogStatus.INFO, "Site is not visible in search : " +test.addScreenCapture("./"+className+"/"+screenShotName+"15"+".png"));
        	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
                extent.flush(); 
                
                
                LOGGER.info("Test case Upload Document executed");
                test.log(LogStatus.INFO, "Test case Upload Document executed");                     
                extent.flush();
                extent.endTest(test);     
        
	}
	
	/**
	 * 
	 * @Test Check whether Document is uploaded or not
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */
	
	/*@Parameters({"siteChrome", "documentNameChrome","screenShotNameChrome" })
*/	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Upload Document in Chrome",priority = 4)
	public void verifyUploadDocument(String siteName,String documentName,String screenShotName) throws InterruptedException, IOException
	{       	
    	
    	 ExtentTest test = extent.startTest("Verify Upload Document", "Verify whether document " +documentName+" uploaded or not"); 		
 		
        LOGGER.info("Test case Verify Upload Document started execution"); 
        test.log(LogStatus.INFO,"Test case Verify Upload Document started execution");
             
                 
       //Verify UploadDocument
        LOGGER.info("Verify whether document " +documentName+" uploaded or not");
       test.log(LogStatus.INFO, "Verify whether document " +documentName+" uploaded or not");     
        
 		LOGGER.info("Accessing the Login Page");
 		test.log(LogStatus.INFO, "Accessing the Login Page");
 		
 		//Login as the Admin
 		LOGGER.info("Login As Admin");
 		test.log(LogStatus.INFO, "Login As Admin");
 		
        LoginPage loginPage=new LoginPage(driver);
        loginPage.loginAsAdmin();    
		
		//Search for the Site
        LOGGER.info("Click \"Search Finder\"");
		test.log(LogStatus.INFO, "Click \"Search Finder\"");
                
        LOGGER.info("Search Site \""+siteName+"\"");
		test.log(LogStatus.INFO, "Search Site \""+siteName+"\"");
		
		SearchObjects search = new SearchObjects(driver);
		search.searchSite(siteName);
				
		//Clicks on the Site Link
        LOGGER.info("Navigate to the " +siteName);
		test.log(LogStatus.INFO, "Navigate to the " +siteName);
		Link siteLink=new Link(driver, By.xpath("//h3[@class='sitename']/a[text()='"+siteName+"']"));
		siteLink.click();         
		Thread.sleep(3000); 
                
        //Navigate to the Document Library
        LOGGER.info("Navigate To DocumentLibrary");
		test.log(LogStatus.INFO, "Navigate To DocumentLibrary");
		
        NavigateToPage navigate=new NavigateToPage(driver);
		navigate.goToDocLib();
		Thread.sleep(5000);
                
        Element currentResult1 = new Element(driver,By.xpath("//h3[@class='filename']//span//a[contains(., " +documentName+ ")]"));
		//Verify whether the uploaded file is available in the Document Library
		if(Button.isElementPresent(driver, By.xpath("//h3[@class='filename']//span//a[contains(., " +documentName+ ")]")))
		{
			Thread.sleep(5000);
		//Expected Result
		    LOGGER.info("Expected Results : " + documentName);
        	test.log(LogStatus.INFO, "Expected Results : " + documentName);
        	
        	//Current Result
        	LOGGER.info("Current Test Results : " +currentResult1.getWebElement().getText());
        	test.log(LogStatus.INFO, "Current Test Results : " + currentResult1.getWebElement().getText());
        	
        	LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"The User has uploaded the " +documentName+ " successfully.");
        	test.log(LogStatus.PASS, "The User has uploaded the " +documentName+ " successfully.");
        	 extent.flush();
        	 
        	TakeScreenShot ts=new TakeScreenShot();
    	   	ts.takeScreenShot(driver,className, screenShotName+"16");
    	   	test.log(LogStatus.PASS, "User has uploaded document : " +test.addScreenCapture("./"+className+"/"+screenShotName+"16"+".png"));
    	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
		}
		else
		{
			Thread.sleep(5000);
			//Expected Result
			LOGGER.info("Expected Results : " + documentName);
        	test.log(LogStatus.INFO, "Expected Results : " + documentName);
        	
        	//Current Result
        	LOGGER.info("Current Test Results : " +currentResult1.getWebElement().getText());
        	test.log(LogStatus.INFO, "Current Test Results : " +currentResult1.getWebElement().getText());
        	
        	LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"The User has NOT uploaded the " +documentName+ " successfully.");        	
        	test.log(LogStatus.FAIL, "The User has NOT uploaded the " +documentName+ " successfully.");
        	extent.flush();
        	 
        	TakeScreenShot ts=new TakeScreenShot();
    	   	ts.takeScreenShot(driver,className, screenShotName+"17");
    	   	test.log(LogStatus.FAIL, "User has NOT uploaded document : " +test.addScreenCapture("./"+className+"/"+screenShotName+"17"+".png"));
    	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
        	
		}
        
        loginPage.logout();
        LOGGER.info("Test case verify UploadDocument executed"); 
        test.log(LogStatus.INFO,"Test case verify Upload Document executed");
        extent.flush();
        extent.endTest(test);        
           
        
        
	}  
	
	 /**
	 * 
	 * @Test Assign the user/Group to role to manage[Document Level]
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */
    
	/*@Parameters({"siteChrome", "documentNameChrome","userNameChrome","fullNameChrome","roleNameChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Upload Document in Chrome",priority = 5)
	public void managePermission(String siteName,String documentName,String userName,String fullName,String roleName, String screenShotName) throws InterruptedException, IOException
	{    
    	 ExtentTest test = extent.startTest("Manage Permission", "Manage permisison to Document"); 		
                  
        LOGGER.info("Test case Manage Permission started execution"); 
        test.log(LogStatus.INFO,"Test case Manage Permission started execution");   		    
         
  		LOGGER.info("Accessing the Login Page");
  		test.log(LogStatus.INFO, "Accessing the Login Page");  		
  		
  		LOGGER.info("Login As Admin");
  		test.log(LogStatus.INFO, "Login As Admin");
  		extent.flush();
  		
         LoginPage loginPage=new LoginPage(driver);
         loginPage.loginAsAdmin();
         
        LOGGER.info("Give manage permission for document level");
   		test.log(LogStatus.INFO, "Give manage permission for document level");
   		
   		test.log(LogStatus.INFO, "Click \"Search Finder\"");
   		test.log(LogStatus.INFO, "Search Site \""+siteName+"\"");
   		test.log(LogStatus.INFO, "Navigate to the " +siteName);
   		test.log(LogStatus.INFO, "Navigate To DocumentLibrary");
   		test.log(LogStatus.INFO, "Click Document");   
   		test.log(LogStatus.INFO, "Click \"Manage Permissison\"");
   		test.log(LogStatus.INFO, "Click \"Inheritence Permission\"");
   		test.log(LogStatus.INFO, "Click \"Yes\" to remove inheritence");
   		test.log(LogStatus.INFO, "Click \"Add User/Group\" to add user/group");
   		test.log(LogStatus.INFO, "Search User");
   		test.log(LogStatus.INFO, "Click \"Search\"");
   		test.log(LogStatus.INFO, "Click \"Add\" next to user");
   		test.log(LogStatus.INFO, "Set the permission");
   		test.log(LogStatus.INFO, "Click \"SAVE\"");
   		extent.flush();
   		
         CreateObjects managePermission=new CreateObjects(driver);
         managePermission.managePermission(siteName, documentName, userName, roleName);
         Thread.sleep(5000);
         
        TakeScreenShot ts=new TakeScreenShot();
  	   	ts.takeScreenShot(driver,className, screenShotName+"18");
  	   	test.log(LogStatus.INFO, "Give manage permission for document level : " +test.addScreenCapture("./"+className+"/"+screenShotName+"18"+".png"));
  	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
        extent.flush(); 
	        
        loginPage.logout();
         
        LOGGER.info("CHECK THE PERMISSION LEVEL");
   		test.log(LogStatus.INFO, "CHECK THE PERMISSION LEVEL");
   		
   		LOGGER.info("Accessing the Login Page again");
  		test.log(LogStatus.INFO, "Accessing the Login Page again");
  		
  		//Login as the Admin
  		LOGGER.info("Login As Admin");
  		test.log(LogStatus.INFO, "Login As Admin");
  		 extent.flush(); 
  		 
         LoginPage loginPage1=new LoginPage(driver);
         loginPage1.loginAsAdmin();    
 		
 		//Search for the Site
        LOGGER.info("Click \"Search Finder\"");
 		test.log(LogStatus.INFO, "Click \"Search Finder\"");
                 
        LOGGER.info("Search Site \""+siteName+"\"");
 		test.log(LogStatus.INFO, "Search Site \""+siteName+"\"");
 		 extent.flush(); 
 		 
 		 SearchObjects search = new SearchObjects(driver);
 		search.searchSite(siteName);
 				
 		//Clicks on the Site Link
        LOGGER.info("Navigate to the " +siteName);
 		test.log(LogStatus.INFO, "Navigate to the " +siteName);
 		 extent.flush(); 
 		 
 		Link siteLink=new Link(driver, By.xpath("//h3[@class='sitename']/a[text()='"+siteName+"']"));
 		siteLink.click(); 		
 		Thread.sleep(3000); 
 		
 		
 	   	ts.takeScreenShot(driver,className, screenShotName+"19");
 	   	test.log(LogStatus.INFO, "Search the site : " +test.addScreenCapture("./"+className+"/"+screenShotName+"19"+".png"));
 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
        extent.flush();		 
                 
         //Navigate to the Document Library
         LOGGER.info("Navigate To DocumentLibrary");
 		test.log(LogStatus.INFO, "Navigate To DocumentLibrary");
 		 extent.flush(); 
 		 
        NavigateToPage navigate=new NavigateToPage(driver);
 		navigate.goToDocLib();
 		Thread.sleep(3000);
 		
 		//click the document
 		LOGGER.info("Click Document");
 		test.log(LogStatus.INFO, "Click Document");
 		 extent.flush(); 
 		 
 		Link document = new Link(driver, By.xpath("//span/a[text()='"+documentName+"']"));
         document.click();
         Thread.sleep(7000);
        
         //click manage Permisison
 		LOGGER.info("Click \"Manage Permissison\"");
 		test.log(LogStatus.INFO, "Click \"Manage Permissison\"");
 		 extent.flush(); 
 		 
         Span manage = new Span(driver, By.xpath("//span[contains(., 'Manage Permissions')]"));             
         manage.click(); 
         
         Element.waitForLoad(driver);
         Thread.sleep(5000);         
         
         
         if (Element.isElementPresent(driver, By.xpath("//div[text()='"+fullName+"']/ancestor::td/following-sibling::td[1]//button[text()='"+roleName+"']")))
         {
         	LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"Successfully user "+fullName+" added to role "+roleName+" in manage Permission");
      		test.log(LogStatus.PASS, "Successfully user "+fullName+" added to role "+roleName+" in manage Permission");
      		
      		
     	   	ts.takeScreenShot(driver,className, screenShotName+"20");
     	   	test.log(LogStatus.PASS, "Mange permisison was successfull : " +test.addScreenCapture("./"+className+"/"+screenShotName+"20"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
         }
         else
         {
         	LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"User "+fullName+" IS NOT added to role "+roleName+" in manage Permission");
      		test.log(LogStatus.FAIL, "User "+fullName+" IS NOT added to role "+roleName+" in manage Permission");
      		
      		
     	   	ts.takeScreenShot(driver,className, screenShotName+"21");
     	   	test.log(LogStatus.FAIL, "Mange permisison was NOT successfull : " +test.addScreenCapture("./"+className+"/"+screenShotName+"21"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
         }
   		
         loginPage1.logout();
         
         LOGGER.info("Test case manage Permission executed"); 
         test.log(LogStatus.INFO,"Test case manage Permission executed"); 
         extent.flush();
          extent.endTest(test);        
           
   		
	}
	
	 /**
		 * 
		 * @Test DeleteFolder
		 * @return
		 * 
		 * @throws Exception InterruptedException, IOException
		 */
	    
	/*@Parameters({"siteChrome", "documentNameChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Delete Document in Chrome",priority = 6)
	public void deleteDocument(String siteName,String documentName,String screenShotName) throws InterruptedException, IOException

	{
	    ExtentTest test = extent.startTest("Delete Document", "Delete Document and Validation as Admin in the Private Site");	
	       
	    LOGGER.info("Test case Delete Document started execution"); 
	    test.log(LogStatus.INFO,"Test case Delete Document started execution");	   
		
		LoginPage loginPage = new LoginPage(driver);
		LOGGER.info("Accessing the Login Page");
		test.log(LogStatus.INFO, "Accessing the Login Page");
		
		//Login as the Admin
		LOGGER.info("Login As Admin");
		test.log(LogStatus.INFO, "Login As Admin");
		 extent.flush();
		 
	    loginPage.loginAsAdmin();
		Thread.sleep(3000);
		
		SearchObjects search = new SearchObjects(driver);
		
		//Search for the Site
	    LOGGER.info("Click \"Search Finder\"");
		test.log(LogStatus.INFO, "Click \"Search Finder\"");
	            
	    LOGGER.info("Search Site \""+siteName+"\"");
		test.log(LogStatus.INFO, "Search Site \""+siteName+"\"");
		 extent.flush();
		 
		search.searchSite(siteName);
				
		//Clicks on the Site Link
	    LOGGER.info("Navigate to the " +siteName);
		test.log(LogStatus.INFO, "Navigate to the " +siteName);
		 extent.flush();
		 
		Link siteLink=new Link(driver, By.xpath("//h3[@class='sitename']/a[text()='"+siteName+"']"));
		siteLink.click();
	            
	         
		Thread.sleep(3000);             
		
		//Delete Folder
	    LOGGER.info("Delete the Document : " +documentName);
		test.log(LogStatus.INFO, "Delete the Document :  " +documentName);
	            
		test.log(LogStatus.INFO, "Navigate To DocumentLibrary");
		test.log(LogStatus.INFO, "Select Document");
		test.log(LogStatus.INFO, "Click \"Selected Items\"");
		test.log(LogStatus.INFO, "Click \"Delete\" Button");
		test.log(LogStatus.INFO, "Click \"Delete Confirmation\" Button");
		test.log(LogStatus.INFO, "Login as Admin to delete Folder in TrashCan");
		test.log(LogStatus.INFO, "Click \"My Profile\"");
		test.log(LogStatus.INFO, "Place FolderName");
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		test.log(LogStatus.INFO, "Check the patucular FolderName");
		test.log(LogStatus.INFO, "Click \"Selected Item\" Button");
		test.log(LogStatus.INFO, "Click \"Delete\" Button");
		test.log(LogStatus.INFO, "Click \"OK\" Button");
		test.log(LogStatus.INFO, "Click \"OK confirmation\" Button");
		 extent.flush();
		 
	    RemoveObjects delete=new RemoveObjects(driver);
		delete.deleteDocument(documentName);	
		Thread.sleep(3000);
	            
			TakeScreenShot ts=new TakeScreenShot();
		   	ts.takeScreenShot(driver,className, screenShotName+"22");
		   	test.log(LogStatus.INFO, "Delete Document : " +test.addScreenCapture("./"+className+"/"+screenShotName+"22"+".png"));
		   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	    extent.flush(); 
	    
	    Thread.sleep(3000);
	            loginPage.logout();
		//--- Folder Validation	
	           
	            
	            LOGGER.info("Accessing the Login Page Again");
	    		test.log(LogStatus.INFO, "Accessing the Login Page Again");
	    		
	    		//Login as the Admin
	    		LOGGER.info("Login As Admin");
	    		test.log(LogStatus.INFO, "Login As Admin");
	    		 extent.flush();
	    		 
	            LoginPage loginPage1=new LoginPage(driver);
	            loginPage1.loginAsAdmin();                
		
	            NavigateToPage navigateTo = new NavigateToPage(driver);
	    		
	    		LOGGER.info("CHECK WHETHER Document\" "+documentName+" \"IS DELETED OR NOT");
	    		test.log(LogStatus.INFO,"CHECK WHETHER document\" "+documentName+" \"IS DELETED OR NOT");
	    		navigateTo.goToHome();
	                    
	            LOGGER.error("Click \"My Profile\" Again");
	    		test.log(LogStatus.INFO, "Click \"My Profile\" Again");
	    		 extent.flush();
	    		 
	    		navigateTo.goToUserTrashCan();	
	    		
	    		LOGGER.info("Place folderUrl");
	    		test.log(LogStatus.INFO, "Place folderUrl");
	    		 extent.flush();
	    		 
	    		TextField textField = new TextField(
	    				driver,
	    				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-search-text"));
	    		textField.clearText();
	    		textField.enterText(documentName);
	    		
	    		LOGGER.info("Click \"Search\" Button");
	    		test.log(LogStatus.INFO, "Click \"Search\" Button");
	    		 extent.flush();
	    		 
	    		Button searchButton = new Button(driver,By.xpath("//button[text()='Search']"));
	    		searchButton.click();
	    		Thread.sleep(2000);
	    		
	    	
	     	   	ts.takeScreenShot(driver,className, screenShotName+"23");
	     	   	test.log(LogStatus.INFO, "Search the document : " +test.addScreenCapture("./"+className+"/"+screenShotName+"23"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush(); 

	    		LOGGER.info("Check the patucular DocumentUrl");
	    		test.log(LogStatus.INFO, "Check the patucular DocumentUrl");
	    		 extent.flush();
	    		 
	    		 if(Element.isElementPresent(driver, By.xpath("//tbody[@class='yui-dt-message']//tr//td//div[text()='No items exist']")))
	      		{
	      			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,documentName+" Document IS DELETED SUCCESSFULLY");
	      			test.log(LogStatus.PASS, documentName+" Document IS DELETED SUCCESSFULLY");
	      			
	      			
	  	     	   	ts.takeScreenShot(driver,className, screenShotName+"24");
	  	     	   	test.log(LogStatus.PASS, "Document is deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"24"+".png"));
	  	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	  	            extent.flush(); 
	      		}
	      		else if(Element.isElementPresent(driver,By.xpath("//a[text()='"+documentName+"']")))
	      		{
	      			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,documentName+" Document IS DELETED SUCCESSFULLY");
	      			test.log(LogStatus.PASS, documentName+" Document IS DELETED SUCCESSFULLY");
	      			
	      			
	      	 	   	ts.takeScreenShot(driver,className, screenShotName+"A24");
	      	 	   	test.log(LogStatus.PASS, "Document is Deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"A24"+".png"));
	      	 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	      	        extent.flush(); 
	      		}
	    		else{
	    			Element.waitUntilElementPresent(driver, By.xpath("//div//div//table//tbody//tr[contains(.,'" + documentName
	    					+ "')]//td//div"));
	    	
	    	java.util.List<WebElement> myList = driver.findElements(By
	    			.xpath("//div//div//table//tbody//tr[contains(.,'" + documentName
	    					+ "')]//td//div"));

	    	for (int i = 0; i < myList.size(); i++) {

	    		if (myList.get(i).getText().equals(documentName)) 
	    		{	
	    			Thread.sleep(2000);
	    			LOGGER.info(documentName+ "Document IS NOT DELETED");
	    			test.log(LogStatus.FAIL, documentName+" Document IS NOT DELETED");
	    			
	    			
		     	   	ts.takeScreenShot(driver,className, screenShotName+"25");
		     	   	test.log(LogStatus.FAIL, "Document is not deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"25"+".png"));
		     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
		            extent.flush(); 
	    		}
	    		else
	    		{
	    			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,documentName+" Document IS DELETED SUCCESSFULLY");
	    			test.log(LogStatus.PASS, documentName+"Document IS DELETED SUCCESSFULLY");
	    			
	    			
		     	   	ts.takeScreenShot(driver,className, screenShotName+"26");
		     	   	test.log(LogStatus.PASS, "Document is deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"26"+".png"));
		     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
		            extent.flush(); 
	    		}
	    		}
	    		
	    		}

	    		Thread.sleep(3000);
		loginPage1.logout();
		LOGGER.info("Test case Delete Document executed"); 
	            test.log(LogStatus.INFO,"Test case Delete Document executed");
	            extent.flush();
	             extent.endTest(test);        
	            
	    }
	
	/*@Parameters({"siteChrome","siteIdChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Create site in Chrome",priority = 7)
	public void deleteSite(String siteName,String siteId,String screenShotName) throws InterruptedException, IOException

	{
		 LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Delete site called \" "+siteName +" \" ");

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		 ExtentTest test = extent.startTest("Delete Site","Delete site called \" "+siteName +" \" ");
		 
		LOGGER.info("Test case Delete Site started executing");
		test.log(LogStatus.INFO,
				"Test case Delete Site started executing");		

		LOGGER.info("Accessing the Login Page");
	            test.log(LogStatus.INFO, "Accessing the Login Page");
	    
		LOGGER.info("Login as admin");
		test.log(LogStatus.INFO, "Login as admin");
		 extent.flush(); 
		 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsAdmin();
		
		LOGGER.info("Check the site,if site exist Delete the site");
		test.log(LogStatus.INFO, "Check the site,if site exist Delete the Site");
		
		 
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
	            
	            extent.flush();
	            
	            RemoveObjects deleteSite=new RemoveObjects(driver);
		       deleteSite.deleteSite(siteName,siteId);		
	            
	            LOGGER.info("CHECK WHETHER SITE\" "+siteName+" \"IS DELETED OR NOT");
	            test.log(LogStatus.INFO,"CHECK WHETHER SITE\" "+siteName+" \"IS DELETED OR NOT");
	            
	            extent.flush();
	            
	            NavigateToPage navigateTo = new NavigateToPage(driver);		
		navigateTo.goToHome();
	            
	            LOGGER.error("Click \"My Profile\" Again");
		test.log(LogStatus.INFO, "Click \"My Profile\" Again");
	            
		 extent.flush();
		 
	            test.log(LogStatus.INFO, "Accessing trashcan Page");
		navigateTo.goToUserTrashCan();	
		
		LOGGER.info("Place siteUrl");
		test.log(LogStatus.INFO, "Place siteUrl");
		
		 extent.flush();
		 
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
		   	ts.takeScreenShot(driver,className, screenShotName+"27");
		   	test.log(LogStatus.INFO, "Search Site : " +test.addScreenCapture("./"+className+"/"+screenShotName+"27"+".png"));
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
			
			
	 	   	ts.takeScreenShot(driver,className, screenShotName+"28");
	 	   	test.log(LogStatus.PASS, "Site is Deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"28"+".png"));
	 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	        extent.flush(); 
		}
		else if(Element.isElementPresent(driver,By.xpath("//div[text()='"+siteId+"']")))
		{
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+" SITE IS DELETED SUCCESSFULLY");
			
			
	 	   	ts.takeScreenShot(driver,className, screenShotName+"A28");
	 	   	test.log(LogStatus.PASS, "Site is Deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"A28"+".png"));
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
			LOGGER.error(TestCaseProperties.TEXT_TEST_PASS,siteName+ "SITE IS NOT DELETED");
			test.log(LogStatus.FAIL, siteName+" SITE IS NOT DELETED");
			
			
	 	   	ts.takeScreenShot(driver,className, screenShotName+"29");
	 	   	test.log(LogStatus.FAIL, "Site is not deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"29"+".png"));
	 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	        extent.flush(); 
		}
		else
		{
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+"SITE IS DELETED SUCCESSFULLY");
			
			ts.takeScreenShot(driver,className, screenShotName+"30");
	 	   	test.log(LogStatus.PASS, "Site is not deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"30"+".png"));
	 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	        extent.flush(); 
		}
		}
		
		}
		Thread.sleep(3000);
		LoginPage loginpage1=new LoginPage(driver);
		loginpage1.logout();
		
		LOGGER.info("Test case Delete Site executed");
		test.log(LogStatus.INFO, "Test case Delete Site executed");
		 extent.flush();
	     extent.endTest(test);        
	           

	            		
		
	}

	
	@DataProvider(name = "getData")
	public Object[][] provideData(Method method) {
	Object[][] result = null;

	if (method.getName().equals("createUser")) {
	        result = new Object[][] {
	                    { "privateuser", "privateuser" , "privateuser@gmail.com","privateuser" ,"1qaz@WSX" , "privateuser privateuser", "ChromeDocumentLibTest"} 
	                                };
	}else if (method.getName().equals("createSite")) {
	        result = new Object[][] { 
	                    { "UKDOCChrome", true, "UKDOCChromeUrl","COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED","Administrator","ChromeDocumentLibTest"}
	                                };
	}else if (method.getName().equals("uploadDocument")) {
	        result = new Object[][] { 
	                    { "UKDOCChrome", "FileA.rtf","ChromeDocumentLibTest"}
	                                };
	}else if (method.getName().equals("verifyUploadDocument")) {
        result = new Object[][] { 
                { "UKDOCChrome", "FileA.rtf","ChromeDocumentLibTest"}
                            };
	}else if (method.getName().equals("managePermission")) {
	    result = new Object[][] { 
	            { "UKDOCChrome", "FileA.rtf", "privateuser","privateuser privateuser","Site Contributor","ChromeDocumentLibTest"}
	                        };
	}else if (method.getName().equals("deleteDocument")) {
	    result = new Object[][] { 
	            { "UKDOCChrome", "FileA.rtf", "ChromeDocumentLibTest"}
	                        };
	}else if (method.getName().equals("deleteSite")) {
	    result = new Object[][] { 
	            { "UKDOCChrome", "UKDOCChromeUrl", "ChromeDocumentLibTest"}
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
