package com.wise.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wise.utility.GenericRepositery;

public class ClassRoomPage {
	GenericRepositery generic=new GenericRepositery();
	public ClassRoomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[text()='Schedule sessions ']")
	WebElement scheduleSessions;
	@FindBy(xpath = "//div[text()='Schedule sessions']")
	public
	WebElement scheduleSessionsText;
	@FindBy(xpath = "//span[text()='Add session ']")
	WebElement addSessionButton;
	@FindBy(xpath = "//div[text()='PM']")
	WebElement PMbutton;
	@FindBy(xpath = "//div[text()='AM']")
	WebElement AMbutton;
	@FindBy(xpath = "//div[@class='d-flex timing-card']//input[@type='text']")
	WebElement timingTextField;
	@FindBy(xpath = "//span[text()=' Create ']")
	WebElement createButton;
	@FindBy(xpath = "(//span[contains(text(),'Upcoming')])[1]")
	WebElement upcomingButton;
	@FindBy(xpath = "//div[@class='text--16 text-center']")
	public WebElement calender;
	@FindBy(xpath = "(//i[@class='v-icon notranslate mdi mdi-chevron-right theme--light'])[1]")
    WebElement forwardArrow;
	@FindBy(xpath = "//div[text()='1']")
    WebElement staticData;
	public WebElement getcelendericon() {
		return staticData;	
	}
	public WebElement getstaticDate() {
		return calender;	
	}
	public WebElement getforwardArrow() {
		return forwardArrow;	
	}
	public WebElement getScheduleSessionsButton() {
		return scheduleSessions;	
	}
	public WebElement getAddSessionButtonButton() {
		return addSessionButton;	
	}
	public WebElement getPMtimingButton() {
		return PMbutton;	
	}
	public WebElement getAMtimingButton() {
		return AMbutton;	
	}
	public WebElement getTimingTextField() {
		return timingTextField;	
	}
	public WebElement getCreateButton() {
		return createButton;	
	}
	public WebElement getUpcomingButton() {
		return upcomingButton;	
	}

	//Method to get the Dynamic Xpath
	public WebElement classRoomTabs(Object tabs) {
		return generic.elementName("//a[text()= '%s']", tabs);  
	}
	//Method to get the Dynamic Xpath
	public WebElement instituteName(Object instituteName) {
		return generic.elementName("//div[text()= '%s']", instituteName);  
	}
	//Method to get the Dynamic Xpath
	public WebElement timeOfSession(Object timeSession) {
		return generic.elementName("//div[contains(text(), '%s')]", timeSession);  
	}





}
