package com.ipinyou.testcase.batch.Mobile;

import java.awt.AWTException;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.mobile.BatchUploadMobileCreativeInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.batch.mobile.BatchUploadMobileCreativePage;

public class BatchUploadCreativeWeb {
   public WebDriver driver = null;
   PubHandle p = new PubHandle();
   public String mobiletype = "web";
   BatchUploadMobileCreativeInfo buinfo = new BatchUploadMobileCreativeInfo();
   @Parameters({"loginname","password"})
   @Test
   public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
   }
  
  @Parameters({"adname","orname","plname"})
  @Test
  public void search(String adname,String orname,String plname) throws InterruptedException{
	  buinfo.setAdname(adname);
	  buinfo.setOrname(orname);
	  buinfo.setPlname(plname);
	  BatchUploadMobileCreativePage.search(driver, buinfo);
  }
  
  @Parameters({"webstrname","webcreaname","arriveadress","monitoraddress","exposureaddress"})
  @Test
  public void batchupload(String webstrname,String webcreaname,String arriveadress,String monitoraddress,String exposureaddress) throws AWTException, InterruptedException{
	  buinfo.setMobiletype(mobiletype);
	  buinfo.setWebstrname(webstrname);
	  buinfo.setWebcreaname(webcreaname);
	  buinfo.setArriveadress(arriveadress);
	  buinfo.setMonitoraddress(monitoraddress);
	  buinfo.setExposureaddress(exposureaddress);
	  BatchUploadMobileCreativePage.batchupload(driver, buinfo);
  }
	@Test
	public void check() throws InterruptedException{
		BatchUploadMobileCreativePage.check(driver, buinfo);
		
	}
  @Parameters({"browser","batchwebpath"})
  @BeforeTest
  public void beforeTest(String browser,String batchwebpath) {
	  driver = p.before(browser, driver);
	  PubHandle.setClipboardData(batchwebpath);  
	 }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }

}
