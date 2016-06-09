
package com.zaizi.automation.alfresco.core.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Represents element like checkbox
 * @author mketheeswaran
 */
public class CheckBox extends Element {

    public CheckBox(WebDriver driver, By elementIdentifier) {
        super(driver, elementIdentifier);
    }

    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    public void select() {
        if (!getWebElement().isSelected()) {
            getWebElement().click();
        }

    }

    public void unSelect() {
        if (getWebElement().isSelected()) {
            getWebElement().click();
        }
    }
}
