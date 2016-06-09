package com.zaizi.automation.alfresco.core.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.CheckBox;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.Link;
import com.zaizi.automation.alfresco.core.elements.Span;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;


/**
 * @author nbrahmananthan@zaizi.com
 * 
 */

public class CreateObjects {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(CreateObjects.class.getName());
	
	//public static  ExtentReports extent = ExtentReports.get(CreateObjects.class);

	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public CreateObjects(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Create a new Public Site
	 * 
	 * @param siteName  : Name of the site
	 * @param siteURL  : URL of the site
	 * @param expectedResult
	 *            : [SITE IS BEING CREATED...]Message  
	 * @param expectedResult1
	 *            : [COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED]Message                     
	 * @param className
	 *            : ClassName to Save ExtentReport
	 * @param screenShot
	 *            : Save Image as screenShot   
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void createPublicSite(String siteName, String siteURL,String expectedResult)throws InterruptedException, IOException {
		Element.waitForLoad(driver);
		
		Button sitesButton = new Button(driver, By.id("HEADER_SITES_MENU_text"));
		sitesButton.click();
		
		LOGGER.info("Click \"Create Site\" ");
        
		Link createSiteLink = new Link(driver,By.id("HEADER_SITES_MENU_CREATE_SITE_text"));
		//Element.takescreenshot(driver, className, screenShot+"createSite");
		createSiteLink.click();
		Thread.sleep(2000);
		
		LOGGER.info("Enter "+siteName+" in \"site Name Field\" ");
        
		TextField siteNameField = new TextField(driver,	By.id("alfresco-createSite-instance-title"));
		siteNameField.enterText(siteName);
		Thread.sleep(2000);
		
		LOGGER.info("Enter "+siteURL+" in \"site URL Field\" ");
        
		TextField siteURLField = new TextField(driver,By.id("alfresco-createSite-instance-shortName"));
		siteURLField.clearText();
		siteURLField.enterText(siteURL);
		Thread.sleep(2000);
		
		LOGGER.info("Check \"public\" Option");
        
		CheckBox publicOption = new CheckBox(driver,By.id("alfresco-createSite-instance-isPublic"));
		publicOption.click();
		Thread.sleep(2000);	
		
		LOGGER.info("Click \"OK\" Button ");
       
        
		Button okButton = new Button(driver,By.id("alfresco-createSite-instance-ok-button-button"));
		Thread.sleep(3000);
		//Element.takescreenshot(driver, className, screenShot+"filedsfilling");
		okButton.click();
		
		
	
	}

	/**
	 * Create a new Moderate Site
	 * 
	 * @param siteName  : Name of the site
	 * @param siteURL  : URL of the site
	 * @throws InterruptedException
	 */
	public void createModerateSite(String siteName, String siteURL)
			throws InterruptedException {
		Element.waitForLoad(driver);
		// Button sitesButton = new Button(driver,
		// By.xpath("//span[@id='HEADER_SITES_MENU_text']"));
		Button sitesButton = new Button(driver, By.id("HEADER_SITES_MENU_text"));
		sitesButton.click();
		// Link createSiteLink = new Link(driver,
		// By.xpath("//td[@id='HEADER_SITES_MENU_CREATE_SITE_text']"));
		Link createSiteLink = new Link(driver,
				By.id("HEADER_SITES_MENU_CREATE_SITE_text"));
		createSiteLink.click();
		Thread.sleep(2000);
		// TextField siteName = new TextField(driver,
		// By.xpath("//input[@id='alfresco-createSite-instance-title']"));
		TextField siteNameField = new TextField(driver,
				By.id("alfresco-rm-createSite-instance-title"));
		siteNameField.enterText(siteName);
		Thread.sleep(2000);
		TextField siteURLField = new TextField(driver,
				By.id("alfresco-rm-createSite-instance-shortName"));
		siteURLField.clearText();
		siteURLField.enterText(siteURL);
		Thread.sleep(2000);
		// Checkbox publicOption = new Checkbox(driver,
		// By.xpath("//input[@id='alfresco-rm-createSite-instance-isPublic']"));
		CheckBox publicOption = new CheckBox(driver,
				By.id("alfresco-rm-createSite-instance-isPublic"));
		publicOption.click();
		Thread.sleep(2000);
		CheckBox moderateOption = new CheckBox(driver,
				By.id("alfresco-rm-createSite-instance-isModerated"));
		moderateOption.click();
		Thread.sleep(2000);
		// Button okButton = new Button(driver,
		// By.xpath("//button[@id='alfresco-rm-createSite-instance-ok-button-button']"));
		Button okButton = new Button(driver,
				By.id("alfresco-rm-createSite-instance-ok-button-button"));
		okButton.click();
		Element.waitForLoad(driver);
	}

	/**
	 * Create a new Private Site
	 * 
	 * @param siteName  : Name of the site
	 * @param siteURL  : URL of the site
	 * @param expectedResult
	 *            : [SITE IS BEING CREATED...]Message  
	 * @param expectedResult1
	 *            : [COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED]Message                     
	 * @param className
	 *            : ClassName to Save ExtentReport
	 * @param screenShot
	 *            : Save Image as screenShot   
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void createPrivateSite(String siteName, String siteURL,String expectedResult)throws InterruptedException, IOException {
		Element.waitForLoad(driver);	
		
		Button sitesButton = new Button(driver, By.id("HEADER_SITES_MENU_text"));
		sitesButton.click();
		
		LOGGER.info("Click \"Create Site\" ");
        //extent.log(LogStatus.INFO,"Click \"Create Site\" ");
		Link createSiteLink = new Link(driver,By.id("HEADER_SITES_MENU_CREATE_SITE_text"));
		//Element.takescreenshot(driver, className, screenShot+"createprivateSite");
		createSiteLink.click();
		Thread.sleep(2000);
		
		LOGGER.info("Enter "+siteName+" in \"site Name Field\" ");
        //extent.log(LogStatus.INFO,"Enter "+siteName+" in \"site Name Field\" ");
		TextField siteNameField = new TextField(driver,By.id("alfresco-createSite-instance-title"));
		siteNameField.enterText(siteName);
		Thread.sleep(2000);
		
		LOGGER.info("Enter "+siteURL+" in \"site URL Field\" ");
       // extent.log(LogStatus.INFO,"Enter "+siteURL+" in \"site URL Field\" ");
		TextField siteURLField = new TextField(driver,By.id("alfresco-createSite-instance-shortName"));
		siteURLField.clearText();
		siteURLField.enterText(siteURL);
		Thread.sleep(2000);		
		
		LOGGER.info("Check \"private\" Option");
       // extent.log(LogStatus.INFO,"Check \"private\" Option");
		CheckBox privateOption = new CheckBox(driver,By.id("alfresco-createSite-instance-isPrivate"));
		privateOption.click();
		Thread.sleep(2000);
		
		LOGGER.info("Click \"OK\" Button ");
        //extent.log(LogStatus.INFO,"Click \"OK\" Button ");
        
		Button okButton = new Button(driver,By.id("alfresco-createSite-instance-ok-button-button"));
		//Element.takescreenshot(driver, className, screenShot+" privatefiledsfilling");
		okButton.click();
		Element.waitForLoad(driver);
		
		
	
	}
       
	
	
	/**
	 * Create Folder
	 * 
	 * @param siteName  : Name of the site
	 * @param siteURL  : URL of the site
	 * @param expectedResult
	 *            : [SITE IS BEING CREATED...]Message  
	 * @param expectedResult1
	 *            : [COULD NOT CREATE SITE SINCE THE URL IS ALREADY USED]Message                     
	 * @param className
	 *            : ClassName to Save ExtentReport
	 * @param screenShot
	 *            : Save Image as screenShot   
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	
	
        public void createFolder(String folName,String folTitle) throws InterruptedException, IOException
	{	             
		//Navigate to the Document Library
                LOGGER.info("Navigate To DocumentLibrary");
		
                NavigateToPage navigate=new NavigateToPage(driver);
		navigate.goToDocLib();
		Thread.sleep(3000);
                
                LOGGER.info("Click \"Create\" Button");
		
		Button createbutton = new Button(driver, By.id("template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-createContent-button-button"));
		createbutton.click();
		
                LOGGER.info("Click \"Folder\"");
		
		Link createFolder = new Link(driver, By.xpath("//div//ul[@class='first-of-type']//li[1]//a//span[@class='folder-file']"));
		createFolder.click();
		
                LOGGER.info("Enter the Name of the Folder");
		
		TextField folderName = new TextField(driver, By.xpath("//input[@id='template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-createFolder_prop_cm_name']"));
		folderName.enterText(folName);
		
                LOGGER.info("Enter the title of the Folder");
		
		TextField folderTitle = new TextField(driver, By.xpath("//input[@id='template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-createFolder_prop_cm_title']"));
		folderTitle.enterText(folTitle);
		
		Button createCat = new Button(driver, By.id("template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-createFolder-form-submit-button"));
		LOGGER.info("Click \"Save\" Button");
		createCat.click();
       // Element.takescreenshot(driver, className, screenShotName);
           
		Element.waitForLoad(driver);
                
		
	}
        
        /**
    	 * uploadDocument
    	 * 
    	 * @param documentName  : Name of the Document    	                    
    	 * @param className
    	 *            : ClassName to Save ExtentReport
    	 * @param screenShot
    	 *            : Save Image as screenShot   
    	 * @throws InterruptedException 
    	 * @throws IOException 
    	 */
        
        
        public void uploadDocument(String documentName,String className,String screenShotName) throws InterruptedException, IOException
        {
                //Navigate to the Document Library
                LOGGER.info("Navigate To DocumentLibrary");
		
                NavigateToPage navigate=new NavigateToPage(driver);
		navigate.goToDocLib();
		Thread.sleep(3000); 
                
                LOGGER.info("Click \"Upload Document\"");
		
                Button uploadButton = new Button(driver, By.id("template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-fileUpload-button-button"));
		//Element.takescreenshot(driver, className, screenShotName);
                uploadButton.click();
			
                LOGGER.info("Select the file \""+documentName+"\"");
		
		
		
		
		Button selectfile = new Button(driver, By.xpath("//span[@id='template_x002e_dnd-upload_x002e_documentlibrary_x0023_default-file-selection-button-overlay']//span"));
		selectfile.click();

		
		/*WebElement El = driver.findElement(By.xpath("//span//input[@class='dnd-file-selection-button']"));
		((RemoteWebElement) El ).setFileDetector(new LocalFileDetector()); 
		El.sendKeys("/Users/mketheeswaran/Documents/FileA.rtf");
		TestCaseProperties.closeDriver(driver);*/
		
		WebElement El = driver.findElement(By
				.xpath("//span//input[@class='dnd-file-selection-button']"));
		((RemoteWebElement) El).setFileDetector(new LocalFileDetector());
		El.sendKeys(TestCaseProperties.UPLOAD_DOC_PATH + documentName + "/");
		Element.waitForLoad(driver);
		
		/*WebElement browseButton1 = driver.findElement(By.xpath("//span//input[@class='dnd-file-selection-button']"));
        
        LocalFileDetector detector = new LocalFileDetector();      
        String path = TestCaseProperties.UPLOAD_DOC_PATH+documentName+"/";      
        File f = detector.getLocalFile(path);
        ((RemoteWebElement)browseButton1).setFileDetector(detector);
        browseButton1.sendKeys(f.getAbsolutePath());       
        Element.waitForLoad(driver);
        Thread.sleep(1000);*/
        
        
        }
        
        /**
    	 * Verify uploadDocument
    	 * 
    	 * @param siteName  : Name of the Site 
    	 * @param documentName  : Name of the Document   	                    
    	 * @param className
    	 *            : ClassName to Save ExtentReport
    	 * @param screenShot
    	 *            : Save Image as screenShot   
    	 * @throws InterruptedException 
    	 * @throws IOException 
    	 */
        
        public void verifyUploadDocument(String siteName,String documentName,String className,String screenShotName) throws InterruptedException, IOException
        {
            
		SearchObjects search = new SearchObjects(driver);
		
		//Search for the Site
                LOGGER.info("Click \"Search Finder\"");
		//extent.log(LogStatus.INFO, "Click \"Search Finder\"");
                
                LOGGER.info("Search Site \""+siteName+"\"");
		//extent.log(LogStatus.INFO, "Search Site \""+siteName+"\"");
		search.searchSite(siteName);
				
		//Clicks on the Site Link
                LOGGER.info("Navigate to the " +siteName);
		//extent.log(LogStatus.INFO, "Navigate to the " +siteName);
		Link siteLink=new Link(driver, By.xpath("//h3[@class='sitename']/a[text()='"+siteName+"']"));
		siteLink.click();
                
             //   Element.takescreenshot(driver, className, screenShotName+"siteName");
		Thread.sleep(3000); 
                
                //Navigate to the Document Library
                LOGGER.info("Navigate To DocumentLibrary");
		//extent.log(LogStatus.INFO, "Navigate To DocumentLibrary");
                NavigateToPage navigate=new NavigateToPage(driver);
		navigate.goToDocLib();
		Thread.sleep(3000);
                
                Element currentResult1 = new Element(driver,By.xpath("//h3[@class='filename']//span//a[contains(., " +documentName+ ")]"));
		//Verify whether the uploaded file is available in the Document Library
		if(Button.isElementPresent(driver, By.xpath("//h3[@class='filename']//span//a[contains(., " +documentName+ ")]")))
		{
		//Expected Result
		LOGGER.info("Expected Results : " + documentName);
        	//extent.log(LogStatus.INFO, "Expected Results : " + documentName);
        	
        	//Current Result
        	LOGGER.info("Current Test Results : " +currentResult1.getWebElement().getText());
        	//extent.log(LogStatus.INFO, "Current Test Results : " + currentResult1.getWebElement().getText());
        	
        	//extent.log(LogStatus.PASS, "The User has created the " +documentName+ " successfully.");
        	//Screen Shot
        	//Element.takescreenshot(driver,className,screenShotName+"s9");
		}
		else
		{
			//Expected Result
			LOGGER.info("Expected Results : " + documentName);
        	//extent.log(LogStatus.INFO, "Expected Results : " + documentName);
        	
        	//Current Result
        	LOGGER.info("Current Test Results : " +currentResult1.getWebElement().getText());
        	//extent.log(LogStatus.INFO, "Current Test Results : " +currentResult1.getWebElement().getText());
        	
        	//extent.log(LogStatus.FAIL, "The User has not created the " +documentName+ " successfully.");
        	//Screen Shot
           //	Element.takescreenshot(driver,className,screenShotName+"s10");
		}
		 
                
                
        }
        
        
       /**
    	 * managePermission
    	 * 
    	 * @param siteName  : Name of the Site 
    	 * @param documentName  : Name of the Document 
    	 * @param userName  : Name of the user
    	 * @param roleName  : Name of the role to assign[Manager/collabarator...] 	                    
    	 * @param className
    	 *            : ClassName to Save ExtentReport
    	 * @param screenShot
    	 *            : Save Image as screenShot   
    	 * @throws InterruptedException 
    	 * @throws IOException 
    	 */
        
        public void managePermission(String siteName,String documentName,String userName,String roleName) throws InterruptedException, IOException
        {
        	SearchObjects search = new SearchObjects(driver);
    		
    		//Search for the Site
                    LOGGER.info("Click \"Search Finder\"");
    		
                    
                    LOGGER.info("Search Site \""+siteName+"\"");
    		
    		search.searchSite(siteName);
    				
    		//Clicks on the Site Link
                    LOGGER.info("Navigate to the " +siteName);
    		
    		Link siteLink=new Link(driver, By.xpath("//h3[@class='sitename']/a[text()='"+siteName+"']"));
    		siteLink.click();
    		Thread.sleep(3000);      
                   // Element.takescreenshot(driver, className, screenShotName+"siteName1546");
    		
                    
                    //Navigate to the Document Library
                    LOGGER.info("Navigate To DocumentLibrary");
    		
                    NavigateToPage navigate=new NavigateToPage(driver);
    		navigate.goToDocLib();
    		Thread.sleep(3000);
    		
    		//click the document
    		LOGGER.info("Click Document");
    		 	
    		Link document = new Link(driver, By.xpath("//span/a[text()='"+documentName+"']"));
            document.click();
            
            Thread.sleep(5000);
           
            //click manage Permisison
    		LOGGER.info("Click \"Manage Permissison\"");
    		
            com.zaizi.automation.alfresco.core.elements.Span manage = new Span(driver, By.xpath("//span[contains(., 'Manage Permissions')]"));
           // Element.takescreenshot(driver, className, screenShotName+"managepermissionclick1");
            manage.click();
            Thread.sleep(5000);
            
            LOGGER.info("Click \"Inheritence Permission\"");
    		
            Span priviledges = new Span(driver, By.xpath("//span[contains(., 'Inherit Permissions')]"));
            priviledges.click();
            Thread.sleep(5000);
            
            LOGGER.info("Click \"Yes\" to remove inheritence");
    		
            Span remove = new Span(driver, By.xpath("//span/span/span[contains(., 'Yes')]"));
           // Element.takescreenshot(driver, className, screenShotName+"removeinheritence12");
            remove.click();
            Thread.sleep(5000);
            
            LOGGER.info("Click \"Add User/Group\" to add user/group");
    		
            Span add = new Span(driver, By.xpath("//span[contains(., 'Add User/Group')]"));
            add.click();
            Thread.sleep(5000);
            
            LOGGER.info("Search User");
    		
            TextField searchText = new TextField(
                    driver,
                    By.xpath("//input[@id='template_x002e_manage-permissions_x002e_manage-permissions_x0023_default-authorityFinder-search-text']"));
            searchText.enterText(userName);
            Thread.sleep(5000);
            
            LOGGER.info("Click \"Search\"");
    		
            Span serach = new Span(driver, By.xpath("//span[contains(., 'Search')]"));
           // Element.takescreenshot(driver, className, screenShotName+"searchaddgroupuser");
            serach.click();
            Thread.sleep(5000);
            
            LOGGER.info("Click \"Add\" next to user");
    		
            Span user = new Span(driver, By.xpath("//span/span/span/button[contains(., 'Add')]"));
            user.click();
            Thread.sleep(5000);
            
            LOGGER.info("Set the permission");
    		
            Span permissionButton = new Span(driver, By.xpath("//span/button[text()='Site Contributor']"));
            permissionButton.click();
            Thread.sleep(5000);
            Link collaborator = new Link(driver, By.xpath("//a[contains(., '"+roleName+"')]"));
           // Element.takescreenshot(driver, className, screenShotName+"setpermission");
            collaborator.click();
            Thread.sleep(5000);
            
            LOGGER.info("Click \"SAVE\"");
    		
            Button save = new Button(
                    driver,
                    By.xpath("//button[@id='template_x002e_manage-permissions_x002e_manage-permissions_x0023_default-okButton-button']"));
            save.click();
            Thread.sleep(5000);
            Element.waitForLoad(driver);
            
    		
        }
        
        /**
    	 * Check whether managePermission is successfully updated
    	 * 
    	 * @param siteName  : Name of the Site 
    	 * @param documentName  : Name of the Document 
    	 * @param userName  : Name of the user
    	 * @param roleName  : Name of the role to assign[Manager/collabarator...] 	                    
    	 * @param className
    	 *            : ClassName to Save ExtentReport
    	 * @param screenShot
    	 *            : Save Image as screenShot   
    	 * @throws InterruptedException 
    	 * @throws IOException 
    	 */
        
        public void checkPermissions(String siteName,String documentName,String userName,String roleName,String className,String screenShotName) throws InterruptedException, IOException
        {
        	
        	SearchObjects search = new SearchObjects(driver);
    		
    		//Search for the Site
                    LOGGER.info("Click \"Search Finder\"");
    		//extent.log(LogStatus.INFO, "Click \"Search Finder\"");
                    
                    LOGGER.info("Search Site \""+siteName+"\"");
    		//extent.log(LogStatus.INFO, "Search Site \""+siteName+"\"");
    		search.searchSite(siteName);
    				
    		//Clicks on the Site Link
                    LOGGER.info("Navigate to the " +siteName);
    		//extent.log(LogStatus.INFO, "Navigate to the " +siteName);
    		Link siteLink=new Link(driver, By.xpath("//h3[@class='sitename']/a[text()='"+siteName+"']"));
    		siteLink.click();
    		Thread.sleep(3000);      
                 //   Element.takescreenshot(driver, className, screenShotName+"siteName123w");
    		 
                    
                    //Navigate to the Document Library
            LOGGER.info("Navigate To DocumentLibrary");
    		//extent.log(LogStatus.INFO, "Navigate To DocumentLibrary");
                    NavigateToPage navigate=new NavigateToPage(driver);
    		navigate.goToDocLib();
    		Thread.sleep(3000);
    		
    		//click the document
    		LOGGER.info("Click Document");
    		//extent.log(LogStatus.INFO, "Click Document");
    		Link document = new Link(driver, By.xpath("//span/a[text()='"+documentName+"']"));
            document.click();
            Thread.sleep(7000);
           
            //click manage Permisison
    		LOGGER.info("Click \"Manage Permissison\"");
    		//extent.log(LogStatus.INFO, "Click \"Manage Permissison\"");
            Span manage = new Span(driver, By.xpath("//span[contains(., 'Manage Permissions')]"));
          //  Element.takescreenshot(driver, className, screenShotName+"managepermissionclick");
            manage.click();
            
            Thread.sleep(2000);
            Button check = new Button(driver, By.xpath("//span/span/span[contains(., '"+roleName+"')]"));
            if (check.getWebElement().getText().contains("roleName"))
            {
            	 LOGGER.info("Successfully user"+userName+" added to role "+roleName+" in manage Permission");
         		//extent.log(LogStatus.PASS, "Successfully user"+userName+" added to role "+roleName+" in manage Permission");
         		// Element.takescreenshot(driver, className, screenShotName+"managepermissionpass");
            }
            else
            {
            	LOGGER.info("User"+userName+" IS NOT added to role "+roleName+" in manage Permission");
         		//extent.log(LogStatus.FAIL, "User"+userName+" IS NOT added to role "+roleName+" in manage Permission");
         		//Element.takescreenshot(driver, className, screenShotName+"managepermissionfail");
            }
        }

}
