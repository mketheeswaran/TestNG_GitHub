package com.zaizi.automation.extentReports;


import com.relevantcodes.extentreports.ExtentReports;


/**
 *
 * @author Mathuja
 */
public class ExtentManagerChrome {
  
    
    private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        
           extent = new ExtentReports(filePath, true);
            
            extent
                 .addSystemInfo("Environment", "QA");
            

       
        
        
        return extent;
    }
    
    public synchronized static ExtentReports getReporter() {
        return extent;
    }  
    
 }
    
