/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.alfresco.core.pages;

import com.zaizi.automation.alfresco.core.elements.*;
import com.zaizi.automation.alfresco.core.info.*;
import com.zaizi.automation.zaiziautomationapi.core.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 *
 * @author kdesilva
 */
public class ManageUserPage implements ZaiziPageObject {

    private WebDriver driver;

    //web elements
    //xpath constants
    //general constants
    public ManageUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void prepareElements() {
    }
    
    /**
     * Search for the Test User and CLick on the Test User
     * @return
     * @throws InterruptedException
     */

    public ManageUserPage searchAndClickOnTestUser() throws InterruptedException 
    {
        //search user
        Thread.sleep(2000);
        TextField userSearchField = new TextField(driver, By.xpath("//input[contains(@id,'default-search-text')]"));
        userSearchField.enterText(TestCaseProperties.TEST_USER_NAME);

        Button userSearchBtn = new Button(driver, By.xpath("//button[contains(@id,'default-search-button-button')]"));
        userSearchBtn.click();

        //wait until results appears
        ExplicitWait wait = new ExplicitWait(driver);
        wait.waitUntileElementTextContains(By.xpath("//div[contains(@id,'_default-search-bar')]"), "found", 3);
        //click on user
        Link linkUser = new Link(driver, By.xpath("//div[@class='yui-dt-liner']/a[contains(. , '" + TestCaseProperties.TEST_USER_NAME + "')]"));
        linkUser.click();

        return new ManageUserPage(driver);
    }

    /**
     * Clicks on the Edit user Button on the User Page
     * @return
     * @throws InterruptedException
     */
    public ManageUserPage clickOnEditUserButton() throws InterruptedException 
    {
        //click on edit
        ExplicitWait wait = new ExplicitWait(driver);
        wait.waitUntileElementTextContains(By.xpath("//div[contains(@id,'default-view-name')]"), TestCaseProperties.TEST_USER_NAME, 3);

        Button editButton = new Button(driver, By.xpath("//button[contains(@id,'default-edituser-button-button')]"));
        editButton.click();
        Thread.sleep(4000);
        return new ManageUserPage(driver);
    }
    
 
    /**
     * Change the expiry date to a Invalid date that is an Expired date
     * @return
     * @throws InterruptedException
     */
    public ManageUserPage changeUserExpiryDate_expiredDate() throws InterruptedException
    {
        ExplicitWait wait = new ExplicitWait(driver);
        wait.waitUntilElementAppears(By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-account-expiration-date-ctrl-date']"), 3);
        TextArea setExpiryDateTextArea = new TextArea(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-account-expiration-date-ctrl-date']"));
        Thread.sleep(1000);
        setExpiryDateTextArea.clearText();
        Thread.sleep(3000);
        setExpiryDateTextArea.enterText("01/01/2012");
        Thread.sleep(2000);
         return new ManageUserPage(driver);      
    }
    
    /**
     * Change the expiry date to a valid date
     * @return
     * @throws InterruptedException
     */
    public ManageUserPage changeUserExpiryDate_validDate() throws InterruptedException{
        ExplicitWait wait = new ExplicitWait(driver);
        wait.waitUntilElementAppears(By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-account-expiration-date-ctrl-date']"), 3);
        TextArea setExpiryDateTextArea = new TextArea(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-account-expiration-date-ctrl-date']"));
        Thread.sleep(1000);
        setExpiryDateTextArea.clearText();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-account-expiration-date-ctrl-date']")).clear();
        Thread.sleep(1000);
        setExpiryDateTextArea.enterText("01/01/2020");
        Thread.sleep(4000);
         return new ManageUserPage(driver);      
    }

    /**
     * Change the expiry date to a null value
     * @return
     * @throws InterruptedException
     */
    public ManageUserPage changeUserExpiryDate_null() throws InterruptedException{
        ExplicitWait wait = new ExplicitWait(driver);
        wait.waitUntilElementAppears(By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-account-expiration-date-ctrl-date']"), 3);
        TextArea setExpiryDateTextArea = new TextArea(driver, By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-account-expiration-date-ctrl-date']"));
        Thread.sleep(1000);
        setExpiryDateTextArea.clearText();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-account-expiration-date-ctrl-date']")).clear();
        setExpiryDateTextArea.enterText("");
        Thread.sleep(3000);
         return new ManageUserPage(driver);      
    }

    /**
     * Check on the Enable/Disable checkbox under the User details
     * @return
     * @throws InterruptedException
     */
    
    public ManageUserPage clickOnUserEnableDisableCheckbox() throws InterruptedException 
    {
        ExplicitWait wait = new ExplicitWait(driver);
        wait.waitUntilElementAppears(By.xpath("//input[contains(@id,'page_x002e_ctool_x002e_admin-console_x0023_default-update-disableaccount') and @type='checkbox']"), 3);
     
        CheckBox disableUserChkbx = new CheckBox(driver, By.xpath("//input[contains(@id,'page_x002e_ctool_x002e_admin-console_x0023_default-update-disableaccount') and @type='checkbox']"));	
     
        Thread.sleep(4000);											
        disableUserChkbx.click();
        Thread.sleep(4000);
        return new ManageUserPage(driver);
    }

    /**
     * Click on the Save User Button
     * @return
     */
    public ManageUserPage clickOnSaveUserButton() 
    {
        Button saveChangesBtn = new Button(driver, By.xpath("//button[contains(@id,'updateuser-save-button-button')]"));
        saveChangesBtn.click();
        return new ManageUserPage(driver);
    }

    public LoginPage logout() {
        //logout
        Button userButton = new Button(driver, By.xpath("//button[contains(@id,'default-user_user-button')]"));
        userButton.click();
        Link logoutBtn = new Link(driver, By.xpath("//a[@class='yuimenuitemlabel' and @title='Logout']"));
        logoutBtn.click();
        return new LoginPage(driver);
    }
}
