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

public class WebSuit {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
    @Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) {
    	LoginInfo login = new LoginInfo(loginname,password);
		p.login(login, driver);
  }
    @Parameters({"channelname"})
    @Test
    public void switchmedia(String channelname){
    	 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement media = driver.findElement(By.id("mediaMenu"));
    	media.click();
    	WebElement channellink = driver.findElement(By.linkText(channelname));
    	channellink.click();
    }
    @Parameters({"websuit","webdomain","contact","email","cellphone"})
    @Test
    public void websuit (String websuit,String webdomain,String contact,String email,String cellphone){
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement createsuit = driver.findElement(By.className("ico-add"));
    	createsuit.click();
    	WebElement name = driver.findElement(By.id("name"));
    	name.sendKeys(websuit);
    	WebElement domain = driver.findElement(By.id("domain"));
    	domain.sendKeys(webdomain);
    	WebElement contactname = driver.findElement(By.id("contactName"));
    	contactname.sendKeys(contact);
    	WebElement cemail = driver.findElement(By.id("email"));
    	cemail.sendKeys(email);
    	WebElement phonenum = driver.findElement(By.id("cellphone"));
    	phonenum.sendKeys(cellphone);
    	WebElement corner = driver.findElement(By.id("btn-showLogo"));
    	if(corner.isDisplayed()){
    		
    	}else{
    		corner.click();
    	}
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
