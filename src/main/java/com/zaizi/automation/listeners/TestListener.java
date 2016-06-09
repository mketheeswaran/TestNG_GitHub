package com.zaizi.automation.listeners;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;





/**
*
* @author mrashad@zaizi.com
*/


public class TestListener implements ITestListener {

	public static final Logger LOGGER = LogManager
			.getLogger(TestListener.class.getName());
	
    public static WebDriver driver;

	
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {

		

		
		 if (result.getStatus() == ITestResult.FAILURE) {            
             LOGGER.info("***** Error " + result.getName()
                      + " test has failed *****");
            
         }
        
	}

	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		 if (result.getStatus() == ITestResult.SKIP) {
            
             LOGGER.info("***** Error " + result.getName()
                      + " test has skiped *****");
            
         }
         

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}


	public void onFinish(ITestContext context) {

		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}
		
			
					
	}

	

	

}
