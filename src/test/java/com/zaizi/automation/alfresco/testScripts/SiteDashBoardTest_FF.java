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
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.TakeScreenShot;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;
import com.zaizi.automation.alfresco.core.pages.Dashboard;
import com.zaizi.automation.alfresco.core.pages.LoginPage;
import com.zaizi.automation.alfresco.core.pages.NavigateToPage;
import com.zaizi.automation.alfresco.core.pages.RemoveObjects;

import com.zaizi.automation.extentReports.ExtentManagerFF;
import com.zaizi.automation.listeners.FFRetryAnalyzer;


/**
*
* @author mrashad@zaizi.com
*/




public class SiteDashBoardTest_FF {

	
	
	/**
	 * 
	 * Defining log4j
	 */

	public static final Logger LOGGER = LogManager
			.getLogger(SiteDashBoardTest_FF.class.getName());


	

	
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

	public static String className = SiteDashBoardTest_FF.class
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
	
	
	@Test(dataProvider="getData",retryAnalyzer=FFRetryAnalyzer.class,testName = "Add Site Dashlet in Firefox",priority = 1)
	public void addSiteDashlet(String siteName, String siteURL, String dashletTitle, String screenShotName) throws InterruptedException, IOException

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
	    	
	    	
	    	
		 
		 
	        System.out.println("running addDashlet test case");
	        
	        Dashboard dashboard = new Dashboard(driver);
	        
	        
			 LOGGER.info("Create Public Site");

				//Extent Report Start Configuration(testCaseName,Definition of testCase)
				extent.startTest("a_addDashlet","Create Public Site");
	        
	        dashboard.createPublicSite(siteName);
	        Element.waitForLoad(driver);
	        Thread.sleep(10000);
	        
	        LOGGER.info("Customize site dashlet");

			//Extent Report Start Configuration(testCaseName,Definition of testCase)
			extent.startTest("a_addDashlet","Customize site dashlet");
       
	        
	        dashboard.customizeSiteDashboard1(dashletTitle);
	        
	        LOGGER.info("View site dashlet");

			//Extent Report Start Configuration(testCaseName,Definition of testCase)
			extent.startTest("a_addDashlet","View site dashlet");
	        
//			Thread.sleep(10000);
//			
//			
//			Link siteDashlet = new Link(driver, By.xpath("//a[text()='FFTest']//ancestor::h1[@id='HEADER_TITLE']"));
//			siteDashlet.click();
			
	        
	        Thread.sleep(5000);
	        
	       /* Division rssFeedBlog = new Division(
	                driver,
	                By.xpath("//div[@id='page_x002e_component-1-2_x002e_site_x007e_"+siteURL+"_x007e_dashboard_x0023_default-title']"));
	        if (rssFeedBlog.getWebElement().isDisplayed())
	        {*/
	        if(Element.isElementPresent(driver, By.xpath("//div[@id='page_x002e_component-1-2_x002e_site_x007e_"+siteURL+"_x007e_dashboard_x0023_default-title']"))){
	        	LOGGER.info( "Dashlet added");			
				test.log(LogStatus.PASS, "<font color=green>"
						+ "Dashlet is added" + "<font>");
				
				
				TakeScreenShot ts=new TakeScreenShot();
	     	   	ts.takeScreenShotFF(driver,className, screenShotName+"250");
	     	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"105"+".png"));
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
	     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"105"+".png"));
	     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
	            extent.flush();  
				
	        }
	        
	      Thread.sleep(5000);
	      
	        
