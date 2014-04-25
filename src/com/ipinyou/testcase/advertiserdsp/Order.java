package com.ipinyou.testcase.advertiserdsp;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Order {
	WebDriver driver;
	@Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
    	
      WebElement name = driver.findElement(By.name("username"));
  	  name.sendKeys(loginname);
  	  WebElement pass = driver.findElement(By.name("password"));
  	  pass.sendKeys(password);
  	  WebElement submit = driver.findElement(By.cssSelector("input.btn"));
  	  submit.click();	
  	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  	  assertEquals("登录失败",driver.getTitle(),"订单列表");

    }
	
	@Parameters({"ordername","totalbudget","contractNo"})
	@Test
	public void  createorder(String ordername,String totalbudget,String contractNo){
		WebElement createorder = driver.findElement(By.className("ico-add"));
		createorder.click();
		assertEquals("创建订单跳转失败",driver.getTitle(),"创建订单");
		WebElement name = driver.findElement(By.id("name"));
		name.sendKeys(ordername);
		WebElement budget = driver.findElement(By.id("totalBudget"));
		budget.sendKeys(totalbudget);
		WebElement contractno = driver.findElement(By.id("contractNo"));
		contractno.sendKeys(contractNo);
		WebElement submit = driver.findElement(By.name("submitForm"));		
		submit.click();
		assertEquals("创建订单失败",driver.getTitle(),"创建广告计划");
	}
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	  driver = new FirefoxDriver();
	  String url = "http://console.ipinyou.com/login/";
	  driver.get(url);
	  driver.manage().window().maximize();
	  assertEquals("页面未正常打开",driver.getTitle(), "品友优驰，专为高端用户服务的DSP平台");
  }

  @AfterTest
  public void afterTest() {
	  WebElement logout = driver.findElement(By.linkText("退出"));
	  logout.click();
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  assertEquals("退出失败",driver.getTitle(),"品友优驰，专为高端用户服务的DSP平台");
	  driver.quit();
  }

}
