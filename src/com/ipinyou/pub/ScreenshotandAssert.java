package com.ipinyou.pub;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.assertEquals;


public class ScreenshotandAssert {
	
	public static void onlyAssert(String reminder,String expecttext,String actualtext){
    	assertEquals(reminder, expecttext, actualtext);

	}
	
	public static void screenandasserttitle(WebDriver driver,String reminder,String element){
			ScreenShot.taskScreenShot(driver);
			assertEquals(reminder,element,driver.getTitle());
	}
	
	public static void screenandasserttext(WebDriver driver,String reminder,String element,By by){
		    ScreenShot.taskScreenShot(driver);
		    WebElement text = driver.findElement(by);
		    assertEquals(reminder,element,text.getText());
	}
	public static void screenandassertvalue(WebDriver driver,String reminder,String element,By by,String value){
		ScreenShot.taskScreenShot(driver);
		String text = driver.findElement(by).getAttribute(value);
		assertEquals(reminder,element,text);	
	}
	public static void screenandassertisenbled(WebDriver driver,String reminder,boolean expected,By by){
		ScreenShot.taskScreenShot(driver);
		assertEquals(reminder, expected, driver.findElement(by).isEnabled());
	}
    public static void screenandassert(WebDriver driver,String reminder,boolean actual,boolean expected){
    	ScreenShot.taskScreenShot(driver);
    	assertEquals(reminder, expected, actual);
    }
    public static void screenandassertseletext(WebDriver driver,String reminder,String expecttext,String actualtext){
    	ScreenShot.taskScreenShot(driver);
    	assertEquals(reminder, expecttext, actualtext);
    }
    
    public static void screenandasserttext(WebDriver driver,String remainder,String expected,String actual){
    	ScreenShot.taskScreenShot(driver);
    	assertEquals(remainder, expected, actual);
    }
}
