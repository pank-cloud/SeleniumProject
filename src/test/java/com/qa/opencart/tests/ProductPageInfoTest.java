package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{

	@BeforeClass
	public void productInfoPageSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData()
	{
		return new Object[][]
				{
			         {"iMac", "iMac", 3},
			         {"Apple", "Apple Cinema 30\"", 6},
			         {"Samsung", "Samsung SyncMaster 941BW", 1},
			         {"Macbook", "MacBook Pro", 4}
				};
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount)
	{
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	
// =============== Assignment ===============//
	
//	@DataProvider
//	public Object[][] getTitleTestData()
//	{
//		return new Object[][]
//				{
//			        {"MacBook Pro"}
//				};
//	}
//	
//	@Test(priority = 2, dataProvider = "getTitleTestData")
//
//	public void titlePresentTest(String title) 
//	{		
//		Assert.assertEquals(title, productInfoPage.isTitlePresent());
//		//Assert.assertTrue(productInfoPage.isTitlePresent());
//	}
//	
//	@Test(priority = 3)
//	public void productDescriptionCountTest()
//	{
//		int descCount = productInfoPage.getDescriptionCount();
//		Assert.assertTrue(descCount > 0);
//	}
	
	@DataProvider
	public Object[][] getProductInfoTestData()
	{
		return new Object[][]
				{
			       {"MacBook", "MacBook Pro", "MacBook Pro", "Apple", "Product 18", "$2,000.00"},
			       {"MacBook", "MacBook Air", "MacBook Air", "Apple", "Product 17", "$1,202.00"}
				};
	}
	
	@Test(dataProvider = "getProductInfoTestData")
	public void productInfoTest(String searchKey, String productSelect, String nameOfProduct, String brandName, String product_code, String PriceProduct)
	{
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productSelect);
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("productName"), nameOfProduct);
		softAssert.assertEquals(actProductInfoMap.get("Brand"), brandName);
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), product_code);
		softAssert.assertEquals(actProductInfoMap.get("ProductPrice"), PriceProduct);
		softAssert.assertAll();
	}
	
	
	@DataProvider
	public Object[][] getCartTestData()
	{
		
		return new Object[][]
				{
			        {"MacBook", "MacBook Pro", 1},
			        {"iMac", "iMac", 2}
				};		
	}
	
	@Test(dataProvider = "getCartTestData")
	public void addToCartTest(String searchKey, String productName, int quantity)
	{
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		productInfoPage.enterQuantity(quantity);
		String actCartMesg = productInfoPage.addProductToCart();
		
		softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMesg.indexOf(productName)>=0);
		
		softAssert.assertEquals(actCartMesg, "Success: You have added " +productName+ " to your shopping cart!");
	
	    softAssert.assertAll();
	}

}
