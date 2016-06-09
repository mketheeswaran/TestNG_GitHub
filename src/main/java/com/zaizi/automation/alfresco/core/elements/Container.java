package com.zaizi.automation.alfresco.core.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents elements like div, span
 * @author mketheeswaran
 */
public class Container extends Element{
    
    public Container(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
    }
    
    public boolean isVisible(){
        return getWebElement().isDisplayed();
    }
    
    public boolean isEnabled(){
        return getWebElement().isEnabled();
    }
}
