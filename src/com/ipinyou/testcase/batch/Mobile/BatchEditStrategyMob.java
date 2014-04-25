package com.ipinyou.testcase.batch.Mobile;


import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.mobile.BatchEditStrategyMobInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.mobile.BatchEditStrategyMobPage;

public class BatchEditStrategyMob {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchEditStrategyMobInfo bsinfo = new BatchEditStrategyMobInfo();
	public String mobiletype = null;
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
     }
	
	@Parameters({"adname","orname","plname","appstrname","webstrname"})
	@Test
	public void search(String adname,String orname,String plname,String appstrname,String webstrname) throws NoSuchElementException, InterruptedException{
		bsinfo.setAdname(adname);
		bsinfo.setOrname(orname);
		bsinfo.setPlname(plname);
		bsinfo.setAppstrname(appstrname);
		bsinfo.setWebstrname(webstrname);
		bsinfo.setMobiletype(mobiletype);
		BatchEditStrategyMobPage.search(driver, bsinfo);
	}
	
	@Parameters({"pyidcategories","blacklists","cookietext","batchblack","batchdirected","blackapp","whiteapp","positonblack","positionwhite","whitelists","batchwhite",
		"edithighprice","editpricegoal","unitcpc","editeffectgoal","editrate","editscriptcode","editremark","editblanceputin","editpreferdeal","editalgorithmCode"})
	@Test
	public void batchedit(String pyidcategories,String blacklists,String cookietext,String batchblack,String batchdirected,String blackapp,String whiteapp,
			String positonblack,String positionwhite,String whitelists,String batchwhite,String edithighprice,String editpricegoal,String unitcpc,String editeffectgoal,String editrate,String editscriptcode
			,String editremark,String editblanceputin,String editpreferdeal,String editalgorithmCode) throws InterruptedException{
		bsinfo.setPyidcategories(pyidcategories);
		bsinfo.setBlacklists(blacklists);
		bsinfo.setCookietext(cookietext);
		bsinfo.setBatchblack(batchblack);
		bsinfo.setBatchdirected(batchdirected);
		bsinfo.setBlackapp(blackapp);
		bsinfo.setWhiteapp(whiteapp);
		bsinfo.setPositionwhite(positionwhite);
		bsinfo.setPositonblack(positonblack);
		bsinfo.setWhitelists(whitelists);
		bsinfo.setBatchwhite(batchwhite);
		bsinfo.setEdithighprice(edithighprice);
		bsinfo.setEditpricegoal(editpricegoal);
		bsinfo.setUnitcpc(unitcpc);
		bsinfo.setEditeffectgoal(editeffectgoal);
		bsinfo.setEditrate(editrate);
		bsinfo.setEditscriptcode(editscriptcode);
		bsinfo.setEditremark(editremark);
		bsinfo.setEditblanceputin(editblanceputin);
		bsinfo.setEditpreferdeal(editpreferdeal);
		bsinfo.setEditalgorithmCode(editalgorithmCode);
		BatchEditStrategyMobPage.batchedit(driver, bsinfo);
		  
	} 
	
	@Parameters({"peopleproperty","retartext","classfytext","context","peopletext","cookietext","areatext","platetext","ostext","browsertext","blacklists","classifyexclude","conexclude",
		"clicksele","peopclassfy","batchdirected","blackapp","whiteapp","daatexclude","mobiledevice","apptype","mediumblack","mediumwhite","editreachprice",
		"edithighprice","editpricegoal","unitcpc","editeffectgoal","editrate","editscriptcode","editremark","editblanceputin","editpreferdeal","editalgorithmCode"})
	@Test
      public void check(String peopleproperty,String retartext,String classfytext,String context,String peopletext,String cookietext,String areatext,String platetext,String ostext,String browsertext,String blacklists,
    		  String classifyexclude,String conexclude,String clicksele,String peopclassfy,String batchdirected,String blackapp,String whiteapp,String daatexclude,String mobiledevice,
    		  String apptype,String mediumblack,String mediumwhite,String editreachprice,String edithighprice,String editpricegoal,String unitcpc,String editeffectgoal,String editrate,String editscriptcode
  			,String editremark,String editblanceputin,String editpreferdeal,String editalgorithmCode) throws InterruptedException{
		
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
		bsinfo.setDaatexclude(daatexclude);
		bsinfo.setMobiledevice(mobiledevice);
		bsinfo.setApptype(apptype);
		bsinfo.setMediumblack(mediumblack);
		bsinfo.setMediumwhit(mediumwhite);
		bsinfo.setBlackapp(blackapp);
		bsinfo.setWhiteapp(whiteapp);
		bsinfo.setBatchdirected(batchdirected);
		bsinfo.setEditreachprice(editreachprice);
		bsinfo.setEdithighprice(edithighprice);
		bsinfo.setEditpricegoal(editpricegoal);
		bsinfo.setUnitcpc(unitcpc);
		bsinfo.setEditeffectgoal(editeffectgoal);
		bsinfo.setEditrate(editrate);
		bsinfo.setEditscriptcode(editscriptcode);
		bsinfo.setEditremark(editremark);
		bsinfo.setEditblanceputin(editblanceputin);
		bsinfo.setEditpreferdeal(editpreferdeal);
		bsinfo.setEditalgorithmCode(editalgorithmCode);
		BatchEditStrategyMobPage.check(driver, bsinfo);
		
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
