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
		  boolean flag = AdvinteriorauditPage.switchpage(driver, "��̨����");
		  if(flag){
			  ;
		  }else{
			  AdvinteriorauditPage.screen(driver, "δ��ת����̨����ҳ", "��̨����");
		  }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	   try{
			boolean flag1 = AdvinteriorauditPage.auditpage(driver, "������б�");
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
	    public void interioraudit(){
	    	AdvinteriorauditPage.interioraudit(driver);
	    } 
	    @Test
	    public void check(){
	    	
	    	boolean flag = AdvinteriorauditPage.check(driver, "���ͨ��");
	    	if(flag){
	    		return;
	    	}else{
	    		AdvinteriorauditPage.checkscreen(driver, "����ʧ��", "���ͨ��");
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
