package com.ipinyou.abnormaltestcase;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserInfo;
import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserInfo1;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.abnormalwebpage.AbnormalAdvertiserPage;

public class AbnormalAdvertiser {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	AbnormalAdvertiserPage adpage = new AbnormalAdvertiserPage();
	AbnormalAdvertiserInfo1 aainfo = new AbnormalAdvertiserInfo1();
    @Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
      LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
    }
	@Parameters({"adname","registername","advertiserwebsite","cellphone","contactname","email","path","industrytext","servicefeerate"})
	@Test 
	public void createad(String adname,String registername,String advertiserwebsite,String cellphone,String contactname,String email,String path,String industrytext,String servicefeerate) throws NoSuchElementException, InterruptedException{
		try{
			AbnormalAdvertiserInfo adinfo = new AbnormalAdvertiserInfo(adname,registername,advertiserwebsite,cellphone,contactname,email,path,industrytext,servicefeerate);
			AbnormalAdvertiserPage.create(driver,adinfo,AbnormalAdvertiserPage.getuser(driver));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Parameters({"adnamereminder","urlremainder","urlexample","contactremainder","aptituderemainder","emailremainder"})
	@Test
	public void check(String adnamereminder,String urlremainder,String urlexample,String contactremainder,String aptituderemainder,String emailremainder){
		aainfo.setAdnamereminder(adnamereminder);
		aainfo.setAptituderemainder(aptituderemainder);
		aainfo.setContactremainder(contactremainder);
		aainfo.setUrlexample(urlexample);
		aainfo.setUrlremainder(urlremainder);
		aainfo.setEmailremainder(emailremainder);
//		boolean flag = AbnormalAdvertiserPage.check(driver,"广告主列表");
//		if(flag){
//			return;
//		}else if(!flag){
//			ScreenshotandAssert.screenandasserttitle(driver, "广告主创建失败", "广告主列表");
//		}
	}

  @Parameters({"browser"})	
  @BeforeTest
  public void beforeTest(String browser) {
	 driver = p.before(browser, driver);
  }

  @AfterTest
  public void afterTest() {
//	  p.logout(driver);
//	  driver.quit();
  }

}
