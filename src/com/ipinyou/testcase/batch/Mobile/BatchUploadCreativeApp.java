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

public class BatchUploadCreativeApp {
	public WebDriver driver = null;
	   PubHandle p = new PubHandle();
	   BatchUploadMobileCreativeInfo buinfo = new BatchUploadMobileCreativeInfo();
	   public String mobiletype = "app";
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
	  
	  @Parameters({"appstrname","appcreaname","arriveadress","monitoraddress","exposureaddress"})
	  @Test
	  public void batchupload(String appstrname,String appcreaname,String arriveadress,String monitoraddress,String exposureaddress) throws AWTException, InterruptedException{
		  buinfo.setMobiletype(mobiletype);
		  buinfo.setAppstrname(appstrname);
		  buinfo.setAppcreaname(appcreaname);
		  buinfo.setArriveadress(arriveadress);
		  buinfo.setMonitoraddress(monitoraddress);
		  buinfo.setExposureaddress(exposureaddress);
		  BatchUploadMobileCreativePage.batchupload(driver, buinfo);
	  }
		@Test
		public void check() throws InterruptedException{
			BatchUploadMobileCreativePage.check(driver, buinfo);
			
		}
	  @Parameters({"browser","batchapppath"})
	  @BeforeTest
	  public void beforeTest(String browser,String batchapppath) {
		  driver = p.before(browser, driver);
		  PubHandle.setClipboardData(batchapppath);  
		 }

	  @AfterTest
	  public void afterTest() {
		  p.logout(driver);
		  driver.quit();
	  }

}
