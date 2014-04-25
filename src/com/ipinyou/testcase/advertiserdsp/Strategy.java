package com.ipinyou.testcase.advertiserdsp;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Strategy {
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
	
@Parameters({"ordername","planname"})
  @Test
  public void search(String ordername,String planname) {
	WebElement searchorder = driver.findElement(By.id("appendedInputButton"));
	searchorder.sendKeys(ordername);
	WebElement dosearchor  = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
	dosearchor.click();
	WebElement orderlink = driver.findElement(By.linkText(ordername));
	orderlink.click(); 
	assertEquals("跳转失败",driver.getTitle(),"计划列表");
	WebElement searchplan = driver.findElement(By.id("appendedInputButton"));
	searchplan.sendKeys(planname);
	WebElement dosearchpl = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
	dosearchpl.click();
	assertEquals("跳转失败",driver.getTitle(),"创建投放策略");
  }
@Test
    public void createstrategy(){
	
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
