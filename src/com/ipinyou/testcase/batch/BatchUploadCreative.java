package com.ipinyou.testcase.batch;


import java.awt.AWTException;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.strategy.BatchUploadCreativeInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.strategy.BatchUploadCreativePage;

public class BatchUploadCreative {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchUploadCreativeInfo bcinfo = new BatchUploadCreativeInfo();
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname) throws NoSuchElementException, InterruptedException{
		
		bcinfo.setAdname(adname);
		bcinfo.setOrname(orname);
		bcinfo.setPlname(plname);
		BatchUploadCreativePage.search(driver, bcinfo);
	}
	
	@Parameters({"str1","creaname","arriveadress","monitoraddress","exposureaddress"})
	@Test
	public void batchuploadcreative(String str1,String creaname,String arriveadress,String monitoraddress,String exposureaddress) throws AWTException, InterruptedException{
		
		bcinfo.setCreaname(creaname);
		bcinfo.setArriveadress(arriveadress);
		bcinfo.setMonitoraddress(monitoraddress);
		bcinfo.setExposureaddress(exposureaddress);
		bcinfo.setStr1(str1);
		BatchUploadCreativePage.batchuploadcreative(driver, bcinfo);
	}
	
	@Parameters({"str1","creaname"})
	@Test
	public void check(String str1,String creaname) throws InterruptedException{
		
		bcinfo.setCreaname(creaname);
		BatchUploadCreativePage.check(driver, bcinfo);
		
	}
	
  @Parameters({"browser","batchpath"})
  @BeforeTest
  public void beforeTest(String browser,String batchpath) {
	  driver = p.before(browser, driver);
	  PubHandle.setClipboardData(batchpath);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
