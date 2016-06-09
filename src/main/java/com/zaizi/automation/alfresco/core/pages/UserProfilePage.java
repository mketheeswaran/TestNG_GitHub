/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.alfresco.core.pages;

import java.io.IOException;

import com.zaizi.automation.zaiziautomationapi.core.ZaiziPageObject;
import com.zaizi.automation.alfresco.core.elements.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author kdesilva
 */
public class UserProfilePage implements ZaiziPageObject{

	/**
	 * Defining log4j
	 */
	public static final Logger LOGGER = LogManager.getLogger(LoginPage.class
			.getName());
	//public static  ExtentReports extent = ExtentReports.get(LoginPage.class);
	
    private WebDriver driver;

    //web elements
    //xpath constants
    //general constants
    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void prepareElements() {
    }  
    
    public String getAboutUser(){
        Label abtUser=new Label(driver, By.xpath("//div[contains(@id,'default-view-name')]"));
        return abtUser.getText();
    }
    
    /**
     * Returns the Email of the User
     * @return
     */
    public String getEmail(){
        Span spanEmail=new Span(driver, By.xpath("//span[contains(@id,'default-view-email')]"));
        return spanEmail.getText();
    }
    
    /**
     * Returns the Username of the User
     * @return
     */
    public String getUserName(){
        Span spanUserName=new Span(driver, By.xpath("//span[contains(@id,'default-view-username')]"));
        return spanUserName.getText();
    }
    
    /**
     * Returns the Status of the Account
     * @return
     */
    public String getAccountStatus(){
        Span spanEnabled=new Span(driver, By.xpath("//span[contains(@id,'default-view-enabled')]"));
        return spanEnabled.getText();
    }
    
    /**
     * Returns the Usage of the Account
     * @return
     */
    public String getUsage(){
        Span spanUsage=new Span(driver, By.xpath("//span[contains(@id,'default-view-usage')]"));
        return spanUsage.getText();
    }
    
    public String getProfileEmail(String className,String screenShot) throws InterruptedException, IOException 
	  {    
    	    LOGGER.info("Get the Email Address");
           // extent.log(LogStatus.INFO,"Get the Email Address");
	       // Element.takescreenshot(driver,className,screenShot+"getEMAIL");	        
	        Span notification = new Span(driver, By.xpath("//span[text()='Email:']/following::span[@class='fieldvalue']"));     
	        return notification.getText();      
	  }
    
    
}
