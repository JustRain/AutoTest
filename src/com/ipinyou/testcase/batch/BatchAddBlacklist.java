package com.ipinyou.testcase.batch;


import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.strategy.BatchAddBlacklistInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.strategy.BatchAddBlacklistPage;

public class BatchAddBlacklist {
	
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchAddBlacklistInfo bainfo = new BatchAddBlacklistInfo();
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname) throws NoSuchElementException, InterruptedException{
		
		bainfo.setAdname(adname);
		bainfo.setOrname(orname);
		bainfo.setPlname(plname);
		BatchAddBlacklistPage.search(driver, bainfo);
	}
	
	@Parameters({"blacklist","whitelist","blacklistold"})
	@Test
	public void batchaddblacklist(String blacklist,String whitelist,String blacklistold) throws InterruptedException{
		
		bainfo.setBlacklist(blacklist);
		bainfo.setBlacklistold(blacklistold);
		bainfo.setWhitelist(whitelist);
		BatchAddBlacklistPage.batchaddblacklist(driver, bainfo);
	} 
	
	@Parameters({"strname","blacklisttext","whitelisttext"})
	@Test
	public void check(String strname,String blacklisttext,String whitelisttext) throws InterruptedException{
		
		bainfo.setStrname(strname);
		bainfo.setBlacklisttext(blacklisttext);
		bainfo.setWhitelisttext(whitelisttext);
		BatchAddBlacklistPage.check(driver, bainfo);
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
