/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.alfresco.core.pages;



import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.Span;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;


/**
 *
 * @author mketheeswaran
 */
public class AdmintoolPage {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager.getLogger(LoginPage.class
			.getName());
	
	/**
	 * Defining Report
	 */
	//public static  ExtentReports extent = ExtentReports.get(LoginPage.class);

	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public AdmintoolPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}  
    
	/**
	 * Navigate "Users" IN AdminTools
	 * 
	 * @param className
	 * @param screenShot
	 * @throws IOException
	 */
	public void user() throws IOException
	{		
      Span userlink=new Span(driver,By.xpath("//a[@title='User Management']"));     
      userlink.click();
    }
	
	/**
	 * Click Create NewUser
	 * Navigate "New User" IN AdminTools	
	 * @throws IOException 
	 */
	
	public void createNewUser() throws IOException 
	{
		//Element.takescreenshot(driver,className,screenShotName+"201");	
		Button newUser=new Button(driver,By.xpath("//Button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-newuser-button-button']"));		
		newUser.click();
		
		
	}
	
	/**
	 * Create a New User & Validate Whether User Created SucessFully OR NOT
	 * 
	 * @param firstNameValue
	 *            : First Name of the user
	 * @param userNameValue
	 *            : Username of the user
	 * @param passwordValue
	 *            : Password of the user
     * @param verifyPasswordValue
	 *            : Verify Password of the user                
	 * @param emailAddress
	 *            : Email Address of the user                           
	 * @param className
	 *            : ClassName to Save ExtentReport
	 * @param screenShot
	 *            : Save Image as screenShot                      
	 * @throws InterruptedException,IOException
	 * 
	 */
	
	public void fillUserFields(String firstNameValue,String lastNameValue,String emailAddress,String userName,String password,String verifyPassword) throws InterruptedException, IOException 
	{
		Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);
		
        TextField firstName = new TextField(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-firstname']"));
        firstName.clearText();
        LOGGER.info("Enter FirstName: "+firstNameValue);
        //extent.log(LogStatus.INFO, "Enter FirstName: "+firstNameValue);
        firstName.enterText(firstNameValue);
        
        TextField lastName = new TextField(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-lastname']"));
        lastName.clearText();
        LOGGER.info("Enter lastName: "+lastNameValue);
       // extent.log(LogStatus.INFO, "Enter lastName: "+lastNameValue);
        lastName.enterText(lastNameValue);
        
        TextField email = new TextField(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-email']"));        
        email.clearText();
        LOGGER.info("Enter Email: "+emailAddress);
        //extent.log(LogStatus.INFO, "Enter Email: "+emailAddress);
        email.enterText(emailAddress);
        
        TextField userNameTextField = new TextField(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-username']"));
        userNameTextField.clearText();
        LOGGER.info("Enter UserName : "+userName);
        //extent.log(LogStatus.INFO, "Enter UserName : "+userNameValue);
        userNameTextField.enterText(userName);
        
        TextField passwordTxtField = new TextField(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-password']"));
        passwordTxtField.clearText();
        LOGGER.info("Enter password: "+password);
        //extent.log(LogStatus.INFO, "Enter password: "+passwordValue);
        passwordTxtField.enterText(password);
        
        TextField verifypassword = new TextField(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-verifypassword']"));
        verifypassword.clearText();
        LOGGER.info("Enter Verify password: "+password);
        //extent.log(LogStatus.INFO, "Enter Verify password: "+verifyPasswordValue);
        verifypassword.enterText(password);
        
        //Element.takescreenshot(driver,className,screenShot+"filledDetails");
        
        LOGGER.info("Click \"Create User\"");
        //extent.log(LogStatus.INFO, "Click \"Create User\"");
        Button btncreateNewUser = new Button(driver, By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-createuser-ok-button-button']"));      
        btncreateNewUser.click();        
        
        Element.waitForLoad(driver);
       
                        
	}
}
