package com.ipinyou.testcase.codecenter;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.CodeCenterInfo;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.CodeCenterPage;

public class CodeCenter {
	WebDriver driver;
    ScreenShot s = new ScreenShot();
    PubHandle p  = new PubHandle();
    CodeCenterInfo ccinfo = new CodeCenterInfo();
	@Parameters({"loginname","password"})
	@Test
  
  public void login(String loginname,String password) {
		LoginInfo login = new LoginInfo(loginname,password);
		p.login(login, driver);
  }
	  @Parameters({"advname","agentname","advertisername"})
	  @Test
	  public void codecenter(String advname,String agentname,String advertisername) throws InterruptedException{
		  ccinfo.setAdvname(advname);
		  ccinfo.setAgentname(agentname);
		  ccinfo.setAdvertisername(advertisername);
		  CodeCenterPage.codecenter(driver, ccinfo, CodeCenterPage.getuser(driver));
	  }
	  @Parameters({"catename","catevalue"})
	  @Test
	  public void createcategory(String catename,String catevalue) throws InterruptedException{
		  
		  ccinfo.setCatename(catename);
		  ccinfo.setCatevalue(catevalue);
		  CodeCenterPage.createcategory(driver, ccinfo);
	  }
	  
	  @Parameters({"conname"})
	  @Test
	  public void createpageconversion(String conname) throws InterruptedException{
		  
		  ccinfo.setConname(conname);
		  CodeCenterPage.createpageconversion(driver, ccinfo);
	  }
	  
	  @Parameters({"catename1","catevalue1"})
	  @Test
	  public void createcategory1(String catename1,String catevalue1) throws InterruptedException{
	  	  
		  ccinfo.setCatename1(catename1);
		  ccinfo.setCatevalue1(catevalue1);
		  CodeCenterPage.createcategory1(driver, ccinfo);
	  }
	  @Parameters({"conname1"})
	  @Test
	  public void createpageconversion1(String conname1) throws InterruptedException{
		  ccinfo.setConname1(conname1);
          CodeCenterPage.createpageconversion1(driver, ccinfo);
	  }
  @Parameters({"browser"})	  
  @BeforeTest
  public void beforeTest(String browser) {
	  driver = p.before(browser, driver);
  }

  @AfterTest
  public void afterTest() {
	  try {
			 Thread.sleep(2000);
			 p.logout(driver);
			 driver.quit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }
}
