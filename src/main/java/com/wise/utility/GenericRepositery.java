/**
 * 
 */
package com.wise.utility;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import com.wise.base.BaseClass;



/**
 * 
 */
public class GenericRepositery extends BaseClass{
	public static final String[] Domains= {"gmail", "yahoo", "hotmail", "aol", "outlook"};
	public static final String[] TLD={".com", ".net", ".org", ".io", ".edu"};
	@Test

	
	 public WebElement dynamicXpath(String xpath ,Object value) {
	        String dynamicXPath = String.format(xpath, value);
	        return driver.findElement(By.xpath(dynamicXPath));
	    }

	    // Use @FindBy with a custom locator method that returns a WebElement
	    @FindBy(xpath = "") // Placeholder for the custom locator
	    private WebElement elementName;
@Test
	    // Getter method to return the FromCity WebElement
	    public  WebElement elementName(String xpath,Object value) {
	        return elementName = dynamicXpath(xpath,value);
	    }
	    @Test
	    public static   String generateRandomEmail(int length) {
	    	 String username=generateRandomString(length);
	    	 String domain=getRandomElement(Domains);
	    	 String tld=getRandomElement(TLD);
			return username+"@"+domain+tld;
	    		
	    }
	    @Test
	    public static String generateRandomString(int length) {
	    	String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    	StringBuilder sb=new StringBuilder();
	    	Random random= new Random();
	    	for (int i = 0; i < length; i++) {
	    		sb.append(chars.charAt(random.nextInt(chars.length())));
				
			}
			return sb.toString();
	    	
	    }
	    @Test
	    public static <T> T getRandomElement(T[]array) {
	    	Random random= new Random();
	        return array[random.nextInt(array.length)];
         
	    }
}
