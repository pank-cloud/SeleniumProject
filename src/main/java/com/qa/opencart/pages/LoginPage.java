package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	//POM says that the first thing we have to maintain is private by locators
	//& class reference variables.
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private by locators
	private By EmailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By PageLogo = By.xpath("//img[@title='naveenopencart']");
	private By IsSearchExist = By.xpath("//input[@name='search']");
	private By IsSiteMapExist = By.linkText("Site Map");
	private By RegisterLinkExist = By.xpath("//h2[text()='New Customer']");
	
	private By registerLink = By.linkText("Register");
	
	//whenever you create any page, every page will have its own driver also.
	//Don't do unnecessary inheritence (remember). Why i am saying because you
	//might think that lets inherit DriverFactory class because i cannot say that
	//every loginpage is a driverfactory. In order to supply driver to the 
	// private webdriver driver.so we can create one public constructor and 
	// this public constructor says that whenever you have to create the object 
	// of this LoginPage class, this constructor will be called, you have to give 
	//me the driver. same driver i have to give to private webdriver driver. so
	//for that we have to use this keyword.
	
	//page constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//page actions/ methods
	public String getLoginPageTitle()
	{
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login page title: " +title);
		return title;
	}
	
	public String getLoginPageUrl()
	{
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page url: " +url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed(); //encapsulation
	}
	
	// a private class variable is used in public method so encapsulation
	//concept is used. public method could be getter setter or normal method also)
	
	//why are we creating private variable?
	// if i am making these variables public, dont you think that anyone can
	//access or change the value of un, pw or forgotpwdlink. That's why we are
	//making it as private so that no one can access or change the values. 
	//No one can get the raw url of the forgot pwd link. So i am saying that 
	//ForgotPwd link cannot be used by other pages.Otherwise From other pages like
	//addToCart page, if we are making public variable in loginPage class then other
	//dev might start creating the obj of loginPage class & then they will have 
	//direct access to email, password, loginBtn but we dont want to give access
	//of locators to any other class. That's why we have created private variables.
	
	
	public AccountsPage doLogin(String un, String pw)
	{
		System.out.println("App credentials are : " + un + " : " + pw );
		eleUtil.waitForElementVisible(EmailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pw);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public boolean PageLogo()
	{
		return driver.findElement(PageLogo).isDisplayed();
	}
	
	public boolean IsSearchExist()
	{
		return driver.findElement(IsSearchExist).isDisplayed();
	}
	
	public boolean IsSiteMapExist()
	{
		return driver.findElement(IsSiteMapExist).isDisplayed();
	}
	
	public boolean RegisterLinkExist()
	{
		return driver.findElement(RegisterLinkExist).isDisplayed();
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
}
