package com.zaizi.automation.extentReports;


import com.relevantcodes.extentreports.ExtentReports;


/**
 *
 * @author Mathuja
 */
public class ExtentManagerRetry {
  
    
    private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        
           extent = new ExtentReports(filePath, false);
            
            extent
                 .addSystemInfo("Environment", "QA");
            

       
        
        
        return extent;
    }
    
    public synchronized static ExtentReports getReporter() {
        return extent;
    }  
    
 }
    
