package com.ipinyou.testcase.batch;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.plan.BatchClosePlanInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.plan.BatchClosePlanPage;

public class BatchClosePlan {
	WebDriver driver ;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchClosePlanInfo bpinfo = new BatchClosePlanInfo();
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
		BatchClosePlanPage.search(driver, bpinfo);
	}
	
	@Test
	public void batchclose() throws InterruptedException{
		BatchClosePlanPage.batchclose(driver);
	}
	@Parameters({"planname1"})
	@Test
	public void check1(String planname1){
		bpinfo.setPlanname1(planname1);
		BatchClosePlanPage.check1(driver, bpinfo);
	}
	
	@Parameters({"planname2"})
	@Test
	public void check2(String planname2){
		bpinfo.setPlanname2(planname2);
		BatchClosePlanPage.check2(driver, bpinfo);
        
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