//	        dashboard.searchSite(siteName);
//	       
//	        dashboard.deleteSite();
	        TestCaseProperties.closeDriver(driver);
	    }
	
	
	@Test(dataProvider="getData",retryAnalyzer=FFRetryAnalyzer.class,testName = "DeleteSite in Firefox",priority = 2)
    public void DeleteSite(String siteName,String siteURL,
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
		deleteSite.deleteSite(siteName, siteURL);		
                
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
		textField.enterText(siteURL);
		
		LOGGER.info("Click \"Search\" Button");
		test.log(LogStatus.INFO, "Click \"Search\" Button");
		Button searchButton = new Button(driver,By.xpath("//button[text()='Search']"));
		searchButton.click();
		Thread.sleep(2000);
                
		TakeScreenShot ts=new TakeScreenShot();
 	   	ts.takeScreenShotFF(driver,className, screenShotName+"210");
 	   	test.log(LogStatus.PASS, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"210"+".png"));
 	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
        extent.flush();  
		//Element.takeScreenShotFF(driver, className, screenShotName+"deleteConfirmation");
		
		//CheckBox checkbox=new CheckBox(driver, By.xpath("//div[@class='name'][text()='"+item+"']/ancestor::td[1]/preceding-sibling::td[2]//input"));
		//checkbox.click();

		LOGGER.info("Check the patucular siteUrl");
		test.log(LogStatus.INFO, "Check the patucular siteUrl");
		
		if(Element.isElementPresent(driver, By.xpath("//tbody[@class='yui-dt-message']//tr//td//div[text()='No items exist']")))
		{
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+" SITE IS DELETED SUCCESSFULLY");
			
			
     	   	ts.takeScreenShotFF(driver,className, screenShotName+"211");
     	   	test.log(LogStatus.PASS, "Site is Deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"211"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
		}
		else if(Element.isElementPresent(driver,By.xpath("//div[text()='"+siteURL+"']")))
		{
			LOGGER.info(TestCaseProperties.TEXT_TEST_PASS,siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+" SITE IS DELETED SUCCESSFULLY");
			
			
     	   	ts.takeScreenShotFF(driver,className, screenShotName+"A18");
     	   	test.log(LogStatus.PASS, "Site is Deleted : " +test.addScreenCapture("./"+className+"/"+screenShotName+"A18"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush(); 
		}		
		else{
			Element.waitUntilElementPresent(driver, By.xpath("//div//div//table//tbody//tr[contains(.,'" + siteURL
					+ "')]//td//div"));
	
	java.util.List<WebElement> myList = driver.findElements(By
			.xpath("//div//div//table//tbody//tr[contains(.,'" + siteURL
					+ "')]//td//div"));

	for (int i = 0; i < myList.size(); i++) {

		if (myList.get(i).getText().equals(siteURL)) 
		{	
			Thread.sleep(2000);
			LOGGER.info(siteName+ "SITE IS NOT DELETED");
			test.log(LogStatus.FAIL, siteName+" SITE IS NOT DELETED");
			TakeScreenShot ts2=new TakeScreenShot();
     	   	ts2.takeScreenShotFF(driver,className, screenShotName+"212");
     	   	test.log(LogStatus.FAIL, "Snapshot below: " +test.addScreenCapture("./"+className+"/"+screenShotName+"212"+".png"));
     	   	LOGGER.info("Screenshot Taken Successfully!!!!");  
            extent.flush();  
		}
		else
		{
			LOGGER.info(siteName+" SITE IS DELETED SUCCESSFULLY");
			test.log(LogStatus.PASS, siteName+"SITE IS DELETED SUCCESSFULLY");
			TakeScreenShot ts3=new TakeScreenShot();
     	   	ts3.takeScreenShotFF(driver,className, screenShotName+"213");
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
	
	
	
	
			@DataProvider(name = "getData")
			public Object[][] provideData(Method method) {
			Object[][] result = null;

			if (method.getName().equals("addSiteDashlet")) {
					result = new Object[][] {
								{"FFTest","fftest","RSS Feed - Display the contents of an RSS feed given a URL configuration parameter.", "FFLoginTest20"} 
											};
						}else if (method.getName().equals("DeleteSite")) {
							result = new Object[][] { 
									{ "FFTest", "fftest", "FFLoginTest21"}
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
