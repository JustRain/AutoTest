package com.ipinyou.testcase.onlyadvertiser;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.onlyadvertiser.TestAdvertiserInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.onlyadvertiser.LoginPage;
import com.ipinyou.webpage.onlyadvertiser.TestAdvertiserPage;

public class TestAdvertiser {
    static WebDriver driver;
	PubHandle p  = new PubHandle();
	LoginPage loingpage = new LoginPage();
	TestAdvertiserInfo tainfo = new TestAdvertiserInfo();
  @Parameters({"loginname","password"})	
  @Test
  public void login(String loginname,String password) throws InterruptedException {
	  LoginInfo login = new LoginInfo(loginname,password);
	  LoginPage.login(driver, login);
	  Thread.sleep(1000);
  }
  @Parameters({"advertiserurl"})
  @Test
  public void switchurl(String advertiserurl) throws AWTException{
	  tainfo.setAdvertiserurl(advertiserurl);
	  TestAdvertiserPage.switchurl(tainfo);
  }
  @Test
  public static void check(){
	  TestAdvertiserPage.check(driver);
  }
  @Parameters({"browser"})	
  @BeforeTest
  public void beforeTest(String browser) {
	 driver = p.before(browser, driver);
  }

  @AfterTest
  public void afterTest() {
	  TestAdvertiserPage.aftertest(driver);
	  p.logout(driver);
	  driver.quit();
  }

}
