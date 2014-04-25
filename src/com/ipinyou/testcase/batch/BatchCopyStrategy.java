package com.ipinyou.testcase.batch;

import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;
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
import com.ipinyou.entity.batch.strategy.BatchCopyStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.strategy.BatchCopyStrategyPage;

public class BatchCopyStrategy {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchCopyStrategyInfo bcinfo = new BatchCopyStrategyInfo();
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
  	 }
	
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname) throws NoSuchElementException, InterruptedException{
		bcinfo.setAdname(adname);
		bcinfo.setOrname(orname);
		bcinfo.setPlname(plname);
		BatchCopyStrategyPage.search(driver, bcinfo);
	}
	
	@Test
	public void batchcopy() throws InterruptedException{
		
		BatchCopyStrategyPage.batchcopy(driver);
		Thread.sleep(2000);
	
	}
	
	@Parameters({"plname1","copystr1","copystr2"})
	@Test
	public void check(String plname1,String copystr1,String copystr2){
		
		bcinfo.setPlname1(plname1);
		bcinfo.setCopystr1(copystr1);
		bcinfo.setCopystr2(copystr2);
		BatchCopyStrategyPage.check(driver, bcinfo);
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
