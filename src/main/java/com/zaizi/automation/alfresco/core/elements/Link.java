
package com.zaizi.automation.alfresco.core.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author mketheeswaran
 */
public class Link extends Element{ 
    
    public Link(WebDriver driver,By elementIdentifier){
        super(driver, elementIdentifier);
    }
    
    public String getUrl(){
        return getElementAttribute("href");
    }
   
}
