package com.ipinyou.testcase.dsp;

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
import com.ipinyou.webpage.StrategyPage;

public class StatisticsStrategy {
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
	@Parameters({"strategyname","statisticstrategy","statisticremark","statisticprice","statisticcreativetheme","staheight","stawidth","staclickurl","staexposeurl"})
	@Test
	public void createstatistic(String strategyname,String statisticstrategy,String statisticremark,String statisticprice,String statisticcreativetheme,String staheight,String stawidth,String staclickurl,String staexposeurl) throws InterruptedException{
		ssinfo.setStrategyname(strategyname);
		ssinfo.setStatisticstrategy(statisticstrategy);
		ssinfo.setStatisticremark(statisticremark);
		ssinfo.setStatisticprice(statisticprice);
		ssinfo.setStatisticcreativetheme(statisticcreativetheme);
		ssinfo.setStaheight(staheight);
		ssinfo.setStawidth(stawidth);
		ssinfo.setStaclickurl(staclickurl);
		ssinfo.setStaexposeurl(staexposeurl);
		StatisticsStrategyPage.createstatistic(driver, ssinfo);
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
