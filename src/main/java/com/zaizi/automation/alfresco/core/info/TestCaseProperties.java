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
package com.zaizi.automation.alfresco.core.info;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zaizi.automation.alfresco.drivers.*;

/**
 * @author cthalayasingam@zaizi.com
 * 
 */
public class TestCaseProperties {
	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(TestCaseProperties.class.getName());

	public static final String TEXT_TEST_PREPARING = "Preparing For {} ...";
	public static final String TEXT_TEST_EXECUTING = "Executing Test :  {} ...";
	public static final String TEXT_TEST_PASS = "Test Case : {} PASSED!";
	public static final String TEXT_TEST_FAIL = "Test Case : {} FAILED!";
	
	/*
	 * Define Threadsleep value
	 */
	public static final int THREAD_SLEEP_TIME_100 = 100;
	public static final int THREAD_SLEEP_TIME_200 = 200;
	public static final int THREAD_SLEEP_TIME_300 = 300;
	public static final int THREAD_SLEEP_TIME_500 = 500;
	public static final int THREAD_SLEEP_TIME_1000 = 1000;
	public static final int THREAD_SLEEP_TIME_2000 = 2000;
	public static final int THREAD_SLEEP_TIME_5000 = 5000;
	public static final int THREAD_SLEEP_TIME_10000 = 10000;

	/**
	 * Defining Alfresco Share URL
	 *//*
	public static final String HOST_URL = getPropertyValue("Host");*/
	
	/**
	 * Defining Alfresco Login Screen URL
	 */
	public static final String LOGIN_SCREEN_URL = getPropertyValue("LoginScreen");
	
	/**
	 * Defining Alfresco User Profile Page URL
	 */
	public static final String USER_PROFILE_PAGE_URL = getPropertyValue("UserProfilePage");
	
	/**
	 * Defining Alfresco Edit User Profile Page URL
	 */
	public static final String EDIT_USER_PROFILE_PAGE_URL = getPropertyValue("EditUserProfilePage");;
	
	/**
	 * Defining Alfresco Change Password Page URL
	 */
	public static final String CHANGE_PASSWORD_PAGE_URL = getPropertyValue("ChangePasswordPage");
	
	/**
	 * Defining Alfresco Admin tool Page URL
	 */
	public static final String ADMINTOOL_PAGE_URL = getPropertyValue("AdmintoolPage");
	
	/**
	 * Defining Alfresco Admin User Name URL
	 */
	public static final String ADMIN_USER_NAME = getPropertyValue("AdminUserName");
	
	/**
	 * Defining Alfresco Admin User Password
	 */
	public static final String ADMIN_PASSWORD = getPropertyValue("AdminPassword");
	
	/**
	 * Defining Alfresco Test User Name URL
	 */
	public static final String TEST_USER_NAME = getPropertyValue("TestUserName");
	
	/**
	 * Defining Alfresco Test User Password
	 */
	public static final String TEST_PASSWORD = getPropertyValue("TestPassword");
	
	/**
	 * Defining Alfresco Test Invalid Password
	 */
	public static final String INVALID_PASSWORD = getPropertyValue("InvalidPassword");

	/**
	 * Defining Browser (Firefox or Safari or Chrome)
	 */
	/*public static final String BROWSER = getPropertyValue("Browser");
	
	*//**
	 * Defining Operating System (MAC, Windows and Vista)
	 *//*
	public static final String OS = getPropertyValue("OS");*/
	/**
	 * Defining nodeURL
	 */
	public static final String CHROMENODEURL = getPropertyValue("NodeURLChrome");
	/**
	 * Defining nodeURL
	 */
	public static final String IENODEURL = getPropertyValue("NodeURLIE");
	/**
	 * Defining nodeURL
	 */
	public static final String FFNODEURL = getPropertyValue("NodeURLFF");
	
	/**
	 * Defining nodeURL
	 */
	public static final String DRIVERTYPE = getPropertyValue("DriverType");
	/**
	 * Defining Chrome driver path
	 */
	public static final String CHROME_DRIVER_PATH = getPropertyValue("ChromeDriverPath");
	
	/**
	 * Defining IE driver path
	 */
	public static final String IE_DRIVER_PATH = getPropertyValue("IEDriverPath");
		
	/**
	 * Defining Alfresco Share Admin username
	 */
	public static final String ADMIN_FIRSTNAME = getPropertyValue("AdminFirstName");
	
	/**
	 * Defining Alfresco Share Admin lastname
	 */
	public static final String ADMIN_LASTNAME = getPropertyValue("AdminLastName");
	
