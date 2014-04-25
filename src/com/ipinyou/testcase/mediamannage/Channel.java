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

public class Channel {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
    @Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) {
    	LoginInfo login = new LoginInfo(loginname,password);
		p.login(login, driver);
    	
  }
    @Test
    public void switchmedia(){
    	 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement media = driver.findElement(By.id("mediaMenu"));
    	media.click();
    }
    @Parameters({"channelname","contacts","email","phonenum"})
    @Test
    public void createwebsuit(String channelname,String contacts,String email,String phonenum) throws InterruptedException{
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement createchannel = driver.findElement(By.className("ico-add"));
    	createchannel.click();
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement name = driver.findElement(By.id("channel.name"));
    	name.sendKeys(channelname);
    	WebElement contact = driver.findElement(By.id("channel.contactName"));
    	contact.sendKeys(contacts);
    	WebElement channel_email = driver.findElement(By.id("channel.email"));
    	channel_email.sendKeys(email);
    	WebElement channel_num = driver.findElement(By.id("channel.cellphone"));
    	channel_num.sendKeys(phonenum);
    	WebElement agent = driver.findElement(By.id("pool_2"));
    	agent.click();
    	
    	//选择广告主
//    	WebElement chooseadv = driver.findElement(By.id("btn-advertiser"));
//    	chooseadv.click();
//    	WebElement advframe = driver.findElement(By.className("myiframe"));
//    	driver.switchTo().frame(advframe);
//        Thread.sleep(3000);
//        WebElement adv = driver.findElement(By.id("advertiser_3685"));
//        adv.click();
//        WebElement confirm  = driver.findElement(By.id("confirm"));
//        confirm.click();
//        driver.switchTo().defaultContent();
    	
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
