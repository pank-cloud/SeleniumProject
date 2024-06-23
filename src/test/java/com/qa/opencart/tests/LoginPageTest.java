package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest

{

	@Test(priority=1)
	public void LoginPageTitleTest()
	{
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Test(priority=2)
	public void LoginPageUrlTest()
	{
		String actualUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority=3)
	public void ForgotPwdLinkExistTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	
	@Test(priority=4)
	public void PageLogoTest()
	{
		Assert.assertTrue(loginPage.PageLogo());
	}
	
	@Test(priority=5)
	public void IsSearchExistTest()
	{
		Assert.assertTrue(loginPage.IsSearchExist());
	}
	
	@Test(priority=5)
	public void IsSiteMapExistTest()
	{
		Assert.assertTrue(loginPage.IsSiteMapExist());
	}
	
	@Test(priority=6)
	public void RegisterLinkExist()
	{
		Assert.assertTrue(loginPage.RegisterLinkExist());
	}
	
	@Test(priority=7)
	public void LoginTest()
	{
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		//In manual testing also, if i am successfully logged in then 
		// logout button will be visible.
		
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
	
}
