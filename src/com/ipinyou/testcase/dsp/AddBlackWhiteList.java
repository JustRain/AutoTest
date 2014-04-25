package com.ipinyou.testcase.dsp;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.AddBlackWhiteListInfo;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.AddBlackWhiteListPage;

public class AddBlackWhiteList {
	WebDriver driver;
	PubHandle p  = new PubHandle();
	AddBlackWhiteListInfo abinfo = new AddBlackWhiteListInfo();
 @Parameters({"loginname","password"})
  @Test
  public void login(String loginname,String password) {
	  LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
  }
 @Parameters({"agentname","advertisername"})
 @Test
 public void switchlist(String agentname,String advertisername) throws InterruptedException{
	 abinfo.setAgentname(agentname);
	 abinfo.setAdvertisername(advertisername);
	 AddBlackWhiteListPage.switchlist(driver, abinfo);
 }
 
 @Parameters({"blackname","blackvalue"})
 @Test
 public void createblacklist(String blackname,String blackvalue) throws InterruptedException{
	 abinfo.setBlackname(blackname);
	 abinfo.setBlackvalue(blackvalue);
	 AddBlackWhiteListPage.createblacklist(driver, abinfo);
	 AddBlackWhiteListPage.blackcheck(driver, abinfo);
 }
 
 @Parameters({"whitename","whitevalue"})
 @Test
 public void createwhitelist(String whitename,String whitevalue) throws InterruptedException{
	 abinfo.setWhitename(whitename);
	 abinfo.setWhitevalue(whitevalue);
	 AddBlackWhiteListPage.createwhitelist(driver, abinfo);
	 AddBlackWhiteListPage.whitecheck(driver, abinfo);
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
