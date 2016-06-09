package com.zaizi.automation.alfresco.core.pages;


import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zaizi.automation.alfresco.core.elements.Button;
import com.zaizi.automation.alfresco.core.elements.Element;
import com.zaizi.automation.alfresco.core.elements.ExplicitWait;
import com.zaizi.automation.alfresco.core.elements.Link;
import com.zaizi.automation.alfresco.core.elements.Span;
import com.zaizi.automation.alfresco.core.elements.TextField;
import com.zaizi.automation.alfresco.core.info.TestCaseProperties;




/**
 * @author mketheeswaran@zaizi.com
 *
 */
public class LoginPage {

	
	//public static  ExtentReports extent = ExtentReports.get(LoginPage.class.getName());
	//static final ExtentReports extent = new ExtentReports();
	
	/**
	 * 
	 * Defining log4j
	 */

	public static final Logger LOGGER = LogManager
			.getLogger(LoginPage.class.getName());
	
	/**
	 * Defining WebDriver
	 */
	private WebDriver driver;
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	/**
	 * @param driver	 */
	

	/**
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * Login As Test User
	 * 
	 * @throws InterruptedException
	 */
	
	 public void loginAsTestUser() throws InterruptedException {
	        Thread.sleep(TestCaseProperties.THREAD_SLEEP_TIME_2000);	       
	        TextField userName = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
	        userName.clearText();
	        
	        LOGGER.info("Enter username as "+TestCaseProperties.TEST_USER_NAME);	
	        userName.enterText(TestCaseProperties.TEST_USER_NAME);
	        TextField password = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
	        password.clearText();
	        //extent.log(LogStatus.INFO, "Enter password "+TestCaseProperties.TEST_PASSWORD);
	        
	        LOGGER.info("Enter username as "+TestCaseProperties.TEST_PASSWORD);	
	        password.enterText(TestCaseProperties.TEST_PASSWORD);
	        
	        Button btnLogn = new Button(driver, By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));      
	        btnLogn.click();   
	        
	        
	    }
	    
	
	 	/**
		 * Login As Test User
		 * 
		 * @return DashBoard
		 * @throws InterruptedException
		 */
	 
	public Dashboard loginAsTestUser1() throws InterruptedException {
        Thread.sleep(2000);
        TextField userName = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
        TextField password = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
        Button btnLogn = new Button(driver, By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));

        userName.clearText();
        Thread.sleep(500);
        password.clearText();
        Thread.sleep(500);
        //login as test
        userName.enterText(TestCaseProperties.TEST_USER_NAME);
        password.enterText(TestCaseProperties.TEST_PASSWORD);
        btnLogn.click();
        Thread.sleep(4000);
        return new Dashboard(driver);
    }
	
	/**
	 * Login As User
	 * 
	 * @param userNameValue:
	 * 				UserName of User
	 * 
	 * @param password1:
	 * 				Password of the UserName
	 * @return DashBoard
	 * @throws InterruptedException
	 */
	
	public Dashboard loginAsUser1(String userNameValue,String password1) throws InterruptedException {
        Thread.sleep(2000);
        TextField userName = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
        TextField password = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
        Button btnLogn = new Button(driver, By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));

        userName.clearText();
        Thread.sleep(500);
        password.clearText();
        Thread.sleep(500);
        //login as test
        userName.enterText(userNameValue);
        password.enterText(password1);        
        btnLogn.click();
        Thread.sleep(4000);
        return new Dashboard(driver);
    }
	
	/**
	 * Login As User
	 * 
	 * @param userNameValue:
	 * 				UserName of User
	 * 
	 * @param password1:
	 * 				Password of the UserName
	 * 
	 * @param className
	 *            : ClassName to Save ExtentReport
	 *            
	 * @param screenShot
	 *            : Save Image as screenShot 
	 * @return DashBoard
	 * @throws InterruptedException
	 */
	
	public void loginAsUser(String userName,String passWord) throws InterruptedException, IOException {
        
		Element.waitForLoad(driver);
		
        TextField userNameTxt = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
        TextField password = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
        

        userNameTxt.clearText();
        Thread.sleep(500);
        
        password.clearText();
        Thread.sleep(5000); 
        
        LOGGER.info("Enter username as "+userName);		
        userNameTxt.enterText(userName);
        
        LOGGER.info("Enter password as "+passWord);		
        password.enterText(passWord);  
        
        Button btnLogn = new Button(driver, By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));
        btnLogn.click();
        Element.waitForLoad(driver);
       
        
       
    }
	
	/**
	 * Logout From System
	 * 	 
	 */
	
	public void logout() throws InterruptedException {
        
        Button btnUser = new Button(driver, By.xpath("//div//span[@id='HEADER_USER_MENU_POPUP_text']"));
        btnUser.click();
        Element.waitForLoad(driver);

        Thread.sleep(2000);
        Link logoutLink = new Link(driver, By.xpath("//td[@id='HEADER_USER_MENU_LOGOUT_text']"));
        logoutLink.click();
         Element.waitForLoad(driver);
       
    } 
	
	
	/**
	 * Login As Administrator
	 * 
	 * @throws InterruptedException
	 */
	 public Dashboard loginAsAdmin1() throws InterruptedException, IOException {
	        //login as admin
	        TextField userNameAdmin = new TextField(driver, By.xpath("//input[contains(@id,'default-username')]"));
	        TextField passwordAdmin = new TextField(driver, By.xpath("//input[contains(@id,'default-password')]"));
	        Button btnLognAdmin = new Button(driver, By.xpath("//button[contains(@id,'default-submit-button')]"));
	        userNameAdmin.clearText();
	        Thread.sleep(500);
	        passwordAdmin.clearText();
	        Thread.sleep(500);
	        userNameAdmin.enterText(TestCaseProperties.ADMIN_USER_NAME);
	        passwordAdmin.enterText(TestCaseProperties.ADMIN_PASSWORD);	      
	        btnLognAdmin.click();
	        Thread.sleep(2000);
	        return new Dashboard(driver);
	    }
	 
	 public Dashboard loginAsAdmins() throws InterruptedException, IOException {
	        //login as admin
	        TextField userNameAdmin = new TextField(driver, By.xpath("//input[contains(@id,'default-username')]"));
	        TextField passwordAdmin = new TextField(driver, By.xpath("//input[contains(@id,'default-password')]"));
	        Button btnLognAdmin = new Button(driver, By.xpath("//button[contains(@id,'default-submit-button')]"));
	        userNameAdmin.clearText();
	        Thread.sleep(500);
	        passwordAdmin.clearText();
	        Thread.sleep(500);
	        userNameAdmin.enterText(TestCaseProperties.ADMIN_USER_NAME);
	        passwordAdmin.enterText(TestCaseProperties.ADMIN_PASSWORD);	      
	        btnLognAdmin.click();
	        Thread.sleep(2000);
	        return new Dashboard(driver);
	    }
	 
	 	/**
		 * Login As Administrator
		 * 
		 * @return DashBoard
		 * @throws InterruptedException
		 */
	 
	 public void loginAsAdmin() throws InterruptedException, IOException {
	        
	        TextField userNameAdmin = new TextField(driver, By.xpath("//input[contains(@id,'default-username')]"));
	        TextField passwordAdmin = new TextField(driver, By.xpath("//input[contains(@id,'default-password')]"));
	        Button btnLognAdmin = new Button(driver, By.xpath("//button[contains(@id,'default-submit-button')]"));
	        userNameAdmin.clearText();
	        Thread.sleep(500);
	        passwordAdmin.clearText();
	        Thread.sleep(500);	        
	        LOGGER.info("Enter username as "+TestCaseProperties.ADMIN_USER_NAME);	        
	        userNameAdmin.enterText(TestCaseProperties.ADMIN_USER_NAME);
	        
	        LOGGER.info("Enter username as "+TestCaseProperties.ADMIN_PASSWORD);	        
	        passwordAdmin.enterText(TestCaseProperties.ADMIN_PASSWORD);
	        btnLognAdmin.click();
	        Thread.sleep(2000);
	        
	    }
	 
	 
	/* public ErrorMessageDialog tryInvalidLoginAttempt(String password) throws InterruptedException
	 {
	        Thread.sleep(2000);
	        
	        TextField userNameValue = new TextField(driver,By.xpath("//input[@id='username']"));
	        userNameValue.enterText(TestCaseProperties.TEST_USER_NAME);
	        
	        TextField passwordValue = new TextField(driver,By.xpath("//input[@id='password']"));
	        passwordValue.enterText(password);	               
	        
	        if(Element.isElementPresent(driver,By.xpath("//div[@id='prompt_h'][text()='Failed to Login']")))
	        {
	        	Button btnLogn=new Button(driver,By.xpath("//input[@id='btn-login']"));
		        btnLogn.click();
		        
		        Thread.sleep(5 * 60*1000);
		        
		        LoginPage loginPage = new LoginPage(driver);
		        Dashboard dashboard = loginPage.loginAsTestUser();
		        loginPage = dashboard.logout();
	        }
	        else
	        {
	        	Element element=new Element(driver, By.xpath("//div[@class='error']"));
	        	if(Element.isTextPresentInElement(element,"Your authentication details have not been recognized or Alfresco may not be available at this time."))
	        	{
	        		LOGGER.info("Expected Result:\"Your authentication details have not been recognized or Alfresco may not be available at this time.\" message");	    			
	    			extent.log(LogStatus.INFO, "Expected Result:\"Your authentication details have not been recognized or Alfresco may not be available at this time.\" message");
	    			
	    			LOGGER.info("Current Result: " +element.getText());	    			
	    			extent.log(LogStatus.INFO, "<font color=green size=5>"+"Current Result: "+element.getText()+"</font>");
	        	}
	        	else
	        	{
	        		LOGGER.info("Expected Result:\"Your authentication details have not been recognized or Alfresco may not be available at this time.\" message");	    			
	    			extent.log(LogStatus.INFO, "Expected Result:\"Your authentication details have not been recognized or Alfresco may not be available at this time.\" message");
	    			
	    			LOGGER.info("Current Result: " +element.getText());	    			
	    			extent.log(LogStatus.INFO, "<font color=red size=5>"+"Current Result: "+element.getText()+"</font>");
	        	}
	        	
	        }

	        return new ErrorMessageDialog(driver);
	  }*/
	  
	 
	 /**
		 * Login As User
		 * 
		 * @param username:
		 * 				UserName of User
		 * 
		 * @param password:
		 * 				Password of the UserName
		 * 
		 * @param className
		 *            : ClassName to Save ExtentReport
		 *            
		 * @param screenShot
		 *            : Save Image as screenShot 
		 * @throws IOException
		 * @throws InterruptedException
		 */
	 
	 public void tryInvalidLoginAttempt(String fullname,String username,String password,String className,String screenShot) throws InterruptedException, IOException
	 {        
		 	Thread.sleep(5000);
		 	
		 	LOGGER.info("Accessing the Login Page ");
	        //extent.log(LogStatus.INFO, "Accessing the Login Page ");
	        
	        LOGGER.info("Enter username "+username);
		   // extent.log(LogStatus.INFO, "Enter username "+username);
	        TextField userNameValue = new TextField(driver,By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
	        userNameValue.enterText(username);
	        
	        LOGGER.info("Enter password "+password);
	        //extent.log(LogStatus.INFO, "Enter password "+password);
	        TextField passwordValue = new TextField(driver,By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
	        passwordValue.enterText(password);	        
	        
	        LOGGER.info("Click \"Login\" ");
	        //extent.log(LogStatus.INFO, "Click \"Login\" ");
	        
	        Button btnLogn=new Button(driver,By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));	        
	       // Element.takescreenshot(driver,className,screenShot+"newlogin");
	        btnLogn.click();
	        Thread.sleep(3000);	        
	        Element.waitForLoad(driver);
	        
	        //Check if "Account Locked Message" Display or Not
	        
	        //Account Locked
	        if(Element.isElementPresent(driver,By.xpath("//div[@id='prompt_h'][text()='Failed to Login']")))
	        {
	        	Thread.sleep(5000);
	        	Span notification = new Span(driver, By.xpath("//div[@id='prompt']/descendant::div[@class='bd']"));
	            notification.getText();
	            //extent.log(LogStatus.INFO, "Message display as : "+"<font color=green>"+notification.getText() +"<font>");	        	
	        	//Element.takescreenshot(driver,className,screenShot+"FailedtoLogin");
	        	
	        	Button btnokay=new Button(driver,By.xpath("//button[text()='OK']"));
	        	btnokay.click();
		        
	        	//Wait 5 minutes
		        Thread.sleep(5 *60*1000);
		      //  extent.log(LogStatus.INFO, "After 5 Mins");
		        
		        driver.get(TestCaseProperties.LOGIN_SCREEN_URL);
		        LoginPage loginPage = new LoginPage(driver);		        
		        loginPage.loginAsTestUser();
		        Thread.sleep(3000);
		        
		        //Check whether Account is ReOpen or Not
		        
		        //Account is Reopen
		        if(Element.isElementPresent(driver,By.xpath("//Span[@id='HEADER_USER_MENU_POPUP_text'][text()='"+fullname+"']")))
		        {
		        	LOGGER.info("Successfully login As "+username);	    			
	    			//extent.log(LogStatus.PASS,"<font color=green>Successfully login As "+username+"<font>");    			
			      //  Element.takescreenshot(driver,className,screenShot+"login");
			        
			        Dashboard logout1=new Dashboard(driver);
			        logout1.logout();
			        
			        LOGGER.info("Successfully LOGOUT from user \""+username+" \"");	    			
	    			//extent.log(LogStatus.PASS,"<font color=green>Successfully LOGOUT from user \""+username+" \"<font>");    			
		        }
		        
		       //Account is NOT Reopen
		        else
		        {
		        	LOGGER.info("Displayed \"failed to Login\" Prompt message");	    			
	    			//extent.log(LogStatus.FAIL, "<font color=red> Displayed \"failed to Login\" Prompt message <font>");
	    		//	Element.takescreenshot(driver,className,screenShot+"loginFAIL");
		        }	        
		        
		       
	        }
	        //else if "authentication error message " IS NOT DISPLAY 
	        else if(!(Element.isElementPresent(driver,By.xpath("//div[@class='error']"))))
	        {
	        	Thread.sleep(5000);
	        	
		        if(Element.isElementPresent(driver,By.xpath("//Span[@id='HEADER_USER_MENU_POPUP_text'][text()='"+fullname+"']")))
		        {
		        	LOGGER.info("Successfully login As "+username);	    			
	    			//extent.log(LogStatus.PASS,"<font color=green>Successfully login As "+username+"<font>");    			
			       // Element.takescreenshot(driver,className,screenShot+"login1");
			        
			        Dashboard logout=new Dashboard(driver);
			        logout.logout();
			        
			        LOGGER.info("Successfully LOGOUT from user \""+username+" \"");	    			
	    			//extent.log(LogStatus.PASS,"<font color=green>Successfully LOGOUT from user \""+username+" \"<font>");    			
			        
			        			       
		        }
		        else
		        {
		        	LOGGER.info("Displayed \"failed to Login\" Prompt message");	    			
	    			//extent.log(LogStatus.FAIL, "<font color=red> Displayed \"failed to Login\" Prompt message <font>");
	    			//Element.takescreenshot(driver,className,screenShot+"loginFAIL1");
		        }	        
	        }
	        //else if "authentication error message " IS DISPLAY 
	        else
	        {
	        	Element element=new Element(driver, By.xpath("//div[@class='error']"));
	        	if(Element.isTextPresentInElement(element,"Your authentication details have not been recognized or Alfresco may not be available at this time."))
	        	{
	        		Thread.sleep(5000);
	        		LOGGER.info("Expected Result: Display message as  \"Your authentication details have not been recognized or Alfresco may not be available at this time.\"");
	        		LOGGER.info("Current Result: Message Display as  "+element.getText());
	        		
	    			//extent.log(LogStatus.INFO,"Expected Result: Display message as\"Your authentication details have not been recognized or Alfresco may not be available at this time.\"");
	    			//extent.log(LogStatus.INFO,"Current Result: Message Display as  "+"<font color=green>" +element.getText()+"<font>");
	    			//Element.takescreenshot(driver,className,screenShot+"PROMPT1");   			
	    			
	        	}
	        	else
	        	{
	        		Thread.sleep(5000);
	        		LOGGER.info("Expected Result:\"Your authentication details have not been recognized or Alfresco may not be available at this time.\"");	
	        		LOGGER.info("Current Result: Message Display as  " +element.getText());
	        		
	    			//extent.log(LogStatus.INFO,"Expected Result: \"Your authentication details have not been recognized or Alfresco may not be available at this time.\" ");  				    			
	    			//extent.log(LogStatus.INFO,"Current Result: Message Display as  "+"<font color=red>" +element.getText()+"<font>");
	    		//	Element.takescreenshot(driver,className,screenShot+"PROMPT2");
	        	}
	        	
	        }

	  }
	 public ErrorMessageDialog tryInvalidLoginAttempt() throws InterruptedException
     {
            Thread.sleep(2000);
            TextField userName = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
            TextField password = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
            Button btnLogn = new Button(driver, By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));

            userName.clearText();
            Thread.sleep(500);
            password.clearText();
            Thread.sleep(500);
            //login as test
            userName.enterText(TestCaseProperties.TEST_USER_NAME);
            password.enterText(TestCaseProperties.INVALID_PASSWORD);
            btnLogn.click();

            return new ErrorMessageDialog(driver);
        }
	 
	 /**
	  * Try Login as the Blocked user 
	  * @return
	  * @throws Exception
	  */
	 public ErrorMessageDialog tryToLoginAsBlockedUser() throws Exception 
     {
            //try to login as test
            Thread.sleep(3000);
            TextField userName = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
            TextField password = new TextField(driver, By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
            Button btnLogn = new Button(driver, By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));

            userName.clearText();
            Thread.sleep(500);
            password.clearText();
            Thread.sleep(500);
            //login as test
            userName.enterText(TestCaseProperties.TEST_USER_NAME);
            password.enterText(TestCaseProperties.TEST_PASSWORD);
            btnLogn.click();

            //wait until login fail message box
            ExplicitWait wait = new ExplicitWait(driver);
            wait.waitUntilElementAppears(By.xpath("//div[@id='prompt_h']"), 5);
         
            Thread.sleep(3000);
            return new ErrorMessageDialog(driver);
        }
	 
	   /**
		 * check Reset Password Link Availability
		 * @param resetLinkAvalilability
		 * 
		 * @param className
		 *            : ClassName to Save ExtentReport
		 *            
		 * @param screenShot
		 *            : Save Image as screenShot 
		 * @throws InterruptedException
		 * @throws IOException
		 */
	 
	 public void checkReset(Boolean resetLinkAvalilability,String className,String screenShot) throws InterruptedException, IOException
	 {
		 //If the Requirement IS reset Link Avalilability== TRUE
		if(resetLinkAvalilability==true)
		{
			//Check whether  reset Password Link element is Present
			if(Element.isElementPresent(driver, By.xpath("//a[@id='page_x002e_components_x002e_slingshot-login_x0023_default-btn-resetpassword'][text()='Reset password']")))
			{
				//Check whether  reset Password Link IS DISPLAYED
				
				//Link is Displayed
				if( driver.findElement(By.xpath("//a[@id='page_x002e_components_x002e_slingshot-login_x0023_default-btn-resetpassword'][text()='Reset password']")).isDisplayed())
				{
					LOGGER.info("Reset Link is Available");    		
					//extent.log(LogStatus.PASS,"<font color=green>Reset Link is Available<font>");
					//Element.takescreenshot(driver,className,screenShot+"resetavailable3");
				}
				
				//Link is NOT Displayed
				else
				{
					LOGGER.info("Reset Link IS NOT Available");    		
					//extent.log(LogStatus.FAIL,"<font color=red>Reset Link IS NOT Available<font>");
					//Element.takescreenshot(driver,className,screenShot+"resetNotAvailable3");
				}				
				
					
			}
			
			//If Reset Link Is not Available
			else if(!(Element.isElementPresent(driver, By.xpath("//a[@id='page_x002e_components_x002e_slingshot-login_x0023_default-btn-resetpassword'][text()='Reset password']"))))
			{
				LOGGER.info("Reset Link IS NOT Available");    		
				//extent.log(LogStatus.FAIL,"<font color=red>Reset Link IS NOT Available<font>");
				//Element.takescreenshot(driver,className,screenShot+"resetNotAvailable");
			}
		}
		//If the Requirement IS reset Link Avalilability== false
		else if(resetLinkAvalilability==false)
		{
			//Check whether  reset Password Link element is Present
			if(Element.isElementPresent(driver, By.xpath("//a[@id='page_x002e_components_x002e_slingshot-login_x0023_default-btn-resetpassword'][text()='Reset password']")))
			{
				
				//Check whether  reset Password Link IS DISPLAYED
				
				//Link is Displayed
				if( driver.findElement(By.xpath("//a[@id='page_x002e_components_x002e_slingshot-login_x0023_default-btn-resetpassword'][text()='Reset password']")).isDisplayed())
				{
					LOGGER.info("Reset Link is Available");    		
					//extent.log(LogStatus.FAIL,"<font color=red>Reset Link is Available<font>");
				//	Element.takescreenshot(driver,className,screenShot+"resetavailable1");
				}
				//Link is NOT Displayed
				else
				{
					LOGGER.info("Reset Link IS NOT Available");    		
					//extent.log(LogStatus.PASS,"<font color=green>Reset Link IS NOT Available<font>");
					///Element.takescreenshot(driver,className,screenShot+"resetNotAvailable2");
				}				
						
				
			}
			//If Reset Link Is not Available
			else if(!(Element.isElementPresent(driver, By.xpath("//a[@id='page_x002e_components_x002e_slingshot-login_x0023_default-btn-resetpassword'][text()='Reset password']"))))
			{
				LOGGER.info("Reset Link IS NOT Available");    		
				//extent.log(LogStatus.PASS,"<font color=green>Reset Link IS NOT Available<font>");
				//Element.takescreenshot(driver,className,screenShot+"resetNotAvailable1");
			}
		}
		
		
	 }
	 
	 public void checkCaptcha(Boolean resetLinkAvalilability,Boolean cpatchaAvalilability,String className,String screenShot) throws InterruptedException, IOException
	 {		
		
		 //If the Requirement IS reset Link Avalilability== TRUE And ResetLink Captcha Availability==TRUE
		 if(resetLinkAvalilability==true && cpatchaAvalilability==true)
		 {
			//Click Reset link
			Link reset=new Link(driver,By.xpath("//a[@id='page_x002e_components_x002e_slingshot-login_x0023_default-btn-resetpassword'][text()='Reset password']"));
			reset.click();
			Thread.sleep(4000);
			
			//Check Whether Captcha Is Present Or NOT
			
			//Captcha is Not Available
			if(!(Element.isElementPresent(driver,By.xpath("//div[@id='recaptcha_area']"))))
			{
				LOGGER.info("Captcha IS NOT Available");    		
				//extent.log(LogStatus.FAIL,"<font color=red>Captcha IS NOT Available<font>");
				//Element.takescreenshot(driver,className,screenShot+"captchaNotavailable");
			}
			//Captcha is  Available
			else if((Element.isElementPresent(driver,By.xpath("//div[@id='recaptcha_area']"))))
			{
				LOGGER.info("Captcha IS AVAILABLE");    		
				//extent.log(LogStatus.PASS,"<font color=green>Captcha IS AVAILABLE<font>");
				//Element.takescreenshot(driver,className,screenShot+"captchaavailable");
			}  
		 }
		 
		//If the Requirement IS reset Link Avalilability== TRUE And ResetLink Captcha Availability==False
		 else if(resetLinkAvalilability==true && cpatchaAvalilability==false)
		 {
			//Click Reset link
			 Link reset=new Link(driver,By.xpath("//a[@id='page_x002e_components_x002e_slingshot-login_x0023_default-btn-resetpassword'][text()='Reset password']"));
			 reset.click();
			 Thread.sleep(4000);
				
			//Check Whether Captcha Is Present Or NOT
				
			//Captcha is Not Available
			if(!(Element.isElementPresent(driver,By.xpath("//div[@id='recaptcha_area']"))))
			{
				LOGGER.info("Captcha IS NOT Available");    		
				//extent.log(LogStatus.PASS,"<font color=green>Captcha IS NOT Available<font>");
				//Element.takescreenshot(driver,className,screenShot+"captchaNotavailableab");
			}
			//Captcha is  Available
			else if((Element.isElementPresent(driver,By.xpath("//div[@id='recaptcha_area']"))))
			{
				LOGGER.info("Captcha IS AVAILABLE");    		
				//extent.log(LogStatus.FAIL,"<font color=red>Captcha IS AVAILABLE<font>");
				//Element.takescreenshot(driver,className,screenShot+"captchaavailablecd");
				
			}  
		 }
		 
		//If the Requirement IS reset Link Avalilability== False And ResetLink Captcha Availability==False
		 else if(resetLinkAvalilability==false && cpatchaAvalilability==false)
		 {		 
			
			//Check Whether Captcha Is Present Or NOT
				
		    //Captcha is Not Available
			if(!(Element.isElementPresent(driver,By.xpath("//div[@id='recaptcha_area']"))))
			{
				LOGGER.info("Captcha IS NOT Available");    		
				//extent.log(LogStatus.PASS,"<font color=green>Captcha IS NOT Available<font>");
				//Element.takescreenshot(driver,className,screenShot+"captchaNotavailable1");
			}
			//Captcha is  Available
			else if((Element.isElementPresent(driver,By.xpath("//div[@id='recaptcha_area']"))))
			{
				LOGGER.info("Captcha IS AVAILABLE");    		
				//extent.log(LogStatus.FAIL,"<font color=red>Captcha IS AVAILABLE<font>");
				//Element.takescreenshot(driver,className,screenShot+"captchaavailable1");
				
			}  
		 }
		 
	 }
	 
	 
	
}
