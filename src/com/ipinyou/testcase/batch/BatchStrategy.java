package com.ipinyou.testcase.batch;

import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.strategy.BatchStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.strategy.BatchStrategyPage;

@Test
public class BatchStrategy {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchStrategyInfo bsinfo = new BatchStrategyInfo();
	@Parameters({"loginname","password"})
    public void login(String loginname,String password) throws NoSuchElementException{
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname"})
	public void search(String adname,String orname,String plname) throws NoSuchElementException, InterruptedException{
		
		bsinfo.setAdname(adname);
		bsinfo.setOrname(orname);
		bsinfo.setPlname(plname);
		BatchStrategyPage.search(driver, bsinfo);
	}
	
	public void instrategy() throws NoSuchElementException{
		BatchStrategyPage.instrategy(driver);
	}
	
  @Parameters({"batchstrategyname"})
  public void createstrategy(String batchstrategyname)throws NoSuchElementException, InterruptedException {
	  bsinfo.setStrategyname(batchstrategyname);
	  BatchStrategyPage.createstrategy(driver, bsinfo);
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
