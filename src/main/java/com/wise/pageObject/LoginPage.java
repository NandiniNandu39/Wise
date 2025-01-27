/**
 * 
 */
package com.wise.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wise.actionsDriver.Action;

/**
 * 
 */
public class LoginPage {
		
		public LoginPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		@FindBy(xpath = "//div[text()='Create account']")
		public
		 WebElement createAccountText;
		@FindBy(xpath = "//img[@src='/svgs/icon/login/mobile.svg']")
		 WebElement continueWithMobileButton;
		@FindBy(xpath = "//div[text()='Please enter your phone number to continue']/preceding-sibling::div[text()='Phone Number']")
		public
		 WebElement phoneNumberText;
		@FindBy(xpath = "//input[@placeholder='Phone number']")
		 WebElement phoneNumberTextField;
		@FindBy(xpath = "//span[text()=' Get OTP ']")
		 WebElement  otpButton;
		@FindBy(xpath = "//p[text()='Enter OTP']")
		public
		 WebElement  enterOTPtext;
		@FindBy(xpath = "//input[@id='input-35--0']")
		WebElement  enterOTP1;
		@FindBy(xpath = "//input[@id='input-35--1']")
		 WebElement  enterOTP2; 
		@FindBy(xpath = "//input[@id='input-35--2']")
		WebElement  enterOTP3;
		@FindBy(xpath = "//input[@id='input-35--3']")
		WebElement  enterOTP4;
		
		@FindBy(xpath = "//span[text()=' Verify ']")
		 WebElement  verifyOTPButton;
		public WebElement getContinuewithMobileNumberButton() {
			return continueWithMobileButton;	
		}
		public WebElement getPhoneNumTextField () {
			return phoneNumberTextField;	
		}
		public WebElement getOTPbutton() {
			return otpButton;
		}
		public WebElement getEnterotpTextfiled1() {
			return enterOTP1;
		}
		public WebElement getEnterotpTextfiled2() {
			return enterOTP2;
		}
		public WebElement getEnterotpTextfiled3() {
			return enterOTP3;
		}
		public WebElement getEnterotpTextfiled4() {
			return enterOTP4;
		}
		
		public WebElement getVerifyotpButton() {
			return verifyOTPButton;
		}
		
		
	

}
