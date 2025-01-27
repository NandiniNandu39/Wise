/**
 * 
 */
package com.wise.actionsDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wise.base.BaseClass;

/**
 * 
 */
public class Action extends BaseClass {
	public static void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}

	public static void click(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().perform();
	}

	public static boolean isElementDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Element is Displayed successully");
			} else {
				System.out.println("Element is not Displayed successully");
			}
		}
		return flag;

	}

	public static boolean isSelected(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = isElementDisplayed(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				System.out.println("The element is Selected");
			} else {
				System.out.println("The element is not Selected");
			}
		}
		return flag;
	}

	public static boolean isEnable(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = isElementDisplayed(driver, ele);
		if (flag) {
			flag = ele.isEnabled();
			if (flag) {
				System.out.println("The element is Enabled");
			} else {
				System.out.println("The element is not Enabled");
			}
		}
		return flag;
	}

	public static boolean type(WebDriver driver, WebElement ele, String text) {
		boolean flag = false;

		try {
			if (ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(text);
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Location not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered the value");
			} else {
				System.out.println("Failed to enter the value");
			}
		}

		return flag;
	}

	public static boolean selectByIndex(WebElement element, int Index) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByIndex(Index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option Selected by index");
			} else {
				System.out.println("Option not selected by index");
			}
		}
	}

	public static boolean selectByValue(WebElement element, String value) {
		boolean flag = false;
		try {
			Select s = new Select(element);

			// Check if the value is numeric and should be treated as an integer
			if (value.matches("-?\\d+(\\.\\d+)?")) {
				// Try parsing the value as a double
				double numericValue = Double.parseDouble(value);
				// Check if the numeric value is an integer
				if (numericValue == (int) numericValue) {
					// Format the value as an integer string
					value = String.valueOf((int) numericValue);
				}
			}

			System.out.println(value);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option Selected by Value");
			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}


	public static boolean selectByVisibleText(WebElement element, String text) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByVisibleText(text);
			flag = true;
			return true;

		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option Selected by Visible Text ");
			} else {
				System.out.println("Option not selected by Visible Text");
			}
		}
	}

	public static boolean JSClick(WebDriver driver, WebElement element) {
		boolean flag = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Clicked on the Element Successfully");
			} else {
				System.out.println("Failed to clicked on the Element");
			}
		}
		return flag;
	}

	public static void waitTillVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50)); // Initialize WebDriverWait with a timeout of 10 seconds
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void waitTillClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void pressEnter() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
	}

	public static boolean switchToFrameByIndex(int Index) {
		boolean flag = false;
		try {
			driver.switchTo().frame(Index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame Switching do Successfully");
			} else {
				System.out.println("Failed to switch the frame");
			}
		}

	}

	public static boolean switchToFrameByIdOrName(String value) {
		boolean flag = false;
		try {
			driver.switchTo().frame(value);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame Switching do Successfully");
			} else {
				System.out.println("Failed to switch the frame");
			}
		}

	}
	public static void clearTheData(WebElement ele) {
		try {
			ele.clear();
			System.out.println("Input field cleared successfully");
		} catch (Exception e) {
			System.out.println("Failed to clear the input field: " + e.getMessage());
		}
	}

	public static void selectNextDay(WebDriver driver, WebElement wb) {
		try {
			// Step 1: Get today's date
			LocalDate today = LocalDate.now();

			// Step 2: Calculate the next day
			LocalDate nextDay = today.plusDays(1);

			// Step 3: Format the date to match the calendar's format (e.g., "dd-MM-yyyy")
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE d MMM");
			String nextDayString = nextDay.format(formatter);

			// Step 4: Open the calendar (if necessary)
			WebElement calendar = wb;
			calendar.click();

			// Step 5: Select the next day
			WebElement nextDayElement = driver.findElement(By.xpath("//td[text()='" + nextDayString + "']"));
			nextDayElement.click();

			System.out.println("Selected the next day: " + nextDay);
		} catch (Exception e) {
			System.out.println("Failed to select the next day: " + e.getMessage());
		}
	}
	public static boolean switchToFrameByFrameElement(WebElement iFrameClose) {
		boolean flag = false;
		try {
			driver.switchTo().frame(iFrameClose);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame Switching do Successfully");
			} else {
				System.out.println("Failed to switch the frame");
			}
		}
	}

	public static boolean switchToDefaultFrame() {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Switched to Default Frame");
			} else {
				System.out.println("Failed to switch to default Frame");
			}
		}
		return flag;

	}

	public static void implicityWait(WebDriver driver, int wait) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
	}

	public static void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	public static void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
	}
	public static boolean isTextOfElementMatchesExpectedValue(WebElement element, Object expectedText, boolean caseSensitive) {
		boolean flag = false;
		String actualText=null;
		try {
			if (element.isDisplayed()) {
				actualText = element.getText().trim();
				String expectedTextStr = expectedText.toString().trim();
				System.out.println("Actual text: [" + actualText + "]");
				System.out.println("Expected text: [" + expectedTextStr + "]");
				if (caseSensitive) {
					if (actualText.equals(expectedTextStr)) {
						flag = true;
					}
				} else {
					if (actualText.equalsIgnoreCase(expectedTextStr)) {
						flag = true;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		} finally {
			if (flag) {
				System.out.println("Element Text matches the expected value. Actual: '"+actualText +  "', Expected: '" + expectedText + "'");
			} else {
				System.out.println("Element Text does not match the expected value. Actual: '"+ actualText + "', Expected: '" + expectedText + "'");
			}
		}
		return flag;
	}

	public static boolean isValueOfElementMatchesExpectedValue(WebElement element, String attribute, String expectedValue, boolean caseSensitive) {
		boolean flag = false;
		String actualValue=null;
		try {
			if (element == null) {
				System.out.println("WebElement is null.");
				return false;
			}

			if (element.isDisplayed()) {
				actualValue = element.getAttribute(attribute);
				System.out.println("Actual value of attribute '" + attribute + "' is: " + actualValue);

				if (actualValue != null) {
					if (caseSensitive) {
						flag = actualValue.equals(expectedValue);
					} else {
						flag = actualValue.equalsIgnoreCase(expectedValue);
					}
				} else {
					System.out.println("The attribute '" + attribute + "' is null.");
				}
			} else {
				System.out.println("Element is not displayed.");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		} finally {
			if (flag) {
				System.out.println("Element value matches the expected value. Actual: '"+actualValue +  "', Expected: '" + expectedValue + "'");
			} else {
				System.out.println("Element value does not match the expected value. Actual: '"+ actualValue + "', Expected: '" + expectedValue + "'");
			}
		}

		return flag;
	}


}





