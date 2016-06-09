package com.zaizi.automation.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;
import com.zaizi.automation.extentReports.ExtentManagerRetry;

public class ChromeRetryAnalyzer implements IRetryAnalyzer  { 
private int count = 0; 
private int maxCount = 2; // set your count to re-run test

public static String className = ChromeRetryAnalyzer.class.getSimpleName();
/**
 * 
 * Defining log4j
 */

public static final Logger LOGGER = LogManager
		.getLogger(ChromeRetryAnalyzer.class.getName());

ExtentReports extent = ExtentManagerRetry.getReporter(TestCaseProperties.REPORT_TEST_PATH_RETRY+className+".html");


public String getResultStatusName(int status) {
   	String resultName = null;
   	if(status==1)
   		resultName = "SUCCESS";
   	if(status==2)
   		resultName = "FAILURE";
   	if(status==3)
   		resultName = "SKIP";
		return resultName;
   }


public boolean retry(ITestResult result) { 
	ExtentTest test = extent.startTest(result.getName(),"This "+result.getName()+" test method got fail/skip");
	

	
        if(count < maxCount) {                     
             
                
            	
            	LOGGER.info("Retrying test " + result.getName() + " with status "
                        + getResultStatusName(result.getStatus()) + " for the " + (count+1) + " time(s).");
                
                test.log(LogStatus.FAIL, "Retrying test " + result.getName() + " with status "
                        + getResultStatusName(result.getStatus()) + " for the " + (count+1) + " time(s).");  
            	
            	   count++;  
            	   extent.flush();
                  	extent.endTest(test); 
               // extent.flush();
                return true;
                
        } 
        //extent.flush();
        return false; 
}


/*@AfterClass
public void CloseDriver() {
	
	
	extent.close();		
	driver.close();

} */

}