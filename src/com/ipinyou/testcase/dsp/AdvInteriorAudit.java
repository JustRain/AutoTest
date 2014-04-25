package com.ipinyou.testcase.dsp;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.AdvinteriorauditPage;

public class AdvInteriorAudit {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p = new PubHandle();
	@Parameters({"checkloginname","checkpassword"})
	@Test
	public void login(String checkloginname,String checkpassword){
	   LoginInfo logininfo = new LoginInfo(checkloginname, checkpassword);
	   p.login(logininfo,driver);
	   try {
		  boolean flag = AdvinteriorauditPage.switchpage(driver, "后台管理");
		  if(flag){
			  ;
		  }else{
			  AdvinteriorauditPage.screen(driver, "未跳转到后台管理页", "后台管理");
		  }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	   try{
			boolean flag1 = AdvinteriorauditPage.auditpage(driver, "广告主列表");
			 if(flag1){
				   return;
			   }else{
				   ScreenshotandAssert.screenandasserttitle(driver, "跳转至广告主审核失败", "广告主列表");
			   }
		}catch(Exception e){
			e.printStackTrace();
		}
	  
	    }
	    @Test
	    public void interioraudit(){
	    	AdvinteriorauditPage.interioraudit(driver);
	    } 
	    @Test
	    public void check(){
	    	
	    	boolean flag = AdvinteriorauditPage.check(driver, "审核通过");
	    	if(flag){
	    		return;
	    	}else{
	    		AdvinteriorauditPage.checkscreen(driver, "内审失败", "审核通过");
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
