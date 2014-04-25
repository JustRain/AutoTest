package com.ipinyou.testcase.batch;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.creative.BatchUpdataCreativeUrlInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.creative.BatchUpdataCreativeUrlPage;

public class BatchUpdataCreativeUrl {
	WebDriver driver;
    ScreenShot s = new ScreenShot();
    PubHandle p  = new PubHandle();
    BatchUpdataCreativeUrlInfo buinfo = new BatchUpdataCreativeUrlInfo();
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
			buinfo.setAdname(adname);
			buinfo.setOrname(orname);
			buinfo.setPlname(plname);
			buinfo.setStrname(strname);
			BatchUpdataCreativeUrlPage.search(driver, buinfo);
		}
		
		@Parameters({"arriveurl"})
		@Test
		public void batchupdatecreativearrive(String arriveurl) throws InterruptedException{
			buinfo.setArriveurl(arriveurl);
			BatchUpdataCreativeUrlPage.batchupdateurl(driver, buinfo, 1);
		}
		
		@Parameters({"clickurl"})
		@Test
		public void batchupdatecreativeclick(String clickurl) throws InterruptedException{
			buinfo.setClickurl(clickurl);
			BatchUpdataCreativeUrlPage.batchupdateurl(driver, buinfo, 2);
		}
		
		@Parameters({"trackurl"})
		@Test
		public void batchupdatetrack(String trackurl) throws InterruptedException{
			buinfo.setTrackurl(trackurl);
			BatchUpdataCreativeUrlPage.batchupdateurl(driver, buinfo, 3);
		}
	  @AfterTest
	  public void afterTest() {
		  p.logout(driver);
		  driver.quit();
	  }
}
