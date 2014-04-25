package com.ipinyou.testcase.selfadvertiser;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.selfadvertiser.OpenStatusInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.selfadvertiserpage.OpenStatusPage;

public class OpenStatus {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	OpenStatusInfo osi = new OpenStatusInfo();
	@Parameters({"loginname","password"})
	@Test
	public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitcho(login, driver);
	}
	
	 @Parameters({"adname","orname","plname","strategyname"})	
  @Test
 
  public void openstatus(String adname,String orname,String plname ,String strategyname) throws InterruptedException {
	  
		 osi.setAdname(adname);
		 osi.setOrname(orname);
		 osi.setPlname(plname);
		 osi.setStrategyname(strategyname);
		 OpenStatusPage.openstatus(driver, osi);
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
