package com.wise.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wise.utility.GenericRepositery;

public class LogOutPage {
	GenericRepositery generic=new GenericRepositery();
	public LogOutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[text()='Account']")
	WebElement account;
	@FindBy(xpath = "//div[text()='Logout']")
	WebElement logout;
	@FindBy(xpath = "//div[text()='Create account']")
	public
	 WebElement createAccountText;
	public WebElement getAccountTab() {
		return account;	
	}
	public WebElement getLogout() {
		return logout;	
	}
	

}
