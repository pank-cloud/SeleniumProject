package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//locators
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	
	private By agreeCheckBox = By.name("agree");
    private By continueButton = By.xpath("//input[@type='submit']"); 
	
    private By registerSuccessMesg = By.cssSelector("div#content h1");
    
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");
    
    //constructor
    public RegisterPage(WebDriver driver)
    {
            this.driver = driver;
            eleUtil = new ElementUtil(driver);
    }
    
    //method
    public boolean registerUser(String firstName, String lastName, 
    		String emailId, String telephone, String password, String subscribe)
    {
    	eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstName);
    	eleUtil.doSendKeys(this.lastName, lastName);
    	eleUtil.doSendKeys(this.emailId, emailId);
    	eleUtil.doSendKeys(this.telephone, telephone);
    	eleUtil.doSendKeys(this.password, password);
    	eleUtil.doSendKeys(this.confirmpassword, password);
		
    	if(subscribe.trim().equalsIgnoreCase("Yes"))
    	{
    		eleUtil.doClick(subscribeYes);
    	}
    	
    	else
    	{
    		eleUtil.doClick(subscribeNo);
    	}
    	
    	eleUtil.doActionsClick(agreeCheckBox);
    	eleUtil.doClick(continueButton);
    	
    	String successMesg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
    	System.out.println("user reg. success mesg : " +successMesg);
    	
    	if(successMesg.contains(AppConstants.USER_REG_SUCCESS_MESSG))
    	{
    	eleUtil.doClick(logoutLink);
    	eleUtil.doClick(registerLink);
    	return true;
    	}
     return false;	
    }
    
    
}
