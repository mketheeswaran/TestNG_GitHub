package com.zaizi.automation.alfresco.core.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Container;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;


/**
 * @author nbrahmananthan@zaizi.com
 * 
 */
public class RemoveObjects {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager
			.getLogger(RemoveObjects.class.getName());
	
	//public static  ExtentReports extent = ExtentReports.get(SearchObjects.class);

	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public RemoveObjects(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Delete Site from System
	 * 
	 * @param siteName
	 *            : Name of the site
	 * @param siteURL
	 *            : URL of the site
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void deleteSite(String siteName, String siteURL)
			throws InterruptedException, IOException {

		SiteDashboardPage siteDashboardPage = new SiteDashboardPage(driver);
		if (!siteDashboardPage.checkPublicSitePresence(siteName)) {
			LOGGER.info("Site : " + siteName + " Not Available To Delete");
			
			LOGGER.error("Site : "
					+ siteName
					+ " Could be Deleted Already or User does not have premision to view it");
			
		} else {
			
                       /* NavigateToPage navigateTo = new NavigateToPage(driver);
			LOGGER.info("Click \"Site Finder\"");
			extent.log(LogStatus.INFO, "Click \"Site Finder\"");
			navigateTo.goToSiteSearch();
                        */
			
			SearchObjects searchObjects = new SearchObjects(driver);
			searchObjects.searchSite(siteName);

			Thread.sleep(2000);
			LOGGER.info("Click \"Delete\" Button,next to site\""+siteName+"\"");
			
			//Button deleteButton = new Button(driver,By.xpath("//tbody[@class='yui-dt-data']//tr//td//div//span[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-deleteButton-"+ siteURL+"']//span//span"));
			//deleteButton.click();
			
			Button deleteButton = new Button(driver,By.xpath("//h3/a[text()='"+siteName+"']/ancestor::td/following-sibling::td//button[text()='Delete']"));
			deleteButton.click();
			Thread.sleep(1000);
			
			LOGGER.info("Click \"Delete\" Button,for delete confirmation");
			
			Button confirmDeletion = new Button(
					driver,
					By.xpath("//div[@class='ft']//span//span//span[contains(.,'Delete')]"));
			confirmDeletion.click();
			Thread.sleep(1000);
			Button yesButton = new Button(driver,
					By.xpath("//button[contains(., 'Yes')]"));
			yesButton.click();
			Thread.sleep(3000);
			Element.waitForLoad(driver);

			RemoveObjects removeObjects = new RemoveObjects(driver);
			UserDashboardPage userDashboardPage = new UserDashboardPage(driver);
			if (userDashboardPage
					.checkUserButton("Administrator",TestCaseProperties.ADMIN_LASTNAME)) {
				removeObjects.deleteContentFromTrashCan(siteURL);
			} else {
				LoginPage loginPage = new LoginPage(driver);
				loginPage.logout();
				Element.waitForLoad(driver);
				LOGGER.info("Login as Admin to delete Site in TrashCan");
				
				loginPage.loginAsAdmin();
				Element.waitForLoad(driver);
				removeObjects.deleteContentFromTrashCan(siteURL);
			}

			Element.waitForLoad(driver);
		}

	}

	/**
	 * Delete Object from user trashcan
	 * 
	 * @param item
	 *            : Name of the object to be deleted
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void deleteContentFromTrashCan(String item)
			throws InterruptedException, IOException {

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
                
                LOGGER.error("Click \"My Profile\"");
		
                
		navigateTo.goToUserTrashCan();
		
		LOGGER.info("Place siteUrl");
		
		TextField textField = new TextField(
				driver,
				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-search-text"));
		textField.clearText();
		textField.enterText(item);
		
		LOGGER.info("Click \"Search\" Button");
		
		Button searchButton = new Button(driver,By.xpath("//button[text()='Search']"));
		searchButton.click();
		Thread.sleep(5000);
		//Element.takescreenshot(driver, className, screenShotName);
		
		//CheckBox checkbox=new CheckBox(driver, By.xpath("//div[@class='name'][text()='"+item+"']/ancestor::td[1]/preceding-sibling::td[2]//input"));
		//checkbox.click();

		LOGGER.info("Check the patucular siteUrl");
		
		Element.waitUntilElementPresent(driver, By.xpath("//div//div//table//tbody//tr[contains(.,'" + item
						+ "')]//td//div"));
		
		java.util.List<WebElement> myList = driver.findElements(By
				.xpath("//div//div//table//tbody//tr[contains(.,'" + item
						+ "')]//td//div"));

		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).getText().equals(item)) {
				int j = ((i - 3) / 7);
				java.util.List<WebElement> myCheckList = driver.findElements(By
						.xpath("//div//div//table//tbody//tr//td/div//input"));
				myCheckList.get(j).click();
				i = myList.size();
				Thread.sleep(2000);
			}
		}

		LOGGER.info("Click \"Selected Item\" Button");
		
		Element.waitUntilElementClickable(driver, By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-selected-button"));
		Button selectedItem = new Button(
				driver,
				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-selected-button"));
		selectedItem.click();
		Thread.sleep(3000);
		
		LOGGER.info("Click \"Delete\" Button");
		
		Button deleteButton = new Button(driver,
				By.xpath("//div//ul//li//a[contains(.,'Delete')]"));
		deleteButton.click();
		Thread.sleep(3000);
		
		LOGGER.info("Click \"OK\" Button");
		
		Button okButton = new Button(driver,
				By.xpath("//div//span//span//span[contains(.,'OK')]"));
		okButton.click();
		
		LOGGER.info("Click \"OK confirmation\" Button");
		
		Button okButton1 = new Button(driver,
				By.xpath("//button[text()='OK']"));
		okButton1.click();
		Thread.sleep(3000);
		Element.waitForLoad(driver);

	}
	
	/**
	 * Delete Object from user trashcan
	 * 
	 * @param item
	 *            : Name of the object to be deleted
	 * @throws InterruptedException
	 * @throws IOException 
	 */

	public void deleteFolderFromTrashCan(String item)
			throws InterruptedException, IOException {

		NavigateToPage navigateTo = new NavigateToPage(driver);
		navigateTo.goToHome();
                
                LOGGER.error("Click \"My Profile\"");
		
                
		navigateTo.goToUserTrashCan();
		
		LOGGER.info("Place FolderName");
		
		TextField textField = new TextField(
				driver,
				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-search-text"));
		textField.clearText();
		textField.enterText(item);
		
		LOGGER.info("Click \"Search\" Button");
		
		Button searchButton = new Button(driver,By.xpath("//button[text()='Search']"));
		searchButton.click();
		Thread.sleep(2000);
		//Element.takescreenshot(driver, className, screenShotName);
		
		//CheckBox checkbox=new CheckBox(driver, By.xpath("//div[@class='name'][text()='"+item+"']/ancestor::td[1]/preceding-sibling::td[2]//input"));
		//checkbox.click();

		LOGGER.info("Check the patucular FolderName");
		
		Element.waitUntilElementPresent(driver, By.xpath("//div//div//table//tbody//tr[contains(.,'" + item
						+ "')]//td//div"));
		
		java.util.List<WebElement> myList = driver.findElements(By
				.xpath("//div//div//table//tbody//tr[contains(.,'" + item
						+ "')]//td//div"));

		for (int i = 0; i < myList.size(); i++) {

			if (myList.get(i).getText().equals(item)) {
				int j = ((i - 3) / 7);
				java.util.List<WebElement> myCheckList = driver.findElements(By
						.xpath("//div//div//table//tbody//tr//td/div//input"));
				myCheckList.get(j).click();
				i = myList.size();
				Thread.sleep(2000);
			}
		}

		LOGGER.info("Click \"Selected Item\" Button");
		
		Element.waitUntilElementClickable(driver, By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-selected-button"));
		Button selectedItem = new Button(
				driver,
				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-selected-button"));
		selectedItem.click();
		Thread.sleep(1000);
		
		LOGGER.info("Click \"Delete\" Button");
		
		Button deleteButton = new Button(driver,
				By.xpath("//div//ul//li//a[contains(.,'Delete')]"));
		deleteButton.click();
		Thread.sleep(1000);
		
		LOGGER.info("Click \"OK\" Button");
		
		Button okButton = new Button(driver,
				By.xpath("//div//span//span//span[contains(.,'OK')]"));
		okButton.click();
		
		LOGGER.info("Click \"OK confirmation\" Button");
		
		Button okButton1 = new Button(driver,
				By.xpath("//button[text()='OK']"));
		okButton1.click();
		Thread.sleep(1000);
		Element.waitForLoad(driver);

	}
	
	
	/**
	 * Delete Object from user trashcan
	 * 
	 * @param item
	 *            : Name of the object to be deleted
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	
	public void deleteConfirmation(String item,String className,String screenShotName)
			throws InterruptedException, IOException {

		NavigateToPage navigateTo = new NavigateToPage(driver);		
		navigateTo.goToHome();
                
                LOGGER.error("Click \"My Profile\" Again");
		
                
		navigateTo.goToUserTrashCan();	
		
		LOGGER.info("Place siteUrl");
		
		TextField textField = new TextField(
				driver,
				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-search-text"));
		textField.clearText();
		textField.enterText(item);
		
		LOGGER.info("Click \"Search\" Button");
		
		Button searchButton = new Button(driver,By.xpath("//button[text()='Search']"));
		searchButton.click();
		Thread.sleep(2000);
		//Element.takescreenshot(driver, className, screenShotName+"deleteConfirmation");
		
		//CheckBox checkbox=new CheckBox(driver, By.xpath("//div[@class='name'][text()='"+item+"']/ancestor::td[1]/preceding-sibling::td[2]//input"));
		//checkbox.click();

		LOGGER.info("Check the patucular siteUrl");

		
		if(Element.isElementPresent(driver, By.xpath("//tbody[@class='yui-dt-message']//tr//td//div")))
		{
			LOGGER.info(item+" SITE IS DELETED SUCCESSFULLY");
			
		}
		else{
			Element.waitUntilElementPresent(driver, By.xpath("//div//div//table//tbody//tr[contains(.,'" + item
					+ "')]//td//div"));
	
	java.util.List<WebElement> myList = driver.findElements(By
			.xpath("//div//div//table//tbody//tr[contains(.,'" + item
					+ "')]//td//div"));

	for (int i = 0; i < myList.size(); i++) {

		if (myList.get(i).getText().equals(item)) 
		{	
			Thread.sleep(2000);
			LOGGER.info(item+ "SITE IS NOT DELETED");
			
		}
		else
		{
			LOGGER.info(item+" SITE IS DELETED SUCCESSFULLY");
			
		}
		}
		
		}
	}
        
        
	/**
	 * Delete Folder
	 * 
	 * @param folder
	 *            : Name of the Folder to be deleted
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	
        public void deleteFolder(String folder)
			throws InterruptedException, IOException 
        {
            //Navigate to the Document Library
                LOGGER.info("Navigate To DocumentLibrary");
		
                NavigateToPage navigate=new NavigateToPage(driver);
		navigate.goToDocLib();
		Thread.sleep(3000);
                
                LOGGER.info("Select Folder");
		
               Container selectFolder=new Container(driver,By.xpath("//a[text()='"+folder+"']/ancestor::td/preceding-sibling::td[3]//input"));
               selectFolder.click();
               
               LOGGER.info("Click \"Selected Items\"");
		
               Button selectItem=new Button(driver,By.xpath("//button[@id='template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-selectedItems-button-button']"));
               selectItem.click();
               
             //  Element.takescreenshot(driver, className, screenShotName);
               LOGGER.info("Click \"Delete\" Button");
	      
               Button deleteItem=new Button(driver,By.xpath("//span[@class='onActionDelete']"));
               deleteItem.click();
               
               LOGGER.info("Click \"Delete Confirmation\" Button");
	     
               Button deleteButton=new Button(driver,By.xpath("//button[text()='Delete']"));
             //  Element.takescreenshot(driver, className, screenShotName+"deleteFolder15");
               deleteButton.click();
               
               Element.waitForLoad(driver);

   			RemoveObjects removeObjects = new RemoveObjects(driver);
   			UserDashboardPage userDashboardPage = new UserDashboardPage(driver);
   			if (userDashboardPage
   					.checkUserButton("Administrator",TestCaseProperties.ADMIN_LASTNAME)) {
   				removeObjects.deleteContentFromTrashCan(folder);
   			} else {
   				LoginPage loginPage = new LoginPage(driver);
   				loginPage.logout();
   				Element.waitForLoad(driver);
   				LOGGER.info("Login as Admin to delete Folder in TrashCan");
   				
   				loginPage.loginAsAdmin();
   				Element.waitForLoad(driver);
   				removeObjects.deleteFolderFromTrashCan(folder);
   			}

   			Element.waitForLoad(driver);
        }
        
        /**
    	 * Delete Folder from user trashcan
    	 * 
    	 * @param item
    	 *            : Name of the Folder to be deleted
    	 * @throws InterruptedException
    	 * @throws IOException 
    	 */
        
        public void deleteFolderConfirmation(String item,String className,String screenShotName)
    			throws InterruptedException, IOException {

    		NavigateToPage navigateTo = new NavigateToPage(driver);
    		
    		LOGGER.info("CHECK WHETHER FOLDER\" "+item+" \"IS DELETED OR NOT");
    		//extent.log(LogStatus.INFO,"CHECK WHETHER FOLDER\" "+item+" \"IS DELETED OR NOT");
    		navigateTo.goToHome();
                    
            LOGGER.error("Click \"My Profile\" Again");
    		//extent.log(LogStatus.INFO, "Click \"My Profile\" Again");
                    
    		navigateTo.goToUserTrashCan();	
    		
    		LOGGER.info("Place folderUrl");
    		//extent.log(LogStatus.INFO, "Place folderUrl");
    		TextField textField = new TextField(
    				driver,
    				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-search-text"));
    		textField.clearText();
    		textField.enterText(item);
    		
    		LOGGER.info("Click \"Search\" Button");
    		//extent.log(LogStatus.INFO, "Click \"Search\" Button");
    		Button searchButton = new Button(driver,By.xpath("//button[text()='Search']"));
    		searchButton.click();
    		Thread.sleep(2000);
    	//	Element.takescreenshot(driver, className, screenShotName+"deleteConfirmation123");
    		
    		//CheckBox checkbox=new CheckBox(driver, By.xpath("//div[@class='name'][text()='"+item+"']/ancestor::td[1]/preceding-sibling::td[2]//input"));
    		//checkbox.click();

    		LOGGER.info("Check the patucular folderUrl");
    		//extent.log(LogStatus.INFO, "Check the patucular folderUrl");
    		
    		if(Element.isElementPresent(driver, By.xpath("//tbody[@class='yui-dt-message']//tr//td//div")))
    		{
    			LOGGER.info(item+" Folder IS DELETED SUCCESSFULLY");
    			//extent.log(LogStatus.PASS, item+" Folder IS DELETED SUCCESSFULLY");
    		}
    		else{
    			Element.waitUntilElementPresent(driver, By.xpath("//div//div//table//tbody//tr[contains(.,'" + item
    					+ "')]//td//div"));
    	
    	java.util.List<WebElement> myList = driver.findElements(By
    			.xpath("//div//div//table//tbody//tr[contains(.,'" + item
    					+ "')]//td//div"));

    	for (int i = 0; i < myList.size(); i++) {

    		if (myList.get(i).getText().equals(item)) 
    		{	
    			Thread.sleep(2000);
    			LOGGER.info(item+ "Folder IS NOT DELETED");
    			//extent.log(LogStatus.FAIL, item+" Folder IS NOT DELETED");
    		}
    		else
    		{
    			LOGGER.info(item+" Folder IS DELETED SUCCESSFULLY");
    			//extent.log(LogStatus.PASS, item+"Folder IS DELETED SUCCESSFULLY");
    		}
    		}
    		
    		}
    	}
        
        
        /**
    	 * Delete Document 
    	 * 
    	 * @param document
    	 *            : Name of the document to be deleted
    	 * @throws InterruptedException
    	 * @throws IOException 
    	 */
        
        public void deleteDocument(String document)
    			throws InterruptedException, IOException 
            {
                //Navigate to the Document Library
                    LOGGER.info("Navigate To DocumentLibrary");
    		
                    NavigateToPage navigate=new NavigateToPage(driver);
    		navigate.goToDocLib();
    		Thread.sleep(3000);
                    
                    LOGGER.info("Select Document");
    		
                   Container selectFolder=new Container(driver,By.xpath("//a[text()='"+document+"']/ancestor::td/preceding-sibling::td[3]//input"));
                   selectFolder.click();
                   
                   LOGGER.info("Click \"Selected Items\"");
    		
                   Button selectItem=new Button(driver,By.xpath("//button[@id='template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-selectedItems-button-button']"));
                   selectItem.click();
                   
                 //  Element.takescreenshot(driver, className, screenShotName);
                   LOGGER.info("Click \"Delete\" Button");
    	       
                   Button deleteItem=new Button(driver,By.xpath("//span[@class='onActionDelete']"));
                   deleteItem.click();
                   
                   LOGGER.info("Click \"Delete Confirmation\" Button");
    	       
                   Button deleteButton=new Button(driver,By.xpath("//button[text()='Delete']"));
                //   Element.takescreenshot(driver, className, screenShotName+"deleteDocument15");
                   deleteButton.click();
                   
                   Element.waitForLoad(driver);

       			RemoveObjects removeObjects = new RemoveObjects(driver);
       			UserDashboardPage userDashboardPage = new UserDashboardPage(driver);
       			if (userDashboardPage
       					.checkUserButton("Administrator",TestCaseProperties.ADMIN_LASTNAME)) {
       				removeObjects.deleteContentFromTrashCan(document);
       			} else {
       				LoginPage loginPage = new LoginPage(driver);
       				loginPage.logout();
       				Element.waitForLoad(driver);
       				LOGGER.info("Login as Admin to delete Document in TrashCan");
       				
       				loginPage.loginAsAdmin();
       				Element.waitForLoad(driver);
       				removeObjects.deleteFolderFromTrashCan(document);
       			}

       			Element.waitForLoad(driver);
            }
            
        /**
    	 * Delete Document from the trashcan
    	 * 
    	 * @param document
    	 *            : Name of the document to be deleted
    	 * @throws InterruptedException
    	 * @throws IOException 
    	 */
        
            public void deleteDocumentConfirmation(String item,String className,String screenShotName)
        			throws InterruptedException, IOException {

        		NavigateToPage navigateTo = new NavigateToPage(driver);
        		
        		LOGGER.info("CHECK WHETHER DOCUMENT\" "+item+" \"IS DELETED OR NOT");
        		//extent.log(LogStatus.INFO,"CHECK WHETHER DOCUMENT\" "+item+" \"IS DELETED OR NOT");
        		navigateTo.goToHome();
                        
                LOGGER.error("Click \"My Profile\" Again");
        		//extent.log(LogStatus.INFO, "Click \"My Profile\" Again");
                        
        		navigateTo.goToUserTrashCan();	
        		
        		LOGGER.info("Place folderUrl");
        		//extent.log(LogStatus.INFO, "Place folderUrl");
        		TextField textField = new TextField(
        				driver,
        				By.id("template_x002e_user-trashcan_x002e_user-trashcan_x0023_default-search-text"));
        		textField.clearText();
        		textField.enterText(item);
        		
        		LOGGER.info("Click \"Search\" Button");
        		//extent.log(LogStatus.INFO, "Click \"Search\" Button");
        		Button searchButton = new Button(driver,By.xpath("//button[text()='Search']"));
        		searchButton.click();
        		Thread.sleep(2000);
        		//Element.takescreenshot(driver, className, screenShotName+"deleteConfirmationDOC123");
        		
        		//CheckBox checkbox=new CheckBox(driver, By.xpath("//div[@class='name'][text()='"+item+"']/ancestor::td[1]/preceding-sibling::td[2]//input"));
        		//checkbox.click();

        		LOGGER.info("Check the patucular docUrl");
        		//extent.log(LogStatus.INFO, "Check the patucular docUrl");
        		
        		if(Element.isElementPresent(driver, By.xpath("//tbody[@class='yui-dt-message']//tr//td//div")))
        		{
        			LOGGER.info(item+" Document IS DELETED SUCCESSFULLY");
        			//extent.log(LogStatus.PASS, item+" Document IS DELETED SUCCESSFULLY");
        		}
        		else{
        			Element.waitUntilElementPresent(driver, By.xpath("//div//div//table//tbody//tr[contains(.,'" + item
        					+ "')]//td//div"));
        	
        	java.util.List<WebElement> myList = driver.findElements(By
        			.xpath("//div//div//table//tbody//tr[contains(.,'" + item
        					+ "')]//td//div"));

        	for (int i = 0; i < myList.size(); i++) {

        		if (myList.get(i).getText().equals(item)) 
        		{	
        			Thread.sleep(2000);
        			LOGGER.info(item+ "Document IS NOT DELETED");
        			//extent.log(LogStatus.FAIL, item+" Document IS NOT DELETED");
        		}
        		else
        		{
        			LOGGER.info(item+" Document IS DELETED SUCCESSFULLY");
        			//extent.log(LogStatus.PASS, item+"Document IS DELETED SUCCESSFULLY");
        		}
        		}
        		
        		}
        	}

}
