/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.alfresco.core.pages;

import com.zaizi.automation.alfresco.core.elements.*;
import com.zaizi.automation.zaiziautomationapi.core.ZaiziPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author kdesilva
 */
public class ErrorMessageDialog implements ZaiziPageObject {

    private WebDriver driver;

    //web elements
    //xpath constants
    //general constants
    public ErrorMessageDialog(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void prepareElements() {
    }
    
    public void waitUntilDialogAppears(){
        ExplicitWait wait=new ExplicitWait(driver);
        wait.waitUntilElementAppears(By.xpath("//button[contains(. , 'OK')]"), 10);
    }

    public void clickOkButton() {
        //click ok on dialog
        Button btnOk = new Button(driver, By.xpath("//button[contains(. , 'OK')]"));
        btnOk.click();
    }
}
