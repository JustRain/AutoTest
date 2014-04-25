package com.ipinyou.testcase.batch;


import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.strategy.BatchEditStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.strategy.BatchEditStrategyPage;

public class BatchEditStrategy {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchEditStrategyInfo bsinfo = new BatchEditStrategyInfo();
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
     }
	
	@Parameters({"adname","orname","plname","strategyname","strname"})
	@Test
	public void search(String adname,String orname,String plname,String strategyname,String strname) throws NoSuchElementException, InterruptedException{
		bsinfo.setAdname(adname);
		bsinfo.setOrname(orname);
		bsinfo.setPlname(plname);
		bsinfo.setStrategyname(strategyname);
		bsinfo.setStrname(strname);
		BatchEditStrategyPage.search(driver, bsinfo);
	}
	
	@Parameters({"pyidcategories","pyid","blacklists","cookietext","batchblack","positonblack","positionwhite","editblanceputin","editpreferdeal",
		"edithighprice","editpricegoal","unitcpc","editeffectgoal","editrate","editscriptcode","editremark","dailybudget","dailyreminder","plateposition"})
	@Test
	public void batchedit(String pyidcategories,String pyid,String blacklists,String cookietext,String batchblack,String positonblack,String positionwhite,
			String editblanceputin,String editpreferdeal,String edithighprice,String editpricegoal,String unitcpc,String editeffectgoal,String editrate,String editscriptcode,String editremark
			,String dailybudget,String dailyreminder,String plateposition) throws InterruptedException{
		bsinfo.setPyidcategories(pyidcategories);
		bsinfo.setPyid(pyid);
		bsinfo.setBlacklists(blacklists);
		bsinfo.setCookietext(cookietext);
		bsinfo.setBatchblack(batchblack);
		bsinfo.setPositionwhite(positionwhite);
		bsinfo.setPositonblack(positonblack);
		
		bsinfo.setEdithighprice(edithighprice);
		bsinfo.setEditpricegoal(editpricegoal);
		bsinfo.setUnitcpc(unitcpc);
		bsinfo.setEditeffectgoal(editeffectgoal);
		bsinfo.setEditrate(editrate);
		bsinfo.setEditscriptcode(editscriptcode);
		bsinfo.setEditremark(editremark);
		bsinfo.setEditblanceputin(editblanceputin);
		bsinfo.setEditpreferdeal(editpreferdeal);
		bsinfo.setDailybudget(dailybudget);
		bsinfo.setDailyreminder(dailyreminder);
		bsinfo.setPlateposition(plateposition);
		BatchEditStrategyPage.batchedit(driver, bsinfo);
		
		  
	} 
	
	@Parameters({"strname","peopleproperty","retartext","classfytext","context","peopletext","cookietext","areatext","platetext","ostext","browsertext",
		"blacklists","classifyexclude","conexclude","clicksele","peopclassfy","blackcount","whitecount","editreachprice"})
	@Test
      public void check(String strname,String peopleproperty,String retartext,String classfytext,String context,String peopletext,String cookietext,String areatext,String platetext,String ostext,String browsertext,String blacklists,
    		  String classifyexclude,String conexclude,String clicksele,String peopclassfy,String blackcount,String whitecount,String editreachprice) throws InterruptedException{
		
		bsinfo.setStrname(strname);
		bsinfo.setPeopleproperty(peopleproperty);
		bsinfo.setRetartext(retartext);
		bsinfo.setClassfytext(classfytext);
		bsinfo.setContext(context);
		bsinfo.setPeopletext(peopletext);
		bsinfo.setCookietext(cookietext);
		bsinfo.setAreatext(areatext);
		bsinfo.setPlatetext(platetext);
		bsinfo.setOstext(ostext);
		bsinfo.setBrowsertext(browsertext);
		bsinfo.setBlacklists(blacklists);
		bsinfo.setClassifyexclude(classifyexclude);
		bsinfo.setConexclude(conexclude);
		bsinfo.setClicksele(clicksele);
		bsinfo.setPeopclassfy(peopclassfy);
		bsinfo.setBlackcount(blackcount);
		bsinfo.setWhitecount(whitecount);
		bsinfo.setEditreachprice(editreachprice);
		BatchEditStrategyPage.check(driver, bsinfo);
		
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
