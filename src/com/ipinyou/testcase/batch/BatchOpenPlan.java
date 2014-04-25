package com.ipinyou.testcase.batch;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.plan.BatchOpenPlanInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.plan.BatchOpenPlanPage;

public class BatchOpenPlan {
	WebDriver driver ;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchOpenPlanInfo bpinfo = new BatchOpenPlanInfo();
	@Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
  	  }
	
	@Parameters({"adname","orname"})
	@Test
	public void search(String adname,String orname) throws InterruptedException{
		bpinfo.setAdname(adname);
		bpinfo.setOrname(orname);
		BatchOpenPlanPage.search(driver, bpinfo);
	}
	
	@Test
	public void batchopen() throws InterruptedException{
		BatchOpenPlanPage.batchopen(driver);
	}
	@Parameters({"planname1"})
	@Test
	public void check1(String planname1) throws InterruptedException{
		bpinfo.setPlanname1(planname1);
		BatchOpenPlanPage.check1(driver, bpinfo);
	}
	
	@Parameters({"planname2"})
	@Test
	public void check2(String planname2) throws InterruptedException{
		bpinfo.setPlanname2(planname2);
		BatchOpenPlanPage.check2(driver, bpinfo);
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
