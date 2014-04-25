package com.ipinyou.testcase.batch;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.creative.BatchOpenCreativeInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.creative.BatchOpenCreativePage;


public class BatchOpenCreative {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchOpenCreativeInfo bcinfo = new BatchOpenCreativeInfo();
  @Parameters({"browser"})	
  @BeforeTest
  public void beforeTest(String browser) {
	  driver = p.before(browser, driver);
  }
  @Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) throws NoSuchElementException{
	  LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
  }
  
	@Parameters({"adname","orname","plname","strname"})
	@Test
	public void search(String adname,String orname,String plname,String strname) throws NoSuchElementException, InterruptedException{
		
		bcinfo.setAdname(adname);
		bcinfo.setOrname(orname);
		bcinfo.setPlname(plname);
		bcinfo.setStrname(strname);
		BatchOpenCreativePage.search(driver, bcinfo);
	}
	@Test
	public void batchopencreative() throws InterruptedException{
		BatchOpenCreativePage.batchopencreative(driver);
	}
	@Test
	public void check(){
		BatchOpenCreativePage.check(driver);
	}

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
