package com.ipinyou.testcase.onlyadvertiser;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.onlyadvertiser.LoginPage;

public class Login {
	WebDriver driver;
	PubHandle p  = new PubHandle();
	LoginPage loingpage = new LoginPage();
  @Parameters({"loginname","password"})	
  @Test
  public void login(String loginname,String password) {
	  LoginInfo login = new LoginInfo(loginname,password);
	  LoginPage.login(driver, login);
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