	/**
	 * Define Default Download Path
	 */
	public static final String DEFAULT_DOWNLOAD_PATH = getPropertyValue("DefaultDownloadPath");
	/**
	 * Defining test case properties xml path
	 */
	private static final String TEST_CASE_PROPERTIES_XML = "src/test/resources/TestProperties.xml";
	/**
	 * Defining ReportTest Path for Chrome Browser 
	 */
	public static final String REPORT_TEST_PATH_CHROME = getPropertyValue("ReportPathCHROME");
	
	/**
	 * Defining ReportTest Path Firefox
	 */
	public static final String REPORT_TEST_PATH_FF = getPropertyValue("ReportPathFF");
	
	/**
	 * Defining ReportTest Path  for IE
	 */
	public static final String REPORT_TEST_PATH_IE = getPropertyValue("ReportPathIE");
	
	/**
	 * Defining to Fail testcase report
	 */
	
	public static final String REPORT_TEST_PATH_RETRY = getPropertyValue("ReportPathRetry");
	
	/**
	 * Defining zipFile out put path
	 */
	
	public static final String OUTPUT_ZIP_FILE = getPropertyValue("ZipFileOutput");
	
	/**
	 * Defining zipFile sourceFolder Path
	 */
	
	public static final String SOURCE_FOLDER = getPropertyValue("SourceFolder");
	
	/**
	 * Defining to whom send the mail
	 */
	
	public static final String EMAIL = getPropertyValue("EmailAddress");
	
	/**
	 * Defining to screenShotPath
	 */
	
	public static final String SCREENSHOTPATH = getPropertyValue("ScreenShotPath");
	
	/**
	 * Defining to screenShotPath
	 */
	
	public static final String SCREENSHOTPATH_FF = getPropertyValue("ScreenShotPath_FF");
	
	/**
	 * Defining to screenShotPath
	 */
	
	public static final String SCREENSHOTPATH_IE = getPropertyValue("ScreenShotPath_IE");
	
	
	
	/**
	 * Defining UploadDocument Path 
	 */
	public static final String UPLOAD_DOC_PATH = getPropertyValue("UploadPath");

	/**
	 * Defining Dashlet count
	 */
	public static int dashletCount;

	/**
	 * Defining User Roles
	 */
	public static final String SITE_ROLE_MANAGER = "Manager";
	public static final String SITE_ROLE_COLLABORATOR = "Collaborator";
	public static final String SITE_ROLE_CONTRIBUTOR = "Contributor";
	public static final String SITE_ROLE_CONSUMER = "Consumer";

	

	/**
	 * Defining WebDriver
	 */
	private static WebDriver driver = null;
	public static void test() {
		System.out.println("test calld...");
	}
	/**
	 * getWebDriver method
	 * 
	 * @return
	 */
	public static WebDriver getWebDriver(String browser) {
//		FirefoxDriverStore webDrvFac;
		System.out.println("driver..." + driver);
		if (driver != null) {
			closeDriver(driver);
		}
		if ("Firefox".equals(browser)) {
//			WebDriverEventListener eventListener = new MyEventListener();
			System.out.println("Firefox calling...");
			FirefoxDriverStore webDrvFac = new FirefoxDriverStore();
			driver = webDrvFac.createWebDriver();
//			 driver = new EventFiringWebDriver(new FirefoxDriver()).register(eventListener);
//			driver = new EventFiringWebDriver(webDrvFac.createWebDriver()).register(eventListener);
		} else if ("Chrome".equals(browser)) {
//			WebDriverEventListener eventListener = new MyEventListener();
			ChromeDriverStore webDrvFac = new ChromeDriverStore(
					CHROME_DRIVER_PATH);
			driver = webDrvFac.createWebDriver();
//			driver = new EventFiringWebDriver(webDrvFac.createWebDriver()).register(eventListener);
		} else if ("Safari".equals(browser)) {
//			WebDriverEventListener eventListener = new MyEventListener();
			SafariDriverStore webDrvFac = new SafariDriverStore();
			driver = webDrvFac.createWebDriver();
//			driver = new EventFiringWebDriver(webDrvFac.createWebDriver()).register(eventListener);
		}
		else if ("IE".equals(browser)) {
//			WebDriverEventListener eventListener = new MyEventListener();
			InternetExplorerDriverStore webDrvFac = new InternetExplorerDriverStore(IE_DRIVER_PATH);
			driver = webDrvFac.createWebDriver();
//			driver = new EventFiringWebDriver(webDrvFac.createWebDriver()).register(eventListener);
		}
		return driver;
	}
	
