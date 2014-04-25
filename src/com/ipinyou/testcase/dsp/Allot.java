package com.ipinyou.testcase.dsp;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.AllotInfo;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.*;
import com.ipinyou.webpage.AllotPage;


public class Allot {
    WebDriver driver;
	PubHandle p = new PubHandle();
@Parameters({"checkloginname","checkpassword"})
@Test
public void login(String lname,String pword){
	LoginInfo logininfo = new LoginInfo(lname, pword);
	p.login(logininfo,driver);
}
  @Parameters({"adname","acount","remark","paypassword"})	
  @Test
  public void allot(String adname,String acount,String remark,String paypassword){
	  AllotInfo alinfo = new AllotInfo(adname,acount,remark,paypassword);
	  AllotPage.allot(driver, "财务管理", "跳转至财务管理页失败", alinfo);
	  
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
