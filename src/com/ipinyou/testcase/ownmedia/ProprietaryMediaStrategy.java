package com.ipinyou.testcase.ownmedia;

import java.util.NoSuchElementException;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.ownmedia.OwnMediaStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.ownmediawebpage.ProprietaryMediaStrategyPage;



public class ProprietaryMediaStrategy {
	WebDriver driver;
	PubHandle p  = new PubHandle();
	OwnMediaStrategyInfo ominfo = new OwnMediaStrategyInfo();
	
	@Parameters({"loginname","password"})
    @Test()
    public void login(String loginname,String password) throws NoSuchElementException{
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname) throws NoSuchElementException{
		
		ominfo.setAdname(adname);
		ominfo.setOrname(orname);
		ominfo.setPlname(plname);
		ProprietaryMediaStrategyPage.search(driver, ominfo);
	}
	@Test
	public void instrategy(){
		ProprietaryMediaStrategyPage.instrategy(driver);
	}
	 @Parameters({"strategyname","totlebudget","dailybudget","imptotallimit","clicktotallimit","imdailylimit","clickdailylimit","indvdlimitimplimit","indvdlimitclicklimit"
		  ,"pyid","blacklists","scriptcode","remarkcode","blackdefinedname","whitedefinedname","whitelists","retargetingcode1","conversioncode1","retargetingcode2","conversioncode2","advertiserposition"})
	  @Test
	  public void createstrategy(String strategyname,String totlebudget,String dailybudget,String imptotallimit,String clicktotallimit,
			  String imdailylimit,String clickdailylimit,String indvdlimitimplimit,String indvdlimitclicklimit,String pyid
			  ,String blacklists,String scriptcode,String remarkcode,String blackdefinedname,String whitedefinedname,String whitelists,
			  String retargetingcode1,String conversioncode1,String retargetingcode2,String conversioncode2,String advertiserposition)throws InterruptedException,NoSuchElementException,AssertionError,ElementNotVisibleException {
		  
		 ominfo.setStrategyname(strategyname);
		 ominfo.setTotlebudget(totlebudget);
		 ominfo.setDailybudget(dailybudget);
		 ominfo.setImptotallimit(imptotallimit);
		 ominfo.setClicktotallimit(clicktotallimit);
		 ominfo.setImdailylimit(imdailylimit);
		  ominfo.setClickdailylimit(clickdailylimit);
		  ominfo.setIndvdlimitimplimit(indvdlimitimplimit);
		  ominfo.setIndvdlimitclicklimit(indvdlimitclicklimit);
		  ominfo.setPyid(pyid);
		  ominfo.setBlacklists(blacklists);
		  ominfo.setScriptcode(scriptcode);
		  ominfo.setRemarkcode(remarkcode);  
		  ominfo.setWhitelists(whitelists);
		  ominfo.setRetargetingcode1(retargetingcode1);
		  ominfo.setRetargetingcode2(retargetingcode2);
		  ominfo.setConversioncode1(conversioncode1);
		  ominfo.setConversioncode2(conversioncode2);
		  ominfo.setBlackdefinedname(blackdefinedname);
		  ominfo.setWhitedefinedname(whitedefinedname);
		  ominfo.setAdvertiserposition(advertiserposition);
		  ProprietaryMediaStrategyPage.createstrategy(driver, ominfo);
		  ProprietaryMediaStrategyPage.submit(driver);
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