	public static WebDriver gridDriver(String browser,String operatingSystem) throws MalformedURLException {
		/*if (driver != null) {
			closeDriver(driver);
		}*/
		if ("Firefox".equals(browser) && "MAC".equals(operatingSystem)) {
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setCapability("ignoreZoomSetting", true);
			capability.setPlatform(Platform.MAC);
			capability.setVersion("ANY");
			capability.setCapability("nativeEvents", false);
			driver = new RemoteWebDriver(new URL(FFNODEURL), capability);
		} else if ("Firefox".equals(browser) && "VISTA".equals(operatingSystem)) {
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setCapability("ignoreZoomSetting", true);
			capability.setPlatform(Platform.VISTA);
			capability.setVersion("ANY");
			capability.setCapability("nativeEvents", false);
			driver = new RemoteWebDriver(new URL(FFNODEURL), capability);
		} else if ("Firefox".equals(browser) && "WINDOWS".equals(operatingSystem)) {
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setCapability("ignoreZoomSetting", true);
			capability.setPlatform(Platform.WINDOWS);
			capability.setVersion("ANY");
			capability.setCapability("nativeEvents", false);
			driver = new RemoteWebDriver(new URL(FFNODEURL), capability);
		} else if ("Chrome".equals(browser) && "VISTA".equals(operatingSystem)) {
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setCapability("ignoreZoomSetting", true);
			capability.setPlatform(Platform.VISTA);
			capability.setVersion("ANY");
			capability.setCapability("nativeEvents", false);
			driver = new RemoteWebDriver(new URL(CHROMENODEURL), capability);
		} else if ("Chrome".equals(browser) && "MAC".equals(operatingSystem)) {
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setCapability("ignoreZoomSetting", true);
			capability.setPlatform(Platform.MAC);
			capability.setVersion("ANY");
			capability.setCapability("nativeEvents", false);
			driver = new RemoteWebDriver(new URL(CHROMENODEURL), capability);
		} else if ("Chrome".equals(browser) && "WINDOWS".equals(operatingSystem)) {
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setCapability("ignoreZoomSetting", true);
			capability.setPlatform(Platform.WINDOWS);
			capability.setVersion("ANY");
			capability.setCapability("nativeEvents", false);
			driver = new RemoteWebDriver(new URL(CHROMENODEURL), capability);
		} else if ("IE".equals(browser) && "VISTA".equals(operatingSystem) ) {
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability("ignoreZoomSetting", true);
	        capability.setPlatform(Platform.VISTA);
	        capability.setVersion("ANY");
	        capability.setCapability("nativeEvents",false);
	        capability.setCapability("name", "Remote File Upload using Selenium 2's FileDetectors");
	        driver = new RemoteWebDriver(new URL(IENODEURL), capability);
		} else if ("IE".equals(browser) && "WINDOWS".equals(operatingSystem) ) {
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability("ignoreZoomSetting", true);
	        capability.setPlatform(Platform.WINDOWS);
	        capability.setVersion("ANY");
	        capability.setCapability("nativeEvents",false);
	        driver = new RemoteWebDriver(new URL(IENODEURL), capability);
		}else if ("Safari".equals(browser) && "MAC".equals(operatingSystem) ) {
			DesiredCapabilities capability = DesiredCapabilities.safari();
			capability.setBrowserName("safari");
			capability.setCapability("ignoreZoomSetting", true);
	        capability.setPlatform(Platform.MAC);
	        capability.setVersion("ANY");
	        capability.setCapability("nativeEvents",false);
	        driver = new RemoteWebDriver(new URL(IENODEURL), capability);
		}
		return driver;
	}
	
	public static WebDriver driverType(String browser,String operatingSystem) throws MalformedURLException {
		/*if (driver != null) {
			closeDriver(driver);
		}*/
		if ("SeleniumGrid".equals(DRIVERTYPE)) {
			return gridDriver(browser, operatingSystem);
		}
		else if ("SeleniumWebDriver".equals(DRIVERTYPE)) {
			return getWebDriver(browser);
		}
		return driver;
		
		}

	/**
	 * @param oldDriver
	 */
	public static void closeDriver(WebDriver oldDriver) {
		oldDriver.close();
		driver = null;
	}

	/**
	 * @param propertyName
	 * @return
	 */
	public static String getPropertyValue(String propertyName) {
		String result = null;
		try {
			Node serverUrl = getProperty(propertyName);
			result = serverUrl.getNodeValue();
		} catch (ParserConfigurationException e) {
			LOGGER.error("ParserConfigurationException", e);
		} catch (SAXException e) {
			LOGGER.error("SAXException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		return result;
	}

	/**
	 * @param PropertyName
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static Node getProperty(String propertyName)
			throws ParserConfigurationException, SAXException, IOException {
		File testValues = new File(TEST_CASE_PROPERTIES_XML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(testValues);
		doc.getDocumentElement().normalize();
		NodeList nodes = doc.getElementsByTagName(propertyName);
		Node node = nodes.item(0);
		NodeList testdata = node.getChildNodes();
		return testdata.item(0);
	}

}
