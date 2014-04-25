package com.ipinyou.testcase.dsp.del;



import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.OrderInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.del.DelOrderPage;

public class DelOrder {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	OrderInfo info = new OrderInfo();
	@Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
        }
	
	@Parameters({"adname"})
	@Test
	public void searchad(String adname){
		info.setAdname(adname);
		DelOrderPage.search(driver, info);
		try{
			DelOrderPage.checkpage(driver, "创建订单","未跳转到创建订单页");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Parameters({"delorname","orderbudget","contractNo"})
	@Test
	public void createorder(String delorname,String orderbudget,String contractNo){
		
		info.setOrname(delorname);
		info.setOrderbudget(orderbudget);
		info.setContractNo(contractNo);
		
		DelOrderPage.createorder(driver, info);
		
		boolean flag = Check.usualexist(driver, "创建广告计划", 2);
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "创建订单失败", "创建广告计划");
		}
	}
	@Parameters({"delorname","orderbudget","contractNo"})
	@Test
	public void check(String delorname,String orderbudget,String contractNo){
		try {
			
			DelOrderPage.check(driver, info);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void delorder(){
		DelOrderPage.delorder(driver, info);
	}
	@Test
	public void datacheck() throws SQLException{
		DelOrderPage.datacheck(info);
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
