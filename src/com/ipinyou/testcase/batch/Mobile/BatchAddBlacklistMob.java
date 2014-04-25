package com.ipinyou.testcase.batch.Mobile;


import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.mobile.BatchAddBlacklistMobInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.mobile.BatchAddBlacklistMobPage;

public class BatchAddBlacklistMob {
	
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	
	String mobiletype="";
	
	BatchAddBlacklistMobInfo bainfo = new BatchAddBlacklistMobInfo();
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname","webstrname","appstrname"})
	@Test
	public void search(String adname,String orname,String plname,String webstrname,String appstrname) throws NoSuchElementException, InterruptedException{
		
		bainfo.setAdname(adname);
		bainfo.setOrname(orname);
		bainfo.setPlname(plname);
		bainfo.setAppstrname(appstrname);
		bainfo.setWebstrname(webstrname);
		bainfo.setMobiletype(this.mobiletype);
		BatchAddBlacklistMobPage.search(driver, bainfo);
	}
	
	@Parameters({"adname","batchappwhitelist","batchappblacklist","batchwebblackname","batchwebwhitename","batchwebblackurl","batchwebwhiteurl"})
	@Test
	public void batchaddblacklist(String adname,String batchappwhitelist,String batchappblacklist,String batchwebblackname,String batchwebwhitename,String batchwebblackurl,String batchwebwhiteurl) throws InterruptedException{
		bainfo.setAdname(adname);
		bainfo.setBatchappwhitelist(batchappwhitelist);
		bainfo.setBatchappblacklist(batchappblacklist);
		bainfo.setBatchwebblackname(batchwebblackname);
		bainfo.setBatchwebwhitename(batchwebwhitename);
		bainfo.setBatchwebblackurl(batchwebblackurl);
		bainfo.setBatchwebwhiteurl(batchwebwhiteurl);
		BatchAddBlacklistMobPage.batchaddblacklist(driver, bainfo);
	} 
	
	@Parameters({"batchappwhitelistold","batchappblacklistold","batchappwhitelist","batchappblacklist"})
	@Test
	public void check(String batchappwhitelistold,String batchappblacklistold,String batchappwhitelist,String batchappblacklist) throws InterruptedException{
		bainfo.setBatchappblacklistold(batchappblacklistold);
		bainfo.setBatchappwhitelistold(batchappwhitelistold);
		bainfo.setBatchappblacklist(batchappblacklist);
		bainfo.setBatchappwhitelist(batchappwhitelist);
		BatchAddBlacklistMobPage.check(driver, bainfo);
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
