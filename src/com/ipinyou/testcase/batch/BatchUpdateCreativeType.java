package com.ipinyou.testcase.batch;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.creative.BatchUpdateCreativeTypeInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.creative.BatchUpdateCreativeTypePage;


public class BatchUpdateCreativeType {
	WebDriver driver;
	ScreenShot s =  new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchUpdateCreativeTypeInfo btinfo = new BatchUpdateCreativeTypeInfo();
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
		btinfo.setAdname(adname);
		btinfo.setOrname(orname);
		btinfo.setPlname(plname);
		btinfo.setStrname(strname);
		BatchUpdateCreativeTypePage.search(driver, btinfo);
	}
	
	@Parameters({"parenttext","childtext"})
	@Test
	public void batchupdatecreativetheme(String parenttext,String childtext) throws InterruptedException{
		
		btinfo.setParenttext(parenttext);
		btinfo.setChildtext(childtext);
		BatchUpdateCreativeTypePage.batchupdatecreativetheme(driver, btinfo);
	}
	
    @Parameters({"parenttext","childtext"})
	@Test
	public void check(String parenttext,String childtext) throws InterruptedException{
    	btinfo.setParenttext(parenttext);
    	btinfo.setChildtext(childtext);
    	BatchUpdateCreativeTypePage.check(driver, btinfo);
	}
  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }

}
