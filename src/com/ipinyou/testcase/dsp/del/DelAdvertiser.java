package com.ipinyou.testcase.dsp.del;

import java.sql.SQLException;
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
import com.ipinyou.webpage.del.DelAdvertiserPage;


public class DelAdvertiser {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	DelAdvertiserPage adpage = new DelAdvertiserPage();
    @Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
      LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
    }
	@Parameters({"deladname","registername","advertiserwebsite","cellphone","contactname","email","path","industrytext","servicefeerate"})
	@Test 
	public void createad(String deladname,String registername,String advertiserwebsite,String cellphone,String contactname,String email,String path,String industrytext,String servicefeerate,String orgcodeno) throws NoSuchElementException, InterruptedException{
		try{
			AdvertiserInfo adinfo = new AdvertiserInfo(deladname,registername,advertiserwebsite,cellphone,contactname,email,path,industrytext,servicefeerate, orgcodeno);
			DelAdvertiserPage.create(driver,adinfo,DelAdvertiserPage.getuser(driver));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void check(){
		boolean flag = DelAdvertiserPage.check(driver,"广告主列表");
		if(flag){
			return;
		}else if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "广告主创建失败", "广告主列表");
		}
	}
	@Parameters({"deladname","registername","advertiserwebsite","cellphone","contactname","email","path","industrytext","servicefeerate","orgcodeno"})
	@Test
	public void contentcheck(String deladname,String registername,String advertiserwebsite,String cellphone,String contactname,String email,String path,String industrytext,String servicefeerate,String orgcodeno){
		AdvertiserInfo adinfo = new AdvertiserInfo(deladname,registername,advertiserwebsite,cellphone,contactname,email,path,industrytext,servicefeerate, orgcodeno);
		try {
			DelAdvertiserPage.contentcheck(driver, adinfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Parameters({"deladname"})
	@Test
	public void deladvertiser(String deladname) throws InterruptedException{
		AdvertiserInfo adinfo = new AdvertiserInfo();
		adinfo.setDeladname(deladname);
		DelAdvertiserPage.delAdvertiser(driver, adinfo);
	}
	@Parameters({"deladname"})
	@Test
	public void datacheck(String deladname) throws SQLException{
		AdvertiserInfo adinfo = new AdvertiserInfo();
		adinfo.setDeladname(deladname);
		DelAdvertiserPage.datacheck(driver, adinfo);
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
