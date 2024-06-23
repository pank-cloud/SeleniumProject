package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

    private WebDriver driver; 
	private ElementUtil eleUtil;
	
	private Map<String, String> productInfoMap;
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMessg = By.cssSelector("div.alert.alert-success");
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By pageTitle = By.cssSelector("head title");
	private By descriptionHeading = By.cssSelector("div#tab-description b");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("Product Header : " +productHeaderVal);
		return productHeaderVal;
	}
	
	public int getProductImagesCount()
	{
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product Images Count" +imagesCount);
		return imagesCount;
	}
	
	public String isTitlePresent()
	{
		String title = eleUtil.PageTitle();
		return title;
	}
	
	public int getDescriptionCount()
	{
		int descCount = eleUtil.waitForElementsVisible(descriptionHeading, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		return descCount;
	}
	
	public Map<String, String> getProductInfo()
	{
	productInfoMap = new LinkedHashMap<>();
	//header 
	productInfoMap.put("productName", getProductHeaderValue());
	
	getProductMetaData();
	getProductPriceData();
	System.out.println(productInfoMap);
	return productInfoMap;
}
	
	private void getProductMetaData() 
	{
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		
		for(WebElement e : metaList)
		{
			String meta = e.getText();
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			
			productInfoMap.put(key, value);
		}		
	}
	
	private void getProductPriceData()
	{
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		String exTaxInfo[] = exTax.split(":");
        String exTaxVal = exTaxInfo[1].trim();
        
        productInfoMap.put("ProductPrice", price);
        productInfoMap.put("ExTax", exTaxVal);
	}
	
	public void enterQuantity(int qty)
	{
		System.out.println("Product Quantity : " +qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	
	public String addProductToCart()
	{
		eleUtil.doClick(addToCartBtn);
		String successMessg = eleUtil.waitForElementVisible(cartSuccessMessg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
	    StringBuilder sb = new StringBuilder(successMessg);
	    String mesg = sb.substring(0, successMessg.length()-1).replace("\n", "").toString();
	    System.out.println("Cart Success Mesg " +mesg);
	    return mesg; 
	
	}
	
}
	
