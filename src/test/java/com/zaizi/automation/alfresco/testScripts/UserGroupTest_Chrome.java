package com.zaizi.automation.alfresco.testScripts;

import java.io.IOException;
import java.lang.reflect.Method;

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
import com.zaizi.automation.alfresco.core.elements.TakeScreenShot;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;
import com.zaizi.automation.alfresco.core.pages.AdminConsolePage;
import com.zaizi.automation.alfresco.core.pages.LoginPage;
import com.zaizi.automation.alfresco.core.pages.NavigateToPage;
import com.zaizi.automation.extentReports.ExtentManagerChrome;
import com.zaizi.automation.listeners.ChromeRetryAnalyzer;

public class UserGroupTest_Chrome {

	
	/**
	 * 
	 * Defining log4j
	 */

	public static final Logger LOGGER = LogManager
			.getLogger(UserGroupTest_Chrome.class.getName());
	
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

	public static String className = UserGroupTest_Chrome.class
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
	public static void beforeClass() throws Exception

	{
		
		extent = ExtentManagerChrome.getReporter(TestCaseProperties.REPORT_TEST_PATH_CHROME+className+".html");
		LOGGER.info("Testcases Started");		
		
		

	}
	
	@BeforeMethod(alwaysRun=true)
	public static void beforemethod() throws Exception{
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

	/*@Parameters({"groupNameChrome", "groupIdChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Create Group in Chrome",priority = 1)
	public void createGroup(String groupName,String groupId,String screenShotName) throws Exception

	{
		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Create Group called\" "+groupName+" \"");

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("Create Group","Create Group called\" "+groupName+" \"");                
	
				LOGGER.info("Test case create Group started executing");
				test.log(LogStatus.INFO,
						"Test case create Group started executing");		

				LOGGER.info("Accessing the Login Page");
		                test.log(LogStatus.INFO, "Accessing the Login Page");
		        
				LOGGER.info("Login as ADMIN");
				test.log(LogStatus.INFO, "Login as ADMIN ");
				extent.flush(); 
				
				
				LoginPage loginPage = new LoginPage(driver);
				loginPage.loginAsAdmin();
				Thread.sleep(5000);
				
				LOGGER.info("Create Group");
				test.log(LogStatus.INFO, "Create Group ");
				test.log(LogStatus.INFO, "Navigate to \"Groups\" in Admin tools");
				test.log(LogStatus.INFO, "Click \"Browse\" Button");
				test.log(LogStatus.INFO, "Click \"+\" ,to create Group");
				test.log(LogStatus.INFO, "Enter the groupID as "+groupId);
				test.log(LogStatus.INFO, "Enter the group name as "+groupName);
				test.log(LogStatus.INFO, "Click \"Create\" Button");
				extent.flush(); 
				 
				AdminConsolePage createGroup=new AdminConsolePage(driver);
				createGroup.createGroup(groupName,groupId);
			
				TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShot(driver,className, screenShotName+"1");
	     	   	test.log(LogStatus.INFO, "Create Group : " +test.addScreenCapture("./"+className+"/"+screenShotName+"1"+".png"));     		
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();   
				
	            if(Element.isElementPresent(driver, By.xpath("//div[@id='prompt']//div[@class='bd']")))
	            {
	            	LOGGER.info("Group "+groupName +" is already exist");
					test.log(LogStatus.INFO, "Group "+groupName +" is already exist");
					
					
		     	   	ts.takeScreenShot(driver,className, screenShotName+"1T");
		     	   	test.log(LogStatus.INFO, "Create Group : " +test.addScreenCapture("./"+className+"/"+screenShotName+"1T"+".png"));     		
		     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
		            extent.flush();
		            
		            Button cancelBtn=new Button(driver, By.xpath("//div[@id='prompt']//div[3]//button[text()='Cancel']"));
		            cancelBtn.click();
		            
	            }
	            
	            else 
	            {
	            	LOGGER.info("Group "+groupName +" is NOT already exist");
					test.log(LogStatus.INFO, "Group "+groupName +" is NOT already exist");
					 extent.flush();
	            }
	            
	            LOGGER.info("CHECK WHETHER GROUP IS SUCESSFULLY CREATED OR NOT");
				test.log(LogStatus.INFO, "CHECK WHETHER GROUP IS SUCESSFULLY CREATED OR NOT");
				
				test.log(LogStatus.INFO, "Navigate to \"Groups\" in Admin tools");
				LOGGER.info("Navigate to \"Groups\" in Admin tools");
				extent.flush(); 
				 
				NavigateToPage navigateToPage = new NavigateToPage(driver);
				navigateToPage.goToGroups();
				Element.waitForLoad(driver);
				
				
				LOGGER.info("Enter GroupName to search");
				test.log(LogStatus.INFO, "Enter GroupName to search");
				extent.flush(); 
				 
				TextField groupSearchBar = new TextField(
						driver,
						By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-text"));
				groupSearchBar.clearText();
				groupSearchBar.enterText(groupName);
				 
				ts.takeScreenShot(driver,className, screenShotName+"2");
	     	   	test.log(LogStatus.INFO, "Group search : " +test.addScreenCapture("./"+className+"/"+screenShotName+"2"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();  
				
				LOGGER.info("Click \"Search\" Button");
				test.log(LogStatus.INFO, "Click \"Search\" Button");
				extent.flush();
				 
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
					
					LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"Group "+groupName+" IS SUCCESSFULLY CREATED");
					test.log(LogStatus.PASS, "Group "+groupName+" IS SUCCESSFULLY CREATED");
					
					ts.takeScreenShot(driver,className, screenShotName+"3");
		     	   	test.log(LogStatus.PASS, "Group is created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"3"+".png"));
		     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
		            extent.flush();  
					
				}

				else {
					
					LOGGER.info(TestCaseProperties.TEXT_TEST_FAIL,"Group "+groupName+" IS NOT SUCCESSFULLY CREATED");
					test.log(LogStatus.FAIL, "Group "+groupName+" IS NOT SUCCESSFULLY CREATED");
					
					ts.takeScreenShot(driver,className, screenShotName+"3");
		     	   	test.log(LogStatus.FAIL, "Group is NOT created : " +test.addScreenCapture("./"+className+"/"+screenShotName+"3"+".png"));
		     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
		            extent.flush();  

				}
				Thread.sleep(3000);
				loginPage.logout();
				LOGGER.info("Test case Create Group executed");
				test.log(LogStatus.INFO, "Test case Create Group executed");
				extent.flush();
		        extent.endTest(test);        
		               

	}
	
	/**
	 * 
	 * @Test editGroup 
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */
	
	/*@Parameters({"groupNameChrome", "newGroupNameChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Edit Group in Chrome",priority = 2)
	public void EditGroup(String groupName,String newGroupName,String screenShotName) throws InterruptedException, IOException

	{

		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Edit Group from\" "+groupName+" \" to group "+newGroupName);

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("Edit Group","Edit Group from\" "+groupName+" \" to group "+newGroupName);           
		
		LOGGER.info("Test case Edit Group started executing");
		test.log(LogStatus.INFO,
				"Test case Edit Group started executing");		

		LOGGER.info("Accessing the Login Page");
        test.log(LogStatus.INFO, "Accessing the Login Page");
        
		LOGGER.info("Login as ADMIN");
		test.log(LogStatus.INFO, "Login as ADMIN ");
		 extent.flush();
		 
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsAdmin();
		Thread.sleep(5000);
		
		LOGGER.info("EDIT THE GROUP NAME");
		test.log(LogStatus.INFO, "EDIT THE GROUP NAME");
		extent.flush();
		 
		LOGGER.info("Accessing AdminToolPage");
        test.log(LogStatus.INFO, "Accessing AdmintoolPage");
		test.log(LogStatus.INFO, "Navigate to \"Groups\" in admintools");
		test.log(LogStatus.INFO, "Check whether particular group is Exist or not");
		test.log(LogStatus.INFO, "Edit  GroupName from \""+groupName+"\" to \""+newGroupName+"\"");
		test.log(LogStatus.INFO, "Click \"Save\" button");
		extent.flush();
		 
		AdminConsolePage editGroup=new AdminConsolePage(driver);
		editGroup.editGroup(groupName,newGroupName);
		
		TakeScreenShot ts=new TakeScreenShot();
		ts.takeScreenShot(driver,className, screenShotName+"4");
		test.log(LogStatus.INFO, "Login Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"4"+".png"));
	    extent.flush(); 
        
        LOGGER.info("CHECK WHETHER GROUPNAME IS SUCCESSFULLY EDITED FROM \""+groupName+"\" to \""+newGroupName+"\"");
		test.log(LogStatus.INFO, "CHECK WHETHER GROUPNAME IS SUCCESSFULLY EDITED FROM \""+groupName+"\" to \""+newGroupName+"\"");
		
		test.log(LogStatus.INFO, "Navigate to \"Groups\" in admintools AGAIN");
		extent.flush();
		 
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToGroups();
		Element.waitForLoad(driver);
		
		LOGGER.info("Enter GroupName to search");
		test.log(LogStatus.INFO, "Enter GroupName to search");
		extent.flush();
		 
		TextField groupSearchBar = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-text"));
		groupSearchBar.clearText();
		groupSearchBar.enterText(newGroupName);		 
		
		   ts.takeScreenShot(driver,className, screenShotName+"5");
		   test.log(LogStatus.INFO, "Login Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"5"+".png"));
	       extent.flush(); 
		
		LOGGER.info("Click \"Search\" Button");
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		extent.flush(); 
		
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
			
			
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"Group "+groupName+" IS SUCCESSFULLY EDITED as Group "+newGroupName);
			test.log(LogStatus.PASS, "Group "+groupName+"  IS SUCCESSFULLY EDITED as Group "+newGroupName);	
			
			ts.takeScreenShot(driver,className, screenShotName+"6");
			   test.log(LogStatus.PASS, "Group successfully edited: " +test.addScreenCapture("./"+className+"/"+screenShotName+"6"+".png"));
		       extent.flush();
		}

		else {
			LOGGER.info(TestCaseProperties.TEXT_TEST_FAIL,"Group "+groupName+" IS NOT SUCCESSFULLY EDITED as Group "+newGroupName);
			test.log(LogStatus.FAIL, "Group "+groupName+" IS NOT SUCCESSFULLY EDITED as Group "+newGroupName);
			
				ts.takeScreenShot(driver,className, screenShotName+"7");
			   test.log(LogStatus.FAIL, "Group is not successfully edited: " +test.addScreenCapture("./"+className+"/"+screenShotName+"7"+".png"));
		       extent.flush();
		}
		Thread.sleep(3000);
		loginPage.logout();
		
		LOGGER.info("Test case Edit Group executed");
		test.log(LogStatus.INFO, "Test case Edit Group executed");                       
        extent.flush();
        extent.endTest(test); 

	}
	
	/**
	 * 
	 * @Test removeGroup 
	 * @return
	 * 
	 * @throws Exception InterruptedException, IOException
	 */
	
	/*@Parameters({"newGroupNameChrome","screenShotNameChrome" })*/
	@Test(dataProvider="getData",retryAnalyzer=ChromeRetryAnalyzer.class,testName = "Remove Group in Chrome",priority = 3)
	public void removeGroup(String newGroupName,String screenShotName) throws InterruptedException, IOException

	{

		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "Remove Group"+newGroupName);

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
		ExtentTest test = extent.startTest("Remove Group","Remove Group"+newGroupName);  
		
		LOGGER.info("Test case Remove Group started executing");
		test.log(LogStatus.INFO,
				"Test case Remove Group started executing");		

		LOGGER.info("Accessing the Login Page");
                test.log(LogStatus.INFO, "Accessing the Login Page");
        
		LOGGER.info("Login as ADMIN");
		test.log(LogStatus.INFO, "Login as ADMIN ");
		extent.flush(); 
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsAdmin();
		Thread.sleep(5000);
		
		LOGGER.info("REMOVE THE GROUP");
		test.log(LogStatus.INFO, "REMOVE THE GROUP");
		extent.flush(); 
		
		test.log(LogStatus.INFO, "Accessing AdmintoolPage");
		test.log(LogStatus.INFO, "Navigate to \"Groups\" in admintoolpage");
		test.log(LogStatus.INFO, "Search the groupname");
		test.log(LogStatus.INFO, "Check whether particular group is Exist or not");
		test.log(LogStatus.INFO, "Remove  Group\""+newGroupName+"\"");
		test.log(LogStatus.INFO, "Click \"Delete\" Button");
		extent.flush(); 
		
		AdminConsolePage removeGroup=new AdminConsolePage(driver);
		removeGroup.removeGroup(newGroupName);
		
		   TakeScreenShot ts=new TakeScreenShot();
		   ts.takeScreenShot(driver,className, screenShotName+"8");
		   test.log(LogStatus.INFO, "Remove Group Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"8"+".png"));
	       extent.flush(); 
                
        LOGGER.info("CHECK WHTHER GROUP "+newGroupName+"IS SUCESSFULLY DELETED OR NOT");
		test.log(LogStatus.INFO, "CHECK WHTHER GROUP "+newGroupName+" IS SUCESSFULLY DELETED OR NOT");
		
		test.log(LogStatus.INFO, "Accessing AdmintoolPage");
		test.log(LogStatus.INFO, "Navigate to \"Groups\" in admintoolpage");
		extent.flush(); 
		
		NavigateToPage navigateToPage = new NavigateToPage(driver);
		navigateToPage.goToGroups();
		Element.waitForLoad(driver);
		
		LOGGER.info("Enter GroupName to search");
		test.log(LogStatus.INFO, "Enter GroupName to search");
		extent.flush(); 
		
		TextField groupSearchBar = new TextField(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-text"));
		groupSearchBar.clearText();
		groupSearchBar.enterText(newGroupName);
		 
		
		   ts.takeScreenShot(driver,className, screenShotName+"9");
		   test.log(LogStatus.INFO, "Group Search below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"9"+".png"));
	       extent.flush(); 
		
		LOGGER.info("Click \"Search\" Button");
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		 extent.flush();
		 
		Button searchButton = new Button(
				driver,
				By.id("page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button"));
		searchButton.click();
		Element.waitForLoad(driver);
		Thread.sleep(3000);
		 
		
		if (Element.isTextPresentInListForGroup(
				driver.findElements(By
						.xpath("//tbody//tr//td//div[contains(., '" + newGroupName
								+ "')]")), newGroupName)) {
			
			LOGGER.error(TestCaseProperties.TEXT_TEST_FAIL,"Group "+newGroupName+" IS NOT SUCCESSFULLY Removed");
			test.log(LogStatus.FAIL, "Group "+newGroupName+" IS NOT SUCCESSFULLY Removed");	
			
			
			   ts.takeScreenShot(driver,className, screenShotName+"10");
			   test.log(LogStatus.FAIL, "Group is not successfully DELETE: " +test.addScreenCapture("./"+className+"/"+screenShotName+"10"+".png"));
		       extent.flush(); 
		       
			
		}

		else {
			
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,"Group "+newGroupName+" IS SUCCESSFULLY Removed");
			test.log(LogStatus.PASS, "Group "+newGroupName+" IS SUCCESSFULLY Removed");
			
			
			   ts.takeScreenShot(driver,className, screenShotName+"11");
			   test.log(LogStatus.PASS, "Group is successfully removed: " +test.addScreenCapture("./"+className+"/"+screenShotName+"11"+".png"));
		       extent.flush(); 
			
		}
				Thread.sleep(3000);
                loginPage.logout();
		
		
		LOGGER.info("Test case Remove Group executed");
		test.log(LogStatus.INFO, "Test case Remove Group executed");                    
                extent.flush();
                extent.endTest(test);    
	}
	
	
	@DataProvider(name = "getData")
    public Object[][] provideData(Method method) {
    Object[][] result = null;

    if (method.getName().equals("createGroup")) {
            result = new Object[][] {
                        { "GroupChrome", "GroupChromeID" , "ChromeGroupUserTest"} 
                                    };
    }else if (method.getName().equals("EditGroup")) {
            result = new Object[][] { 
                        { "GroupChrome", "newGroupChrome","ChromeGroupUserTest"}
                                    };
    }else if (method.getName().equals("removeGroup")) {
            result = new Object[][] { 
                        { "newGroupChrome","ChromeGroupUserTest"}
                                    };
    }
                        return result;
        }
	
	@AfterMethod
	   public void close() throws Exception{
		
		
		driver.quit(); 
		
	   }

	@AfterTest(alwaysRun=true)
	public void extent() throws Exception {
		
		
		LOGGER.info("Test case closed");
		extent.close();	
		driver.quit();
		

	} 

	
}
