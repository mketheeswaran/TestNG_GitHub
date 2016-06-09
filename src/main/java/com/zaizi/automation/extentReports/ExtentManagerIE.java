package com.zaizi.automation.extentReports;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.relevantcodes.extentreports.ExtentReports;

/**
 *
 * @author Mathuja
 */
public class ExtentManagerIE {
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
