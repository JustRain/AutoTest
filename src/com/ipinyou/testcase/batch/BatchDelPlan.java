package com.ipinyou.testcase.batch;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.PlanInfo;
import com.ipinyou.entity.strategy.AlgorithmPlanInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.PlanPage;
import com.ipinyou.webpage.batch.plan.BatchDelPlanPage;
import com.ipinyou.webpage.strategy.AlgorithmPlanPage;

public class BatchDelPlan {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	AlgorithmPlanInfo pinfo =  new AlgorithmPlanInfo();
	@Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
    }
	
	@Parameters({"delplan","adname","orname"})
	@Test
	public void search(String delplan,String adname,String orname){
		pinfo.setAdname(adname);
		pinfo.setOrname(orname);
		pinfo.setPlname(delplan);

		try {
			BatchDelPlanPage.search(driver, pinfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void inplan() throws InterruptedException {
		BatchDelPlanPage.inplan(driver);	
	}
	@Parameters({"totalbudget","newbegindate","newenddate"})
	@Test
	public void createplan(String totalbudget ,String newbegindate,String newenddate){
		pinfo.setTotalbudget(totalbudget);
		pinfo.setBegindate(newbegindate);
		pinfo.setEnddate(newenddate);
		BatchDelPlanPage.createplan(driver, pinfo);
	}
	@Test
	public void check(){
		AlgorithmPlanPage.check(driver);
	}
  @Test
  public void delplan(){
	  BatchDelPlanPage.delPlan(driver, pinfo);
  }
  @Test
  public void datacheck() throws SQLException{
	  BatchDelPlanPage.datacheck(pinfo);
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
