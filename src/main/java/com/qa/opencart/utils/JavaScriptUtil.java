package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void flash(WebElement ele)
	{
		String bgcolor = ele.getCssValue("backgroundColor");
		for(int i = 0 ; i<10 ; i++)
		{
			changeColor("rgb(0,200,0)",ele );
			changeColor(bgcolor, ele);
		}
	}
	public void changeColor(String color, WebElement ele)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].style.backgroundColor = '" +color+ "'", ele);
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	    }
		
		public String getTitleByJS()
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			return js.executeScript("return document.title;").toString(); 
		}
		
		public void goBackByJS()
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("history.go(-1)");
		}
		
		public void goForwardByJS()
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("history.go(1)");
		}
		
		public void refreshBrowserByJS()
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("history.go(0)");
		}
		
		public void generateAlert(String message)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("alert ('" +message+ "')");			
		}
		
		public void generateConfirmPopup(String message)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("confirm('" +message+ "')");
		}
		
		public String getPageInnerText()
		{
		   JavascriptExecutor js = (JavascriptExecutor)driver;
			return js.executeScript("return document.documentElement.innerText;").toString();
		}
		
		public void clickElementByJS(WebElement ele)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", ele);
		}
		
		public void sendKeysUsingWithId(String id, String value)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("document.getElementById('" +id+ "').value = '" +value+ "'");			
		}
		
		public void ScrollPageDown()
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
		
		public void ScrollPageDown(String height)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, '" +height+ "')");
		}
		
		public void scrollPageUp()
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		}
		
		public void ScrollPageDownMiddlePage()
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
		}
		
		public void ScrollIntoView(WebElement ele)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		}
		
		public void drawBorder(WebElement ele)
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].style.border='3px solid red'", ele);
		}
		
	}


