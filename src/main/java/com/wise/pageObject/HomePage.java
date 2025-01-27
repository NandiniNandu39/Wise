package com.wise.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wise.utility.GenericRepositery;

public class HomePage {
	GenericRepositery generic=new GenericRepositery();
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[text()='Testing Institute']")
	public
	 WebElement instituteNameText;
	@FindBy(xpath = "//span[text()='Group courses ']")
	 WebElement groupCourceTab;
	@FindBy(xpath = "//div[text()='Group courses']")
	public
	 WebElement groupCourcetext;
	public WebElement getGroupCourseTab() {
		return groupCourceTab;
	}
	//Method to get the Dynamic Xpath
	public WebElement classRoominGroupCourceLins(Object classRoom) {
		return generic.elementName("//a[contains(text(), '%s')]", classRoom);  
	}
	//Method to get the Dynamic Xpath
	public WebElement classRoomTextinGroupCource(Object classRoom) {
		return generic.elementName("//div[text()= '%s']", classRoom);  
	}
	
	
}
