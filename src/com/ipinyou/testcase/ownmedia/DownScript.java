package com.ipinyou.testcase.ownmedia;

import java.awt.AWTException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.ownmedia.DownScriptInfo;
import com.ipinyou.pub.*;
import com.ipinyou.webpage.ownmediawebpage.DownScriptPage;

public class DownScript {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	DownScriptInfo dsinfo = new DownScriptInfo();
	@Parameters({"loginname","password"})
	@Test
	public void login(String loginname,String password) throws NoSuchElementException{
		 LoginInfo login = new LoginInfo(loginname,password);
		 p.login(login, driver);
	}
  @Parameters({"channel","websuit","part","filepath","position"})
  @Test
  public void downscript(String channel,String websuit,String part,String filepath,String position) throws InterruptedException, AWTException {
	  
	  dsinfo.setChannel(channel);
	  dsinfo.setWebsuit(websuit);
	  dsinfo.setPart(part);
	  dsinfo.setFilepath(filepath);
	  dsinfo.setPosition(position);
	  DownScriptPage.downscript(driver, dsinfo);
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
