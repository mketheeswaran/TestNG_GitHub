/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.alfresco.core.pages;


import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import com.zaizi.automation.alfresco.core.elements.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author kdesilva
 */
public class EditUserProfilePage{

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
	public EditUserProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
    
	/*
    public String enterWeakPasswordOnPasswordFieldsAndVerifySaveButton(String text) throws InterruptedException{
        Thread.sleep(1000);
        TextField passwordField=new TextField(driver, By.xpath("//input[contains(@id,'default-update-password')]"));
        TextField passwordConfirmField=new TextField(driver, By.xpath("//input[contains(@id,'default-update-verifypassword')]"));
        passwordField.clearText();
        passwordField.enterText(text);
        Thread.sleep(300);
        passwordConfirmField.clearText();
        passwordConfirmField.enterText(text);
        new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//span[contains(@id,'default-update-password-ind')]"), 5);
        System.out.println("Verification Passed : \"Weak\" Alert Found.");
        
        //click on save button
        //Button btnUpdate=new Button(driver, By.xpath("//button[contains(@id,'updateuser-save-button-button')]"));
        //btnUpdate.click();
        //new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//div[@class='bd']/span[@class='message']"), 5);
        boolean test = driver.findElement(By.xpath("//button[contains(@id,'updateuser-save-button-button')]")).isEnabled();
        String result = Boolean.toString(test);
        return result;
    }
    */
    public String enterPasswordAndReturnNotificationString(String password) throws InterruptedException {
        TextField passwordField1 = new TextField(driver, By.xpath("//input[contains(@id,'default-update-password')]"));
        TextField passwordField2 = new TextField(driver, By.xpath("//input[contains(@id,'default-update-verifypassword')]"));
        passwordField1.clearText();
        Thread.sleep(200);
        passwordField1.enterText(password);
        Thread.sleep(200);
        passwordField2.clearText();
        Thread.sleep(200);
        passwordField2.enterText(password);
        Thread.sleep(200);

        new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//span[contains(@id,'default-update-password-ind')]/span"), 5);
        Span notification = new Span(driver, By.xpath("//span[contains(@id,'default-update-password-ind')]/span"));
        return notification.getText();

    }
    
    public String enterWeakPasswordOnPasswordFieldsAndVerifySaveButton(String text) throws InterruptedException, TimeoutException{
        Thread.sleep(1000);
        TextField passwordField=new TextField(driver, By.xpath("//input[contains(@id,'default-update-password')]"));
        TextField passwordConfirmField=new TextField(driver, By.xpath("//input[contains(@id,'default-update-verifypassword')]"));
        passwordField.clearText();
        passwordField.enterText(text);
        Thread.sleep(300);
        passwordConfirmField.clearText();
        passwordConfirmField.enterText(text);
        new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//span[contains(@id,'default-update-password-ind')]"), 5);
        //System.out.println("Verification Passed : \"Weak\" Alert Found.");
       
        //click on save button
        Button btnUpdate=new Button(driver, By.xpath("//button[contains(@id,'updateuser-save-button-button')]"));
        btnUpdate.click();
       
        try{
       
        //new ExplicitWait(driver).waitUntilElementAppears(By.xpath("//input[@title='Please select a valid password']"), 5);
        //boolean test = driver.findElement(By.xpath("//input[@title='Please select a valid password']")).isDisplayed();
        boolean test = Element.isElementPresent(driver, By.xpath("//input[@title='Please select a valid password']"));
        String result = Boolean.toString(test);
        return result;
        }
        catch(NoSuchElementException e )
        {
          String result="false";
          return result;
         
        }
       
       
        //boolean test = driver.findElement(By.xpath("//button[contains(@id,'updateuser-save-button-button')]")).isEnabled();
        //String result = Boolean.toString(test);
        //return result;
    }
    
}
