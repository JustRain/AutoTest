package com.ipinyou.testcase.ownmedia;


import java.awt.AWTException;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.ownmedia.OwnMediaCreativeInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.ownmediawebpage.OwnMediaCreativePage;

public class OwnMediaCreative {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	OwnMediaCreativeInfo ocinfo = new OwnMediaCreativeInfo();
	@Parameters({"loginname","password"})
    @Test()
    public void login(String loginname,String password) throws NoSuchElementException{
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname","strategyname"})
	@Test()
	public void search(String adname,String orname,String plname,String strategyname) throws NoSuchElementException{
		
		ocinfo.setAdname(adname);
		ocinfo.setOrname(orname);
		ocinfo.setPlname(plname);
		ocinfo.setStrategyname(strategyname);
		try {
			OwnMediaCreativePage.search(driver, ocinfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  @Test
  public void increative() throws NoSuchElementException{
	  OwnMediaCreativePage.increative(driver, "上传创意", "没有进入上传创意页");
  }
  @Test
  public void selcreative() throws AWTException,NoSuchElementException, InterruptedException{
	  OwnMediaCreativePage.selcreative(driver, "本次上传创意，成功1，失败0");
  }
  @Parameters({"arriveadress","ownmediatheme"})
  @Test
  public void completecreative(String arriveadress,String ownmediatheme){
	  ocinfo.setArriveadress(arriveadress);
	  ocinfo.setOwnmediatheme(ownmediatheme);
	  OwnMediaCreativePage.completecreative(driver,ocinfo);
	  try {
		OwnMediaCreativePage.check(driver, "创意主题不正确", "到达地址不正确",ocinfo);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }
  @Parameters({"browser","ownpath"})
  @BeforeTest
  public void beforeTest(String browser,String ownpath) {
	  driver = p.before(browser, driver);
	  PubHandle.setClipboardData(ownpath);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
}
}
