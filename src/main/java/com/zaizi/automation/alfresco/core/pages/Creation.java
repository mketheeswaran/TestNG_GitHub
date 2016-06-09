package com.zaizi.automation.alfresco.core.pages;

import java.awt.AWTException;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.Link;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;






public class Creation 
{

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager.getLogger(Creation.class
			.getName());

	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	
	/**
	 * @param driver
	 */
	
	public Creation(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	
	
	public void goToSiteSearch() throws InterruptedException 
	{
		Button sitesButton = new Button(driver, By.id("HEADER_SITES_MENU_text"));
		sitesButton.click();
		
		Thread.sleep(3000);
		
		Link searchSites = new Link(driver, By.id("HEADER_SITES_MENU_SITE_FINDER_text"));
		searchSites.click();
		
		Element.waitForLoad(driver);
	}
	
	/**
	 * Navigate to "site Search"	 
	 * @throws InterruptedException	 
	 */
	public void siteSearch(String siteName) throws InterruptedException 
	{
		TextField searchText = new TextField(driver, By.xpath("//input[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-term']"));
		searchText.enterText(siteName);
		
		Thread.sleep(3000);
		
		Button searchButton = new Button(driver, By.id("template_x002e_site-finder_x002e_site-finder_x0023_default-button-button"));
		searchButton.click();
	}
	
	/**
	 * If Site Exist In System,Click The Particular Site
	 * 
	 * @param siteName
	 *            : Name of the site	 
	 * @throws InterruptedException	  
	 */
	public void goToSite(String siteName) throws InterruptedException 
	{
		java.util.List<WebElement> mySiteList = driver.findElements(By.xpath("//tbody//tr//td//div//h3//a[contains(., '"+siteName+ "')]"));
		
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
	}
	
	/**
	 * Navigates to the Document Library
	 */
	public void gotoDocumentLibrary() 
	{
		Link fileplanlink = new Link(driver, By.xpath("//span[@id='HEADER_SITE_DOCUMENTLIBRARY_text']//a"));
		//Link fileplanlink = new Link(driver, By.xpath("//span[@id='HEADER_SITE_DOCUMENTLIBRARY_text']//a[@tabindex='-1']"));
		fileplanlink.click();		
	}
	
	/**
	 * Creates the Folder
	 * @param folName
	 */
	public void createFolder(String folName)
	{		
		Button createbutton = new Button(driver, By.id("template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-createContent-button-button"));
		createbutton.click();
		
		Link createFolder = new Link(driver, By.xpath("//div//ul[@class='first-of-type']//li[1]//a//span[@class='folder-file']"));
		createFolder.click();
		
		TextField folderName = new TextField(driver, By.xpath("//input[@id='template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-createFolder_prop_cm_name']"));
		folderName.enterText(folName);
		
		TextField folderTitle = new TextField(driver, By.xpath("//input[@id='template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-createFolder_prop_cm_title']"));
		folderTitle.enterText(folName);
		
		Button createCat = new Button(driver, By.id("template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-createFolder-form-submit-button"));
		createCat.click();
		
		System.out.println("Folder Created Successfully");
	}
	
	
	/**
	 * Uploads the Files
	 * @param documentName
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void uploadElectronicFile(String documentName) throws InterruptedException, AWTException
	{
		Button uploadButton = new Button(driver, By.id("template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-fileUpload-button-button"));
		uploadButton.click();
			
		Button selfile = new Button(driver, By.xpath("//span[@id='template_x002e_dnd-upload_x002e_documentlibrary_x0023_default-file-selection-button-overlay']//span"));
		selfile.click();
		
		WebElement browseButton1 = driver.findElement(By.xpath("//span//input[@class='dnd-file-selection-button']"));
        
        LocalFileDetector detector = new LocalFileDetector();      
        String path = TestCaseProperties.UPLOAD_DOC_PATH+documentName+"/";      
        File f = detector.getLocalFile(path);
        ((RemoteWebElement)browseButton1).setFileDetector(detector);
        browseButton1.sendKeys(f.getAbsolutePath());       
        Element.waitForLoad(driver);
        Thread.sleep(1000);
	}
	
	
	    
	   
	}

