package com.ipinyou.testcase.ownmedia;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.ownmedia.OwnMediaStatusInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.ownmediawebpage.OwnMediaStatusPage;

public class OwnMediaStatus {
	
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	OwnMediaStatusInfo osinfo = new OwnMediaStatusInfo();
  @Parameters({"loginname","password"})
  @Test
  public void login(String loginname,String password) throws NoSuchElementException{
	  LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
  }
  
  @Parameters({"adname","orname"})
  @Test
  public void search(String adname,String orname) throws NoSuchElementException{
	  osinfo.setAdname(adname);
	  osinfo.setOrname(orname);
	  OwnMediaStatusPage.search(driver, osinfo);	
  }
@Parameters({"plname","strategyname"})	
  @Test
  public void ownmediastatus(String plname,String strategyname) {
	osinfo.setPlname(plname);
	osinfo.setStrategyname(strategyname);
	try {
		OwnMediaStatusPage.ownmediastatus(driver, osinfo);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
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
