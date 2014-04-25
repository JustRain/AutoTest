package com.ipinyou.testcase.selfadvertiser;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.selfadvertiser.OrderInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.selfadvertiserpage.OrderPage;

public class Order {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	OrderInfo info = new OrderInfo();
	@Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitcho(login, driver);
        }
	
	@Parameters({"adname"})
	@Test
	public void searchad(String adname){
		try{
			OrderPage.checkpage(driver, "创建订单","未跳转到创建订单页");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Parameters({"orname","orderbudget","contractNo","KPI1","KPI2","coverpoint"})
	@Test
	public void createorder(String orname,String orderbudget,String contractNo,String KPI1,String KPI2,String converpoint){
		
		info.setOrname(orname);
		info.setOrderbudget(orderbudget);
		info.setContractNo(contractNo);
		info.setKPI1(KPI1);
		info.setKPI2(KPI2);
		info.setConverpoint(converpoint);
		OrderPage.createorder(driver, info);
		
		boolean flag = Check.usualexist(driver, "创建广告计划", 2);
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "创建订单失败", "创建广告计划");
		}
	}
	@Parameters({"orname","orderbudget","contractNo","adname"})
	@Test
	public void check(String orname,String orderbudget,String contractNo,String adname){
		try {
			info.setAdname(orname);
			info.setOrderbudget(orderbudget);
			info.setContractNo(contractNo);
			info.setAdname(adname);
			OrderPage.check(driver, info);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
