package com.zaizi.automation.alfresco.testScripts;


import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
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
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;
import com.zaizi.automation.alfresco.core.pages.AdminConsolePage;
import com.zaizi.automation.alfresco.core.pages.LoginPage;
import com.zaizi.automation.alfresco.core.pages.UserDashboardPage;
import com.zaizi.automation.extentReports.ExtentManagerChrome;
import com.zaizi.automation.listeners.ChromeRetryAnalyzer;

public class UserTest_Chrome {

	/**
	 * 
	 * Defining log4j
	 */

	public static final Logger LOGGER = LogManager
			.getLogger(UserTest_Chrome.class.getName());
	
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

	public static String className = UserTest_Chrome.class
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
	
	/**
	 * 
	 * @Test createUser	 
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */

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
        	Thread.sleep(5000);
            Span notification = new Span(driver, By.xpath("//div[@id='prompt']/descendant::div[@class='bd']"));
            notification.getText();
            
            LOGGER.info("Message display as : "+notification.getText());
            test.log(LogStatus.INFO, "<font color=blue>Message display as : "+notification.getText()+"<font>");
            
            TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShot(driver,className, screenShotName+"1");
     	   	test.log(LogStatus.INFO, "User is alredy created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"1"+".png"));
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
	 * @Test editUser	 
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */

	/*@Parameters({"userNameChrome","PasswordChrome","newFirstNameChrome","lastNameChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Edit User in Chrome",priority = 2)
	public void EditUser(String userName,String password,String newFirstName,String lastName,String screenShotName) throws InterruptedException, IOException

	{

		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Edit User Details "+userName);

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		 ExtentTest test = extent.startTest("Edit User","Edit User Details "+userName);    

		LOGGER.info("Test case Edit User started executing");
		test.log(LogStatus.INFO,"Test case Edit User started executing");		

		LOGGER.info("Accessing the Login Page");
        test.log(LogStatus.INFO, "Accessing the Login Page");	
        extent.flush();
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsUser(userName, password);
		
        test.log(LogStatus.INFO, "Click \"My Profile\"");
        test.log(LogStatus.INFO, "Click \"Edit Profile\"");
        test.log(LogStatus.INFO, "Change the User FirstName");
		test.log(LogStatus.INFO, "Click \"Save\" to save the changes");
		extent.flush();
		
        UserDashboardPage editUser=new UserDashboardPage(driver);
		editUser.editUserFirstName(newFirstName);
		
		   TakeScreenShot ts=new TakeScreenShot();
		   ts.takeScreenShot(driver,className, screenShotName+"5");
		   test.log(LogStatus.INFO, "Edited profile below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"5"+".png"));
	       extent.flush();
                
	     Thread.sleep(3000);      
        loginPage.logout();
		
		LOGGER.info("Accessing the Login Page Again");
        test.log(LogStatus.INFO, "Accessing the Login Page Again");
        extent.flush();
        
		loginPage.loginAsUser(userName, password);
                
                LOGGER.info("Check whether User FirstName/LastName is Edited or Not");
                test.log(LogStatus.INFO, "Check whether User FirstName/LastName is Edited or Not");
                
			Element element = new Element(driver,By.id("HEADER_USER_MENU_POPUP_text"));	
			extent.flush();
			
		String name;
		
		if(lastName.isEmpty())
		{
			name = newFirstName;
		}
		else
		{
		name = newFirstName + " " + lastName;
		}
		
		if (Element.isTextPresentInElement(element, name)) {
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"User firstName is successfully edited");
			test.log(LogStatus.PASS, "User firstName is successfully edited");
			
			   ts.takeScreenShot(driver,className, screenShotName+"6");
			   test.log(LogStatus.INFO, "Login Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"6"+".png"));
		       extent.flush();
			

		} else {
			LOGGER.info(TestCaseProperties.TEXT_TEST_FAIL,"User firstName IS NOT successfully edited");
			test.log(LogStatus.FAIL, "User firstName IS NOT successfully edited");
			
			 	ts.takeScreenShot(driver,className, screenShotName+"7");
			   test.log(LogStatus.INFO, "Login Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"7"+".png"));
		       extent.flush();

		}
		Thread.sleep(3000);
		loginPage.logout();
		
		LOGGER.info("Test case Edit User executed");
		test.log(LogStatus.INFO, "Test case Edit User executed");                   
        extent.flush();
        extent.endTest(test);      

	}
	
	/**
     * deleteUser test case
     * 
     * @throws InterruptedException
	 * @throws IOException 
     */
/*	@Parameters({"userNameChrome","newFirstNameChrome", "lastNameChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Delete User in Chrome",priority = 3)
    public void deleteUser(String userName,String newFirstName,String lastName,String screenShotName) throws InterruptedException, IOException
    {
    	LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Delete User called \" "+userName+ " \"");

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		 ExtentTest test = extent.startTest("Delete User","Delete User called \" "+userName+" \"");
		
		LOGGER.info("Test case Delete User started executing");
		test.log(LogStatus.INFO,
				"Test case Delete User started executing");		

		LOGGER.info("Accessing the Login Page Again");
        test.log(LogStatus.INFO, "Accessing the Login Page Again");
        
		LOGGER.info("Login as admin");
		test.log(LogStatus.INFO, "Login as admin");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsAdmin();
		
		LOGGER.info("CHECK WHETHER USER EXIST TO DELETE");
		test.log(LogStatus.INFO, "CHECK WHETHER USER EXIST TO DELETE");
		
		test.log(LogStatus.INFO, "Accessing HomePage");
		test.log(LogStatus.INFO, "Accessing Admintool page \"Users\"");
		test.log(LogStatus.INFO, "Accessing UserProfilePage");
		test.log(LogStatus.INFO, "Search User");
		test.log(LogStatus.INFO, "Enter the username "+userName);	
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		
	    extent.flush();
		AdminConsolePage adminConsolePage4 = new AdminConsolePage(driver);		
		if (adminConsolePage4.checkUserPresence(userName)) {
			
			LOGGER.info("User "+userName+" is exist");
			test.log(LogStatus.INFO, "<font color=blue>User "+userName+" is exist <font>");
			
			TakeScreenShot ts=new TakeScreenShot();
			   ts.takeScreenShot(driver,className, screenShotName+"8");
			   test.log(LogStatus.INFO, "Login Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"8"+".png"));
		       extent.flush(); 
	            
			test.log(LogStatus.INFO, "Accessing UserProfilePage Again");
			test.log(LogStatus.INFO, "Enter the username "+userName+" to search");
			test.log(LogStatus.INFO, "Click \"search\" button");
			test.log(LogStatus.INFO, "Click user to go inside the userprofile");		
			test.log(LogStatus.INFO, "Click \"Delete User\" Button");
			test.log(LogStatus.INFO, "Click \"Delete Confirmation\" Button");
			
			AdminConsolePage deleteUser=new AdminConsolePage(driver);                
			deleteUser.deleteTestUser(newFirstName,lastName,userName);
			extent.flush(); 
			
		}
		else
		{
			test.log(LogStatus.INFO, "User : " + newFirstName
					+ " Not Available in the System to Delete");
			TakeScreenShot ts=new TakeScreenShot();
			   ts.takeScreenShot(driver,className, screenShotName+"9");
			   test.log(LogStatus.INFO, "Login Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"9"+".png"));
		       extent.flush(); 
		}
		
		
        LOGGER.info("CHECK WHETHER USER IS DELETED OR NOT");
		test.log(LogStatus.INFO, "CHECK WHETHER USER IS DELETED OR NOT");
           
		test.log(LogStatus.INFO, "Accessing HomePage");
		test.log(LogStatus.INFO, "Accessing Admintool page \"Users\"");
		test.log(LogStatus.INFO, "Accessing UserProfilePage");
		test.log(LogStatus.INFO, "Search User");
		test.log(LogStatus.INFO, "Enter the username "+userName);	
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		extent.flush();
		
		AdminConsolePage adminConsolePage3 = new AdminConsolePage(driver);
		if(adminConsolePage3.checkUserPresence(userName))
			{
				LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"User "+newFirstName+" IS NOT DELETED");
		    	test.log(LogStatus.FAIL, "<font color=red>User "+newFirstName+" IS NOT DELETED<font>");
		    	
		    	TakeScreenShot ts=new TakeScreenShot();
				   ts.takeScreenShot(driver,className, screenShotName+"10");
				   test.log(LogStatus.INFO, "Login Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"10"+".png"));
			       extent.flush();   
		    	
			}
			
			else
			{
				LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"User "+newFirstName+" IS DELETED SUCCESSFULLY DELETED");
		        test.log(LogStatus.PASS, "<font color=green>User "+newFirstName+" IS SUCCESSFULLY DELETED<font>");
		        
		        TakeScreenShot ts=new TakeScreenShot();
				   ts.takeScreenShot(driver,className, screenShotName+"11");
				   test.log(LogStatus.INFO, "Login Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"11"+".png"));
			       extent.flush(); 
		       
			}
		Thread.sleep(3000);
		loginPage.logout();
		
		LOGGER.info("Test case deleteUser executed");
		test.log(LogStatus.INFO, "Test case deleteUser executed");                       
                extent.flush();
                extent.endTest(test);
		
		
    }
	
	@DataProvider(name = "getData")
    public Object[][] provideData(Method method) {
    Object[][] result = null;

    if (method.getName().equals("createUser")) {
            result = new Object[][] {
                        { "test", "test" , "test@gmail.com","testChrome" ,"user@123" , "test test", "ChromeUserTest"} 
                                    };
    }else if (method.getName().equals("EditUser")) {
            result = new Object[][] { 
                        { "testChrome","user@123","testM","test","ChromeUserTest"}
                                    };
    }else if (method.getName().equals("deleteUser")) {
            result = new Object[][] { 
                        { "testChrome","testM", "test", "ChromeUserTest"}
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
