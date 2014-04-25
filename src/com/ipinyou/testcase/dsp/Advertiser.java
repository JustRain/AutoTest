package com.ipinyou.testcase.dsp;

import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.AdvertiserInfo;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.AdvertiserPage;


public class Advertiser {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	AdvertiserPage adpage = new AdvertiserPage();
    @Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
      LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
    }
	@Parameters({"adname","registername","advertiserwebsite","cellphone","contactname","email","path","industrytext","servicefeerate","orgcodeno"})
	@Test 
	public void createad(String adname,String registername,String advertiserwebsite,String cellphone,String contactname,String email,String path,String industrytext,String servicefeerate,String orgcodeno) throws NoSuchElementException, InterruptedException{
		try{
			AdvertiserInfo adinfo = new AdvertiserInfo(adname,registername,advertiserwebsite,cellphone,contactname,email,path,industrytext,servicefeerate,orgcodeno);
			AdvertiserPage.create(driver,adinfo,AdvertiserPage.getuser(driver));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void check(){
		boolean flag = AdvertiserPage.check(driver,"广告主列表");
		if(flag){
			return;
		}else if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "广告主创建失败", "广告主列表");
		}
	}
	@Parameters({"adname","registername","advertiserwebsite","cellphone","contactname","email","path","industrytext","servicefeerate","orgcodeno"})
	@Test
	public void contentcheck(String adname,String registername,String advertiserwebsite,String cellphone,String contactname,String email,String path,String industrytext,String servicefeerate,String orgcodeno){
		AdvertiserInfo adinfo = new AdvertiserInfo(adname,registername,advertiserwebsite,cellphone,contactname,email,path,industrytext,servicefeerate, orgcodeno);
		try {
			AdvertiserPage.contentcheck(driver, adinfo);
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
