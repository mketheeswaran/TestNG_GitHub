package com.zaizi.automation.alfresco.core.elements;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.zaizi.automation.alfresco.core.info.TestCaseProperties;

public class TakeScreenShot {

	
	//public static WebDriver driver;
	   
	public void takeScreenShot(WebDriver driver,String className,String screenShotName) {

        
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
//			// The below method will save the screen shot in d drive with test
//			// method name
		try {
				FileUtils.copyFile(scrFile,
						new File(TestCaseProperties.SCREENSHOTPATH+className+"/"+screenShotName+".png"));
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	public void takeScreenShotFF(WebDriver driver,String className,String screenShotName) {

        
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
//			// The below method will save the screen shot in d drive with test
//			// method name
		try {
				FileUtils.copyFile(scrFile,
						new File(TestCaseProperties.SCREENSHOTPATH_FF+className+"/"+screenShotName+".png"));
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	public void takeScreenShotIE(WebDriver driver,String className,String screenShotName) {

        
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
//			// The below method will save the screen shot in d drive with test
//			// method name
		try {
				FileUtils.copyFile(scrFile,
						new File(TestCaseProperties.SCREENSHOTPATH_IE+className+"/"+screenShotName+".png"));
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
   
}
