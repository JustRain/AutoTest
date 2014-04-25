package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NewTest {
    WebDriver driver;
	@Test
	public void test(){
		driver.navigate().to("http://console.ipinyou.com/");
		String cookies = driver.manage().getCookieNamed("PYID").getValue().toString();
		System.out.println("PYID ============== "+cookies);
	}
	
	@BeforeTest
	public void befoetest(){
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	@AfterTest
	public void aftertest(){
		driver.quit();
	}
}
