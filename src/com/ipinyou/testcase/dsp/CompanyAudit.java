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
	    	  boolean flag = CompanyAuditPage.switchpage(driver, "��̨����"); 
		      if(flag){
		    	  ;
		      }else{
		    	  AdvinteriorauditPage.screen(driver, "δ��ת����̨����ҳ", "��̨����");
		      }
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
	      
	      try{
	    	 boolean flag1 = CompanyAuditPage.auditpage(driver, "������б�");
	    	 if(flag1){
	    		 return;
	    	 }else{
	    		 ScreenshotandAssert.screenandasserttitle(driver, "��ת����������ʧ��", "������б�");
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
	    	boolean flag = CompanyAuditPage.check(driver, "��ȷ��");
	    	if(flag){
	    		return;
	    	}else{
	    		CompanyAuditPage.checkscreen(driver, "��˾���ʧ��", "��ȷ��");
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
