package com.ipinyou.testcase.dsp.strategy;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.strategy.StrCrowdMutexInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.strategy.StrCrowdMutexPage;

public class StrCrowdMutex {
	WebDriver driver = null;
	PubHandle p  = new PubHandle();
	StrCrowdMutexInfo scminfo = new StrCrowdMutexInfo(); 
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname){
		scminfo.setAdname(adname);
		scminfo.setOrname(orname);
		scminfo.setPlname(plname);
		StrCrowdMutexPage.search(driver, scminfo);
	} 
	
	@Test
	public void instrategy() throws NoSuchElementException{
		StrCrowdMutexPage.instrategy(driver);
	}
	@Parameters({"strategyname","retargetingcode1","conversioncode1","retargetingcode2","conversioncode2"})
	@Test
	public void crowdmutex(String strategyname,String retargetingcode1,String conversioncode1,String retargetingcode2,String conversioncode2) throws InterruptedException{
		scminfo.setStrategyname(strategyname);
		scminfo.setRetargetingcode1(retargetingcode1);
		scminfo.setRetargetingcode2(retargetingcode2);
		scminfo.setConversioncode1(conversioncode1);
		scminfo.setConversioncode2(conversioncode2);
		StrCrowdMutexPage.crowdmutex(driver);
	}
	
	@Test
	public void visitorcrowd() throws InterruptedException{
		StrCrowdMutexPage.visitorcrowd(driver, scminfo);
		StrCrowdMutexPage.visitorcheck(driver, scminfo);
	}
	@Test
	public void visitorupdate() throws InterruptedException{
		StrCrowdMutexPage.visitorupdate(driver, scminfo);
	}
	@Parameters({"budgetblance","exposureblance","budgetreminder","exposurereminder"})
	@Test
	public void blanceputin(String budgetblance,String exposureblance,String budgetreminder,String exposurereminder) throws InterruptedException{
		scminfo.setBudgetblance(budgetblance);
		scminfo.setExposureblance(exposureblance);
		scminfo.setBudgetblance(budgetblance);
		scminfo.setExposureblance(exposureblance);
		scminfo.setBudgetreminder(budgetreminder);
		scminfo.setExposurereminder(exposurereminder);
		StrCrowdMutexPage.blanceputin(driver, scminfo);
	}
	@Parameters({"begindate","enddate","oldbegindate","begindate1","enddate1","oldenddate"})
	@Test
	public void datecrowd(String begindate,String enddate,String oldbegindate,String begindate1,String enddate1,String oldenddate) throws InterruptedException{
		scminfo.setBegindate(begindate);
		scminfo.setEnddate(enddate);
		scminfo.setBegindate(oldbegindate);
		scminfo.setBegindate1(begindate1);
		scminfo.setEnddate1(enddate1);
		scminfo.setOldenddate(oldenddate);
		StrCrowdMutexPage.datecheck(driver, scminfo);
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
