package com.ipinyou.testcase.mediamannage;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;

public class Part {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	@Parameters({"loginname","password","channelname","websuit"})
	@Test
  public void login(String loginname,String password,String channelname,String websuit) throws InterruptedException {
		LoginInfo login = new LoginInfo(loginname,password);
		p.login(login, driver);
  }
	    @Parameters({"channelname","websuit"})
	    @Test
	    public void switchmedia(String channelname,String websuit){
		    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    	WebElement media = driver.findElement(By.id("mediaMenu"));
	    	media.click();
	    	WebElement channellink = driver.findElement(By.linkText(channelname));
	    	channellink.click();
	    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    	WebElement weblink = driver.findElement(By.linkText(websuit));
	    	weblink.click();
	    }
	 @Parameters({"partname"})
	 @Test
	 public void part(String partname){
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	     WebElement createpart = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[1]/a/span[2]")); 
	     createpart.click();
	     WebElement name = driver.findElement(By.id("name"));
	     name.sendKeys(partname);
	     WebElement submit = driver.findElement(By.name("submitForm"));
	     submit.click();
	 }
	@Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser) {
		driver = p.before(browser, driver);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
