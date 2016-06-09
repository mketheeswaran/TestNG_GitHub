package com.zaizi.automation.alfresco.core.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
*
* @author mketheeswaran
*/

public class TextField  extends Element {

    public TextField(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
    }
    
    public void enterText(String text){
        getWebElement().sendKeys(text);
    }
    
    public void clearText(){
        getWebElement().clear();
    }
    
    public String getText(){
        return getElementAttribute("value");
    }
}
