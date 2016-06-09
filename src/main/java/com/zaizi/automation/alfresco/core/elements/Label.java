/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaizi.automation.alfresco.core.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author mketheeswaran
 */
public class Label extends Element {

    public Label(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
        
    }
    
    public String getText(){
        return getWebElement().getText();
    }
}
