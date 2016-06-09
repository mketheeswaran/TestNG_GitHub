package com.zaizi.automation.alfresco.testScripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zaizi.automation.alfresco.core.elements.Division;
import com.zaizi.automation.alfresco.core.elements.TakeScreenShot;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;
import com.zaizi.automation.alfresco.core.pages.Dashboard;
import com.zaizi.automation.alfresco.core.pages.LoginPage;
import com.zaizi.automation.extentReports.ExtentManagerFF;
import com.zaizi.automation.listeners.FFRetryAnalyzer;

/**
*
* @author mrashad@zaizi.com
*/


public class MainDashBoardTest_FF {

	
	/**
	 * 
	 * Defining log4j
	 */

	public static final Logger LOGGER = LogManager
			.getLogger(MainDashBoardTest_FF.class.getName());




	
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

	public static String className = MainDashBoardTest_FF.class
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
		
		extent = ExtentManagerFF.getReporter(TestCaseProperties.REPORT_TEST_PATH_FF+className+".html");
		System.out.println("Testcase started");
		

	}
	
	@BeforeMethod(alwaysRun=true)
	public static void beforemethod() throws MalformedURLException{
				//Set the DriverType(BrowserName,Platform)
				driver = TestCaseProperties.driverType("Firefox", "WINDOWS");
				
				driver.manage().window().setSize(new Dimension(1920, 1920));
				
				//Get LoginScreen_URl from TestcaseProperties Values
				driver.get(TestCaseProperties.LOGIN_SCREEN_URL);	
	}
	
	
	@Test(dataProvider="getData",retryAnalyzer=FFRetryAnalyzer.class,testName = "Add Dashlet in Firefox",priority = 1)
	public void addDashlet(String dashletTitle, String screenShotName) throws InterruptedException, IOException

	{
	
		
		LOGGER.info(TestCaseProperties.TEXT_TEST_EXECUTING, "a_addDashlet");

		//Extent Report Start Configuration(testCaseName,Definition of testCase)
    	ExtentTest test = extent.startTest("a_addDashlet","Admin for loginTest");		

			

	     LoginPage loginPage = new LoginPage(driver);
	        loginPage.loginAsAdmin();
	        Thread.sleep(2000);
		
		LOGGER.info("Test case a_addDashlet started executing");
		test.log(LogStatus.INFO,
				"Test case a_addDashlet started executing");	
    	
    	
    	Dashboard dashboard = new Dashboard(driver);
        dashboard.customizeDashboard(dashletTitle);
        
        Thread.sleep(2000);
        
        Division rssFeedBlog = new Division(
                driver,
                By.xpath("//div[@id='page_x002e_component-1-3_x002e_user_x007e_admin_x007e_dashboard_x0023_default-title']"));
        if (rssFeedBlog.getWebElement().isDisplayed())
        {
        	LOGGER.info( "Dashlet added");			
			test.log(LogStatus.PASS, "<font color=green>"
					+ "Dashlet is added" + "<font>");
			
			
			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShotFF(driver,className, screenShotName+"250");
     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"250"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();  
			
        }
        else
        {
        	LOGGER.info(" Dashlet is not added");
			test.log(LogStatus.FAIL, "<font color=green>"
					+ "Dashlet is not added" + "<font>");
			
			
			TakeScreenShot ts=new TakeScreenShot();
     	   	ts.takeScreenShotFF(driver,className, screenShotName+"251");
     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"251"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();  
			
        }
        
        
       
    }
		
	
	
	
	
		@DataProvider(name = "getData")
		public Object[][] provideData(Method method) {
		Object[][] result = null;

		if (method.getName().equals("addDashlet")) {
				result = new Object[][] {
							{"RSS Feed - Display the contents of an RSS feed given a URL configuration parameter.", "FFLoginTest15"} 
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
