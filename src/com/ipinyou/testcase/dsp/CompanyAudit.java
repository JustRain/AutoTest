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
import com.ipinyou.webpage.CompanyAuditPage;

public class CompanyAudit {
	WebDriver driver;
	PubHandle p  = new PubHandle();
	ScreenShot s = new ScreenShot();
	@Parameters({"checkloginname","checkpassword"})
    @Test
 public void login(String checkloginname,String checkpassword){
	    	
		  LoginInfo login = new LoginInfo(checkloginname,checkpassword);
	      p.login(login, driver);
	      try{
	    	  boolean flag = CompanyAuditPage.switchpage(driver, "后台管理"); 
		      if(flag){
		    	  ;
		      }else{
		    	  AdvinteriorauditPage.screen(driver, "未跳转到后台管理页", "后台管理");
		      }
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
	      
	      try{
	    	 boolean flag1 = CompanyAuditPage.auditpage(driver, "广告主列表");
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
	    public void companyaudit(){
	    	try{
	    		CompanyAuditPage.companyaudit(driver);
	    	}catch(InterruptedException e){
	    		e.printStackTrace();
	    	}
	    } 
	    @Test
	    public void check(){
	    	boolean flag = CompanyAuditPage.check(driver, "已确认");
	    	if(flag){
	    		return;
	    	}else{
	    		CompanyAuditPage.checkscreen(driver, "公司审核失败", "已确认");
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
