package com.ipinyou.abnormaltestcase;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserNameInfo;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.abnormalwebpage.AbnormalAdvertiserPage;
import com.ipinyou.webpage.abnormalwebpage.AbnormalAdvertiserPageName;

public class AbnormalAdvertiserName {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	AbnormalAdvertiserPage adpage = new AbnormalAdvertiserPage();
	AbnormalAdvertiserNameInfo aainfo = new AbnormalAdvertiserNameInfo();
    @Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
      LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
    }
	@Parameters({"adname","registername","advertiserwebsite","cellphone","contactname","email","path","industrytext","servicefeerate","adname1","adname2","adname3","registername1"})
	@Test 
	public void createad(String adname,String registername,String advertiserwebsite,String cellphone,String contactname,String email,String path,String industrytext,String servicefeerate
			,String adname1,String adname2,String adname3,String registername1) throws NoSuchElementException, InterruptedException{
		try{
			aainfo.setAdname(adname);
			aainfo.setRegistername(registername);
			aainfo.setCellphone(cellphone);
			aainfo.setAdvertiserwebsite(advertiserwebsite);
			aainfo.setCellphone(cellphone);
			aainfo.setContactname(contactname);
			aainfo.setEmail(email);
			aainfo.setPath(path);
			aainfo.setIndustrytext(industrytext);
			aainfo.setServicefeerate(servicefeerate);
			aainfo.setAdname3(adname3);
			aainfo.setAdname2(adname2);
			aainfo.setAdname1(adname1);
			aainfo.setRegistername1(registername1);
			AbnormalAdvertiserPageName.create(driver,aainfo,AbnormalAdvertiserPage.getuser(driver));
			
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
		AbnormalAdvertiserPageName.check(driver, aainfo);
		AbnormalAdvertiserPageName.editname(driver, aainfo);
	}
	@Parameters({"registername2","registername3","advertiserwebsite1"})
	@Test
	public void comoanynamecheck(String registername2,String registername3,String advertiserwebsite1,String registername4){
		aainfo.setRegistername2(registername2);
		aainfo.setRegistername3(registername3);
		aainfo.setAdvertiserwebsite1(advertiserwebsite1);
		
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
