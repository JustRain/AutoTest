package com.ipinyou.testcase.batch;


import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.strategy.BatchCloseStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.strategy.BatchCloseStrategyPage;

public class BatchCloseStrategy {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchCloseStrategyInfo bsinfo = new BatchCloseStrategyInfo();
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver); 
	}
	
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname) throws NoSuchElementException, InterruptedException{
		
		bsinfo.setAdname(adname);
		bsinfo.setOrname(orname);
		bsinfo.setPlname(plname);
		BatchCloseStrategyPage.search(driver, bsinfo);
	}
	@Test
	public void batchclose() throws InterruptedException{
		BatchCloseStrategyPage.batchclose(driver);
	} 
	
	@Parameters({"name1"})
	@Test
	public void check1(String name1) throws InterruptedException{
		bsinfo.setName1(name1);
		BatchCloseStrategyPage.check(driver, bsinfo, name1);
	}
	@Parameters({"name2"})
	@Test
	public void check2(String name2) throws InterruptedException{
		bsinfo.setName2(name2);
		BatchCloseStrategyPage.check(driver, bsinfo, name2);
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