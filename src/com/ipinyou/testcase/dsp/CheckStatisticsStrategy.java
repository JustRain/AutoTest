package com.ipinyou.testcase.dsp;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.StatisticsStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.StatisticsStrategyPage;

public class CheckStatisticsStrategy {
	WebDriver driver;
	PubHandle p = new PubHandle();
	StatisticsStrategyInfo ssinfo = new StatisticsStrategyInfo();
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
    }
  
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname) throws NoSuchElementException{
		
		ssinfo.setAdname(adname);
		ssinfo.setOrname(orname);
		ssinfo.setPlname(plname);
		StatisticsStrategyPage.search(driver, ssinfo);
	}
	@Parameters({"statisticstrategy","staclickurl","staexposeurl"})
	@Test
	public void intostatistic(String statisticstrategy,String staclickurl,String staexposeurl) throws InterruptedException{
		ssinfo.setStatisticstrategy(statisticstrategy);
		ssinfo.setStaclickurl(staclickurl);
		ssinfo.setStaexposeurl(staexposeurl);
		StatisticsStrategyPage.intoStatistic(driver, ssinfo);
	}
	
	@Parameters({"clicklog","impresslog"})
	@Test
	public void checkClickuri(String clicklog,String impresslog) throws IOException{
		ssinfo.setClicklog(clicklog);
		ssinfo.setImpresslog(impresslog);
		StatisticsStrategyPage.clickuriCheck(driver, ssinfo);
	}
	@Test
	public void checkImpressuri() throws IOException{
		StatisticsStrategyPage.impressuriCheck(driver, ssinfo);
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
