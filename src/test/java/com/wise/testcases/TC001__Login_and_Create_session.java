package com.wise.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.wise.actionsDriver.Action;
import com.wise.actionsDriver.Variables;
import com.wise.base.BaseClass;
import com.wise.base.ExtentTestNGITestListener;
import com.wise.dataprovider.TestData;
import com.wise.pageObject.ClassRoomPage;
import com.wise.pageObject.HomePage;
import com.wise.pageObject.LoginPage;

@Listeners(ExtentTestNGITestListener.class)
public class TC001__Login_and_Create_session extends BaseClass {

	@Test
	public void login_Create_Session() throws IOException, InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		Variables variables = new Variables();
		HomePage home=new HomePage(driver);
		ClassRoomPage classRoomPage=new ClassRoomPage(driver);
		variables.filePath = prop.getProperty("filePath");
		if (variables.filePath == null || variables.filePath.isEmpty()) {
			throw new IOException("File path is not set in properties file");
		}

		Map<String, String> commonData = TestData.loadData(variables.filePath, "Wise_Test_Data", "TC01");
		System.out.println("Loaded commonData: " + commonData);

		String createAccount = TestData.getValueByKey(commonData, "CreateAccount_Text");

		WebElement createAccountText = loginPage.createAccountText;
		Assert.assertEquals(createAccount, createAccountText.getText(), "Text comparsion Create Account");
		Action.click(driver, loginPage.getContinuewithMobileNumberButton());
		Action.isElementDisplayed(driver, loginPage.phoneNumberText);
		variables.mobileNumber=prop.getProperty("MobileNumber");
		Action.type(driver, loginPage.getPhoneNumTextField(), variables.mobileNumber);
		Action.click(driver, loginPage.getOTPbutton());
		Action.waitTillVisible(driver, loginPage.enterOTPtext);
		variables.otp=prop.getProperty("OTP");
		Action.type(driver, loginPage.getEnterotpTextfiled1(),variables.otp );
		Action.type(driver, loginPage.getEnterotpTextfiled2(),variables.otp );
		Action.type(driver, loginPage.getEnterotpTextfiled3(),variables.otp );
		Action.type(driver, loginPage.getEnterotpTextfiled4(),variables.otp );
		Action.click(driver, loginPage.getVerifyotpButton());
		Action.waitTillVisible(driver,home.instituteNameText);
		String instituteText = TestData.getValueByKey(commonData, "Institute_Name");
		Assert.assertEquals(instituteText,home.instituteNameText.getText(), "Text comparsion in Institute Name");
		Action.click(driver, home.getGroupCourseTab());
		Action.isElementDisplayed(driver, home.groupCourcetext);
		String classRoom = TestData.getValueByKey(commonData, "ClassRoom_Name");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), 'Classroom for Automated testing')]")));
		Action.click(driver,home.classRoominGroupCourceLins(classRoom));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()= 'Classroom for Automated testing']")));
		Assert.assertEquals(classRoom,home.classRoomTextinGroupCource(classRoom).getText(), "Text comparsion Create Account");
		String classRoomTab = TestData.getValueByKey(commonData, "ClassRoom_Tab");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Live Sessions']")));
		Action.click(driver,classRoomPage.classRoomTabs(classRoomTab));
		Action.waitTillClickable(driver,classRoomPage.getScheduleSessionsButton());
		Action.click(driver,classRoomPage.getScheduleSessionsButton());
		Action.isElementDisplayed(driver,classRoomPage.scheduleSessionsText);
		Action.waitTillClickable(driver,classRoomPage.getAddSessionButtonButton());
		Action.click(driver,classRoomPage.getAddSessionButtonButton());       
		Action.click(driver,classRoomPage.getTimingTextField());
		Action.clearTheData(classRoomPage.getTimingTextField());
		Action.type(driver,classRoomPage.getTimingTextField(),variables.timing);
		Action.pressEnter() ;
		try {
			
		    WebElement element = classRoomPage.getPMtimingButton(); 
		    System.out.println(element+" Element is present.");
		} catch (NoSuchElementException e) {
		    System.out.println("Element is not present. Clicking on another element.");
		    WebElement anotherElement = classRoomPage.getAMtimingButton(); 
		    anotherElement.click();
		}

		Action.waitTillClickable(driver,classRoomPage.getCreateButton());
		Action.click(driver,classRoomPage.getCreateButton());
		String tabInClassRoom = TestData.getValueByKey(commonData, "Tab_ClassRoom");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Timeline']")));
		Action.click(driver,classRoomPage.classRoomTabs(tabInClassRoom));
		String instituteName = TestData.getValueByKey(commonData, "Institute_Name_Class");
		Assert.assertEquals(instituteName,classRoomPage.instituteName(instituteName).getText(), "Text comparsion in Institute Name");
		Action.scrollByVisibilityOfElement(driver, classRoomPage.getUpcomingButton());
		Action.isElementDisplayed(driver,classRoomPage.getUpcomingButton() );
		Assert.assertTrue(
			    classRoomPage.timeOfSession(variables.timing).getText().contains(variables.timing),
			    "The session time does not contain the expected value: " + variables.timing
			);	}
}
