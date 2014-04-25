package com.ipinyou.testcase.dsp.strategy;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.strategy.AlgorithmPlanInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.strategy.AlgorithmPlanPage;

public class AlgorithmPlan {
	WebDriver driver;
	PubHandle p  = new PubHandle();
	AlgorithmPlanInfo apinfo = new AlgorithmPlanInfo();
	@Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
    }
	@Parameters({"adname","orname"})
	@Test
	public void search(String adname,String orname) throws InterruptedException{
		apinfo.setAdname(adname);
		apinfo.setOrname(orname);
		AlgorithmPlanPage.search(driver, apinfo);
	}
	@Test
	public void inplan() throws InterruptedException{
		AlgorithmPlanPage.inplan(driver);
	}
	@Parameters({"alorplname","totalbudget","newbegindate","newenddate"})
	@Test
	public void createplan(String alorplname,String totalbudget ,String newbegindate,String newenddate){
		apinfo.setPlname(alorplname);
		apinfo.setTotalbudget(totalbudget);
		apinfo.setBegindate(newbegindate);
		apinfo.setEnddate(newenddate);
		AlgorithmPlanPage.createplan(driver, apinfo);
	}
	@Test
	public void check(){
		AlgorithmPlanPage.check(driver);
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
