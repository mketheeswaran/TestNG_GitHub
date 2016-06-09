/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.alfresco.core.pages;


import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import com.zaizi.automation.alfresco.core.elements.*;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;



/**
 *
 * @author kdesilva
 */
public class ChangePasswordPage {

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager.getLogger(LoginPage.class
			.getName());
	
	
	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;

	/**
	 * @param driver
	 */
	public ChangePasswordPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	
    public String enterPasswordAndReturnNotificationString(String password) throws InterruptedException {
        TextField passwordField1 = new TextField(driver, By.xpath("//input[contains(@id,'default-newpassword1')]"));
        TextField passwordField2 = new TextField(driver, By.xpath("//input[contains(@id,'default-newpassword2')]"));
        passwordField1.clearText();
        Thread.sleep(200);
        passwordField1.enterText(password);
        Thread.sleep(200);
        passwordField2.clearText();
        Thread.sleep(200);
        passwordField2.enterText(password);
        Thread.sleep(200);

        new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//span[contains(@id,'default-newpassword1-ind')]/span"), 5);
        Span notification = new Span(driver, By.xpath("//span[contains(@id,'default-newpassword1-ind')]/span"));
        return notification.getText();

    }
    /**
	 * Change PassWord IN CHANGEPASSWORD PAGE
	 * 	 
	 * @param oldPassword
	 *            : Current Password of the user
	 * @param password
	 *            : newPassword & VerifyPassword of the user	                    
	 * @param className
	 *            : ClassName to Save ExtentReport
	 * @param screenShot
	 *            : Save Image as screenShot   
	 * @throws InterruptedException,IOException
	 */
    public String enterPasswordAndChange(String oldPassword,String password,String className,String screenShot) throws InterruptedException, IOException {
    	TextField oldpassword1 = new TextField(driver, By.xpath(" //input[@id='template_x002e_change-password_x002e_change-password_x0023_default-oldpassword']"));
        TextField passwordField1 = new TextField(driver, By.xpath("//input[contains(@id,'default-newpassword1')]"));
        TextField passwordField2 = new TextField(driver, By.xpath("//input[contains(@id,'default-newpassword2')]"));
        oldpassword1.clearText();
        Thread.sleep(200);
        oldpassword1.enterText(oldPassword);
        passwordField1.clearText();
        Thread.sleep(200);
        passwordField1.enterText(password);
        Thread.sleep(200);
        passwordField2.clearText();
        Thread.sleep(200);
        passwordField2.enterText(password);
        Thread.sleep(200);
        
       // Element.takescreenshot(driver,className,screenShot+oldpassword1);
        new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//span[contains(@id,'default-newpassword1-ind')]/span"), 5);
        Span notification = new Span(driver, By.xpath("//span[contains(@id,'default-newpassword1-ind')]/span"));     
        
        return notification.getText();    

    }
    
    /**
     *Enter the Password and get the Info 
     * @param passwordInfo
     * @return
     * @throws InterruptedException
     */
    public boolean enterPassAndGetInfo(String passwordInfo) throws InterruptedException
    {
        TextField oldPassword = new TextField(driver, By.xpath("//input[@id='template_x002e_change-password_x002e_change-password_x0023_default-oldpassword']"));
        TextField newPassword = new TextField(driver, By.xpath("//input[@id='template_x002e_change-password_x002e_change-password_x0023_default-newpassword1']"));
        //TextField confirmPassword = new TextField(driver, By.xpath("//input[@id='template_x002e_change-password_x002e_change-password_x0023_default-newpassword2']"));
        
        
        oldPassword.enterText(TestCaseProperties.TEST_PASSWORD);
        newPassword.enterText(passwordInfo);
        Thread.sleep(2000);
        
        if(Element.isElementPresent(driver, By.xpath("//span[@id='template_x002e_change-password_x002e_change-password_x0023_default-newpassword1-ind']//span")))
        {
            System.out.println("Message Prompted");
            return true;
            
        }
        else 
        {
            System.out.println("Message is not Prompted");
            return false;
         }
         
    }
   
    /**
     * Returns whether the More info message is prompted or not
     * @return
     * @throws InterruptedException
     */
    public boolean moreInfo() throws InterruptedException
    {
        Link moreInfoLink = new Link(driver, By.xpath("//span[@id='template_x002e_change-password_x002e_change-password_x0023_default-newpassword1-ind']//span//a"));
        moreInfoLink.click();
        
        if(Element.isElementPresent(driver, By.xpath("//div[@id='prompt_h']")))
        {
            System.out.println("MessageBox Prompted");
           return true;
        }
        else 
       {
           System.out.println("Message is not Prompted");
           return false;
        }
    }
   
}
